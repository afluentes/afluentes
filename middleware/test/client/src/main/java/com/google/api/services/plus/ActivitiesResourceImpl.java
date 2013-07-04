package com.google.api.services.plus;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class ActivitiesResourceImpl implements IActivitiesResource {
	private final Client client;
	private final String uri;

	public ActivitiesResourceImpl(final Client client, final String uri) {
		this.client = client;
		this.uri = uri;
	}

	@Override
	public Activity get(final String activityId) {
		WebTarget target = client.target(uri);
		target = target.path("{activityId}");

		target = target.path(activityId);

		Builder request = target.request();

		Invocation invocation = request.build("GET");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		Activity responseEntity = response.readEntity(Activity.class);
		return responseEntity;
	}

	@Override
	public ActivityFeed list(final String collection, final int maxResults,
			final String pageToken, final String userId) {
		WebTarget target = client.target(uri);
		target = target.path("{userId}/activities/{collection}");

		target = target.path(collection);
		target = target.path(userId);

		target = target.queryParam("maxResults", maxResults);
		target = target.queryParam("pageToken", pageToken);
		Builder request = target.request();

		Invocation invocation = request.build("GET");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		ActivityFeed responseEntity = response.readEntity(ActivityFeed.class);
		return responseEntity;
	}

	@Override
	public ActivityFeed search(final String language, final int maxResults,
			final String orderBy, final String pageToken, final String query) {
		WebTarget target = client.target(uri);

		target = target.queryParam("language", language);
		target = target.queryParam("maxResults", maxResults);
		target = target.queryParam("orderBy", orderBy);
		target = target.queryParam("pageToken", pageToken);
		target = target.queryParam("query", query);
		Builder request = target.request();

		Invocation invocation = request.build("GET");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		ActivityFeed responseEntity = response.readEntity(ActivityFeed.class);
		return responseEntity;
	}

}