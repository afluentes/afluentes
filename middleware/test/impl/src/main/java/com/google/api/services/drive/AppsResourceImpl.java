package com.google.api.services.drive;

import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

@Path("apps")
@Component
public class AppsResourceImpl implements IAppsResource {
	@Override
	public App get(final String appId) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public AppList list() {
		// TODO
		throw new UnsupportedOperationException();
	}
}