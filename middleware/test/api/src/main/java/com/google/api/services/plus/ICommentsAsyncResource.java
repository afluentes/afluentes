package com.google.api.services.plus;

import afluentes.core.api.IEvaluation;

public interface ICommentsAsyncResource {
	/**
	 * Get a comment.
	 * 
	 * @param commentId
	 *            The ID of the comment to get.
	 */
	IEvaluation<Comment> get(String commentId);

	IEvaluation<Comment> get(IEvaluation<String> commentId);

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
	IEvaluation<CommentFeed> list(String activityId, Integer maxResults,
			String pageToken, String sortOrder);

	IEvaluation<CommentFeed> list(IEvaluation<String> activityId,
			IEvaluation<Integer> maxResults, IEvaluation<String> pageToken,
			IEvaluation<String> sortOrder);

}
