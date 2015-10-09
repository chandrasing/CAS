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
