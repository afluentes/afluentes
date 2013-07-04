package com.google.api.services.discovery;

import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

@Path("apis")
@Component
public class ApisResourceImpl implements IApisResource {
	@Override
	public RestDescription getRest(final String api, final String version) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public DirectoryList list(final String name, final boolean preferred) {
		// TODO
		throw new UnsupportedOperationException();
	}
}