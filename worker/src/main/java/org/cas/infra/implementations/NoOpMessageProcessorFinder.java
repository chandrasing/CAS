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
package org.cas.infra.implementations;

import org.cas.infra.BaseActor;
import org.cas.infra.MessageProcessor;
import org.cas.infra.MessageProcessorFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoOpMessageProcessorFinder implements MessageProcessorFinder {
	private static final Logger LOGGER = LoggerFactory.getLogger(NoOpMessageProcessorFinder.class);
	private static final MessageProcessor DO_NOTHING_MESSAGE_PROCESSOR;

	static {
		DO_NOTHING_MESSAGE_PROCESSOR = new MessageProcessor() {
			@Override
			public String id() {
				return "NOP Processor";
			}

			@Override
			public void process(Object message, BaseActor messageOwner) {
				LOGGER.info("Do nothing processor called for message with hashcode {}", message.hashCode());
			}
		};
	}

	public static MessageProcessor getDoNothingMessageProcessor() {
		return DO_NOTHING_MESSAGE_PROCESSOR;
	}

	@Override
	public MessageProcessor find(Object message) {
		return DO_NOTHING_MESSAGE_PROCESSOR;
	}
}
