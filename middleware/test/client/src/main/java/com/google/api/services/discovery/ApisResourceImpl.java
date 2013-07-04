package com.google.api.services.discovery;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class ApisResourceImpl implements IApisResource {
	private final Client client;
	private final String uri;

	public ApisResourceImpl(final Client client, final String uri) {
		this.client = client;
		this.uri = uri;
	}

	@Override
	public RestDescription getRest(final String api, final String version) {
		WebTarget target = client.target(uri);
		target = target.path("{api}/{version}/rest");

		target = target.path(api);
		target = target.path(version);

		Builder request = target.request();

		Invocation invocation = request.build("GET");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		RestDescription responseEntity = response
				.readEntity(RestDescription.class);
		return responseEntity;
	}

	@Override
	public DirectoryList list(final String name, final boolean preferred) {
		WebTarget target = client.target(uri);

		target = target.queryParam("name", name);
		target = target.queryParam("preferred", preferred);
		Builder request = target.request();

		Invocation invocation = request.build("GET");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		DirectoryList responseEntity = response.readEntity(DirectoryList.class);
		return responseEntity;
	}

}