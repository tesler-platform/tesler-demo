package io.demo.conf.tesler.file;

import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.StatObjectArgs;
import io.minio.StatObjectResponse;
import io.tesler.core.file.dto.FileDownloadDto;
import io.tesler.core.file.service.TeslerFileService;
import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.UUID;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.Nullable;

@RequiredArgsConstructor
public class TeslerFileServiceMinio implements TeslerFileService {

	public static final String FILENAME_FIELD = "filename";

	private final MinioClient minioClient;

	private final String defaultBucketName;

	@Override
	@SneakyThrows
	public String upload(@NonNull FileDownloadDto file, @Nullable String source) {
		ObjectWriteResponse objectWriteResponse = minioClient.putObject(PutObjectArgs
				.builder()
				.bucket(defaultBucketName)
				.object(UUID.randomUUID().toString())
				.contentType(file.getType())
				.userMetadata(Collections.singletonMap(FILENAME_FIELD, file.getName()))
				.stream(new ByteArrayInputStream(file.getBytes()), file.getBytes().length, -1)
				.build()
		);
		return objectWriteResponse.object();
	}

	@Override
	@SneakyThrows
	public FileDownloadDto download(@NonNull String id, @Nullable String source) {
		GetObjectResponse response = minioClient.getObject(GetObjectArgs
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
		return new FileDownloadDto(
				IOUtils.toByteArray(response),
				statObjectResponse.userMetadata().get(FILENAME_FIELD),
				statObjectResponse.contentType()
		);
	}

	@Override
	@SneakyThrows
	public void remove(@NonNull String id, @Nullable String source) {
		minioClient.removeObject(RemoveObjectArgs
				.builder()
				.bucket(defaultBucketName)
				.object(id)
				.build()
		);
	}
}
