package com.google.api.services.drive;

import afluentes.core.api.IEvaluation;

public interface ICommentsAsyncResource {
	/**
	 * Deletes a comment.
	 * 
	 * @param commentId
	 *            The ID of the comment.
	 * @param fileId
	 *            The ID of the file.
	 */
	IEvaluation<Void> delete(String commentId, String fileId);

	IEvaluation<Void> delete(IEvaluation<String> commentId,
			IEvaluation<String> fileId);

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
	IEvaluation<Comment> get(String commentId, String fileId,
			Boolean includeDeleted);

	IEvaluation<Comment> get(IEvaluation<String> commentId,
			IEvaluation<String> fileId, IEvaluation<Boolean> includeDeleted);

	/**
	 * Creates a new comment on the given file.
	 * 
	 * @param fileId
	 *            The ID of the file.
	 */
	IEvaluation<Comment> insert(String fileId, Comment requestEntity);

	IEvaluation<Comment> insert(IEvaluation<String> fileId,
			IEvaluation<Comment> requestEntity);

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
	IEvaluation<CommentList> list(String fileId, Boolean includeDeleted,
			Integer maxResults, String pageToken, String updatedMin);

	IEvaluation<CommentList> list(IEvaluation<String> fileId,
			IEvaluation<Boolean> includeDeleted,
			IEvaluation<Integer> maxResults, IEvaluation<String> pageToken,
			IEvaluation<String> updatedMin);

	/**
	 * Updates an existing comment. This method supports patch semantics.
	 * 
	 * @param commentId
	 *            The ID of the comment.
	 * @param fileId
	 *            The ID of the file.
	 */
	IEvaluation<Comment> patch(String commentId, String fileId,
			Comment requestEntity);

	IEvaluation<Comment> patch(IEvaluation<String> commentId,
			IEvaluation<String> fileId, IEvaluation<Comment> requestEntity);

	/**
	 * Updates an existing comment.
	 * 
	 * @param commentId
	 *            The ID of the comment.
	 * @param fileId
	 *            The ID of the file.
	 */
	IEvaluation<Comment> update(String commentId, String fileId,
			Comment requestEntity);

	IEvaluation<Comment> update(IEvaluation<String> commentId,
			IEvaluation<String> fileId, IEvaluation<Comment> requestEntity);

}
