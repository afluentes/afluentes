package com.google.api.services.plus;

import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;

import afluentes.core.api.IAsynchronousFunction1;
import afluentes.core.api.IAsynchronousFunction4;
import afluentes.core.api.ICallback;
import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator1;
import afluentes.core.api.IEvaluator4;
import afluentes.core.impl.AsynchronousEvaluator1;
import afluentes.core.impl.AsynchronousEvaluator4;
import afluentes.core.impl.Constant;
import afluentes.middleware.ws.rs.client.ICallbackAdapter;

@SuppressWarnings("unused")
public class CommentsAsyncResourceImpl implements ICommentsAsyncResource {
	private final Client client;
	private final String uri;

	private final IEvaluator1<String, Comment> get;
	private final IEvaluator4<String, Integer, String, String, CommentFeed> list;

	public CommentsAsyncResourceImpl(final Client client, final String uri) {
		this.client = client;
		this.uri = uri;

		this.get = new AsynchronousEvaluator1<>(
				new IAsynchronousFunction1<String, Comment>() {
					@Override
					public void y(String commentId, ICallback<Comment> callback) {
						WebTarget target = client.target(uri);
						target = target.path("{commentId}");

						target = target.path(commentId);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("GET", new ICallbackAdapter<Comment>(
								callback) {
						});
					}
				});

		this.list = new AsynchronousEvaluator4<>(
				new IAsynchronousFunction4<String, Integer, String, String, CommentFeed>() {
					@Override
					public void y(String activityId, Integer maxResults,
							String pageToken, String sortOrder,
							ICallback<CommentFeed> callback) {
						WebTarget target = client.target(uri);
						target = target.path("{activityId}/comments");

						target = target.path(activityId);

						target = target.queryParam("maxResults", maxResults);
						target = target.queryParam("pageToken", pageToken);
						target = target.queryParam("sortOrder", sortOrder);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("GET",
								new ICallbackAdapter<CommentFeed>(callback) {
								});
					}
				});

	}

	@Override
	public IEvaluation<Comment> get(final String commentId) {
		return get(new Constant<>(commentId));
	}

	@Override
	public IEvaluation<Comment> get(final IEvaluation<String> commentId) {
		return get.y(commentId);
	}

	@Override
	public IEvaluation<CommentFeed> list(final String activityId,
			final Integer maxResults, final String pageToken,
			final String sortOrder) {
		return list(new Constant<>(activityId), new Constant<>(maxResults),
				new Constant<>(pageToken), new Constant<>(sortOrder));
	}

	@Override
	public IEvaluation<CommentFeed> list(final IEvaluation<String> activityId,
			final IEvaluation<Integer> maxResults,
			final IEvaluation<String> pageToken,
			final IEvaluation<String> sortOrder) {
		return list.y(activityId, maxResults, pageToken, sortOrder);
	}

}
