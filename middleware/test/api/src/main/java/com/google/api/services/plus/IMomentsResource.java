package com.google.api.services.plus;

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
public interface IMomentsResource {
	/**
	 * Record a moment representing a user's activity such as making a purchase
	 * or commenting on a blog.
	 * 
	 * @param collection
	 *            The collection to which to write moments.
	 * @param debug
	 *            Return the moment as written. Should be used only for
	 *            debugging.
	 * @param userId
	 *            The ID of the user to record activities for. The only valid
	 *            values are "me" and the ID of the authenticated user.
	 */
	@POST
	@Path("{userId}/moments/{collection}")
	Moment insert(@PathParam("collection") String collection,
			@QueryParam("debug") boolean debug,
			@PathParam("userId") String userId, Moment requestEntity);

	/**
	 * List all of the moments for a particular user.
	 * 
	 * @param collection
	 *            The collection of moments to list.
	 * @param maxResults
	 *            The maximum number of moments to include in the response,
	 *            which is used for paging. For any response, the actual number
	 *            returned might be less than the specified maxResults.
	 * @param pageToken
	 *            The continuation token, which is used to page through large
	 *            result sets. To get the next page of results, set this
	 *            parameter to the value of "nextPageToken" from the previous
	 *            response.
	 * @param targetUrl
	 *            Only moments containing this targetUrl will be returned.
	 * @param type
	 *            Only moments of this type will be returned.
	 * @param userId
	 *            The ID of the user to get moments for. The special value "me"
	 *            can be used to indicate the authenticated user.
	 */
	@GET
	@Path("{userId}/moments/{collection}")
	MomentsFeed list(@PathParam("collection") String collection,
			@QueryParam("maxResults") int maxResults,
			@QueryParam("pageToken") String pageToken,
			@QueryParam("targetUrl") String targetUrl,
			@QueryParam("type") String type, @PathParam("userId") String userId);

	/**
	 * Delete a moment.
	 * 
	 * @param id
	 *            The ID of the moment to delete.
	 */
	@DELETE
	@Path("{id}")
	void remove(@PathParam("id") String id);

}
