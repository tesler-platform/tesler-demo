package io.demo.client.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import java.util.Objects;

public class DadataTokenRequestInterceptor implements RequestInterceptor {

	private final String headerValue;

	public DadataTokenRequestInterceptor(String token) {
		this.headerValue = "Token " + Objects.requireNonNull(token);
	}

	@Override
	public void apply(RequestTemplate template) {
		template.header("Authorization", headerValue);
	}

}
