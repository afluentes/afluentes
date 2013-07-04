package com.google.api.services.drive;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class ChildrenResourceImpl implements IChildrenResource {
	private final Client client;
	private final String uri;

	public ChildrenResourceImpl(final Client client, final String uri) {
		this.client = client;
		this.uri = uri;
	}

	@Override
	public void delete(final String childId, final String folderId) {
		WebTarget target = client.target(uri);
		target = target.path("{folderId}/children/{childId}");

		target = target.path(childId);
		target = target.path(folderId);

		Builder request = target.request();

		Invocation invocation = request.build("DELETE");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
	}

	@Override
	public ChildReference get(final String childId, final String folderId) {
		WebTarget target = client.target(uri);
		target = target.path("{folderId}/children/{childId}");

		target = target.path(childId);
		target = target.path(folderId);

		Builder request = target.request();

		Invocation invocation = request.build("GET");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		ChildReference responseEntity = response
				.readEntity(ChildReference.class);
		return responseEntity;
	}

	@Override
	public ChildReference insert(final String folderId,
			final ChildReference requestEntity) {
		WebTarget target = client.target(uri);
		target = target.path("{folderId}/children");

		target = target.path(folderId);

		Builder request = target.request();

		Invocation invocation = request.build("POST",
				Entity.json(requestEntity));
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		ChildReference responseEntity = response
				.readEntity(ChildReference.class);
		return responseEntity;
	}

	@Override
	public ChildList list(final String folderId, final int maxResults,
			final String pageToken, final String q) {
		WebTarget target = client.target(uri);
		target = target.path("{folderId}/children");

		target = target.path(folderId);

		target = target.queryParam("maxResults", maxResults);
		target = target.queryParam("pageToken", pageToken);
		target = target.queryParam("q", q);
		Builder request = target.request();

		Invocation invocation = request.build("GET");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		ChildList responseEntity = response.readEntity(ChildList.class);
		return responseEntity;
	}

}