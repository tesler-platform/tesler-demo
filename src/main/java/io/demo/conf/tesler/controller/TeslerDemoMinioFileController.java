/*-
 * #%L
 * IO Tesler - Core
 * %%
 * Copyright (C) 2018 - 2019 Tesler Contributors
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

package io.demo.conf.tesler.controller;

import static io.tesler.core.config.properties.APIProperties.TESLER_API_PATH_SPEL;

import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.StatObjectArgs;
import io.minio.StatObjectResponse;
import io.tesler.core.file.dto.FileUploadDto;
import io.tesler.core.file.dto.TeslerResponseDTO;
import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.UUID;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@Slf4j
@RestController
@RequestMapping(TESLER_API_PATH_SPEL + "/file")
public class TeslerDemoMinioFileController {
	public static final String FILENAME_FIELD = "filename";
	public static final	int FIVE_MIB = 5242880;
	private final MinioClient minioClient;
	private final String defaultBucketName;

	public TeslerDemoMinioFileController(
			MinioClient minioClient,
			@Value("${minio.bucket.name}") String defaultBucketName
	) {
		this.minioClient = minioClient;
		this.defaultBucketName = defaultBucketName;
	}

	@SneakyThrows
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public TeslerResponseDTO<FileUploadDto> upload(MultipartFile file, String source) {
		String contentType = file.getContentType();
		String name = file.getOriginalFilename();
		ObjectWriteResponse objectWriteResponse = minioClient.putObject(PutObjectArgs
				.builder()
				.bucket(defaultBucketName)
				.object(UUID.randomUUID().toString())
				.contentType(contentType)
				.userMetadata(Collections.singletonMap(FILENAME_FIELD, name))
				.stream(file.getInputStream(), -1, FIVE_MIB)
				.build()
		);
		String id = objectWriteResponse.object();
		return new TeslerResponseDTO<FileUploadDto>()
				.setData(new FileUploadDto(id, name, contentType));
	}

	@SneakyThrows
	@GetMapping
	public ResponseEntity<StreamingResponseBody> download(String id, String source, boolean preview) {
		GetObjectResponse getObjectResponse = minioClient.getObject(GetObjectArgs
				.builder()
				.bucket(defaultBucketName)
				.object(id)
				.build()
		);
		StatObjectResponse statObjectResponse = minioClient.statObject(StatObjectArgs
				.builder()
				.bucket(defaultBucketName)
				.object(id)
				.build()
		);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + statObjectResponse.userMetadata().get(FILENAME_FIELD) + "\"")
				.contentLength(statObjectResponse.size()) //
				.body(outputStream -> IOUtils.copy(getObjectResponse, outputStream, FIVE_MIB));
	}

	@SneakyThrows
	@DeleteMapping
	public TeslerResponseDTO<Void> remove(String id, String source) {
		minioClient.removeObject(RemoveObjectArgs
				.builder()
				.bucket(defaultBucketName)
				.object(id)
				.build()
		);
		return new TeslerResponseDTO<>();
	}

}
