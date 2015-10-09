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
import org.cas.infra.services.requesthandler.RequestHandlerMeta;
import org.cas.shared.objects.ApiRequest;

public class ValidationContext implements Identifiable {
	private final EndpointContext endpointContext;
	private final RequestHandlerMeta requestHandlerMeta;
	private final ApiRequest apiRequest;

	public ValidationContext(
			final EndpointContext endpointContext,
			final RequestHandlerMeta requestHandlerMeta,
			final ApiRequest apiRequest) {
		this.endpointContext = endpointContext;
		this.requestHandlerMeta = requestHandlerMeta;
		this.apiRequest = apiRequest;
	}

	@Override
	public String id() {
		return endpointContext.getAkkaRequest().getRequestGuid();
	}

	public EndpointContext getEndpointContext() {
		return endpointContext;
	}

	public RequestHandlerMeta getRequestHandlerMeta() {
		return requestHandlerMeta;
	}

	public ApiRequest getApiRequest() {
		return apiRequest;
	}
}
