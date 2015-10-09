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
package org.cas.infra.implementations.request.handlers.example.stepone.messages;

import org.cas.shared.objects.Identifiable;

public class StepTwoContext implements Identifiable {

	private final String guid;

	public StepTwoContext(String guid) {
		this.guid = guid;
	}

	public String getGuid() {
		return guid;
	}

	@Override
	public String toString() {
		return "StepTwoContext{" +
				"guid='" + guid + '\'' +
				'}';
	}

	@Override
	public String id() {
		return StepTwoContext.class.getSimpleName();
	}
}
