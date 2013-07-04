package com.google.api.services.drive;

import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

@Path("files")
@Component
public class PropertiesResourceImpl implements IPropertiesResource {
	@Override
	public void delete(final String fileId, final String propertyKey,
			final String visibility) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public Property get(final String fileId, final String propertyKey,
			final String visibility) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public Property insert(final String fileId, final Property requestEntity) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public PropertyList list(final String fileId) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public Property patch(final String fileId, final String propertyKey,
			final String visibility, final Property requestEntity) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public Property update(final String fileId, final String propertyKey,
			final String visibility, final Property requestEntity) {
		// TODO
		throw new UnsupportedOperationException();
	}
}