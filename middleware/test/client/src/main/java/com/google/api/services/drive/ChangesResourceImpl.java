package com.google.api.services.drive;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class ChangesResourceImpl implements IChangesResource {
	private final Client client;
	private final String uri;

	public ChangesResourceImpl(final Client client, final String uri) {
		this.client = client;
		this.uri = uri;
	}

	@Override
	public Change get(final String changeId) {
		WebTarget target = client.target(uri);
		target = target.path("{changeId}");

		target = target.path(changeId);

		Builder request = target.request();

		Invocation invocation = request.build("GET");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		Change responseEntity = response.readEntity(Change.class);
		return responseEntity;
	}

	@Override
	public ChangeList list(final boolean includeDeleted,
			final boolean includeSubscribed, final int maxResults,
			final String pageToken, final String startChangeId) {
		WebTarget target = client.target(uri);

		target = target.queryParam("includeDeleted", includeDeleted);
		target = target.queryParam("includeSubscribed", includeSubscribed);
		target = target.queryParam("maxResults", maxResults);
		target = target.queryParam("pageToken", pageToken);
		target = target.queryParam("startChangeId", startChangeId);
		Builder request = target.request();

		Invocation invocation = request.build("GET");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		ChangeList responseEntity = response.readEntity(ChangeList.class);
		return responseEntity;
	}

}