package com.google.api.services.drive;

import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

@Path("changes")
@Component
public class ChangesResourceImpl implements IChangesResource {
	@Override
	public Change get(final String changeId) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public ChangeList list(final boolean includeDeleted,
			final boolean includeSubscribed, final int maxResults,
			final String pageToken, final String startChangeId) {
		// TODO
		throw new UnsupportedOperationException();
	}
}