package com.google.api.services.drive;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class CommentsResourceImpl implements ICommentsResource {
	private final Client client;
	private final String uri;

	public CommentsResourceImpl(final Client client, final String uri) {
		this.client = client;
		this.uri = uri;
	}

	@Override
	public void delete(final String commentId, final String fileId) {
		WebTarget target = client.target(uri);
		target = target.path("{fileId}/comments/{commentId}");

		target = target.path(commentId);
		target = target.path(fileId);

		Builder request = target.request();

		Invocation invocation = request.build("DELETE");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
	}

	@Override
	public Comment get(final String commentId, final String fileId,
			final boolean includeDeleted) {
		WebTarget target = client.target(uri);
		target = target.path("{fileId}/comments/{commentId}");

		target = target.path(commentId);
		target = target.path(fileId);

		target = target.queryParam("includeDeleted", includeDeleted);
		Builder request = target.request();

		Invocation invocation = request.build("GET");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		Comment responseEntity = response.readEntity(Comment.class);
		return responseEntity;
	}

	@Override
	public Comment insert(final String fileId, final Comment requestEntity) {
		WebTarget target = client.target(uri);
		target = target.path("{fileId}/comments");

		target = target.path(fileId);

		Builder request = target.request();

		Invocation invocation = request.build("POST",
				Entity.json(requestEntity));
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		Comment responseEntity = response.readEntity(Comment.class);
		return responseEntity;
	}

	@Override
	public CommentList list(final String fileId, final boolean includeDeleted,
			final int maxResults, final String pageToken,
			final String updatedMin) {
		WebTarget target = client.target(uri);
		target = target.path("{fileId}/comments");

		target = target.path(fileId);

		target = target.queryParam("includeDeleted", includeDeleted);
		target = target.queryParam("maxResults", maxResults);
		target = target.queryParam("pageToken", pageToken);
		target = target.queryParam("updatedMin", updatedMin);
		Builder request = target.request();

		Invocation invocation = request.build("GET");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		CommentList responseEntity = response.readEntity(CommentList.class);
		return responseEntity;
	}

	@Override
	public Comment patch(final String commentId, final String fileId,
			final Comment requestEntity) {
		WebTarget target = client.target(uri);
		target = target.path("{fileId}/comments/{commentId}");

		target = target.path(commentId);
		target = target.path(fileId);

		Builder request = target.request();

		Invocation invocation = request.build("PATCH",
				Entity.json(requestEntity));
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		Comment responseEntity = response.readEntity(Comment.class);
		return responseEntity;
	}

	@Override
	public Comment update(final String commentId, final String fileId,
			final Comment requestEntity) {
		WebTarget target = client.target(uri);
		target = target.path("{fileId}/comments/{commentId}");

		target = target.path(commentId);
		target = target.path(fileId);

		Builder request = target.request();

		Invocation invocation = request
				.build("PUT", Entity.json(requestEntity));
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		Comment responseEntity = response.readEntity(Comment.class);
		return responseEntity;
	}

}