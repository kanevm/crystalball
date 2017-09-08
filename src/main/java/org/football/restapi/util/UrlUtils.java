package org.football.restapi.util;

import java.net.URI;
import java.util.Collections;
import java.util.Map;

import org.springframework.web.util.UriComponentsBuilder;

public final class UrlUtils {

	public static String safeUrl(final String url) {
		if (url.endsWith("/")) {
			return "" + url.subSequence(0, url.lastIndexOf("/"));
		}

		return url;
	}

	public static URI uriFrom(final URI uri, final String path) {
		return uriFrom(uri, path, Collections.<String, String> emptyMap());
	}

	public static URI uriFrom(final URI uri, final String path, final Map<String, ?> pathParams) {
		return UriComponentsBuilder.fromUri(uri).path(path).buildAndExpand(pathParams).toUri();
	}
}
