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
package org.cas.infra.implementations.request.handlers.messages;

import org.cas.shared.objects.Identifiable;
import org.cas.shared.objects.akka.AkkaRequest;

public class EndpointContext implements Identifiable {
	private final AkkaRequest akkaRequest;

	public EndpointContext(
			final AkkaRequest akkaRequest) {
		this.akkaRequest = akkaRequest;
	}

	public String id() {
		return akkaRequest.getRequestGuid();
	}

	public AkkaRequest getAkkaRequest() {
		return akkaRequest;
	}
}
