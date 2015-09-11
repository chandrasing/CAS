package org.cas.infra;

import akka.actor.ActorSystem;

public class ActorSystemSingleton {

	private final ActorSystem actorSystem;

	private ActorSystemSingleton() {
		this.actorSystem = ActorSystem.create("CAS_ACTOR_SYSTEM");
	}

	private static class SingletonHolder {
		private static final ActorSystemSingleton ACTOR_SYSTEM_INSTANCE = new ActorSystemSingleton();
	}

	private static ActorSystemSingleton getInstance() {
		return SingletonHolder.ACTOR_SYSTEM_INSTANCE;
	}

	public ActorSystem getActorSystem() {
		return getInstance().actorSystem;
	}
}