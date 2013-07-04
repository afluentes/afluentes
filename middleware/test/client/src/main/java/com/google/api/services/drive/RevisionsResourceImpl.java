package com.google.api.services.drive;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class RevisionsResourceImpl implements IRevisionsResource {
	private final Client client;
	private final String uri;

	public RevisionsResourceImpl(final Client client, final String uri) {
		this.client = client;
		this.uri = uri;
	}

	@Override
	public void delete(final String fileId, final String revisionId) {
		WebTarget target = client.target(uri);
		target = target.path("{fileId}/revisions/{revisionId}");

		target = target.path(fileId);
		target = target.path(revisionId);

		Builder request = target.request();

		Invocation invocation = request.build("DELETE");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
	}

	@Override
	public Revision get(final String fileId, final String revisionId) {
		WebTarget target = client.target(uri);
		target = target.path("{fileId}/revisions/{revisionId}");

		target = target.path(fileId);
		target = target.path(revisionId);

		Builder request = target.request();

		Invocation invocation = request.build("GET");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		Revision responseEntity = response.readEntity(Revision.class);
		return responseEntity;
	}

	@Override
	public RevisionList list(final String fileId) {
		WebTarget target = client.target(uri);
		target = target.path("{fileId}/revisions");

		target = target.path(fileId);

		Builder request = target.request();

		Invocation invocation = request.build("GET");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		RevisionList responseEntity = response.readEntity(RevisionList.class);
		return responseEntity;
	}

	@Override
	public Revision patch(final String fileId, final String revisionId,
			final Revision requestEntity) {
		WebTarget target = client.target(uri);
		target = target.path("{fileId}/revisions/{revisionId}");

		target = target.path(fileId);
		target = target.path(revisionId);

		Builder request = target.request();

		Invocation invocation = request.build("PATCH",
				Entity.json(requestEntity));
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		Revision responseEntity = response.readEntity(Revision.class);
		return responseEntity;
	}

	@Override
	public Revision update(final String fileId, final String revisionId,
			final Revision requestEntity) {
		WebTarget target = client.target(uri);
		target = target.path("{fileId}/revisions/{revisionId}");

		target = target.path(fileId);
		target = target.path(revisionId);

		Builder request = target.request();

		Invocation invocation = request
				.build("PUT", Entity.json(requestEntity));
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		Revision responseEntity = response.readEntity(Revision.class);
		return responseEntity;
	}

}