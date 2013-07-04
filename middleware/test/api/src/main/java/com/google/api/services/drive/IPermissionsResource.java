package com.google.api.services.drive;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import afluentes.middleware.ws.rs.PATCH;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface IPermissionsResource {
	/**
	 * Deletes a permission from a file.
	 * 
	 * @param fileId
	 *            The ID for the file.
	 * @param permissionId
	 *            The ID for the permission.
	 */
	@DELETE
	@Path("{fileId}/permissions/{permissionId}")
	void delete(@PathParam("fileId") String fileId,
			@PathParam("permissionId") String permissionId);

	/**
	 * Gets a permission by ID.
	 * 
	 * @param fileId
	 *            The ID for the file.
	 * @param permissionId
	 *            The ID for the permission.
	 */
	@GET
	@Path("{fileId}/permissions/{permissionId}")
	Permission get(@PathParam("fileId") String fileId,
			@PathParam("permissionId") String permissionId);

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
	@POST
	@Path("{fileId}/permissions")
	Permission insert(
			@QueryParam("emailMessage") String emailMessage,
			@PathParam("fileId") String fileId,
			@QueryParam("sendNotificationEmails") boolean sendNotificationEmails,
			Permission requestEntity);

	/**
	 * Lists a file's permissions.
	 * 
	 * @param fileId
	 *            The ID for the file.
	 */
	@GET
	@Path("{fileId}/permissions")
	PermissionList list(@PathParam("fileId") String fileId);

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
	@PATCH
	@Path("{fileId}/permissions/{permissionId}")
	Permission patch(@PathParam("fileId") String fileId,
			@PathParam("permissionId") String permissionId,
			@QueryParam("transferOwnership") boolean transferOwnership,
			Permission requestEntity);

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
	@PUT
	@Path("{fileId}/permissions/{permissionId}")
	Permission update(@PathParam("fileId") String fileId,
			@PathParam("permissionId") String permissionId,
			@QueryParam("transferOwnership") boolean transferOwnership,
			Permission requestEntity);

}
