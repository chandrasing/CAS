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
