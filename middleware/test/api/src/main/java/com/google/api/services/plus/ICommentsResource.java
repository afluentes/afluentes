package com.google.api.services.plus;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ICommentsResource {
	/**
	 * Get a comment.
	 * 
	 * @param commentId
	 *            The ID of the comment to get.
	 */
	@GET
	@Path("{commentId}")
	Comment get(@PathParam("commentId") String commentId);

	/**
	 * List all of the comments for an activity.
	 * 
	 * @param activityId
	 *            The ID of the activity to get comments for.
	 * @param maxResults
	 *            The maximum number of comments to include in the response,
	 *            which is used for paging. For any response, the actual number
	 *            returned might be less than the specified maxResults.
	 * @param pageToken
	 *            The continuation token, which is used to page through large
	 *            result sets. To get the next page of results, set this
	 *            parameter to the value of "nextPageToken" from the previous
	 *            response.
	 * @param sortOrder
	 *            The order in which to sort the list of comments.
	 */
	@GET
	@Path("{activityId}/comments")
	CommentFeed list(@PathParam("activityId") String activityId,
			@QueryParam("maxResults") int maxResults,
			@QueryParam("pageToken") String pageToken,
			@QueryParam("sortOrder") String sortOrder);

}
