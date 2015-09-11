package org.cas.infra;

public interface MessageProcessorFinderBuilder {
	MessageProcessorFinder build(final BaseActor owningActor);
}
