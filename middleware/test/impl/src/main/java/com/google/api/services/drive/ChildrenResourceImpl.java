package com.google.api.services.drive;

import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

@Path("files")
@Component
public class ChildrenResourceImpl implements IChildrenResource {
	@Override
	public void delete(final String childId, final String folderId) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public ChildReference get(final String childId, final String folderId) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public ChildReference insert(final String folderId,
			final ChildReference requestEntity) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public ChildList list(final String folderId, final int maxResults,
			final String pageToken, final String q) {
		// TODO
		throw new UnsupportedOperationException();
	}
}