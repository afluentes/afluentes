package com.google.api.services.drive;

import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

@Path("files")
@Component
public class RepliesResourceImpl implements IRepliesResource {
	@Override
	public void delete(final String commentId, final String fileId,
			final String replyId) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public CommentReply get(final String commentId, final String fileId,
			final boolean includeDeleted, final String replyId) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public CommentReply insert(final String commentId, final String fileId,
			final CommentReply requestEntity) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public CommentReplyList list(final String commentId, final String fileId,
			final boolean includeDeleted, final int maxResults,
			final String pageToken) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public CommentReply patch(final String commentId, final String fileId,
			final String replyId, final CommentReply requestEntity) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public CommentReply update(final String commentId, final String fileId,
			final String replyId, final CommentReply requestEntity) {
		// TODO
		throw new UnsupportedOperationException();
	}
}