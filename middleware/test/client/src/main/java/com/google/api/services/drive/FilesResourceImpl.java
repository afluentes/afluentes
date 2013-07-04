package com.google.api.services.drive;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class FilesResourceImpl implements IFilesResource {
	private final Client client;
	private final String uri;

	public FilesResourceImpl(final Client client, final String uri) {
		this.client = client;
		this.uri = uri;
	}

	@Override
	public File copy(final boolean convert, final String fileId,
			final boolean ocr, final String ocrLanguage, final boolean pinned,
			final String timedTextLanguage, final String timedTextTrackName,
			final File requestEntity) {
		WebTarget target = client.target(uri);
		target = target.path("{fileId}/copy");

		target = target.path(fileId);

		target = target.queryParam("convert", convert);
		target = target.queryParam("ocr", ocr);
		target = target.queryParam("ocrLanguage", ocrLanguage);
		target = target.queryParam("pinned", pinned);
		target = target.queryParam("timedTextLanguage", timedTextLanguage);
		target = target.queryParam("timedTextTrackName", timedTextTrackName);
		Builder request = target.request();

		Invocation invocation = request.build("POST",
				Entity.json(requestEntity));
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		File responseEntity = response.readEntity(File.class);
		return responseEntity;
	}

	@Override
	public void delete(final String fileId) {
		WebTarget target = client.target(uri);
		target = target.path("{fileId}");

		target = target.path(fileId);

		Builder request = target.request();

		Invocation invocation = request.build("DELETE");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
	}

	@Override
	public File get(final String fileId, final String projection,
			final boolean updateViewedDate) {
		WebTarget target = client.target(uri);
		target = target.path("{fileId}");

		target = target.path(fileId);

		target = target.queryParam("projection", projection);
		target = target.queryParam("updateViewedDate", updateViewedDate);
		Builder request = target.request();

		Invocation invocation = request.build("GET");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		File responseEntity = response.readEntity(File.class);
		return responseEntity;
	}

	@Override
	public File insert(final boolean convert, final boolean ocr,
			final String ocrLanguage, final boolean pinned,
			final String timedTextLanguage, final String timedTextTrackName,
			final boolean useContentAsIndexableText, final File requestEntity) {
		WebTarget target = client.target(uri);

		target = target.queryParam("convert", convert);
		target = target.queryParam("ocr", ocr);
		target = target.queryParam("ocrLanguage", ocrLanguage);
		target = target.queryParam("pinned", pinned);
		target = target.queryParam("timedTextLanguage", timedTextLanguage);
		target = target.queryParam("timedTextTrackName", timedTextTrackName);
		target = target.queryParam("useContentAsIndexableText",
				useContentAsIndexableText);
		Builder request = target.request();

		Invocation invocation = request.build("POST",
				Entity.json(requestEntity));
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		File responseEntity = response.readEntity(File.class);
		return responseEntity;
	}

	@Override
	public FileList list(final int maxResults, final String pageToken,
			final String projection, final String q) {
		WebTarget target = client.target(uri);

		target = target.queryParam("maxResults", maxResults);
		target = target.queryParam("pageToken", pageToken);
		target = target.queryParam("projection", projection);
		target = target.queryParam("q", q);
		Builder request = target.request();

		Invocation invocation = request.build("GET");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		FileList responseEntity = response.readEntity(FileList.class);
		return responseEntity;
	}

	@Override
	public File patch(final boolean convert, final String fileId,
			final boolean newRevision, final boolean ocr,
			final String ocrLanguage, final boolean pinned,
			final boolean setModifiedDate, final String timedTextLanguage,
			final String timedTextTrackName, final boolean updateViewedDate,
			final boolean useContentAsIndexableText, final File requestEntity) {
		WebTarget target = client.target(uri);
		target = target.path("{fileId}");

		target = target.path(fileId);

		target = target.queryParam("convert", convert);
		target = target.queryParam("newRevision", newRevision);
		target = target.queryParam("ocr", ocr);
		target = target.queryParam("ocrLanguage", ocrLanguage);
		target = target.queryParam("pinned", pinned);
		target = target.queryParam("setModifiedDate", setModifiedDate);
		target = target.queryParam("timedTextLanguage", timedTextLanguage);
		target = target.queryParam("timedTextTrackName", timedTextTrackName);
		target = target.queryParam("updateViewedDate", updateViewedDate);
		target = target.queryParam("useContentAsIndexableText",
				useContentAsIndexableText);
		Builder request = target.request();

		Invocation invocation = request.build("PATCH",
				Entity.json(requestEntity));
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		File responseEntity = response.readEntity(File.class);
		return responseEntity;
	}

	@Override
	public File touch(final String fileId) {
		WebTarget target = client.target(uri);
		target = target.path("{fileId}/touch");

		target = target.path(fileId);

		Builder request = target.request();

		Invocation invocation = request.build("POST");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		File responseEntity = response.readEntity(File.class);
		return responseEntity;
	}

	@Override
	public File trash(final String fileId) {
		WebTarget target = client.target(uri);
		target = target.path("{fileId}/trash");

		target = target.path(fileId);

		Builder request = target.request();

		Invocation invocation = request.build("POST");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		File responseEntity = response.readEntity(File.class);
		return responseEntity;
	}

	@Override
	public File untrash(final String fileId) {
		WebTarget target = client.target(uri);
		target = target.path("{fileId}/untrash");

		target = target.path(fileId);

		Builder request = target.request();

		Invocation invocation = request.build("POST");
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		File responseEntity = response.readEntity(File.class);
		return responseEntity;
	}

	@Override
	public File update(final boolean convert, final String fileId,
			final boolean newRevision, final boolean ocr,
			final String ocrLanguage, final boolean pinned,
			final boolean setModifiedDate, final String timedTextLanguage,
			final String timedTextTrackName, final boolean updateViewedDate,
			final boolean useContentAsIndexableText, final File requestEntity) {
		WebTarget target = client.target(uri);
		target = target.path("{fileId}");

		target = target.path(fileId);

		target = target.queryParam("convert", convert);
		target = target.queryParam("newRevision", newRevision);
		target = target.queryParam("ocr", ocr);
		target = target.queryParam("ocrLanguage", ocrLanguage);
		target = target.queryParam("pinned", pinned);
		target = target.queryParam("setModifiedDate", setModifiedDate);
		target = target.queryParam("timedTextLanguage", timedTextLanguage);
		target = target.queryParam("timedTextTrackName", timedTextTrackName);
		target = target.queryParam("updateViewedDate", updateViewedDate);
		target = target.queryParam("useContentAsIndexableText",
				useContentAsIndexableText);
		Builder request = target.request();

		Invocation invocation = request
				.build("PUT", Entity.json(requestEntity));
		Response response = invocation.invoke();
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		File responseEntity = response.readEntity(File.class);
		return responseEntity;
	}

}