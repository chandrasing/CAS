package org.cas.infra.implementations.request.handlers.example.steptwo.messageprocessors;

import org.cas.infra.AbstractMessageHandler;
import org.cas.infra.BaseActor;
import org.cas.infra.implementations.request.handlers.example.stepone.messages.StepTwoContext;

public class StepTwoContextProcessor extends AbstractMessageHandler<StepTwoContext, StepTwoContext, StepTwoContext> {
	public StepTwoContextProcessor() {
		super();
	}

	@Override
	public StepTwoContext handle(final StepTwoContext stepTwoContext, final BaseActor messageOwner) {
		return new StepTwoContext(stepTwoContext.getGuid());
	}

	@Override
	public StepTwoContext handle(final Throwable throwable, final StepTwoContext stepTwoContext, final BaseActor messageOwner) {
		System.out.println("Failed to process akka request with GUID:" + stepTwoContext.getGuid());
		return stepTwoContext;
	}

	@Override
	public String id() {
		return getClass().getSimpleName();
	}
}
