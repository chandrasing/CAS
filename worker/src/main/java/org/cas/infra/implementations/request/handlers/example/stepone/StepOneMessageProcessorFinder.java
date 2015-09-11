package org.cas.infra.implementations.request.handlers.example.stepone;

import org.cas.infra.MessageProcessor;
import org.cas.infra.MessageProcessorFinder;
import org.cas.infra.implementations.request.handlers.example.messages.StepOneContext;
import org.cas.infra.implementations.request.handlers.example.stepone.messageprocessors.StepOneContextProcessor;

public class StepOneMessageProcessorFinder implements MessageProcessorFinder {
	private final StepOneContextProcessor stepOneContextProcessor;

	public StepOneMessageProcessorFinder(final StepOneContextProcessor stepOneContextProcessor) {
		this.stepOneContextProcessor = stepOneContextProcessor;
	}

	@Override
	public MessageProcessor find(final Object message) {
		if (message instanceof StepOneContext) {
			return stepOneContextProcessor;
		}

		return null;
	}
}
