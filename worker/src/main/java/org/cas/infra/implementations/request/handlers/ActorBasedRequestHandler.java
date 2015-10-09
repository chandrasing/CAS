/**
 * CAS - https://github.com/chandrasing/CAS
 *
 * Copyright (C) 2015 Chandrasing
 * Copyright (C) 2015 contributors
 *
 * CAS is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by the Free Software Foundation; either version 3 of the License,
 * or (at your option) any later version.
 *
 * CAS is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with CAS; if not, see <http://www.gnu.org/licenses/>.
 */
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
