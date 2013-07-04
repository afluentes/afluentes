package com.google.api.services.drive;

import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

@Path("files")
@Component
public class RevisionsResourceImpl implements IRevisionsResource {
	@Override
	public void delete(final String fileId, final String revisionId) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public Revision get(final String fileId, final String revisionId) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public RevisionList list(final String fileId) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public Revision patch(final String fileId, final String revisionId,
			final Revision requestEntity) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public Revision update(final String fileId, final String revisionId,
			final Revision requestEntity) {
		// TODO
		throw new UnsupportedOperationException();
	}
}