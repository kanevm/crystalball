package org.football.interceptor;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class XAuthTokenInterceptor implements ClientHttpRequestInterceptor {

	private static final String X_AUTH_TOKEN = "X-Auth-Token";
	private final String apiKey;

	public XAuthTokenInterceptor(final String apiKey) {
		this.apiKey = apiKey;
	}

	@Override
	public ClientHttpResponse intercept(final HttpRequest request, final byte[] body,
			final ClientHttpRequestExecution execution) throws IOException {

		final HttpHeaders headers = request.getHeaders();
		headers.add(X_AUTH_TOKEN, apiKey);

		return execution.execute(request, body);
	}
}
