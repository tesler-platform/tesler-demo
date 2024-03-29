package io.demo.conf.tesler.file;

import io.minio.MinioClient;
import io.tesler.core.file.service.TeslerFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class TeslerFileMinioConfig {
	@Bean
	public MinioClient minioClient(
			@Value("${minio.access.name}") String accessKey,
			@Value("${minio.access.secret}") String accessSecret,
			@Value("${minio.url}") String minioUrl) {
		return MinioClient.builder()
				.endpoint(minioUrl)
				.credentials(accessKey, accessSecret)
				.build();
	}

}
