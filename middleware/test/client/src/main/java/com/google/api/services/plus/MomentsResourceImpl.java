package com.google.api.services.plus;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class MomentsResourceImpl implements IMomentsResource {
	private final Client client;
	private final String uri;

	public MomentsResourceImpl(final Client client, final String uri) {
		this.client = client;
		this.uri = uri;
	}

	@Override
	public Moment insert(final String collection, final boolean debug,
			final String userId, final Moment requestEntity) {
		WebTarget target = client.target(uri);
		target = target.path("{userId}/moments/{collection}");

		target = target.path(collection);
		target = target.path(userId);

		target = target.queryParam("debug", debug);
		Builder request = target.request();

		Invocation invocation = request.build("POST",
				Entity.json(requestEntity));
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		Moment responseEntity = response.readEntity(Moment.class);
		return responseEntity;
	}

	@Override
	public MomentsFeed list(final String collection, final int maxResults,
			final String pageToken, final String targetUrl, final String type,
			final String userId) {
		WebTarget target = client.target(uri);
		target = target.path("{userId}/moments/{collection}");

		target = target.path(collection);
		target = target.path(userId);

		target = target.queryParam("maxResults", maxResults);
		target = target.queryParam("pageToken", pageToken);
		target = target.queryParam("targetUrl", targetUrl);
		target = target.queryParam("type", type);
		Builder request = target.request();

		Invocation invocation = request.build("GET");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		MomentsFeed responseEntity = response.readEntity(MomentsFeed.class);
		return responseEntity;
	}

	@Override
	public void remove(final String id) {
		WebTarget target = client.target(uri);
		target = target.path("{id}");

		target = target.path(id);

		Builder request = target.request();

		Invocation invocation = request.build("DELETE");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
	}

}