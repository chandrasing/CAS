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

import org.cas.shared.objects.Identifiable;

public interface MessageProcessor<IN> extends Identifiable {
	/**
	 * No return is on purpose. Return value means some caller is
	 * waiting on it. By explicitly stating that there is no return,
	 * it is abundantly clear that it's async call.
	 *
	 * @param message      Actual message to be processed
	 * @param messageOwner Which actor sent the message?
	 */
	void process(final IN message, final BaseActor messageOwner);
}
