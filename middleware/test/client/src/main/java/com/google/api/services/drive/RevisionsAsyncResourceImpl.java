package com.google.api.services.drive;

import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;

import afluentes.core.api.IAsynchronousFunction1;
import afluentes.core.api.IAsynchronousFunction2;
import afluentes.core.api.IAsynchronousFunction3;
import afluentes.core.api.ICallback;
import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator1;
import afluentes.core.api.IEvaluator2;
import afluentes.core.api.IEvaluator3;
import afluentes.core.impl.AsynchronousEvaluator1;
import afluentes.core.impl.AsynchronousEvaluator2;
import afluentes.core.impl.AsynchronousEvaluator3;
import afluentes.core.impl.Constant;
import afluentes.middleware.ws.rs.client.ICallbackAdapter;

@SuppressWarnings("unused")
public class RevisionsAsyncResourceImpl implements IRevisionsAsyncResource {
	private final Client client;
	private final String uri;

	private final IEvaluator2<String, String, Void> delete;
	private final IEvaluator2<String, String, Revision> get;
	private final IEvaluator1<String, RevisionList> list;
	private final IEvaluator3<String, String, Revision, Revision> patch;
	private final IEvaluator3<String, String, Revision, Revision> update;

	public RevisionsAsyncResourceImpl(final Client client, final String uri) {
		this.client = client;
		this.uri = uri;

		this.delete = new AsynchronousEvaluator2<>(
				new IAsynchronousFunction2<String, String, Void>() {
					@Override
					public void y(String fileId, String revisionId,
							ICallback<Void> callback) {
						WebTarget target = client.target(uri);
						target = target.path("{fileId}/revisions/{revisionId}");

						target = target.path(fileId);
						target = target.path(revisionId);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("DELETE", new ICallbackAdapter<Void>(
								callback) {
						});
					}
				});

		this.get = new AsynchronousEvaluator2<>(
				new IAsynchronousFunction2<String, String, Revision>() {
					@Override
					public void y(String fileId, String revisionId,
							ICallback<Revision> callback) {
						WebTarget target = client.target(uri);
						target = target.path("{fileId}/revisions/{revisionId}");

						target = target.path(fileId);
						target = target.path(revisionId);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("GET", new ICallbackAdapter<Revision>(
								callback) {
						});
					}
				});

		this.list = new AsynchronousEvaluator1<>(
				new IAsynchronousFunction1<String, RevisionList>() {
					@Override
					public void y(String fileId,
							ICallback<RevisionList> callback) {
						WebTarget target = client.target(uri);
						target = target.path("{fileId}/revisions");

						target = target.path(fileId);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("GET",
								new ICallbackAdapter<RevisionList>(callback) {
								});
					}
				});

		this.patch = new AsynchronousEvaluator3<>(
				new IAsynchronousFunction3<String, String, Revision, Revision>() {
					@Override
					public void y(String fileId, String revisionId,
							Revision requestEntity, ICallback<Revision> callback) {
						WebTarget target = client.target(uri);
						target = target.path("{fileId}/revisions/{revisionId}");

						target = target.path(fileId);
						target = target.path(revisionId);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("PATCH", Entity.json(requestEntity),
								new ICallbackAdapter<Revision>(callback) {
								});
					}
				});

		this.update = new AsynchronousEvaluator3<>(
				new IAsynchronousFunction3<String, String, Revision, Revision>() {
					@Override
					public void y(String fileId, String revisionId,
							Revision requestEntity, ICallback<Revision> callback) {
						WebTarget target = client.target(uri);
						target = target.path("{fileId}/revisions/{revisionId}");

						target = target.path(fileId);
						target = target.path(revisionId);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("PUT", Entity.json(requestEntity),
								new ICallbackAdapter<Revision>(callback) {
								});
					}
				});

	}

	@Override
	public IEvaluation<Void> delete(final String fileId, final String revisionId) {
		return delete(new Constant<>(fileId), new Constant<>(revisionId));
	}

	@Override
	public IEvaluation<Void> delete(final IEvaluation<String> fileId,
			final IEvaluation<String> revisionId) {
		return delete.y(fileId, revisionId);
	}

	@Override
	public IEvaluation<Revision> get(final String fileId,
			final String revisionId) {
		return get(new Constant<>(fileId), new Constant<>(revisionId));
	}

	@Override
	public IEvaluation<Revision> get(final IEvaluation<String> fileId,
			final IEvaluation<String> revisionId) {
		return get.y(fileId, revisionId);
	}

	@Override
	public IEvaluation<RevisionList> list(final String fileId) {
		return list(new Constant<>(fileId));
	}

	@Override
	public IEvaluation<RevisionList> list(final IEvaluation<String> fileId) {
		return list.y(fileId);
	}

	@Override
	public IEvaluation<Revision> patch(final String fileId,
			final String revisionId, final Revision requestEntity) {
		return patch(new Constant<>(fileId), new Constant<>(revisionId),
				new Constant<>(requestEntity));
	}

	@Override
	public IEvaluation<Revision> patch(final IEvaluation<String> fileId,
			final IEvaluation<String> revisionId,
			final IEvaluation<Revision> requestEntity) {
		return patch.y(fileId, revisionId, requestEntity);
	}

	@Override
	public IEvaluation<Revision> update(final String fileId,
			final String revisionId, final Revision requestEntity) {
		return update(new Constant<>(fileId), new Constant<>(revisionId),
				new Constant<>(requestEntity));
	}

	@Override
	public IEvaluation<Revision> update(final IEvaluation<String> fileId,
			final IEvaluation<String> revisionId,
			final IEvaluation<Revision> requestEntity) {
		return update.y(fileId, revisionId, requestEntity);
	}

}
