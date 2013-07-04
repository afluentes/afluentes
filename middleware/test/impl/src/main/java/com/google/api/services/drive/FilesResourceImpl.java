package com.google.api.services.drive;

import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

@Path("files")
@Component
public class FilesResourceImpl implements IFilesResource {
	@Override
	public File copy(final boolean convert, final String fileId,
			final boolean ocr, final String ocrLanguage, final boolean pinned,
			final String timedTextLanguage, final String timedTextTrackName,
			final File requestEntity) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete(final String fileId) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public File get(final String fileId, final String projection,
			final boolean updateViewedDate) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public File insert(final boolean convert, final boolean ocr,
			final String ocrLanguage, final boolean pinned,
			final String timedTextLanguage, final String timedTextTrackName,
			final boolean useContentAsIndexableText, final File requestEntity) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public FileList list(final int maxResults, final String pageToken,
			final String projection, final String q) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public File patch(final boolean convert, final String fileId,
			final boolean newRevision, final boolean ocr,
			final String ocrLanguage, final boolean pinned,
			final boolean setModifiedDate, final String timedTextLanguage,
			final String timedTextTrackName, final boolean updateViewedDate,
			final boolean useContentAsIndexableText, final File requestEntity) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public File touch(final String fileId) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public File trash(final String fileId) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public File untrash(final String fileId) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public File update(final boolean convert, final String fileId,
			final boolean newRevision, final boolean ocr,
			final String ocrLanguage, final boolean pinned,
			final boolean setModifiedDate, final String timedTextLanguage,
			final String timedTextTrackName, final boolean updateViewedDate,
			final boolean useContentAsIndexableText, final File requestEntity) {
		// TODO
		throw new UnsupportedOperationException();
	}
}