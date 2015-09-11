package org.cas.infra.implementations.request.handlers.example.messageprocessors;

import akka.actor.ActorRef;
import org.cas.infra.AbstractMessageHandler;
import org.cas.infra.BaseActor;
import org.cas.infra.implementations.request.handlers.example.messages.StepOneContext;
import org.cas.shared.objects.akka.AkkaRequest;


public class ExampleAkkaRequestProcessor extends AbstractMessageHandler<AkkaRequest, StepOneContext, AkkaRequest> {
	public ExampleAkkaRequestProcessor(ActorRef success) {
		super(success, null);
	}

	@Override
	public StepOneContext handle(final AkkaRequest akkaRequest, final BaseActor messageOwner) {
		return new StepOneContext(akkaRequest.getRequestGuid());
	}

	@Override
	public AkkaRequest handle(final Throwable throwable, final AkkaRequest akkaRequest, final BaseActor messageOwner) {
		System.out.println("Failed to process akka request with GUID:" + akkaRequest.getRequestGuid());
		return akkaRequest;
	}

	@Override
	public String id() {
		return getClass().getSimpleName();
	}
}
