package com.google.api.services.drive;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import afluentes.middleware.ws.rs.PATCH;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface IRevisionsResource {
	/**
	 * Removes a revision.
	 * 
	 * @param fileId
	 *            The ID of the file.
	 * @param revisionId
	 *            The ID of the revision.
	 */
	@DELETE
	@Path("{fileId}/revisions/{revisionId}")
	void delete(@PathParam("fileId") String fileId,
			@PathParam("revisionId") String revisionId);

	/**
	 * Gets a specific revision.
	 * 
	 * @param fileId
	 *            The ID of the file.
	 * @param revisionId
	 *            The ID of the revision.
	 */
	@GET
	@Path("{fileId}/revisions/{revisionId}")
	Revision get(@PathParam("fileId") String fileId,
			@PathParam("revisionId") String revisionId);

	/**
	 * Lists a file's revisions.
	 * 
	 * @param fileId
	 *            The ID of the file.
	 */
	@GET
	@Path("{fileId}/revisions")
	RevisionList list(@PathParam("fileId") String fileId);

	/**
	 * Updates a revision. This method supports patch semantics.
	 * 
	 * @param fileId
	 *            The ID for the file.
	 * @param revisionId
	 *            The ID for the revision.
	 */
	@PATCH
	@Path("{fileId}/revisions/{revisionId}")
	Revision patch(@PathParam("fileId") String fileId,
			@PathParam("revisionId") String revisionId, Revision requestEntity);

	/**
	 * Updates a revision.
	 * 
	 * @param fileId
	 *            The ID for the file.
	 * @param revisionId
	 *            The ID for the revision.
	 */
	@PUT
	@Path("{fileId}/revisions/{revisionId}")
	Revision update(@PathParam("fileId") String fileId,
			@PathParam("revisionId") String revisionId, Revision requestEntity);

}
