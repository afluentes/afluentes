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
public interface IRepliesResource {
	/**
	 * Deletes a reply.
	 * 
	 * @param commentId
	 *            The ID of the comment.
	 * @param fileId
	 *            The ID of the file.
	 * @param replyId
	 *            The ID of the reply.
	 */
	@DELETE
	@Path("{fileId}/comments/{commentId}/replies/{replyId}")
	void delete(@PathParam("commentId") String commentId,
			@PathParam("fileId") String fileId,
			@PathParam("replyId") String replyId);

	/**
	 * Gets a reply.
	 * 
	 * @param commentId
	 *            The ID of the comment.
	 * @param fileId
	 *            The ID of the file.
	 * @param includeDeleted
	 *            If set, this will succeed when retrieving a deleted reply.
	 * @param replyId
	 *            The ID of the reply.
	 */
	@GET
	@Path("{fileId}/comments/{commentId}/replies/{replyId}")
	CommentReply get(@PathParam("commentId") String commentId,
			@PathParam("fileId") String fileId,
			@QueryParam("includeDeleted") boolean includeDeleted,
			@PathParam("replyId") String replyId);

	/**
	 * Creates a new reply to the given comment.
	 * 
	 * @param commentId
	 *            The ID of the comment.
	 * @param fileId
	 *            The ID of the file.
	 */
	@POST
	@Path("{fileId}/comments/{commentId}/replies")
	CommentReply insert(@PathParam("commentId") String commentId,
			@PathParam("fileId") String fileId, CommentReply requestEntity);

	/**
	 * Lists all of the replies to a comment.
	 * 
	 * @param commentId
	 *            The ID of the comment.
	 * @param fileId
	 *            The ID of the file.
	 * @param includeDeleted
	 *            If set, all replies, including deleted replies (with content
	 *            stripped) will be returned.
	 * @param maxResults
	 *            The maximum number of replies to include in the response, used
	 *            for paging.
	 * @param pageToken
	 *            The continuation token, used to page through large result
	 *            sets. To get the next page of results, set this parameter to
	 *            the value of "nextPageToken" from the previous response.
	 */
	@GET
	@Path("{fileId}/comments/{commentId}/replies")
	CommentReplyList list(@PathParam("commentId") String commentId,
			@PathParam("fileId") String fileId,
			@QueryParam("includeDeleted") boolean includeDeleted,
			@QueryParam("maxResults") int maxResults,
			@QueryParam("pageToken") String pageToken);

	/**
	 * Updates an existing reply. This method supports patch semantics.
	 * 
	 * @param commentId
	 *            The ID of the comment.
	 * @param fileId
	 *            The ID of the file.
	 * @param replyId
	 *            The ID of the reply.
	 */
	@PATCH
	@Path("{fileId}/comments/{commentId}/replies/{replyId}")
	CommentReply patch(@PathParam("commentId") String commentId,
			@PathParam("fileId") String fileId,
			@PathParam("replyId") String replyId, CommentReply requestEntity);

	/**
	 * Updates an existing reply.
	 * 
	 * @param commentId
	 *            The ID of the comment.
	 * @param fileId
	 *            The ID of the file.
	 * @param replyId
	 *            The ID of the reply.
	 */
	@PUT
	@Path("{fileId}/comments/{commentId}/replies/{replyId}")
	CommentReply update(@PathParam("commentId") String commentId,
			@PathParam("fileId") String fileId,
			@PathParam("replyId") String replyId, CommentReply requestEntity);

}
