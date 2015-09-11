package org.cas.shared.objects;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class SerializableHttpRequest implements Serializable, Identifiable {
	private static final long serialVersionUID = 8825323671303468013L;

	private final String httpVersion;
	private final String httpMethod;
	private final String uri;
	private final String hostname;
	private final Map<String, List<String>> headers;
	private final Map<String, List<String>> cookies;
	private final Map<String, List<String>> queryParameters;
	private final Map<String, List<String>> postParameters;
	private final String content;

	public SerializableHttpRequest(
			final String httpVersion,
			final String httpMethod,
			final String uri,
			final String hostname,
			final Map<String, List<String>> headers,
			final Map<String, List<String>> cookies,
			final Map<String, List<String>> queryParameters,
			final Map<String, List<String>> postParameters,
			final String content
	) {
		this.httpVersion = httpVersion;
		this.httpMethod = httpMethod;
		this.uri = uri;
		this.hostname = hostname;
		this.headers = headers;
		this.cookies = cookies;
		this.queryParameters = queryParameters;
		this.postParameters = postParameters;
		this.content = content;
	}

	public String getHttpVersion() {
		return httpVersion;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public String getUri() {
		return uri;
	}

	public String getHostname() {
		return hostname;
	}

	public Map<String, List<String>> getHeaders() {
		return headers;
	}

	public Map<String, List<String>> getCookies() {
		return cookies;
	}

	public Map<String, List<String>> getQueryParameters() {
		return queryParameters;
	}

	public Map<String, List<String>> getPostParameters() {
		return postParameters;
	}

	public String getContent() {
		return content;
	}

	// TODO: How to identify a Http request.
	@Override
	public String id() {
		return "HTTP request: ";
	}

	@Override
	public String toString() {
		return "SerializableHttpRequest{" +
				"httpVersion='" + httpVersion + '\'' +
				", httpMethod='" + httpMethod + '\'' +
				", uri='" + uri + '\'' +
				", hostname='" + hostname + '\'' +
				", headers=" + headers +
				", cookies=" + cookies +
				", queryParameters=" + queryParameters +
				", postParameters=" + postParameters +
				", content='" + content + '\'' +
				'}';
	}
}
