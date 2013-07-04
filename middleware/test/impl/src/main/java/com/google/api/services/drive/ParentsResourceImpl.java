package com.google.api.services.drive;

import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

@Path("files")
@Component
public class ParentsResourceImpl implements IParentsResource {
	@Override
	public void delete(final String fileId, final String parentId) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public ParentReference get(final String fileId, final String parentId) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public ParentReference insert(final String fileId,
			final ParentReference requestEntity) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public ParentList list(final String fileId) {
		// TODO
		throw new UnsupportedOperationException();
	}
}