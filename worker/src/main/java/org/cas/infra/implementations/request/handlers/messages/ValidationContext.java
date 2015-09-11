package org.cas.infra.implementations.request.handlers.messages;

import org.cas.shared.objects.Identifiable;
import org.cas.infra.services.requesthandler.RequestHandlerMeta;
import org.cas.shared.objects.ApiRequest;

public class ValidationContext implements Identifiable {
	private final EndpointContext endpointContext;
	private final RequestHandlerMeta requestHandlerMeta;
	private final ApiRequest apiRequest;

	public ValidationContext(
			final EndpointContext endpointContext,
			final RequestHandlerMeta requestHandlerMeta,
			final ApiRequest apiRequest) {
		this.endpointContext = endpointContext;
		this.requestHandlerMeta = requestHandlerMeta;
		this.apiRequest = apiRequest;
	}

	@Override
	public String id() {
		return endpointContext.getAkkaRequest().getRequestGuid();
	}

	public EndpointContext getEndpointContext() {
		return endpointContext;
	}

	public RequestHandlerMeta getRequestHandlerMeta() {
		return requestHandlerMeta;
	}

	public ApiRequest getApiRequest() {
		return apiRequest;
	}
}
