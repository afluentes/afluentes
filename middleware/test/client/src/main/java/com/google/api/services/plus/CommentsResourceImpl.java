package com.google.api.services.plus;

import javax.ws.rs.client.Client;
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
	public Comment get(final String commentId) {
		WebTarget target = client.target(uri);
		target = target.path("{commentId}");

		target = target.path(commentId);

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
	public CommentFeed list(final String activityId, final int maxResults,
			final String pageToken, final String sortOrder) {
		WebTarget target = client.target(uri);
		target = target.path("{activityId}/comments");

		target = target.path(activityId);

		target = target.queryParam("maxResults", maxResults);
		target = target.queryParam("pageToken", pageToken);
		target = target.queryParam("sortOrder", sortOrder);
		Builder request = target.request();

		Invocation invocation = request.build("GET");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		CommentFeed responseEntity = response.readEntity(CommentFeed.class);
		return responseEntity;
	}

}