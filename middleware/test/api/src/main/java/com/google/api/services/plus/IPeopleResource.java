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
public interface IPeopleResource {
	/**
	 * Get a person's profile. If your app uses scope
	 * https://www.googleapis.com/auth/plus.login, this method is guaranteed to
	 * return ageRange and language.
	 * 
	 * @param userId
	 *            The ID of the person to get the profile for. The special value
	 *            "me" can be used to indicate the authenticated user.
	 */
	@GET
	@Path("{userId}")
	Person get(@PathParam("userId") String userId);

	/**
	 * List all of the people in the specified collection.
	 * 
	 * @param collection
	 *            The collection of people to list.
	 * @param maxResults
	 *            The maximum number of people to include in the response, which
	 *            is used for paging. For any response, the actual number
	 *            returned might be less than the specified maxResults.
	 * @param orderBy
	 *            The order to return people in.
	 * @param pageToken
	 *            The continuation token, which is used to page through large
	 *            result sets. To get the next page of results, set this
	 *            parameter to the value of "nextPageToken" from the previous
	 *            response.
	 * @param userId
	 *            Get the collection of people for the person identified. Use
	 *            "me" to indicate the authenticated user.
	 */
	@GET
	@Path("{userId}/people/{collection}")
	PeopleFeed list(@PathParam("collection") String collection,
			@QueryParam("maxResults") int maxResults,
			@QueryParam("orderBy") String orderBy,
			@QueryParam("pageToken") String pageToken,
			@PathParam("userId") String userId);

	/**
	 * List all of the people in the specified collection for a particular
	 * activity.
	 * 
	 * @param activityId
	 *            The ID of the activity to get the list of people for.
	 * @param collection
	 *            The collection of people to list.
	 * @param maxResults
	 *            The maximum number of people to include in the response, which
	 *            is used for paging. For any response, the actual number
	 *            returned might be less than the specified maxResults.
	 * @param pageToken
	 *            The continuation token, which is used to page through large
	 *            result sets. To get the next page of results, set this
	 *            parameter to the value of "nextPageToken" from the previous
	 *            response.
	 */
	@GET
	@Path("{activityId}/people/{collection}")
	PeopleFeed listByActivity(@PathParam("activityId") String activityId,
			@PathParam("collection") String collection,
			@QueryParam("maxResults") int maxResults,
			@QueryParam("pageToken") String pageToken);

	/**
	 * Search all public profiles.
	 * 
	 * @param language
	 *            Specify the preferred language to search with. See search
	 *            language codes for available values.
	 * @param maxResults
	 *            The maximum number of people to include in the response, which
	 *            is used for paging. For any response, the actual number
	 *            returned might be less than the specified maxResults.
	 * @param pageToken
	 *            The continuation token, which is used to page through large
	 *            result sets. To get the next page of results, set this
	 *            parameter to the value of "nextPageToken" from the previous
	 *            response. This token can be of any length.
	 * @param query
	 *            Specify a query string for full text search of public text in
	 *            all profiles.
	 */
	@GET
	PeopleFeed search(@QueryParam("language") String language,
			@QueryParam("maxResults") int maxResults,
			@QueryParam("pageToken") String pageToken,
			@QueryParam("query") String query);

}
