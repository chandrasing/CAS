package org.cas.infra.implementations.request.handlers.example.stepone;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.cas.infra.Assembler;
import org.cas.infra.BaseActor;
import org.cas.infra.implementations.request.handlers.example.stepone.messageprocessors.StepOneContextProcessor;
import org.cas.infra.implementations.request.handlers.example.steptwo.StepTwoAssembler;

public class StepOneAssembler implements Assembler {

	@Override
	public Props assemble(final ActorSystem actorSystem) {
		final ActorRef stepTwo = actorSystem.actorOf(new StepTwoAssembler().assemble(actorSystem), "StepTwoHandler");
		final StepOneContextProcessor stepOneContextProcessor = new StepOneContextProcessor(stepTwo);

		return Props.create(
				BaseActor.class,
				"StepOneHandler",
				new StepOneMessageProcessorFinder(stepOneContextProcessor)
		);
	}

}
