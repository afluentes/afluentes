package com.google.api.services.drive;

import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

@Path("files")
@Component
public class CommentsResourceImpl implements ICommentsResource {
	@Override
	public void delete(final String commentId, final String fileId) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public Comment get(final String commentId, final String fileId,
			final boolean includeDeleted) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public Comment insert(final String fileId, final Comment requestEntity) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public CommentList list(final String fileId, final boolean includeDeleted,
			final int maxResults, final String pageToken,
			final String updatedMin) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public Comment patch(final String commentId, final String fileId,
			final Comment requestEntity) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public Comment update(final String commentId, final String fileId,
			final Comment requestEntity) {
		// TODO
		throw new UnsupportedOperationException();
	}
}