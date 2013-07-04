package com.google.api.services.drive;

import afluentes.core.api.IEvaluation;

public interface IRepliesAsyncResource {
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
	IEvaluation<Void> delete(String commentId, String fileId, String replyId);

	IEvaluation<Void> delete(IEvaluation<String> commentId,
			IEvaluation<String> fileId, IEvaluation<String> replyId);

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
	IEvaluation<CommentReply> get(String commentId, String fileId,
			Boolean includeDeleted, String replyId);

	IEvaluation<CommentReply> get(IEvaluation<String> commentId,
			IEvaluation<String> fileId, IEvaluation<Boolean> includeDeleted,
			IEvaluation<String> replyId);

	/**
	 * Creates a new reply to the given comment.
	 * 
	 * @param commentId
	 *            The ID of the comment.
	 * @param fileId
	 *            The ID of the file.
	 */
	IEvaluation<CommentReply> insert(String commentId, String fileId,
			CommentReply requestEntity);

	IEvaluation<CommentReply> insert(IEvaluation<String> commentId,
			IEvaluation<String> fileId, IEvaluation<CommentReply> requestEntity);

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
	IEvaluation<CommentReplyList> list(String commentId, String fileId,
			Boolean includeDeleted, Integer maxResults, String pageToken);

	IEvaluation<CommentReplyList> list(IEvaluation<String> commentId,
			IEvaluation<String> fileId, IEvaluation<Boolean> includeDeleted,
			IEvaluation<Integer> maxResults, IEvaluation<String> pageToken);

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
	IEvaluation<CommentReply> patch(String commentId, String fileId,
			String replyId, CommentReply requestEntity);

	IEvaluation<CommentReply> patch(IEvaluation<String> commentId,
			IEvaluation<String> fileId, IEvaluation<String> replyId,
			IEvaluation<CommentReply> requestEntity);

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
	IEvaluation<CommentReply> update(String commentId, String fileId,
			String replyId, CommentReply requestEntity);

	IEvaluation<CommentReply> update(IEvaluation<String> commentId,
			IEvaluation<String> fileId, IEvaluation<String> replyId,
			IEvaluation<CommentReply> requestEntity);

}
