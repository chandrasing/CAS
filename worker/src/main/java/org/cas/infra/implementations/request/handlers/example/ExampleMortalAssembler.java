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
