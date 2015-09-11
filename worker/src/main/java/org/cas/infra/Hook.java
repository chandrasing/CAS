package org.cas.infra;

import org.cas.shared.objects.Identifiable;

public interface Hook extends Identifiable {
	void invoke(
			final BaseActor invoker,
			final MessageProcessor messageProcessor,
			final Object message) throws RuntimeException;
}
