package org.cas.infra.implementations.request.handlers.example;

import org.cas.infra.MessageProcessor;
import org.cas.infra.MessageProcessorFinder;
import org.cas.infra.implementations.request.handlers.example.messageprocessors.ExampleAkkaRequestProcessor;
import org.cas.shared.objects.akka.AkkaRequest;


public class ExampleMessageProcessorFinder implements MessageProcessorFinder {
	final ExampleAkkaRequestProcessor exampleAkkaRequestProcessor;

	public ExampleMessageProcessorFinder(final ExampleAkkaRequestProcessor exampleAkkaRequestProcessor) {
		this.exampleAkkaRequestProcessor = exampleAkkaRequestProcessor;
	}

	@Override
	public MessageProcessor find(final Object message) {
		if (message instanceof AkkaRequest) {
			return exampleAkkaRequestProcessor;
		}

		return null;
	}
}
