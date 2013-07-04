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
public interface IActivitiesResource {
	/**
	 * Get an activity.
	 * 
	 * @param activityId
	 *            The ID of the activity to get.
	 */
	@GET
	@Path("{activityId}")
	Activity get(@PathParam("activityId") String activityId);

	/**
	 * List all of the activities in the specified collection for a particular
	 * user.
	 * 
	 * @param collection
	 *            The collection of activities to list.
	 * @param maxResults
	 *            The maximum number of activities to include in the response,
	 *            which is used for paging. For any response, the actual number
	 *            returned might be less than the specified maxResults.
	 * @param pageToken
	 *            The continuation token, which is used to page through large
	 *            result sets. To get the next page of results, set this
	 *            parameter to the value of "nextPageToken" from the previous
	 *            response.
	 * @param userId
	 *            The ID of the user to get activities for. The special value
	 *            "me" can be used to indicate the authenticated user.
	 */
	@GET
	@Path("{userId}/activities/{collection}")
	ActivityFeed list(@PathParam("collection") String collection,
			@QueryParam("maxResults") int maxResults,
			@QueryParam("pageToken") String pageToken,
			@PathParam("userId") String userId);

	/**
	 * Search public activities.
	 * 
	 * @param language
	 *            Specify the preferred language to search with. See search
	 *            language codes for available values.
	 * @param maxResults
	 *            The maximum number of activities to include in the response,
	 *            which is used for paging. For any response, the actual number
	 *            returned might be less than the specified maxResults.
	 * @param orderBy
	 *            Specifies how to order search results.
	 * @param pageToken
	 *            The continuation token, which is used to page through large
	 *            result sets. To get the next page of results, set this
	 *            parameter to the value of "nextPageToken" from the previous
	 *            response. This token can be of any length.
	 * @param query
	 *            Full-text search query string.
	 */
	@GET
	ActivityFeed search(@QueryParam("language") String language,
			@QueryParam("maxResults") int maxResults,
			@QueryParam("orderBy") String orderBy,
			@QueryParam("pageToken") String pageToken,
			@QueryParam("query") String query);

}
