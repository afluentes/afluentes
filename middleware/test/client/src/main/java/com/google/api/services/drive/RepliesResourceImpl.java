package com.google.api.services.drive;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class RepliesResourceImpl implements IRepliesResource {
	private final Client client;
	private final String uri;

	public RepliesResourceImpl(final Client client, final String uri) {
		this.client = client;
		this.uri = uri;
	}

	@Override
	public void delete(final String commentId, final String fileId,
			final String replyId) {
		WebTarget target = client.target(uri);
		target = target.path("{fileId}/comments/{commentId}/replies/{replyId}");

		target = target.path(commentId);
		target = target.path(fileId);
		target = target.path(replyId);

		Builder request = target.request();

		Invocation invocation = request.build("DELETE");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
	}

	@Override
	public CommentReply get(final String commentId, final String fileId,
			final boolean includeDeleted, final String replyId) {
		WebTarget target = client.target(uri);
		target = target.path("{fileId}/comments/{commentId}/replies/{replyId}");

		target = target.path(commentId);
		target = target.path(fileId);
		target = target.path(replyId);

		target = target.queryParam("includeDeleted", includeDeleted);
		Builder request = target.request();

		Invocation invocation = request.build("GET");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		CommentReply responseEntity = response.readEntity(CommentReply.class);
		return responseEntity;
	}

	@Override
	public CommentReply insert(final String commentId, final String fileId,
			final CommentReply requestEntity) {
		WebTarget target = client.target(uri);
		target = target.path("{fileId}/comments/{commentId}/replies");

		target = target.path(commentId);
		target = target.path(fileId);

		Builder request = target.request();

		Invocation invocation = request.build("POST",
				Entity.json(requestEntity));
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		CommentReply responseEntity = response.readEntity(CommentReply.class);
		return responseEntity;
	}

	@Override
	public CommentReplyList list(final String commentId, final String fileId,
			final boolean includeDeleted, final int maxResults,
			final String pageToken) {
		WebTarget target = client.target(uri);
		target = target.path("{fileId}/comments/{commentId}/replies");

		target = target.path(commentId);
		target = target.path(fileId);

		target = target.queryParam("includeDeleted", includeDeleted);
		target = target.queryParam("maxResults", maxResults);
		target = target.queryParam("pageToken", pageToken);
		Builder request = target.request();

		Invocation invocation = request.build("GET");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		CommentReplyList responseEntity = response
				.readEntity(CommentReplyList.class);
		return responseEntity;
	}

	@Override
	public CommentReply patch(final String commentId, final String fileId,
			final String replyId, final CommentReply requestEntity) {
		WebTarget target = client.target(uri);
		target = target.path("{fileId}/comments/{commentId}/replies/{replyId}");

		target = target.path(commentId);
		target = target.path(fileId);
		target = target.path(replyId);

		Builder request = target.request();

		Invocation invocation = request.build("PATCH",
				Entity.json(requestEntity));
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		CommentReply responseEntity = response.readEntity(CommentReply.class);
		return responseEntity;
	}

	@Override
	public CommentReply update(final String commentId, final String fileId,
			final String replyId, final CommentReply requestEntity) {
		WebTarget target = client.target(uri);
		target = target.path("{fileId}/comments/{commentId}/replies/{replyId}");

		target = target.path(commentId);
		target = target.path(fileId);
		target = target.path(replyId);

		Builder request = target.request();

		Invocation invocation = request
				.build("PUT", Entity.json(requestEntity));
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		CommentReply responseEntity = response.readEntity(CommentReply.class);
		return responseEntity;
	}

}