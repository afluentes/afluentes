package com.google.api.services.drive;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class AppsResourceImpl implements IAppsResource {
	private final Client client;
	private final String uri;

	public AppsResourceImpl(final Client client, final String uri) {
		this.client = client;
		this.uri = uri;
	}

	@Override
	public App get(final String appId) {
		WebTarget target = client.target(uri);
		target = target.path("{appId}");

		target = target.path(appId);

		Builder request = target.request();

		Invocation invocation = request.build("GET");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		App responseEntity = response.readEntity(App.class);
		return responseEntity;
	}

	@Override
	public AppList list() {
		WebTarget target = client.target(uri);

		Builder request = target.request();

		Invocation invocation = request.build("GET");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		AppList responseEntity = response.readEntity(AppList.class);
		return responseEntity;
	}

}