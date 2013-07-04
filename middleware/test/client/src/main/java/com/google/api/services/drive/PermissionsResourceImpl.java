package com.google.api.services.drive;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class PermissionsResourceImpl implements IPermissionsResource {
	private final Client client;
	private final String uri;

	public PermissionsResourceImpl(final Client client, final String uri) {
		this.client = client;
		this.uri = uri;
	}

	@Override
	public void delete(final String fileId, final String permissionId) {
		WebTarget target = client.target(uri);
		target = target.path("{fileId}/permissions/{permissionId}");

		target = target.path(fileId);
		target = target.path(permissionId);

		Builder request = target.request();

		Invocation invocation = request.build("DELETE");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
	}

	@Override
	public Permission get(final String fileId, final String permissionId) {
		WebTarget target = client.target(uri);
		target = target.path("{fileId}/permissions/{permissionId}");

		target = target.path(fileId);
		target = target.path(permissionId);

		Builder request = target.request();

		Invocation invocation = request.build("GET");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		Permission responseEntity = response.readEntity(Permission.class);
		return responseEntity;
	}

	@Override
	public Permission insert(final String emailMessage, final String fileId,
			final boolean sendNotificationEmails, final Permission requestEntity) {
		WebTarget target = client.target(uri);
		target = target.path("{fileId}/permissions");

		target = target.path(fileId);

		target = target.queryParam("emailMessage", emailMessage);
		target = target.queryParam("sendNotificationEmails",
				sendNotificationEmails);
		Builder request = target.request();

		Invocation invocation = request.build("POST",
				Entity.json(requestEntity));
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		Permission responseEntity = response.readEntity(Permission.class);
		return responseEntity;
	}

	@Override
	public PermissionList list(final String fileId) {
		WebTarget target = client.target(uri);
		target = target.path("{fileId}/permissions");

		target = target.path(fileId);

		Builder request = target.request();

		Invocation invocation = request.build("GET");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		PermissionList responseEntity = response
				.readEntity(PermissionList.class);
		return responseEntity;
	}

	@Override
	public Permission patch(final String fileId, final String permissionId,
			final boolean transferOwnership, final Permission requestEntity) {
		WebTarget target = client.target(uri);
		target = target.path("{fileId}/permissions/{permissionId}");

		target = target.path(fileId);
		target = target.path(permissionId);

		target = target.queryParam("transferOwnership", transferOwnership);
		Builder request = target.request();

		Invocation invocation = request.build("PATCH",
				Entity.json(requestEntity));
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		Permission responseEntity = response.readEntity(Permission.class);
		return responseEntity;
	}

	@Override
	public Permission update(final String fileId, final String permissionId,
			final boolean transferOwnership, final Permission requestEntity) {
		WebTarget target = client.target(uri);
		target = target.path("{fileId}/permissions/{permissionId}");

		target = target.path(fileId);
		target = target.path(permissionId);

		target = target.queryParam("transferOwnership", transferOwnership);
		Builder request = target.request();

		Invocation invocation = request
				.build("PUT", Entity.json(requestEntity));
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		Permission responseEntity = response.readEntity(Permission.class);
		return responseEntity;
	}

}