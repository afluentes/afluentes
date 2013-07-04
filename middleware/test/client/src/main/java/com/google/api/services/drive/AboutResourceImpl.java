package com.google.api.services.drive;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class AboutResourceImpl implements IAboutResource {
	private final Client client;
	private final String uri;

	public AboutResourceImpl(final Client client, final String uri) {
		this.client = client;
		this.uri = uri;
	}

	@Override
	public About get(final boolean includeSubscribed,
			final String maxChangeIdCount, final String startChangeId) {
		WebTarget target = client.target(uri);

		target = target.queryParam("includeSubscribed", includeSubscribed);
		target = target.queryParam("maxChangeIdCount", maxChangeIdCount);
		target = target.queryParam("startChangeId", startChangeId);
		Builder request = target.request();

		Invocation invocation = request.build("GET");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		About responseEntity = response.readEntity(About.class);
		return responseEntity;
	}

}