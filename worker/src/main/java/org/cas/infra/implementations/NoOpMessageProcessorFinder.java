package org.cas.infra.implementations;

import org.cas.infra.BaseActor;
import org.cas.infra.MessageProcessor;
import org.cas.infra.MessageProcessorFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoOpMessageProcessorFinder implements MessageProcessorFinder {
	private static final Logger LOGGER = LoggerFactory.getLogger(NoOpMessageProcessorFinder.class);
	private static final MessageProcessor DO_NOTHING_MESSAGE_PROCESSOR;

	static {
		DO_NOTHING_MESSAGE_PROCESSOR = new MessageProcessor() {
			@Override
			public String id() {
				return "NOP Processor";
			}

			@Override
			public void process(Object message, BaseActor messageOwner) {
				LOGGER.info("Do nothing processor called for message with hashcode {}", message.hashCode());
			}
		};
	}

	public static MessageProcessor getDoNothingMessageProcessor() {
		return DO_NOTHING_MESSAGE_PROCESSOR;
	}

	@Override
	public MessageProcessor find(Object message) {
		return DO_NOTHING_MESSAGE_PROCESSOR;
	}
}
