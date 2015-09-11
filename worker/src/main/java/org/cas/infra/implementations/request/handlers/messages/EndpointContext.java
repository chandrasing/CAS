package org.cas.infra.implementations.request.handlers.messages;

import org.cas.shared.objects.Identifiable;
import org.cas.shared.objects.akka.AkkaRequest;

public class EndpointContext implements Identifiable {
	private final AkkaRequest akkaRequest;

	public EndpointContext(
			final AkkaRequest akkaRequest) {
		this.akkaRequest = akkaRequest;
	}

	public String id() {
		return akkaRequest.getRequestGuid();
	}

	public AkkaRequest getAkkaRequest() {
		return akkaRequest;
	}
}
