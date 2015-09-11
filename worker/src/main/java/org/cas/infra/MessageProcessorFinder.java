package org.cas.infra;

public interface MessageProcessorFinder {
	MessageProcessor find(final Object message);
}
