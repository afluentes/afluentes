package com.google.api.services.drive;

import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;

import afluentes.core.api.IAsynchronousFunction3;
import afluentes.core.api.IAsynchronousFunction4;
import afluentes.core.api.IAsynchronousFunction5;
import afluentes.core.api.ICallback;
import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator3;
import afluentes.core.api.IEvaluator4;
import afluentes.core.api.IEvaluator5;
import afluentes.core.impl.AsynchronousEvaluator3;
import afluentes.core.impl.AsynchronousEvaluator4;
import afluentes.core.impl.AsynchronousEvaluator5;
import afluentes.core.impl.Constant;
import afluentes.middleware.ws.rs.client.ICallbackAdapter;

@SuppressWarnings("unused")
public class RepliesAsyncResourceImpl implements IRepliesAsyncResource {
	private final Client client;
	private final String uri;

	private final IEvaluator3<String, String, String, Void> delete;
	private final IEvaluator4<String, String, Boolean, String, CommentReply> get;
	private final IEvaluator3<String, String, CommentReply, CommentReply> insert;
	private final IEvaluator5<String, String, Boolean, Integer, String, CommentReplyList> list;
	private final IEvaluator4<String, String, String, CommentReply, CommentReply> patch;
	private final IEvaluator4<String, String, String, CommentReply, CommentReply> update;

	public RepliesAsyncResourceImpl(final Client client, final String uri) {
		this.client = client;
		this.uri = uri;

		this.delete = new AsynchronousEvaluator3<>(
				new IAsynchronousFunction3<String, String, String, Void>() {
					@Override
					public void y(String commentId, String fileId,
							String replyId, ICallback<Void> callback) {
						WebTarget target = client.target(uri);
						target = target
								.path("{fileId}/comments/{commentId}/replies/{replyId}");

						target = target.path(commentId);
						target = target.path(fileId);
						target = target.path(replyId);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("DELETE", new ICallbackAdapter<Void>(
								callback) {
						});
					}
				});

		this.get = new AsynchronousEvaluator4<>(
				new IAsynchronousFunction4<String, String, Boolean, String, CommentReply>() {
					@Override
					public void y(String commentId, String fileId,
							Boolean includeDeleted, String replyId,
							ICallback<CommentReply> callback) {
						WebTarget target = client.target(uri);
						target = target
								.path("{fileId}/comments/{commentId}/replies/{replyId}");

						target = target.path(commentId);
						target = target.path(fileId);
						target = target.path(replyId);

						target = target.queryParam("includeDeleted",
								includeDeleted);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("GET",
								new ICallbackAdapter<CommentReply>(callback) {
								});
					}
				});

		this.insert = new AsynchronousEvaluator3<>(
				new IAsynchronousFunction3<String, String, CommentReply, CommentReply>() {
					@Override
					public void y(String commentId, String fileId,
							CommentReply requestEntity,
							ICallback<CommentReply> callback) {
						WebTarget target = client.target(uri);
						target = target
								.path("{fileId}/comments/{commentId}/replies");

						target = target.path(commentId);
						target = target.path(fileId);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("POST", Entity.json(requestEntity),
								new ICallbackAdapter<CommentReply>(callback) {
								});
					}
				});

		this.list = new AsynchronousEvaluator5<>(
				new IAsynchronousFunction5<String, String, Boolean, Integer, String, CommentReplyList>() {
					@Override
					public void y(String commentId, String fileId,
							Boolean includeDeleted, Integer maxResults,
							String pageToken,
							ICallback<CommentReplyList> callback) {
						WebTarget target = client.target(uri);
						target = target
								.path("{fileId}/comments/{commentId}/replies");

						target = target.path(commentId);
						target = target.path(fileId);

						target = target.queryParam("includeDeleted",
								includeDeleted);
						target = target.queryParam("maxResults", maxResults);
						target = target.queryParam("pageToken", pageToken);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method(
								"GET",
								new ICallbackAdapter<CommentReplyList>(callback) {
								});
					}
				});

		this.patch = new AsynchronousEvaluator4<>(
				new IAsynchronousFunction4<String, String, String, CommentReply, CommentReply>() {
					@Override
					public void y(String commentId, String fileId,
							String replyId, CommentReply requestEntity,
							ICallback<CommentReply> callback) {
						WebTarget target = client.target(uri);
						target = target
								.path("{fileId}/comments/{commentId}/replies/{replyId}");

						target = target.path(commentId);
						target = target.path(fileId);
						target = target.path(replyId);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("PATCH", Entity.json(requestEntity),
								new ICallbackAdapter<CommentReply>(callback) {
								});
					}
				});

		this.update = new AsynchronousEvaluator4<>(
				new IAsynchronousFunction4<String, String, String, CommentReply, CommentReply>() {
					@Override
					public void y(String commentId, String fileId,
							String replyId, CommentReply requestEntity,
							ICallback<CommentReply> callback) {
						WebTarget target = client.target(uri);
						target = target
								.path("{fileId}/comments/{commentId}/replies/{replyId}");

						target = target.path(commentId);
						target = target.path(fileId);
						target = target.path(replyId);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("PUT", Entity.json(requestEntity),
								new ICallbackAdapter<CommentReply>(callback) {
								});
					}
				});

	}

	@Override
	public IEvaluation<Void> delete(final String commentId,
			final String fileId, final String replyId) {
		return delete(new Constant<>(commentId), new Constant<>(fileId),
				new Constant<>(replyId));
	}

	@Override
	public IEvaluation<Void> delete(final IEvaluation<String> commentId,
			final IEvaluation<String> fileId, final IEvaluation<String> replyId) {
		return delete.y(commentId, fileId, replyId);
	}

	@Override
	public IEvaluation<CommentReply> get(final String commentId,
			final String fileId, final Boolean includeDeleted,
			final String replyId) {
		return get(new Constant<>(commentId), new Constant<>(fileId),
				new Constant<>(includeDeleted), new Constant<>(replyId));
	}

	@Override
	public IEvaluation<CommentReply> get(final IEvaluation<String> commentId,
			final IEvaluation<String> fileId,
			final IEvaluation<Boolean> includeDeleted,
			final IEvaluation<String> replyId) {
		return get.y(commentId, fileId, includeDeleted, replyId);
	}

	@Override
	public IEvaluation<CommentReply> insert(final String commentId,
			final String fileId, final CommentReply requestEntity) {
		return insert(new Constant<>(commentId), new Constant<>(fileId),
				new Constant<>(requestEntity));
	}

	@Override
	public IEvaluation<CommentReply> insert(
			final IEvaluation<String> commentId,
			final IEvaluation<String> fileId,
			final IEvaluation<CommentReply> requestEntity) {
		return insert.y(commentId, fileId, requestEntity);
	}

	@Override
	public IEvaluation<CommentReplyList> list(final String commentId,
			final String fileId, final Boolean includeDeleted,
			final Integer maxResults, final String pageToken) {
		return list(new Constant<>(commentId), new Constant<>(fileId),
				new Constant<>(includeDeleted), new Constant<>(maxResults),
				new Constant<>(pageToken));
	}

	@Override
	public IEvaluation<CommentReplyList> list(
			final IEvaluation<String> commentId,
			final IEvaluation<String> fileId,
			final IEvaluation<Boolean> includeDeleted,
			final IEvaluation<Integer> maxResults,
			final IEvaluation<String> pageToken) {
		return list.y(commentId, fileId, includeDeleted, maxResults, pageToken);
	}

	@Override
	public IEvaluation<CommentReply> patch(final String commentId,
			final String fileId, final String replyId,
			final CommentReply requestEntity) {
		return patch(new Constant<>(commentId), new Constant<>(fileId),
				new Constant<>(replyId), new Constant<>(requestEntity));
	}

	@Override
	public IEvaluation<CommentReply> patch(final IEvaluation<String> commentId,
			final IEvaluation<String> fileId,
			final IEvaluation<String> replyId,
			final IEvaluation<CommentReply> requestEntity) {
		return patch.y(commentId, fileId, replyId, requestEntity);
	}

	@Override
	public IEvaluation<CommentReply> update(final String commentId,
			final String fileId, final String replyId,
			final CommentReply requestEntity) {
		return update(new Constant<>(commentId), new Constant<>(fileId),
				new Constant<>(replyId), new Constant<>(requestEntity));
	}

	@Override
	public IEvaluation<CommentReply> update(
			final IEvaluation<String> commentId,
			final IEvaluation<String> fileId,
			final IEvaluation<String> replyId,
			final IEvaluation<CommentReply> requestEntity) {
		return update.y(commentId, fileId, replyId, requestEntity);
	}

}
