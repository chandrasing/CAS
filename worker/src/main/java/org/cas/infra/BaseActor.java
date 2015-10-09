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

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import org.cas.infra.implementations.hooks.PostProcessLoggingHook;
import org.cas.infra.implementations.hooks.PreProcessLoggingHook;
import org.cas.shared.objects.Identifiable;

import java.util.Collections;
import java.util.List;

public class BaseActor extends UntypedActor {
	private static final String PRE_PROCESS_MESSAGE_LOG_FORMAT =
			"Actor({})#{} named {} starting processing of message({}) having {} as {} at {} using {} message processor";
	private static final String POST_PROCESS_MESSAGE_LOG_FORMAT =
			"Actor({})#{} named {} done with processing of message({}) having {} as {} at {} using {} message processor";

	private static final List<Hook> DEFAULT_PRE_PROCESSING_HOOKS
			= Collections.<Hook>singletonList(new PreProcessLoggingHook());
	private static final List<Hook> DEFAULT_POST_PROCESSING_HOOKS
			= Collections.<Hook>singletonList(new PostProcessLoggingHook());

	/**
	 * Note: Instance specific logger. Instance specific logger make sense in
	 * abstract classes. Still chose to make logger instance specific as most
	 * likely one is expected to extend this class to add their own bespoke
	 * additives.
	 */
	transient protected final LoggingAdapter logger = Logging.getLogger(getContext().system(), this); // LoggerFactory.getLogger(getClass());
	private final String name;
	private final MessageProcessorFinder messageProcessorFinder;

	// Hooks
	private final List<Hook> preProcessingHooks;
	private final List<Hook> postProcessingHooks;

	/**
	 * Following form is used when behaviours are not tied to the actor.
	 * In simple words, when reference to actor is *NOT* required while
	 * processing the message.
	 *
	 * @param name                   Name of the Actor (used in logging)
	 * @param messageProcessorFinder Independent behaviours
	 */
	public BaseActor(
			final String name,
			final MessageProcessorFinder messageProcessorFinder) {
		this(name, messageProcessorFinder, null, null);
	}

	public BaseActor(
			final String name,
			final MessageProcessorFinder messageProcessorFinder,
			final List<Hook> preProcessingHooks,
			final List<Hook> postProcessingHooks) {

		if (null == name
				|| null == messageProcessorFinder) {
			throw new IllegalArgumentException("Actor with no behaviours is useless");
		}

		this.name = name;
		this.messageProcessorFinder = messageProcessorFinder;

		// TODO: Pre and post process hooks, check Marker: 1
		/*if (null == preProcessingHooks) {
			this.preProcessingHooks = DEFAULT_PRE_PROCESSING_HOOKS;
		}

		if (null == postProcessingHooks) {
			this.postProcessingHooks = DEFAULT_POST_PROCESSING_HOOKS;
		}*/

		this.preProcessingHooks = this.postProcessingHooks = Collections.<Hook>emptyList();
	}

	/**
	 * Following form is used when behaviours are tied to the actor.
	 * In simple words, when reference to actor is *REQUIRED* while
	 * processing the message.
	 * <p>
	 * If you need to setup proper assembly line with sub-stations, you shall
	 * need the actor references across the line so that any children spawned
	 * to handle certain situation can be managed as a child.
	 * </p>
	 *
	 * @param name                          Name of the Actor (used in logging)
	 * @param messageProcessorFinderBuilder Builder which ties the behaviours with the
	 *                                      actor
	 */
	public BaseActor(
			final String name,
			final MessageProcessorFinderBuilder messageProcessorFinderBuilder) {
		this(name, messageProcessorFinderBuilder, null, null);
	}

	public BaseActor(
			final String name,
			final MessageProcessorFinderBuilder messageProcessorFinderBuilder,
			final List<Hook> preProcessingHooks,
			final List<Hook> postProcessingHooks) {

		if (null == name
				|| null == messageProcessorFinderBuilder) {
			throw new IllegalArgumentException("Actor with no behaviours is useless");
		}

		this.name = name;
		this.messageProcessorFinder = messageProcessorFinderBuilder.build(this);

		// TODO: Pre and post process hooks, check Marker: 1
		/*if (null == preProcessingHooks) {
			this.preProcessingHooks = DEFAULT_PRE_PROCESSING_HOOKS;
		}

		if (null == postProcessingHooks) {
			this.postProcessingHooks = DEFAULT_POST_PROCESSING_HOOKS;
		}*/

		this.preProcessingHooks = this.postProcessingHooks = Collections.<Hook>emptyList();
	}

	public String getName() {
		return this.name;
	}

	/**
	 * Marker: 1
	 * <p>
	 * TODO: Pre and post processor can be refactored out of this class.
	 * Hooks functionality is in place but not used at the moment.
	 * </p><p>
	 * What's the hurdle in addressing this todo?
	 * Pre and post hooks are more like aspects. Aspects are not behaviours, rather
	 * they are orthogonal to the functionality and at times do something completely
	 * irrelevant to underlying algorithm. One can not expect more from them.
	 * </p><p>
	 * By the way hooks have interesting side-effect. One can set up a completely
	 * parallel assembly/production line, eg. say during champion/challenger testing.
	 * </p>
	 * PS: Always enclose hook execution in try-catch as failure to execute a hook is
	 * not a problem for a system.
	 */
	public void preProcess(final MessageProcessor messageProcessor, final Object message) {
		if (message instanceof Identifiable) {
			Identifiable identifiable = (Identifiable) message;
			logger.info(PRE_PROCESS_MESSAGE_LOG_FORMAT, new Object[]{self().path(), this.hashCode(), this.name, message.getClass().getSimpleName(), "id", identifiable.id(), System.nanoTime(), messageProcessor.id()});
			return;
		}

		logger.info(PRE_PROCESS_MESSAGE_LOG_FORMAT, new Object[]{self().path(), this.hashCode(), this.name, message.getClass().getSimpleName(), "hashcode", message.hashCode(), System.nanoTime(), messageProcessor.id()});
	}

	public void postProcess(final MessageProcessor messageProcessor, final Object message) {
		if (message instanceof Identifiable) {
			Identifiable identifiable = (Identifiable) message;
			logger.info(POST_PROCESS_MESSAGE_LOG_FORMAT, new Object[] {self().path(), this.hashCode(), this.name, message.getClass().getSimpleName(), "id", identifiable.id(), System.nanoTime(), messageProcessor.id()});
			return;
		}

		logger.info(POST_PROCESS_MESSAGE_LOG_FORMAT, new Object[] {self().path(), this.hashCode(), this.name, message.getClass().getSimpleName(), "hashcode", message.hashCode(), System.nanoTime(), messageProcessor.id()});
	}

	@Override
	final public void onReceive(Object message) throws Exception {
		final MessageProcessor messageProcessor = messageProcessorFinder.find(message);
		if (null == messageProcessor) {
			logger.error("Message of type {} not supported by {}", message.getClass(), this.getClass());
			unhandled(message);
			return;
		}

		preProcess(messageProcessor, message);
		// TODO: Pre and post process hooks, check Marker: 1
		if (null != preProcessingHooks
				&& !preProcessingHooks.isEmpty()) {
			for (Hook hook : preProcessingHooks) {
				try {
					hook.invoke(this, messageProcessor, message);
				} catch (Throwable t) {
					logger.info("Failed to execute hook named {}, exception was {}", hook.id(), t);
				}
			}
		}

		try {
			messageProcessor.process(message, this);
		} catch (Throwable t) {
			logger.error("Failed to process message {} by processor {}, exception was {}", message, messageProcessor.id(), t);
		} finally {
			postProcess(messageProcessor, message);
			// TODO: Pre and post process hooks, check Marker: 1
			if (null != postProcessingHooks
					&& !postProcessingHooks.isEmpty()) {
				for (Hook hook : postProcessingHooks) {
					try {
						hook.invoke(this, messageProcessor, message);
					} catch (Throwable t) {
						logger.info("Failed to execute hook named {}, exception was {}", hook.id(), t);
					}
				}
			}
		}
	}
}
