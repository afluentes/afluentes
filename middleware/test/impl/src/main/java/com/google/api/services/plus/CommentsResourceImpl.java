package com.google.api.services.plus;

import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

@Path("comments")
@Component
public class CommentsResourceImpl implements ICommentsResource {
	@Override
	public Comment get(final String commentId) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public CommentFeed list(final String activityId, final int maxResults,
			final String pageToken, final String sortOrder) {
		// TODO
		throw new UnsupportedOperationException();
	}
}