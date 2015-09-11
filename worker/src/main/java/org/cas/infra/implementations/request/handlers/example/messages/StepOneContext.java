package org.cas.infra.implementations.request.handlers.example.messages;

import org.cas.shared.objects.Identifiable;

public class StepOneContext implements Identifiable {

	private final String guid;

	public StepOneContext(String guid) {
		this.guid = guid;
	}

	public String getGuid() {
		return guid;
	}

	@Override
	public String toString() {
		return "StepOneContext{" +
				"guid='" + guid + '\'' +
				'}';
	}

	@Override
	public String id() {
		return StepOneContext.class.getSimpleName();
	}
}
