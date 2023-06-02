package io.demo.client;

import io.demo.client.interceptor.DadataTokenRequestInterceptor;
import io.demo.conf.properties.DadataConfigurationProperties;
import org.springframework.context.annotation.Bean;

public class DadataClientConfiguration {

	@Bean
	public DadataTokenRequestInterceptor tokenInterceptor(DadataConfigurationProperties properties) {
		return new DadataTokenRequestInterceptor(properties.getApiKey());
	}

}
