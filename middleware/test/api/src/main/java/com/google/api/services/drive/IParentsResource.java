package com.google.api.services.drive;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface IParentsResource {
	/**
	 * Removes a parent from a file.
	 * 
	 * @param fileId
	 *            The ID of the file.
	 * @param parentId
	 *            The ID of the parent.
	 */
	@DELETE
	@Path("{fileId}/parents/{parentId}")
	void delete(@PathParam("fileId") String fileId,
			@PathParam("parentId") String parentId);

	/**
	 * Gets a specific parent reference.
	 * 
	 * @param fileId
	 *            The ID of the file.
	 * @param parentId
	 *            The ID of the parent.
	 */
	@GET
	@Path("{fileId}/parents/{parentId}")
	ParentReference get(@PathParam("fileId") String fileId,
			@PathParam("parentId") String parentId);

	/**
	 * Adds a parent folder for a file.
	 * 
	 * @param fileId
	 *            The ID of the file.
	 */
	@POST
	@Path("{fileId}/parents")
	ParentReference insert(@PathParam("fileId") String fileId,
			ParentReference requestEntity);

	/**
	 * Lists a file's parents.
	 * 
	 * @param fileId
	 *            The ID of the file.
	 */
	@GET
	@Path("{fileId}/parents")
	ParentList list(@PathParam("fileId") String fileId);

}
