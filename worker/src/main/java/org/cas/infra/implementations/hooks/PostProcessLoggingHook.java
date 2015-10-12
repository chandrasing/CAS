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
package org.cas.infra.implementations.hooks;

import org.cas.infra.BaseActor;
import org.cas.infra.Hook;
import org.cas.shared.objects.Identifiable;
import org.cas.infra.MessageProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostProcessLoggingHook implements Hook {
	private static final Logger LOGGER = LoggerFactory.getLogger(PostProcessLoggingHook.class);

	private static final String POST_PROCESS_MESSAGE_LOG_FORMAT =
			"Actor({})#{} named {} done with processing of message({}) having {} as {} at {} using {} message processor";

	@Override
	public String id() {
		return "Post-process logging hook";
	}

	@Override
	public void invoke(
			final BaseActor invoker,
			final MessageProcessor messageProcessor,
			final Object message) throws RuntimeException {

		LOGGER.info(
				POST_PROCESS_MESSAGE_LOG_FORMAT,
				invoker.self().path(),
				invoker.hashCode(),
				invoker.getName(),
				message.getClass().getCanonicalName(),
				message instanceof Identifiable ? "id" : "hashcode",
				message instanceof Identifiable ? ((Identifiable) message).id() : message.hashCode(),
				System.nanoTime(),
				messageProcessor.id());
	}
}
