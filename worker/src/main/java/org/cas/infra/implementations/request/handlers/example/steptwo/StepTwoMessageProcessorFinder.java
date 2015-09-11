package org.cas.infra.implementations.request.handlers.example.steptwo;

import org.cas.infra.MessageProcessor;
import org.cas.infra.MessageProcessorFinder;
import org.cas.infra.implementations.request.handlers.example.stepone.messages.StepTwoContext;
import org.cas.infra.implementations.request.handlers.example.steptwo.messageprocessors.StepTwoContextProcessor;

public class StepTwoMessageProcessorFinder implements MessageProcessorFinder {
	final StepTwoContextProcessor stepTwoContextProcessor;

	public StepTwoMessageProcessorFinder(final StepTwoContextProcessor stepTwoContextProcessor) {
		this.stepTwoContextProcessor = stepTwoContextProcessor;
	}

	@Override
	public MessageProcessor find(final Object message) {
		if (message instanceof StepTwoContext) {
			return stepTwoContextProcessor;
		}

		return null;
	}
}
