package com.google.api.services.plus;

import afluentes.core.api.IEvaluation;

public interface IActivitiesAsyncResource {
	/**
	 * Get an activity.
	 * 
	 * @param activityId
	 *            The ID of the activity to get.
	 */
	IEvaluation<Activity> get(String activityId);

	IEvaluation<Activity> get(IEvaluation<String> activityId);

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
	IEvaluation<ActivityFeed> list(String collection, Integer maxResults,
			String pageToken, String userId);

	IEvaluation<ActivityFeed> list(IEvaluation<String> collection,
			IEvaluation<Integer> maxResults, IEvaluation<String> pageToken,
			IEvaluation<String> userId);

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
	IEvaluation<ActivityFeed> search(String language, Integer maxResults,
			String orderBy, String pageToken, String query);

	IEvaluation<ActivityFeed> search(IEvaluation<String> language,
			IEvaluation<Integer> maxResults, IEvaluation<String> orderBy,
			IEvaluation<String> pageToken, IEvaluation<String> query);

}
