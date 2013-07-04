package com.google.api.services.drive;

import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

@Path("about")
@Component
public class AboutResourceImpl implements IAboutResource {
	@Override
	public About get(final boolean includeSubscribed,
			final String maxChangeIdCount, final String startChangeId) {
		// TODO
		throw new UnsupportedOperationException();
	}
}