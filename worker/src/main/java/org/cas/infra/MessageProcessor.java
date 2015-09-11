package org.cas.infra;

import org.cas.shared.objects.Identifiable;

public interface MessageProcessor<IN> extends Identifiable {
	/**
	 * No return is on purpose. Return value means some caller is
	 * waiting on it. By explicitly stating that there is no return,
	 * it is abundantly clear that it's async call.
	 *
	 * @param message      Actual message to be processed
	 * @param messageOwner Which actor sent the message?
	 */
	void process(final IN message, final BaseActor messageOwner);
}
