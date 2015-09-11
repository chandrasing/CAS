package org.cas.infra;

import akka.actor.ActorSystem;
import akka.actor.Props;

public interface Assembler {
	Props assemble(final ActorSystem actorSystem);
}
