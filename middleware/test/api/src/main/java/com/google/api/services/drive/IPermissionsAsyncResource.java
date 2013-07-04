package com.google.api.services.drive;

import afluentes.core.api.IEvaluation;

public interface IPermissionsAsyncResource {
	/**
	 * Deletes a permission from a file.
	 * 
	 * @param fileId
	 *            The ID for the file.
	 * @param permissionId
	 *            The ID for the permission.
	 */
	IEvaluation<Void> delete(String fileId, String permissionId);

	IEvaluation<Void> delete(IEvaluation<String> fileId,
			IEvaluation<String> permissionId);

	/**
	 * Gets a permission by ID.
	 * 
	 * @param fileId
	 *            The ID for the file.
	 * @param permissionId
	 *            The ID for the permission.
	 */
	IEvaluation<Permission> get(String fileId, String permissionId);

	IEvaluation<Permission> get(IEvaluation<String> fileId,
			IEvaluation<String> permissionId);

	/**
	 * Inserts a permission for a file.
	 * 
	 * @param emailMessage
	 *            A custom message to include in notification emails.
	 * @param fileId
	 *            The ID for the file.
	 * @param sendNotificationEmails
	 *            Whether to send notification emails when sharing to users or
	 *            groups.
	 */
	IEvaluation<Permission> insert(String emailMessage, String fileId,
			Boolean sendNotificationEmails, Permission requestEntity);

	IEvaluation<Permission> insert(IEvaluation<String> emailMessage,
			IEvaluation<String> fileId,
			IEvaluation<Boolean> sendNotificationEmails,
			IEvaluation<Permission> requestEntity);

	/**
	 * Lists a file's permissions.
	 * 
	 * @param fileId
	 *            The ID for the file.
	 */
	IEvaluation<PermissionList> list(String fileId);

	IEvaluation<PermissionList> list(IEvaluation<String> fileId);

	/**
	 * Updates a permission. This method supports patch semantics.
	 * 
	 * @param fileId
	 *            The ID for the file.
	 * @param permissionId
	 *            The ID for the permission.
	 * @param transferOwnership
	 *            Whether changing a role to 'owner' should also downgrade the
	 *            current owners to writers.
	 */
	IEvaluation<Permission> patch(String fileId, String permissionId,
			Boolean transferOwnership, Permission requestEntity);

	IEvaluation<Permission> patch(IEvaluation<String> fileId,
			IEvaluation<String> permissionId,
			IEvaluation<Boolean> transferOwnership,
			IEvaluation<Permission> requestEntity);

	/**
	 * Updates a permission.
	 * 
	 * @param fileId
	 *            The ID for the file.
	 * @param permissionId
	 *            The ID for the permission.
	 * @param transferOwnership
	 *            Whether changing a role to 'owner' should also downgrade the
	 *            current owners to writers.
	 */
	IEvaluation<Permission> update(String fileId, String permissionId,
			Boolean transferOwnership, Permission requestEntity);

	IEvaluation<Permission> update(IEvaluation<String> fileId,
			IEvaluation<String> permissionId,
			IEvaluation<Boolean> transferOwnership,
			IEvaluation<Permission> requestEntity);

}
