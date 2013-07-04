package com.google.api.services.plus;

import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

@Path("activities")
@Component
public class ActivitiesResourceImpl implements IActivitiesResource {
	@Override
	public Activity get(final String activityId) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public ActivityFeed list(final String collection, final int maxResults,
			final String pageToken, final String userId) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public ActivityFeed search(final String language, final int maxResults,
			final String orderBy, final String pageToken, final String query) {
		// TODO
		throw new UnsupportedOperationException();
	}
}