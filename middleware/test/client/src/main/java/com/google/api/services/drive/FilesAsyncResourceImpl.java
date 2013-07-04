package com.google.api.services.drive;

import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;

import afluentes.core.api.IAsynchronousFunction1;
import afluentes.core.api.IAsynchronousFunction12;
import afluentes.core.api.IAsynchronousFunction3;
import afluentes.core.api.IAsynchronousFunction4;
import afluentes.core.api.IAsynchronousFunction8;
import afluentes.core.api.ICallback;
import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator1;
import afluentes.core.api.IEvaluator12;
import afluentes.core.api.IEvaluator3;
import afluentes.core.api.IEvaluator4;
import afluentes.core.api.IEvaluator8;
import afluentes.core.impl.AsynchronousEvaluator1;
import afluentes.core.impl.AsynchronousEvaluator12;
import afluentes.core.impl.AsynchronousEvaluator3;
import afluentes.core.impl.AsynchronousEvaluator4;
import afluentes.core.impl.AsynchronousEvaluator8;
import afluentes.core.impl.Constant;
import afluentes.middleware.ws.rs.client.ICallbackAdapter;

@SuppressWarnings("unused")
public class FilesAsyncResourceImpl implements IFilesAsyncResource {
	private final Client client;
	private final String uri;

	private final IEvaluator8<Boolean, String, Boolean, String, Boolean, String, String, File, File> copy;
	private final IEvaluator1<String, Void> delete;
	private final IEvaluator3<String, String, Boolean, File> get;
	private final IEvaluator8<Boolean, Boolean, String, Boolean, String, String, Boolean, File, File> insert;
	private final IEvaluator4<Integer, String, String, String, FileList> list;
	private final IEvaluator12<Boolean, String, Boolean, Boolean, String, Boolean, Boolean, String, String, Boolean, Boolean, File, File> patch;
	private final IEvaluator1<String, File> touch;
	private final IEvaluator1<String, File> trash;
	private final IEvaluator1<String, File> untrash;
	private final IEvaluator12<Boolean, String, Boolean, Boolean, String, Boolean, Boolean, String, String, Boolean, Boolean, File, File> update;

	public FilesAsyncResourceImpl(final Client client, final String uri) {
		this.client = client;
		this.uri = uri;

		this.copy = new AsynchronousEvaluator8<>(
				new IAsynchronousFunction8<Boolean, String, Boolean, String, Boolean, String, String, File, File>() {
					@Override
					public void y(Boolean convert, String fileId, Boolean ocr,
							String ocrLanguage, Boolean pinned,
							String timedTextLanguage,
							String timedTextTrackName, File requestEntity,
							ICallback<File> callback) {
						WebTarget target = client.target(uri);
						target = target.path("{fileId}/copy");

						target = target.path(fileId);

						target = target.queryParam("convert", convert);
						target = target.queryParam("ocr", ocr);
						target = target.queryParam("ocrLanguage", ocrLanguage);
						target = target.queryParam("pinned", pinned);
						target = target.queryParam("timedTextLanguage",
								timedTextLanguage);
						target = target.queryParam("timedTextTrackName",
								timedTextTrackName);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("POST", Entity.json(requestEntity),
								new ICallbackAdapter<File>(callback) {
								});
					}
				});

		this.delete = new AsynchronousEvaluator1<>(
				new IAsynchronousFunction1<String, Void>() {
					@Override
					public void y(String fileId, ICallback<Void> callback) {
						WebTarget target = client.target(uri);
						target = target.path("{fileId}");

						target = target.path(fileId);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("DELETE", new ICallbackAdapter<Void>(
								callback) {
						});
					}
				});

		this.get = new AsynchronousEvaluator3<>(
				new IAsynchronousFunction3<String, String, Boolean, File>() {
					@Override
					public void y(String fileId, String projection,
							Boolean updateViewedDate, ICallback<File> callback) {
						WebTarget target = client.target(uri);
						target = target.path("{fileId}");

						target = target.path(fileId);

						target = target.queryParam("projection", projection);
						target = target.queryParam("updateViewedDate",
								updateViewedDate);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("GET", new ICallbackAdapter<File>(
								callback) {
						});
					}
				});

		this.insert = new AsynchronousEvaluator8<>(
				new IAsynchronousFunction8<Boolean, Boolean, String, Boolean, String, String, Boolean, File, File>() {
					@Override
					public void y(Boolean convert, Boolean ocr,
							String ocrLanguage, Boolean pinned,
							String timedTextLanguage,
							String timedTextTrackName,
							Boolean useContentAsIndexableText,
							File requestEntity, ICallback<File> callback) {
						WebTarget target = client.target(uri);

						target = target.queryParam("convert", convert);
						target = target.queryParam("ocr", ocr);
						target = target.queryParam("ocrLanguage", ocrLanguage);
						target = target.queryParam("pinned", pinned);
						target = target.queryParam("timedTextLanguage",
								timedTextLanguage);
						target = target.queryParam("timedTextTrackName",
								timedTextTrackName);
						target = target.queryParam("useContentAsIndexableText",
								useContentAsIndexableText);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("POST", Entity.json(requestEntity),
								new ICallbackAdapter<File>(callback) {
								});
					}
				});

		this.list = new AsynchronousEvaluator4<>(
				new IAsynchronousFunction4<Integer, String, String, String, FileList>() {
					@Override
					public void y(Integer maxResults, String pageToken,
							String projection, String q,
							ICallback<FileList> callback) {
						WebTarget target = client.target(uri);

						target = target.queryParam("maxResults", maxResults);
						target = target.queryParam("pageToken", pageToken);
						target = target.queryParam("projection", projection);
						target = target.queryParam("q", q);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("GET", new ICallbackAdapter<FileList>(
								callback) {
						});
					}
				});

		this.patch = new AsynchronousEvaluator12<>(
				new IAsynchronousFunction12<Boolean, String, Boolean, Boolean, String, Boolean, Boolean, String, String, Boolean, Boolean, File, File>() {
					@Override
					public void y(Boolean convert, String fileId,
							Boolean newRevision, Boolean ocr,
							String ocrLanguage, Boolean pinned,
							Boolean setModifiedDate, String timedTextLanguage,
							String timedTextTrackName,
							Boolean updateViewedDate,
							Boolean useContentAsIndexableText,
							File requestEntity, ICallback<File> callback) {
						WebTarget target = client.target(uri);
						target = target.path("{fileId}");

						target = target.path(fileId);

						target = target.queryParam("convert", convert);
						target = target.queryParam("newRevision", newRevision);
						target = target.queryParam("ocr", ocr);
						target = target.queryParam("ocrLanguage", ocrLanguage);
						target = target.queryParam("pinned", pinned);
						target = target.queryParam("setModifiedDate",
								setModifiedDate);
						target = target.queryParam("timedTextLanguage",
								timedTextLanguage);
						target = target.queryParam("timedTextTrackName",
								timedTextTrackName);
						target = target.queryParam("updateViewedDate",
								updateViewedDate);
						target = target.queryParam("useContentAsIndexableText",
								useContentAsIndexableText);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("PATCH", Entity.json(requestEntity),
								new ICallbackAdapter<File>(callback) {
								});
					}
				});

		this.touch = new AsynchronousEvaluator1<>(
				new IAsynchronousFunction1<String, File>() {
					@Override
					public void y(String fileId, ICallback<File> callback) {
						WebTarget target = client.target(uri);
						target = target.path("{fileId}/touch");

						target = target.path(fileId);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("POST", new ICallbackAdapter<File>(
								callback) {
						});
					}
				});

		this.trash = new AsynchronousEvaluator1<>(
				new IAsynchronousFunction1<String, File>() {
					@Override
					public void y(String fileId, ICallback<File> callback) {
						WebTarget target = client.target(uri);
						target = target.path("{fileId}/trash");

						target = target.path(fileId);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("POST", new ICallbackAdapter<File>(
								callback) {
						});
					}
				});

		this.untrash = new AsynchronousEvaluator1<>(
				new IAsynchronousFunction1<String, File>() {
					@Override
					public void y(String fileId, ICallback<File> callback) {
						WebTarget target = client.target(uri);
						target = target.path("{fileId}/untrash");

						target = target.path(fileId);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("POST", new ICallbackAdapter<File>(
								callback) {
						});
					}
				});

		this.update = new AsynchronousEvaluator12<>(
				new IAsynchronousFunction12<Boolean, String, Boolean, Boolean, String, Boolean, Boolean, String, String, Boolean, Boolean, File, File>() {
					@Override
					public void y(Boolean convert, String fileId,
							Boolean newRevision, Boolean ocr,
							String ocrLanguage, Boolean pinned,
							Boolean setModifiedDate, String timedTextLanguage,
							String timedTextTrackName,
							Boolean updateViewedDate,
							Boolean useContentAsIndexableText,
							File requestEntity, ICallback<File> callback) {
						WebTarget target = client.target(uri);
						target = target.path("{fileId}");

						target = target.path(fileId);

						target = target.queryParam("convert", convert);
						target = target.queryParam("newRevision", newRevision);
						target = target.queryParam("ocr", ocr);
						target = target.queryParam("ocrLanguage", ocrLanguage);
						target = target.queryParam("pinned", pinned);
						target = target.queryParam("setModifiedDate",
								setModifiedDate);
						target = target.queryParam("timedTextLanguage",
								timedTextLanguage);
						target = target.queryParam("timedTextTrackName",
								timedTextTrackName);
						target = target.queryParam("updateViewedDate",
								updateViewedDate);
						target = target.queryParam("useContentAsIndexableText",
								useContentAsIndexableText);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("PUT", Entity.json(requestEntity),
								new ICallbackAdapter<File>(callback) {
								});
					}
				});

	}

	@Override
	public IEvaluation<File> copy(final Boolean convert, final String fileId,
			final Boolean ocr, final String ocrLanguage, final Boolean pinned,
			final String timedTextLanguage, final String timedTextTrackName,
			final File requestEntity) {
		return copy(new Constant<>(convert), new Constant<>(fileId),
				new Constant<>(ocr), new Constant<>(ocrLanguage),
				new Constant<>(pinned), new Constant<>(timedTextLanguage),
				new Constant<>(timedTextTrackName), new Constant<>(
						requestEntity));
	}

	@Override
	public IEvaluation<File> copy(final IEvaluation<Boolean> convert,
			final IEvaluation<String> fileId, final IEvaluation<Boolean> ocr,
			final IEvaluation<String> ocrLanguage,
			final IEvaluation<Boolean> pinned,
			final IEvaluation<String> timedTextLanguage,
			final IEvaluation<String> timedTextTrackName,
			final IEvaluation<File> requestEntity) {
		return copy.y(convert, fileId, ocr, ocrLanguage, pinned,
				timedTextLanguage, timedTextTrackName, requestEntity);
	}

	@Override
	public IEvaluation<Void> delete(final String fileId) {
		return delete(new Constant<>(fileId));
	}

	@Override
	public IEvaluation<Void> delete(final IEvaluation<String> fileId) {
		return delete.y(fileId);
	}

	@Override
	public IEvaluation<File> get(final String fileId, final String projection,
			final Boolean updateViewedDate) {
		return get(new Constant<>(fileId), new Constant<>(projection),
				new Constant<>(updateViewedDate));
	}

	@Override
	public IEvaluation<File> get(final IEvaluation<String> fileId,
			final IEvaluation<String> projection,
			final IEvaluation<Boolean> updateViewedDate) {
		return get.y(fileId, projection, updateViewedDate);
	}

	@Override
	public IEvaluation<File> insert(final Boolean convert, final Boolean ocr,
			final String ocrLanguage, final Boolean pinned,
			final String timedTextLanguage, final String timedTextTrackName,
			final Boolean useContentAsIndexableText, final File requestEntity) {
		return insert(new Constant<>(convert), new Constant<>(ocr),
				new Constant<>(ocrLanguage), new Constant<>(pinned),
				new Constant<>(timedTextLanguage), new Constant<>(
						timedTextTrackName), new Constant<>(
						useContentAsIndexableText), new Constant<>(
						requestEntity));
	}

	@Override
	public IEvaluation<File> insert(final IEvaluation<Boolean> convert,
			final IEvaluation<Boolean> ocr,
			final IEvaluation<String> ocrLanguage,
			final IEvaluation<Boolean> pinned,
			final IEvaluation<String> timedTextLanguage,
			final IEvaluation<String> timedTextTrackName,
			final IEvaluation<Boolean> useContentAsIndexableText,
			final IEvaluation<File> requestEntity) {
		return insert.y(convert, ocr, ocrLanguage, pinned, timedTextLanguage,
				timedTextTrackName, useContentAsIndexableText, requestEntity);
	}

	@Override
	public IEvaluation<FileList> list(final Integer maxResults,
			final String pageToken, final String projection, final String q) {
		return list(new Constant<>(maxResults), new Constant<>(pageToken),
				new Constant<>(projection), new Constant<>(q));
	}

	@Override
	public IEvaluation<FileList> list(final IEvaluation<Integer> maxResults,
			final IEvaluation<String> pageToken,
			final IEvaluation<String> projection, final IEvaluation<String> q) {
		return list.y(maxResults, pageToken, projection, q);
	}

	@Override
	public IEvaluation<File> patch(final Boolean convert, final String fileId,
			final Boolean newRevision, final Boolean ocr,
			final String ocrLanguage, final Boolean pinned,
			final Boolean setModifiedDate, final String timedTextLanguage,
			final String timedTextTrackName, final Boolean updateViewedDate,
			final Boolean useContentAsIndexableText, final File requestEntity) {
		return patch(new Constant<>(convert), new Constant<>(fileId),
				new Constant<>(newRevision), new Constant<>(ocr),
				new Constant<>(ocrLanguage), new Constant<>(pinned),
				new Constant<>(setModifiedDate), new Constant<>(
						timedTextLanguage), new Constant<>(timedTextTrackName),
				new Constant<>(updateViewedDate), new Constant<>(
						useContentAsIndexableText), new Constant<>(
						requestEntity));
	}

	@Override
	public IEvaluation<File> patch(final IEvaluation<Boolean> convert,
			final IEvaluation<String> fileId,
			final IEvaluation<Boolean> newRevision,
			final IEvaluation<Boolean> ocr,
			final IEvaluation<String> ocrLanguage,
			final IEvaluation<Boolean> pinned,
			final IEvaluation<Boolean> setModifiedDate,
			final IEvaluation<String> timedTextLanguage,
			final IEvaluation<String> timedTextTrackName,
			final IEvaluation<Boolean> updateViewedDate,
			final IEvaluation<Boolean> useContentAsIndexableText,
			final IEvaluation<File> requestEntity) {
		return patch.y(convert, fileId, newRevision, ocr, ocrLanguage, pinned,
				setModifiedDate, timedTextLanguage, timedTextTrackName,
				updateViewedDate, useContentAsIndexableText, requestEntity);
	}

	@Override
	public IEvaluation<File> touch(final String fileId) {
		return touch(new Constant<>(fileId));
	}

	@Override
	public IEvaluation<File> touch(final IEvaluation<String> fileId) {
		return touch.y(fileId);
	}

	@Override
	public IEvaluation<File> trash(final String fileId) {
		return trash(new Constant<>(fileId));
	}

	@Override
	public IEvaluation<File> trash(final IEvaluation<String> fileId) {
		return trash.y(fileId);
	}

	@Override
	public IEvaluation<File> untrash(final String fileId) {
		return untrash(new Constant<>(fileId));
	}

	@Override
	public IEvaluation<File> untrash(final IEvaluation<String> fileId) {
		return untrash.y(fileId);
	}

	@Override
	public IEvaluation<File> update(final Boolean convert, final String fileId,
			final Boolean newRevision, final Boolean ocr,
			final String ocrLanguage, final Boolean pinned,
			final Boolean setModifiedDate, final String timedTextLanguage,
			final String timedTextTrackName, final Boolean updateViewedDate,
			final Boolean useContentAsIndexableText, final File requestEntity) {
		return update(new Constant<>(convert), new Constant<>(fileId),
				new Constant<>(newRevision), new Constant<>(ocr),
				new Constant<>(ocrLanguage), new Constant<>(pinned),
				new Constant<>(setModifiedDate), new Constant<>(
						timedTextLanguage), new Constant<>(timedTextTrackName),
				new Constant<>(updateViewedDate), new Constant<>(
						useContentAsIndexableText), new Constant<>(
						requestEntity));
	}

	@Override
	public IEvaluation<File> update(final IEvaluation<Boolean> convert,
			final IEvaluation<String> fileId,
			final IEvaluation<Boolean> newRevision,
			final IEvaluation<Boolean> ocr,
			final IEvaluation<String> ocrLanguage,
			final IEvaluation<Boolean> pinned,
			final IEvaluation<Boolean> setModifiedDate,
			final IEvaluation<String> timedTextLanguage,
			final IEvaluation<String> timedTextTrackName,
			final IEvaluation<Boolean> updateViewedDate,
			final IEvaluation<Boolean> useContentAsIndexableText,
			final IEvaluation<File> requestEntity) {
		return update.y(convert, fileId, newRevision, ocr, ocrLanguage, pinned,
				setModifiedDate, timedTextLanguage, timedTextTrackName,
				updateViewedDate, useContentAsIndexableText, requestEntity);
	}

}
