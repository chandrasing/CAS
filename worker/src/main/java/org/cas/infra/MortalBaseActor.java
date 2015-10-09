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

import akka.japi.Procedure;
import scala.concurrent.duration.Duration;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MortalBaseActor extends BaseActor {
	public interface LastWishExecutor<T extends MortalBaseActor> {
		void execute(final T mortalBaseActor);

		boolean done();
	}

	public enum ACTOR_STATE {ALIVE, DYING, DEAD}

	public enum DYING_SEQ {ASK_LAST_WISH, LAST_WISH_EXECUTED, DIE}

	Procedure<Object> dyingActor = new Procedure<Object>() {

		/**
		 * TODO: Last wish fulfillment check is recursive in nature.
		 * There is one problem with this check. There is no guarantee
		 * about last wish execution being honest. A naive mistake to
		 * implement done() never to return true will result in actor
		 * in a limbo being a dying actor.
		 *
		 * TODO: All these actors in limbo can be rounded up by custom
		 * supervising strategy. *Job for the THE REAPER*
		 *
		 * TODO: impose self checks to ensure that all dying sequence
		 * messages are sent only by dying actor itself. Check Anchor 1
		 */
		private void lastWishFulfillmentCheck() {
			logger.debug("Scheduling last wish fulfillment check");
			if (MortalBaseActor.this.lastWishExecutor.done()) {
				logger.debug("Last wish executed, sending last wish executed message");
				self().tell(DYING_SEQ.LAST_WISH_EXECUTED, self());
				return;
			}

			context().system().scheduler().scheduleOnce(
					Duration.apply(1000, TimeUnit.MILLISECONDS)
					, new Runnable() {
						@Override
						public void run() {
							lastWishFulfillmentCheck();
						}
					}
					, context().dispatcher()
			);
		}

		@Override
		public final void apply(final Object message) throws Exception {
			/**
			 * Anchor 1: Haven't thought through yet about what are the side
			 * effects if dying actor is restricted to listen to self.
			 */
//			if (sender() != self()) {
//				logger.debug("Ignoring messages from other actors");
//				return;
//			}

			if (null == message) {
				// do nothing
				logger.debug("Dying actor {} received null message", MortalBaseActor.this.getName());
				return;
			}

			if (!(message instanceof DYING_SEQ)) {
				// do nothing
				logger.debug("Dying actor {} received message of type {} which is outside of dying sequence", MortalBaseActor.this.getName(), message.getClass());
				return;
			}

			switch ((DYING_SEQ) message) {
				case ASK_LAST_WISH:
					/**
					 * Actor has been asked last wish, safe to be on
					 * deathbed.
					 */
					MortalBaseActor.this.actorState = ACTOR_STATE.DYING;

					/**
					 * Actor without any last wishes can be killed immediately
					 * so break; appears inside the if block.
					 */
					if (null != MortalBaseActor.this.lastWishExecutor) {
						MortalBaseActor.this.lastWishExecutor.execute(MortalBaseActor.this);
						lastWishFulfillmentCheck();
						break;
					}

				case LAST_WISH_EXECUTED:
					logger.debug("{} going to die soon", MortalBaseActor.this.getName());
					self().tell(DYING_SEQ.DIE, self());
					break;

				case DIE:
					context().stop(self());
					MortalBaseActor.this.actorState = ACTOR_STATE.DEAD;
					break;
			}
		}
	};

	private final Long age;
	private final LastWishExecutor lastWishExecutor;
	// TODO: Non-final?
	private ACTOR_STATE actorState = ACTOR_STATE.ALIVE;

	public MortalBaseActor(
			final String name,
			final MessageProcessorFinder messageProcessorFinder,
			final Long age) {
		this(name, messageProcessorFinder, null, null, age, null);
	}

	public MortalBaseActor(
			final String name,
			final MessageProcessorFinder messageProcessorFinder,
			final Long age,
			final LastWishExecutor lastWishExecutor) {
		this(name, messageProcessorFinder, null, null, age, lastWishExecutor);
	}

	public MortalBaseActor(
			final String name,
			final MessageProcessorFinder messageProcessorFinder,
			final List<Hook> preProcessingHooks,
			final List<Hook> postProcessingHooks,
			final Long age) {
		this(name, messageProcessorFinder, null, null, age, null);
	}

	public MortalBaseActor(
			final String name,
			final MessageProcessorFinder messageProcessorFinder,
			final List<Hook> preProcessingHooks,
			final List<Hook> postProcessingHooks,
			final Long age,
			final LastWishExecutor lastWishExecutor) {
		super(name, messageProcessorFinder, preProcessingHooks, postProcessingHooks);

		if (null == age || 1 > age) {
			throw new IllegalArgumentException("Mortal actor can not have age less than 1ms");
		}

		this.age = age;
		this.lastWishExecutor = lastWishExecutor;
	}

	public MortalBaseActor(
			final String name,
			final MessageProcessorFinderBuilder messageProcessorFinderBuilder,
			final Long age) {
		this(name, messageProcessorFinderBuilder, null, null, age, null);
	}

	public MortalBaseActor(
			final String name,
			final MessageProcessorFinderBuilder messageProcessorFinderBuilder,
			final Long age,
			final LastWishExecutor lastWishExecutor) {
		this(name, messageProcessorFinderBuilder, null, null, age, lastWishExecutor);
	}

	public MortalBaseActor(
			final String name,
			final MessageProcessorFinderBuilder messageProcessorFinderBuilder,
			final List<Hook> preProcessingHooks,
			final List<Hook> postProcessingHooks,
			final Long age) {
		this(name, messageProcessorFinderBuilder, null, null, age, null);
	}

	public MortalBaseActor(
			final String name,
			final MessageProcessorFinderBuilder messageProcessorFinderBuilder,
			final List<Hook> preProcessingHooks,
			final List<Hook> postProcessingHooks,
			final Long age,
			final LastWishExecutor lastWishExecutor) {
		super(name, messageProcessorFinderBuilder, preProcessingHooks, postProcessingHooks);

		this.age = age;
		this.lastWishExecutor = lastWishExecutor;
	}

	@Override
	public final void preStart() throws Exception {
		super.preStart();
		context().system().scheduler().scheduleOnce(
				Duration.apply(age, TimeUnit.MILLISECONDS),
				new Runnable() {
					@Override
					public void run() {
						getContext().become(dyingActor);
						self().tell(DYING_SEQ.ASK_LAST_WISH, self());
					}
				}
				, context().dispatcher()
		);
	}

	@Override
	public void postStop() throws Exception {
		super.postStop();
		logger.debug("{} has been stopped", getName());
	}
}
