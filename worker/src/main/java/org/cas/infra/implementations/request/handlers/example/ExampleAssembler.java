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
package org.cas.infra.implementations.request.handlers.example;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.cas.infra.Assembler;
import org.cas.infra.implementations.request.handlers.ActorBasedRequestHandler;
import org.cas.infra.implementations.request.handlers.example.messageprocessors.ExampleAkkaRequestProcessor;
import org.cas.infra.implementations.request.handlers.example.stepone.StepOneAssembler;
import org.cas.infra.services.requesthandler.RequestHandlerMeta;
import org.cas.shared.objects.akka.AkkaMessageType;
import org.cas.shared.objects.akka.AkkaRequest;
import org.cas.shared.objects.SerializableHttpRequest;


/**
 * Event-driven architectures are bit finicky at times and hard to
 * comprehend. Now when one looks at following code, immediate question
 * about object scope can frustrate a newbie.
 * <p/>
 * Following statement actually creates an immortal object.
 * ActorRef dummy
 * = actorSystem.actorOf(new ExampleAssembler().assemble(actorSystem), "ExampleRequestHandler");
 * <p/>
 * How?? actorSystem.actorOf() returns a reference to object created
 * within the event-loop. Event-loop which is single threaded never ending
 * loop. Still confusing?? Have a look at ActorSystem.create() code.
 */
public class ExampleAssembler implements Assembler {

	@Override
	public Props assemble(final ActorSystem actorSystem) {
		ActorRef stepOne = actorSystem.actorOf(new StepOneAssembler().assemble(actorSystem), "StepOneHandler");
		ExampleAkkaRequestProcessor exampleAkkaRequestProcessor = new ExampleAkkaRequestProcessor(stepOne);

		return Props.create(
				ActorBasedRequestHandler.class,
				"ExampleRequestHandler",
				RequestHandlerMeta.EXAMPLE,
				new ExampleMessageProcessorFinder(exampleAkkaRequestProcessor)
		);
	}

	public static void main(String[] args) {
		final ActorSystem actorSystem = ActorSystem.create("CasActorSystem");

		ActorRef dummy = actorSystem.actorOf(new ExampleAssembler().assemble(actorSystem), "RequestHandler");

		dummy.tell(new AkkaRequest<SerializableHttpRequest>(new SerializableHttpRequest(null, null, null, null, null, null, null, null, null), AkkaMessageType.HTTP), dummy);
	}

}
