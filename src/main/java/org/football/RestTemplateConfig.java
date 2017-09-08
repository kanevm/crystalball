package org.football;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.football.interceptor.XAuthTokenInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RestTemplateConfig {
	
	private static final Charset UTF_8 = Charset.forName("UTF-8");

	@Value("${football.api.key}")
	protected String apiKey;
	
	/**
	 * Obtains a reference to the REST client backing this API binding and used
	 * to perform API calls. Callers may use the RestTemplate to invoke other
	 * API operations not yet modeled by the binding interface. Callers may also
	 * modify the configuration of the RestTemplate to support unit testing the
	 * API binding with a mock server in a test environment. During
	 * construction, subclasses may apply customizations to the RestTemplate
	 * needed to invoke a specific API.
	 * 
	 * @see RestTemplate#setMessageConverters(java.util.List)
	 * @see RestTemplate#setErrorHandler(org.springframework.web.client.ResponseErrorHandler)
	 */
	@Bean
	public RestTemplate getRestTemplate() {
		final RestTemplate restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(getRequestFactory());
		restTemplate.setMessageConverters(getMessageConverters());
		restTemplate.setInterceptors(Collections.singletonList(new XAuthTokenInterceptor(apiKey)));

		return restTemplate;
	}

	/**
	 * This is useful when custom configuration of the request factory is
	 * required, such as configuring custom SSL details.
	 */
	protected ClientHttpRequestFactory getRequestFactory() {
		return new SimpleClientHttpRequestFactory();
	}

	/**
	 * Returns a list of {@link HttpMessageConverter}s to be used by the
	 * internal {@link RestTemplate}. By default, this includes a
	 * {@link StringHttpMessageConverter}, a
	 * {@link MappingJackson2HttpMessageConverter}, a
	 * {@link ByteArrayHttpMessageConverter}, and a
	 * {@link FormHttpMessageConverter}. The {@link FormHttpMessageConverter} is
	 * set to use "UTF-8" character encoding. Override this method to add
	 * additional message converters or to replace the default list of message
	 * converters.
	 */
	protected List<HttpMessageConverter<?>> getMessageConverters() {
		final List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		messageConverters.add(getStringHttpMessageConverter());
		messageConverters.add(getFormMessageConverter());
		messageConverters.add(getJsonMessageConverter());
		messageConverters.add(getByteArrayMessageConverter());

		return messageConverters;
	}

	private StringHttpMessageConverter getStringHttpMessageConverter() {
		return new StringHttpMessageConverter(UTF_8);
	}

	/**
	 * Returns an {@link FormHttpMessageConverter} to be used by the internal
	 * {@link RestTemplate}. By default, the message converter is set to use
	 * "UTF-8" character encoding. Override to customize the message converter
	 * (for example, to set supported media types or message converters for the
	 * parts of a multipart message). To remove/replace this or any of the other
	 * message converters that are registered by default, override the
	 * getMessageConverters() method instead.
	 */
	protected FormHttpMessageConverter getFormMessageConverter() {
		final FormHttpMessageConverter converter = new FormHttpMessageConverter();
		converter.setCharset(UTF_8);
		final List<HttpMessageConverter<?>> partConverters = new ArrayList<>();
		partConverters.add(new ByteArrayHttpMessageConverter());
		final StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(UTF_8);
		stringHttpMessageConverter.setWriteAcceptCharset(false);
		partConverters.add(stringHttpMessageConverter);
		partConverters.add(new ResourceHttpMessageConverter());
		converter.setPartConverters(partConverters);

		return converter;
	}

	/**
	 * Returns a {@link MappingJackson2HttpMessageConverter} to be used by the
	 * internal {@link RestTemplate}. Override to customize the message
	 * converter (for example, to set a custom object mapper or supported media
	 * types). To remove/replace this or any of the other message converters
	 * that are registered by default, override the getMessageConverters()
	 * method instead.
	 */
	protected MappingJackson2HttpMessageConverter getJsonMessageConverter() {
		final ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		return new MappingJackson2HttpMessageConverter(mapper);
	}

	/**
	 * Returns a {@link ByteArrayHttpMessageConverter} to be used by the
	 * internal {@link RestTemplate} when consuming image or other binary
	 * resources. By default, the message converter supports "image/jpeg",
	 * "image/gif", and "image/png" media types. Override to customize the
	 * message converter (for example, to set supported media types). To
	 * remove/replace this or any of the other message converters that are
	 * registered by default, override the getMessageConverters() method
	 * instead.
	 */
	protected ByteArrayHttpMessageConverter getByteArrayMessageConverter() {
		final ByteArrayHttpMessageConverter converter = new ByteArrayHttpMessageConverter();
		converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM, MediaType.IMAGE_JPEG,
				MediaType.IMAGE_GIF, MediaType.IMAGE_PNG));

		return converter;
	}

}
