package org.cas.infra.services.validationsupervisor;

import org.cas.infra.BaseActor;
import org.cas.infra.MessageProcessorFinderBuilder;
import org.cas.infra.services.requesthandler.RequestHandlerMeta;

abstract public class ValidationSupervisor extends BaseActor {
	private final RequestHandlerMeta requestHandlerMeta;

	public ValidationSupervisor(
			final String name,
			final MessageProcessorFinderBuilder messageProcessorFinderBuilder,
			final RequestHandlerMeta requestHandlerMeta) {
		super(name, messageProcessorFinderBuilder);
		this.requestHandlerMeta = requestHandlerMeta;
	}

}
