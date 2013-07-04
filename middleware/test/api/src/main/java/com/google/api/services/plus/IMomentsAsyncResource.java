package com.google.api.services.plus;

import afluentes.core.api.IEvaluation;

public interface IMomentsAsyncResource {
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
	IEvaluation<Moment> insert(String collection, Boolean debug, String userId,
			Moment requestEntity);

	IEvaluation<Moment> insert(IEvaluation<String> collection,
			IEvaluation<Boolean> debug, IEvaluation<String> userId,
			IEvaluation<Moment> requestEntity);

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
	IEvaluation<MomentsFeed> list(String collection, Integer maxResults,
			String pageToken, String targetUrl, String type, String userId);

	IEvaluation<MomentsFeed> list(IEvaluation<String> collection,
			IEvaluation<Integer> maxResults, IEvaluation<String> pageToken,
			IEvaluation<String> targetUrl, IEvaluation<String> type,
			IEvaluation<String> userId);

	/**
	 * Delete a moment.
	 * 
	 * @param id
	 *            The ID of the moment to delete.
	 */
	IEvaluation<Void> remove(String id);

	IEvaluation<Void> remove(IEvaluation<String> id);

}
