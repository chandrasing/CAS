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
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.cas.infra.*;
import org.cas.shared.objects.akka.AkkaMessageType;
import org.cas.shared.objects.akka.AkkaRequest;
import org.cas.shared.objects.SerializableHttpRequest;

public class ExampleMortalAssembler implements Assembler {

	@Override
	public Props assemble(final ActorSystem actorSystem) {
		final MortalBaseActor.LastWishExecutor lastWishExecutor = new MortalBaseActor.LastWishExecutor() {
			@Override
			public void execute(MortalBaseActor mortalBaseActor) {
				actorSystem.log().info("Executing last wish for " + mortalBaseActor.getName());
			}

			@Override
			public boolean done() {
				return true;
			}
		};

		MessageProcessorFinder dummy = new MessageProcessorFinder() {
			@Override
			public MessageProcessor find(final Object message) {
				return new MessageProcessor() {
					@Override
					public void process(final Object message, final BaseActor messageOwner) {
						actorSystem.log().info("Does nothing!!!");
					}

					@Override
					public String id() {
						return "DUMMY";
					}
				};
			}
		};

		return Props.create(
				MortalBaseActor.class,
				"ExampleMortal",
				dummy,
				5000l,
				lastWishExecutor
		);
	}

	public static void main(String[] args) {
		final ActorSystem actorSystem = ActorSystem.create("CasActorSystem");

		ActorRef dummy = actorSystem.actorOf(new ExampleMortalAssembler().assemble(actorSystem), "ExampleRequestHandler");

		dummy.tell(new AkkaRequest<SerializableHttpRequest>(new SerializableHttpRequest(null, null, null, null, null, null, null, null, null), AkkaMessageType.HTTP), dummy);
	}

}
