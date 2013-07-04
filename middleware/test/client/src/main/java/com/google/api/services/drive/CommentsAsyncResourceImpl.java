package com.google.api.services.drive;

import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;

import afluentes.core.api.IAsynchronousFunction2;
import afluentes.core.api.IAsynchronousFunction3;
import afluentes.core.api.IAsynchronousFunction5;
import afluentes.core.api.ICallback;
import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator2;
import afluentes.core.api.IEvaluator3;
import afluentes.core.api.IEvaluator5;
import afluentes.core.impl.AsynchronousEvaluator2;
import afluentes.core.impl.AsynchronousEvaluator3;
import afluentes.core.impl.AsynchronousEvaluator5;
import afluentes.core.impl.Constant;
import afluentes.middleware.ws.rs.client.ICallbackAdapter;

@SuppressWarnings("unused")
public class CommentsAsyncResourceImpl implements ICommentsAsyncResource {
	private final Client client;
	private final String uri;

	private final IEvaluator2<String, String, Void> delete;
	private final IEvaluator3<String, String, Boolean, Comment> get;
	private final IEvaluator2<String, Comment, Comment> insert;
	private final IEvaluator5<String, Boolean, Integer, String, String, CommentList> list;
	private final IEvaluator3<String, String, Comment, Comment> patch;
	private final IEvaluator3<String, String, Comment, Comment> update;

	public CommentsAsyncResourceImpl(final Client client, final String uri) {
		this.client = client;
		this.uri = uri;

		this.delete = new AsynchronousEvaluator2<>(
				new IAsynchronousFunction2<String, String, Void>() {
					@Override
					public void y(String commentId, String fileId,
							ICallback<Void> callback) {
						WebTarget target = client.target(uri);
						target = target.path("{fileId}/comments/{commentId}");

						target = target.path(commentId);
						target = target.path(fileId);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("DELETE", new ICallbackAdapter<Void>(
								callback) {
						});
					}
				});

		this.get = new AsynchronousEvaluator3<>(
				new IAsynchronousFunction3<String, String, Boolean, Comment>() {
					@Override
					public void y(String commentId, String fileId,
							Boolean includeDeleted, ICallback<Comment> callback) {
						WebTarget target = client.target(uri);
						target = target.path("{fileId}/comments/{commentId}");

						target = target.path(commentId);
						target = target.path(fileId);

						target = target.queryParam("includeDeleted",
								includeDeleted);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("GET", new ICallbackAdapter<Comment>(
								callback) {
						});
					}
				});

		this.insert = new AsynchronousEvaluator2<>(
				new IAsynchronousFunction2<String, Comment, Comment>() {
					@Override
					public void y(String fileId, Comment requestEntity,
							ICallback<Comment> callback) {
						WebTarget target = client.target(uri);
						target = target.path("{fileId}/comments");

						target = target.path(fileId);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("POST", Entity.json(requestEntity),
								new ICallbackAdapter<Comment>(callback) {
								});
					}
				});

		this.list = new AsynchronousEvaluator5<>(
				new IAsynchronousFunction5<String, Boolean, Integer, String, String, CommentList>() {
					@Override
					public void y(String fileId, Boolean includeDeleted,
							Integer maxResults, String pageToken,
							String updatedMin, ICallback<CommentList> callback) {
						WebTarget target = client.target(uri);
						target = target.path("{fileId}/comments");

						target = target.path(fileId);

						target = target.queryParam("includeDeleted",
								includeDeleted);
						target = target.queryParam("maxResults", maxResults);
						target = target.queryParam("pageToken", pageToken);
						target = target.queryParam("updatedMin", updatedMin);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("GET",
								new ICallbackAdapter<CommentList>(callback) {
								});
					}
				});

		this.patch = new AsynchronousEvaluator3<>(
				new IAsynchronousFunction3<String, String, Comment, Comment>() {
					@Override
					public void y(String commentId, String fileId,
							Comment requestEntity, ICallback<Comment> callback) {
						WebTarget target = client.target(uri);
						target = target.path("{fileId}/comments/{commentId}");

						target = target.path(commentId);
						target = target.path(fileId);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("PATCH", Entity.json(requestEntity),
								new ICallbackAdapter<Comment>(callback) {
								});
					}
				});

		this.update = new AsynchronousEvaluator3<>(
				new IAsynchronousFunction3<String, String, Comment, Comment>() {
					@Override
					public void y(String commentId, String fileId,
							Comment requestEntity, ICallback<Comment> callback) {
						WebTarget target = client.target(uri);
						target = target.path("{fileId}/comments/{commentId}");

						target = target.path(commentId);
						target = target.path(fileId);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("PUT", Entity.json(requestEntity),
								new ICallbackAdapter<Comment>(callback) {
								});
					}
				});

	}

	@Override
	public IEvaluation<Void> delete(final String commentId, final String fileId) {
		return delete(new Constant<>(commentId), new Constant<>(fileId));
	}

	@Override
	public IEvaluation<Void> delete(final IEvaluation<String> commentId,
			final IEvaluation<String> fileId) {
		return delete.y(commentId, fileId);
	}

	@Override
	public IEvaluation<Comment> get(final String commentId,
			final String fileId, final Boolean includeDeleted) {
		return get(new Constant<>(commentId), new Constant<>(fileId),
				new Constant<>(includeDeleted));
	}

	@Override
	public IEvaluation<Comment> get(final IEvaluation<String> commentId,
			final IEvaluation<String> fileId,
			final IEvaluation<Boolean> includeDeleted) {
		return get.y(commentId, fileId, includeDeleted);
	}

	@Override
	public IEvaluation<Comment> insert(final String fileId,
			final Comment requestEntity) {
		return insert(new Constant<>(fileId), new Constant<>(requestEntity));
	}

	@Override
	public IEvaluation<Comment> insert(final IEvaluation<String> fileId,
			final IEvaluation<Comment> requestEntity) {
		return insert.y(fileId, requestEntity);
	}

	@Override
	public IEvaluation<CommentList> list(final String fileId,
			final Boolean includeDeleted, final Integer maxResults,
			final String pageToken, final String updatedMin) {
		return list(new Constant<>(fileId), new Constant<>(includeDeleted),
				new Constant<>(maxResults), new Constant<>(pageToken),
				new Constant<>(updatedMin));
	}

	@Override
	public IEvaluation<CommentList> list(final IEvaluation<String> fileId,
			final IEvaluation<Boolean> includeDeleted,
			final IEvaluation<Integer> maxResults,
			final IEvaluation<String> pageToken,
			final IEvaluation<String> updatedMin) {
		return list
				.y(fileId, includeDeleted, maxResults, pageToken, updatedMin);
	}

	@Override
	public IEvaluation<Comment> patch(final String commentId,
			final String fileId, final Comment requestEntity) {
		return patch(new Constant<>(commentId), new Constant<>(fileId),
				new Constant<>(requestEntity));
	}

	@Override
	public IEvaluation<Comment> patch(final IEvaluation<String> commentId,
			final IEvaluation<String> fileId,
			final IEvaluation<Comment> requestEntity) {
		return patch.y(commentId, fileId, requestEntity);
	}

	@Override
	public IEvaluation<Comment> update(final String commentId,
			final String fileId, final Comment requestEntity) {
		return update(new Constant<>(commentId), new Constant<>(fileId),
				new Constant<>(requestEntity));
	}

	@Override
	public IEvaluation<Comment> update(final IEvaluation<String> commentId,
			final IEvaluation<String> fileId,
			final IEvaluation<Comment> requestEntity) {
		return update.y(commentId, fileId, requestEntity);
	}

}
