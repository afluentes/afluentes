package com.google.api.services.drive;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface IChildrenResource {
	/**
	 * Removes a child from a folder.
	 * 
	 * @param childId
	 *            The ID of the child.
	 * @param folderId
	 *            The ID of the folder.
	 */
	@DELETE
	@Path("{folderId}/children/{childId}")
	void delete(@PathParam("childId") String childId,
			@PathParam("folderId") String folderId);

	/**
	 * Gets a specific child reference.
	 * 
	 * @param childId
	 *            The ID of the child.
	 * @param folderId
	 *            The ID of the folder.
	 */
	@GET
	@Path("{folderId}/children/{childId}")
	ChildReference get(@PathParam("childId") String childId,
			@PathParam("folderId") String folderId);

	/**
	 * Inserts a file into a folder.
	 * 
	 * @param folderId
	 *            The ID of the folder.
	 */
	@POST
	@Path("{folderId}/children")
	ChildReference insert(@PathParam("folderId") String folderId,
			ChildReference requestEntity);

	/**
	 * Lists a folder's children.
	 * 
	 * @param folderId
	 *            The ID of the folder.
	 * @param maxResults
	 *            Maximum number of children to return.
	 * @param pageToken
	 *            Page token for children.
	 * @param q
	 *            Query string for searching children.
	 */
	@GET
	@Path("{folderId}/children")
	ChildList list(@PathParam("folderId") String folderId,
			@QueryParam("maxResults") int maxResults,
			@QueryParam("pageToken") String pageToken, @QueryParam("q") String q);

}
