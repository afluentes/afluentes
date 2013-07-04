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
public interface ICommentsResource {
	/**
	 * Deletes a comment.
	 * 
	 * @param commentId
	 *            The ID of the comment.
	 * @param fileId
	 *            The ID of the file.
	 */
	@DELETE
	@Path("{fileId}/comments/{commentId}")
	void delete(@PathParam("commentId") String commentId,
			@PathParam("fileId") String fileId);

	/**
	 * Gets a comment by ID.
	 * 
	 * @param commentId
	 *            The ID of the comment.
	 * @param fileId
	 *            The ID of the file.
	 * @param includeDeleted
	 *            If set, this will succeed when retrieving a deleted comment,
	 *            and will include any deleted replies.
	 */
	@GET
	@Path("{fileId}/comments/{commentId}")
	Comment get(@PathParam("commentId") String commentId,
			@PathParam("fileId") String fileId,
			@QueryParam("includeDeleted") boolean includeDeleted);

	/**
	 * Creates a new comment on the given file.
	 * 
	 * @param fileId
	 *            The ID of the file.
	 */
	@POST
	@Path("{fileId}/comments")
	Comment insert(@PathParam("fileId") String fileId, Comment requestEntity);

	/**
	 * Lists a file's comments.
	 * 
	 * @param fileId
	 *            The ID of the file.
	 * @param includeDeleted
	 *            If set, all comments and replies, including deleted comments
	 *            and replies (with content stripped) will be returned.
	 * @param maxResults
	 *            The maximum number of discussions to include in the response,
	 *            used for paging.
	 * @param pageToken
	 *            The continuation token, used to page through large result
	 *            sets. To get the next page of results, set this parameter to
	 *            the value of "nextPageToken" from the previous response.
	 * @param updatedMin
	 *            Only discussions that were updated after this timestamp will
	 *            be returned. Formatted as an RFC 3339 timestamp.
	 */
	@GET
	@Path("{fileId}/comments")
	CommentList list(@PathParam("fileId") String fileId,
			@QueryParam("includeDeleted") boolean includeDeleted,
			@QueryParam("maxResults") int maxResults,
			@QueryParam("pageToken") String pageToken,
			@QueryParam("updatedMin") String updatedMin);

	/**
	 * Updates an existing comment. This method supports patch semantics.
	 * 
	 * @param commentId
	 *            The ID of the comment.
	 * @param fileId
	 *            The ID of the file.
	 */
	@PATCH
	@Path("{fileId}/comments/{commentId}")
	Comment patch(@PathParam("commentId") String commentId,
			@PathParam("fileId") String fileId, Comment requestEntity);

	/**
	 * Updates an existing comment.
	 * 
	 * @param commentId
	 *            The ID of the comment.
	 * @param fileId
	 *            The ID of the file.
	 */
	@PUT
	@Path("{fileId}/comments/{commentId}")
	Comment update(@PathParam("commentId") String commentId,
			@PathParam("fileId") String fileId, Comment requestEntity);

}
