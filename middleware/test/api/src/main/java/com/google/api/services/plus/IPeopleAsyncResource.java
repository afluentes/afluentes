package com.google.api.services.plus;

import afluentes.core.api.IEvaluation;

public interface IPeopleAsyncResource {
	/**
	 * Get a person's profile. If your app uses scope
	 * https://www.googleapis.com/auth/plus.login, this method is guaranteed to
	 * return ageRange and language.
	 * 
	 * @param userId
	 *            The ID of the person to get the profile for. The special value
	 *            "me" can be used to indicate the authenticated user.
	 */
	IEvaluation<Person> get(String userId);

	IEvaluation<Person> get(IEvaluation<String> userId);

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
	IEvaluation<PeopleFeed> list(String collection, Integer maxResults,
			String orderBy, String pageToken, String userId);

	IEvaluation<PeopleFeed> list(IEvaluation<String> collection,
			IEvaluation<Integer> maxResults, IEvaluation<String> orderBy,
			IEvaluation<String> pageToken, IEvaluation<String> userId);

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
	IEvaluation<PeopleFeed> listByActivity(String activityId,
			String collection, Integer maxResults, String pageToken);

	IEvaluation<PeopleFeed> listByActivity(IEvaluation<String> activityId,
			IEvaluation<String> collection, IEvaluation<Integer> maxResults,
			IEvaluation<String> pageToken);

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
	IEvaluation<PeopleFeed> search(String language, Integer maxResults,
			String pageToken, String query);

	IEvaluation<PeopleFeed> search(IEvaluation<String> language,
			IEvaluation<Integer> maxResults, IEvaluation<String> pageToken,
			IEvaluation<String> query);

}
