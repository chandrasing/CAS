package org.cas.infra.implementations.hooks;

import org.cas.infra.BaseActor;
import org.cas.infra.Hook;
import org.cas.shared.objects.Identifiable;
import org.cas.infra.MessageProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PreProcessLoggingHook implements Hook {
	private static final Logger LOGGER = LoggerFactory.getLogger(PreProcessLoggingHook.class);

	private static final String PRE_PROCESS_MESSAGE_LOG_FORMAT =
			"Actor({})#{} named {} starting processing of message({}) having {} as {} at {} using {} message processor";

	@Override
	public String id() {
		return "Pre-process logging hook";
	}

	@Override
	public void invoke(
			final BaseActor invoker,
			final MessageProcessor messageProcessor,
			final Object message) throws RuntimeException {

		LOGGER.info(
				PRE_PROCESS_MESSAGE_LOG_FORMAT,
				invoker.self().path(),
				invoker.hashCode(),
				invoker.getName(),
				message.getClass().getCanonicalName(),
				message instanceof Identifiable ? "id" : "hashcode",
				message instanceof Identifiable ? ((Identifiable) message).id() : message.hashCode(),
				System.nanoTime(),
				messageProcessor.id());
	}
}
