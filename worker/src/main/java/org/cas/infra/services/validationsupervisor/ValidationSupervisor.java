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
package org.cas.infra.services.validationsupervisor;

import org.cas.infra.BaseActor;
import org.cas.infra.MessageProcessorFinderBuilder;
import org.cas.infra.services.requesthandler.RequestHandlerMeta;

abstract public class ValidationSupervisor extends BaseActor {
	private final RequestHandlerMeta requestHandlerMeta;

	public ValidationSupervisor(
			final String name,
			final MessageProcessorFinderBuilder messageProcessorFinderBuilder,
			final RequestHandlerMeta requestHandlerMeta) {
		super(name, messageProcessorFinderBuilder);
		this.requestHandlerMeta = requestHandlerMeta;
	}

}
