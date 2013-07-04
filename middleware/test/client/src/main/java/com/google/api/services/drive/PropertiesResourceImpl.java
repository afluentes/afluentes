package com.google.api.services.drive;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class PropertiesResourceImpl implements IPropertiesResource {
	private final Client client;
	private final String uri;

	public PropertiesResourceImpl(final Client client, final String uri) {
		this.client = client;
		this.uri = uri;
	}

	@Override
	public void delete(final String fileId, final String propertyKey,
			final String visibility) {
		WebTarget target = client.target(uri);
		target = target.path("{fileId}/properties/{propertyKey}");

		target = target.path(fileId);
		target = target.path(propertyKey);

		target = target.queryParam("visibility", visibility);
		Builder request = target.request();

		Invocation invocation = request.build("DELETE");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
	}

	@Override
	public Property get(final String fileId, final String propertyKey,
			final String visibility) {
		WebTarget target = client.target(uri);
		target = target.path("{fileId}/properties/{propertyKey}");

		target = target.path(fileId);
		target = target.path(propertyKey);

		target = target.queryParam("visibility", visibility);
		Builder request = target.request();

		Invocation invocation = request.build("GET");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		Property responseEntity = response.readEntity(Property.class);
		return responseEntity;
	}

	@Override
	public Property insert(final String fileId, final Property requestEntity) {
		WebTarget target = client.target(uri);
		target = target.path("{fileId}/properties");

		target = target.path(fileId);

		Builder request = target.request();

		Invocation invocation = request.build("POST",
				Entity.json(requestEntity));
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		Property responseEntity = response.readEntity(Property.class);
		return responseEntity;
	}

	@Override
	public PropertyList list(final String fileId) {
		WebTarget target = client.target(uri);
		target = target.path("{fileId}/properties");

		target = target.path(fileId);

		Builder request = target.request();

		Invocation invocation = request.build("GET");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		PropertyList responseEntity = response.readEntity(PropertyList.class);
		return responseEntity;
	}

	@Override
	public Property patch(final String fileId, final String propertyKey,
			final String visibility, final Property requestEntity) {
		WebTarget target = client.target(uri);
		target = target.path("{fileId}/properties/{propertyKey}");

		target = target.path(fileId);
		target = target.path(propertyKey);

		target = target.queryParam("visibility", visibility);
		Builder request = target.request();

		Invocation invocation = request.build("PATCH",
				Entity.json(requestEntity));
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		Property responseEntity = response.readEntity(Property.class);
		return responseEntity;
	}

	@Override
	public Property update(final String fileId, final String propertyKey,
			final String visibility, final Property requestEntity) {
		WebTarget target = client.target(uri);
		target = target.path("{fileId}/properties/{propertyKey}");

		target = target.path(fileId);
		target = target.path(propertyKey);

		target = target.queryParam("visibility", visibility);
		Builder request = target.request();

		Invocation invocation = request
				.build("PUT", Entity.json(requestEntity));
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		Property responseEntity = response.readEntity(Property.class);
		return responseEntity;
	}

}