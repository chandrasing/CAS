package org.cas.shared.objects.akka;

import com.eaio.uuid.UUID;
import org.cas.shared.objects.Identifiable;

import java.io.Serializable;

public class AkkaRequest<T> implements Serializable, Identifiable {
	private static final long serialVersionUID = 8825323671303468017L;

	private String requestGuid;
	private AkkaMessageType akkaMessageType;
	private T wrappedRequest;

	public AkkaRequest(
			final T wrappedRequest,
			final AkkaMessageType akkaMessageType) {
		this.requestGuid = new UUID().toString();
		this.wrappedRequest = wrappedRequest;
		this.akkaMessageType = akkaMessageType;
	}

	public String getRequestGuid() {
		return requestGuid;
	}

	public AkkaMessageType getAkkaMessageType() {
		return akkaMessageType;
	}

	public T getWrappedRequest() {
		return wrappedRequest;
	}

	@Override
	public String id() {
		return "AkkaRequest: " + requestGuid;
	}
}