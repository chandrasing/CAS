package org.cas.infra;

public interface MessageProcessorBuilder {
	MessageProcessor build(final BaseActor owningActor);
}
