package org.cas.infra.implementations.request.handlers.example.steptwo;

import akka.actor.ActorSystem;
import akka.actor.Props;
import org.cas.infra.Assembler;
import org.cas.infra.BaseActor;
import org.cas.infra.implementations.request.handlers.example.steptwo.messageprocessors.StepTwoContextProcessor;

public class StepTwoAssembler implements Assembler {

	@Override
	public Props assemble(final ActorSystem actorSystem) {
		final StepTwoContextProcessor stepTwoContextProcessor = new StepTwoContextProcessor();

		return Props.create(
				BaseActor.class,
				"StepTwoHandler",
				new StepTwoMessageProcessorFinder(stepTwoContextProcessor)
		);
	}

}
