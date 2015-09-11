package org.cas.infra.implementations.request.handlers;

import org.cas.infra.*;
import org.cas.infra.services.requesthandler.RequestHandler;
import org.cas.infra.services.requesthandler.RequestHandlerMeta;
import org.cas.shared.objects.Identifiable;

import java.util.List;

@RequestHandler
public class ActorBasedRequestHandler extends BaseActor implements Identifiable {
	/**
	 * Following determines what request type this instance pertains to
	 */
	private final RequestHandlerMeta requestHandlerMeta;

	/**
	 * Anchor 1;
	 * Todo: Following is way too rudimentary.
	 * <p>
	 * With DI one can do something like configure various instances of
	 * this class.
	 * </p><p>
	 * Keep in mind, though, that these instances can **NEVER be singletons**
	 * </p>
	 */

	public ActorBasedRequestHandler(
			final String name,
			final RequestHandlerMeta requestHandlerMeta,
			final MessageProcessorFinder messageProcessorFinder) {
		super(name, messageProcessorFinder);
		this.requestHandlerMeta = requestHandlerMeta;
	}

	public ActorBasedRequestHandler(
			final String name,
			final RequestHandlerMeta requestHandlerMeta,
			final MessageProcessorFinderBuilder messageProcessorFinderBuilder) {
		super(name, messageProcessorFinderBuilder);
		this.requestHandlerMeta = requestHandlerMeta;
	}

	public ActorBasedRequestHandler(
			final String name,
			final RequestHandlerMeta requestHandlerMeta,
			final MessageProcessorFinder messageProcessorFinder,
			final List<Hook> preProcessingHooks,
			final List<Hook> postProcessingHooks) {
		super(name, messageProcessorFinder, preProcessingHooks, postProcessingHooks);
		this.requestHandlerMeta = requestHandlerMeta;
	}

	public ActorBasedRequestHandler(
			final String name,
			final RequestHandlerMeta requestHandlerMeta,
			final MessageProcessorFinderBuilder messageProcessorFinderBuilder,
			final List<Hook> preProcessingHooks,
			final List<Hook> postProcessingHooks) {
		super(name, messageProcessorFinderBuilder, preProcessingHooks, postProcessingHooks);
		this.requestHandlerMeta = requestHandlerMeta;
	}

	@Override
	public String id() {
		return super.getName();
	}

	public RequestHandlerMeta getRequestHandlerMeta() {
		return requestHandlerMeta;
	}
}
