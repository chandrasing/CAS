package org.cas.infra.implementations.request.handlers.example.stepone.messages;

import org.cas.shared.objects.Identifiable;

public class StepTwoContext implements Identifiable {

	private final String guid;

	public StepTwoContext(String guid) {
		this.guid = guid;
	}

	public String getGuid() {
		return guid;
	}

	@Override
	public String toString() {
		return "StepTwoContext{" +
				"guid='" + guid + '\'' +
				'}';
	}

	@Override
	public String id() {
		return StepTwoContext.class.getSimpleName();
	}
}
