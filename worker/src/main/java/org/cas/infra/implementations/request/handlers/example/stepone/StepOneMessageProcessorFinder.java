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
package org.cas.infra.implementations.request.handlers.example.stepone;

import org.cas.infra.MessageProcessor;
import org.cas.infra.MessageProcessorFinder;
import org.cas.infra.implementations.request.handlers.example.messages.StepOneContext;
import org.cas.infra.implementations.request.handlers.example.stepone.messageprocessors.StepOneContextProcessor;

public class StepOneMessageProcessorFinder implements MessageProcessorFinder {
	private final StepOneContextProcessor stepOneContextProcessor;

	public StepOneMessageProcessorFinder(final StepOneContextProcessor stepOneContextProcessor) {
		this.stepOneContextProcessor = stepOneContextProcessor;
	}

	@Override
	public MessageProcessor find(final Object message) {
		if (message instanceof StepOneContext) {
			return stepOneContextProcessor;
		}

		return null;
	}
}
