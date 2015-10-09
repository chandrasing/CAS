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
package org.cas.infra.implementations.request.handlers.example.steptwo.messageprocessors;

import org.cas.infra.AbstractMessageHandler;
import org.cas.infra.BaseActor;
import org.cas.infra.implementations.request.handlers.example.stepone.messages.StepTwoContext;

public class StepTwoContextProcessor extends AbstractMessageHandler<StepTwoContext, StepTwoContext, StepTwoContext> {
	public StepTwoContextProcessor() {
		super();
	}

	@Override
	public StepTwoContext handle(final StepTwoContext stepTwoContext, final BaseActor messageOwner) {
		return new StepTwoContext(stepTwoContext.getGuid());
	}

	@Override
	public StepTwoContext handle(final Throwable throwable, final StepTwoContext stepTwoContext, final BaseActor messageOwner) {
		System.out.println("Failed to process akka request with GUID:" + stepTwoContext.getGuid());
		return stepTwoContext;
	}

	@Override
	public String id() {
		return getClass().getSimpleName();
	}
}
