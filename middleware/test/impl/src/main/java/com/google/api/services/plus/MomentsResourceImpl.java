package com.google.api.services.plus;

import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

@Path("people")
@Component
public class MomentsResourceImpl implements IMomentsResource {
	@Override
	public Moment insert(final String collection, final boolean debug,
			final String userId, final Moment requestEntity) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public MomentsFeed list(final String collection, final int maxResults,
			final String pageToken, final String targetUrl, final String type,
			final String userId) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public void remove(final String id) {
		// TODO
		throw new UnsupportedOperationException();
	}
}