package com.google.api.services.drive;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class ParentsResourceImpl implements IParentsResource {
	private final Client client;
	private final String uri;

	public ParentsResourceImpl(final Client client, final String uri) {
		this.client = client;
		this.uri = uri;
	}

	@Override
	public void delete(final String fileId, final String parentId) {
		WebTarget target = client.target(uri);
		target = target.path("{fileId}/parents/{parentId}");

		target = target.path(fileId);
		target = target.path(parentId);

		Builder request = target.request();

		Invocation invocation = request.build("DELETE");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
	}

	@Override
	public ParentReference get(final String fileId, final String parentId) {
		WebTarget target = client.target(uri);
		target = target.path("{fileId}/parents/{parentId}");

		target = target.path(fileId);
		target = target.path(parentId);

		Builder request = target.request();

		Invocation invocation = request.build("GET");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		ParentReference responseEntity = response
				.readEntity(ParentReference.class);
		return responseEntity;
	}

	@Override
	public ParentReference insert(final String fileId,
			final ParentReference requestEntity) {
		WebTarget target = client.target(uri);
		target = target.path("{fileId}/parents");

		target = target.path(fileId);

		Builder request = target.request();

		Invocation invocation = request.build("POST",
				Entity.json(requestEntity));
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		ParentReference responseEntity = response
				.readEntity(ParentReference.class);
		return responseEntity;
	}

	@Override
	public ParentList list(final String fileId) {
		WebTarget target = client.target(uri);
		target = target.path("{fileId}/parents");

		target = target.path(fileId);

		Builder request = target.request();

		Invocation invocation = request.build("GET");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		ParentList responseEntity = response.readEntity(ParentList.class);
		return responseEntity;
	}

}