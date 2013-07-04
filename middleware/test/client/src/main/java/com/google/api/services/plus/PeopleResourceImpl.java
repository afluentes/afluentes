package com.google.api.services.plus;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class PeopleResourceImpl implements IPeopleResource {
	private final Client client;
	private final String uri;

	public PeopleResourceImpl(final Client client, final String uri) {
		this.client = client;
		this.uri = uri;
	}

	@Override
	public Person get(final String userId) {
		WebTarget target = client.target(uri);
		target = target.path("{userId}");

		target = target.path(userId);

		Builder request = target.request();

		Invocation invocation = request.build("GET");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		Person responseEntity = response.readEntity(Person.class);
		return responseEntity;
	}

	@Override
	public PeopleFeed list(final String collection, final int maxResults,
			final String orderBy, final String pageToken, final String userId) {
		WebTarget target = client.target(uri);
		target = target.path("{userId}/people/{collection}");

		target = target.path(collection);
		target = target.path(userId);

		target = target.queryParam("maxResults", maxResults);
		target = target.queryParam("orderBy", orderBy);
		target = target.queryParam("pageToken", pageToken);
		Builder request = target.request();

		Invocation invocation = request.build("GET");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		PeopleFeed responseEntity = response.readEntity(PeopleFeed.class);
		return responseEntity;
	}

	@Override
	public PeopleFeed listByActivity(final String activityId,
			final String collection, final int maxResults,
			final String pageToken) {
		WebTarget target = client.target(uri);
		target = target.path("{activityId}/people/{collection}");

		target = target.path(activityId);
		target = target.path(collection);

		target = target.queryParam("maxResults", maxResults);
		target = target.queryParam("pageToken", pageToken);
		Builder request = target.request();

		Invocation invocation = request.build("GET");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		PeopleFeed responseEntity = response.readEntity(PeopleFeed.class);
		return responseEntity;
	}

	@Override
	public PeopleFeed search(final String language, final int maxResults,
			final String pageToken, final String query) {
		WebTarget target = client.target(uri);

		target = target.queryParam("language", language);
		target = target.queryParam("maxResults", maxResults);
		target = target.queryParam("pageToken", pageToken);
		target = target.queryParam("query", query);
		Builder request = target.request();

		Invocation invocation = request.build("GET");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		PeopleFeed responseEntity = response.readEntity(PeopleFeed.class);
		return responseEntity;
	}

}