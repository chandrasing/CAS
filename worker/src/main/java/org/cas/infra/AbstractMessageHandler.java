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
package org.cas.infra;

import akka.actor.ActorRef;

abstract public class AbstractMessageHandler<IN, OUT, ERR> implements MessageProcessor<IN> {
	private final ActorRef onSuccessHandler;
	private final ActorRef onErrorHandler;

	/**
	 * TODO: Explain END_OF_LINE
	 */
	public static final AbstractMessageHandler END_OF_LINE_HANDLER = new AbstractMessageHandler() {
		@Override
		public Object handle(Object message, BaseActor messageOwner) {
			throw new RuntimeException("End of line handler reached");
		}

		@Override
		public Object handle(Throwable throwable, Object message, BaseActor messageOwner) {
			throw new RuntimeException("End of line handler reached");
		}

		@Override
		public String id() {
			return "END OF LINE HANDLER";
		}
	};

	public AbstractMessageHandler() {
		this(null, null);
	}

	public AbstractMessageHandler(
			final ActorRef onSuccessHandler,
			final ActorRef onErrorHandler) {
		this.onSuccessHandler = onSuccessHandler;
		this.onErrorHandler = onErrorHandler;
	}

	@Override
	public final void process(final IN message, final BaseActor messageOwner) {
		try {
			OUT stepOutput = handle(message, messageOwner);
			if (null != onSuccessHandler) onSuccessHandler.tell(stepOutput, messageOwner.self());
		} catch (Throwable t) {
			ERR stepError = handle(t, message, messageOwner);
			if (null != onErrorHandler) onErrorHandler.tell(stepError, messageOwner.self());
		}
	}

	abstract public OUT handle(final IN message, final BaseActor messageOwner);

	abstract public ERR handle(final Throwable throwable, final IN message, final BaseActor messageOwner);
}
