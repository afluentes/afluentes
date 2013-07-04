package com.google.api.services.drive;

import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

@Path("files")
@Component
public class PermissionsResourceImpl implements IPermissionsResource {
	@Override
	public void delete(final String fileId, final String permissionId) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public Permission get(final String fileId, final String permissionId) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public Permission insert(final String emailMessage, final String fileId,
			final boolean sendNotificationEmails, final Permission requestEntity) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public PermissionList list(final String fileId) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public Permission patch(final String fileId, final String permissionId,
			final boolean transferOwnership, final Permission requestEntity) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public Permission update(final String fileId, final String permissionId,
			final boolean transferOwnership, final Permission requestEntity) {
		// TODO
		throw new UnsupportedOperationException();
	}
}