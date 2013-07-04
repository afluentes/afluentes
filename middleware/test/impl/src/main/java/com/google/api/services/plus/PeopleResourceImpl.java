package com.google.api.services.plus;

import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

@Path("people")
@Component
public class PeopleResourceImpl implements IPeopleResource {
	@Override
	public Person get(final String userId) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public PeopleFeed list(final String collection, final int maxResults,
			final String orderBy, final String pageToken, final String userId) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public PeopleFeed listByActivity(final String activityId,
			final String collection, final int maxResults,
			final String pageToken) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public PeopleFeed search(final String language, final int maxResults,
			final String pageToken, final String query) {
		// TODO
		throw new UnsupportedOperationException();
	}
}