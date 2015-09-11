package org.cas.infra.implementations.request.handlers.example.stepone.messageprocessors;

import akka.actor.ActorRef;
import org.cas.infra.AbstractMessageHandler;
import org.cas.infra.BaseActor;
import org.cas.infra.implementations.request.handlers.example.messages.StepOneContext;
import org.cas.infra.implementations.request.handlers.example.stepone.messages.StepTwoContext;

public class StepOneContextProcessor extends AbstractMessageHandler<StepOneContext, StepTwoContext, StepOneContext> {
	public StepOneContextProcessor(ActorRef success) {
		super(success, null);
	}

	@Override
	public StepTwoContext handle(final StepOneContext stepOneContext, final BaseActor messageOwner) {
		return new StepTwoContext(stepOneContext.getGuid());
	}

	@Override
	public StepOneContext handle(final Throwable throwable, final StepOneContext stepOneContext, final BaseActor messageOwner) {
		System.out.println("Failed to process akka request with GUID:" + stepOneContext.getGuid());
		return stepOneContext;
	}

	@Override
	public String id() {
		return getClass().getSimpleName();
	}
}
