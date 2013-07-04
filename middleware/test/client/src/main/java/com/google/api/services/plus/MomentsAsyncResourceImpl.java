package com.google.api.services.plus;

import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;

import afluentes.core.api.IAsynchronousFunction1;
import afluentes.core.api.IAsynchronousFunction4;
import afluentes.core.api.IAsynchronousFunction6;
import afluentes.core.api.ICallback;
import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator1;
import afluentes.core.api.IEvaluator4;
import afluentes.core.api.IEvaluator6;
import afluentes.core.impl.AsynchronousEvaluator1;
import afluentes.core.impl.AsynchronousEvaluator4;
import afluentes.core.impl.AsynchronousEvaluator6;
import afluentes.core.impl.Constant;
import afluentes.middleware.ws.rs.client.ICallbackAdapter;

@SuppressWarnings("unused")
public class MomentsAsyncResourceImpl implements IMomentsAsyncResource {
	private final Client client;
	private final String uri;

	private final IEvaluator4<String, Boolean, String, Moment, Moment> insert;
	private final IEvaluator6<String, Integer, String, String, String, String, MomentsFeed> list;
	private final IEvaluator1<String, Void> remove;

	public MomentsAsyncResourceImpl(final Client client, final String uri) {
		this.client = client;
		this.uri = uri;

		this.insert = new AsynchronousEvaluator4<>(
				new IAsynchronousFunction4<String, Boolean, String, Moment, Moment>() {
					@Override
					public void y(String collection, Boolean debug,
							String userId, Moment requestEntity,
							ICallback<Moment> callback) {
						WebTarget target = client.target(uri);
						target = target.path("{userId}/moments/{collection}");

						target = target.path(collection);
						target = target.path(userId);

						target = target.queryParam("debug", debug);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("POST", Entity.json(requestEntity),
								new ICallbackAdapter<Moment>(callback) {
								});
					}
				});

		this.list = new AsynchronousEvaluator6<>(
				new IAsynchronousFunction6<String, Integer, String, String, String, String, MomentsFeed>() {
					@Override
					public void y(String collection, Integer maxResults,
							String pageToken, String targetUrl, String type,
							String userId, ICallback<MomentsFeed> callback) {
						WebTarget target = client.target(uri);
						target = target.path("{userId}/moments/{collection}");

						target = target.path(collection);
						target = target.path(userId);

						target = target.queryParam("maxResults", maxResults);
						target = target.queryParam("pageToken", pageToken);
						target = target.queryParam("targetUrl", targetUrl);
						target = target.queryParam("type", type);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("GET",
								new ICallbackAdapter<MomentsFeed>(callback) {
								});
					}
				});

		this.remove = new AsynchronousEvaluator1<>(
				new IAsynchronousFunction1<String, Void>() {
					@Override
					public void y(String id, ICallback<Void> callback) {
						WebTarget target = client.target(uri);
						target = target.path("{id}");

						target = target.path(id);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("DELETE", new ICallbackAdapter<Void>(
								callback) {
						});
					}
				});

	}

	@Override
	public IEvaluation<Moment> insert(final String collection,
			final Boolean debug, final String userId, final Moment requestEntity) {
		return insert(new Constant<>(collection), new Constant<>(debug),
				new Constant<>(userId), new Constant<>(requestEntity));
	}

	@Override
	public IEvaluation<Moment> insert(final IEvaluation<String> collection,
			final IEvaluation<Boolean> debug, final IEvaluation<String> userId,
			final IEvaluation<Moment> requestEntity) {
		return insert.y(collection, debug, userId, requestEntity);
	}

	@Override
	public IEvaluation<MomentsFeed> list(final String collection,
			final Integer maxResults, final String pageToken,
			final String targetUrl, final String type, final String userId) {
		return list(new Constant<>(collection), new Constant<>(maxResults),
				new Constant<>(pageToken), new Constant<>(targetUrl),
				new Constant<>(type), new Constant<>(userId));
	}

	@Override
	public IEvaluation<MomentsFeed> list(final IEvaluation<String> collection,
			final IEvaluation<Integer> maxResults,
			final IEvaluation<String> pageToken,
			final IEvaluation<String> targetUrl,
			final IEvaluation<String> type, final IEvaluation<String> userId) {
		return list.y(collection, maxResults, pageToken, targetUrl, type,
				userId);
	}

	@Override
	public IEvaluation<Void> remove(final String id) {
		return remove(new Constant<>(id));
	}

	@Override
	public IEvaluation<Void> remove(final IEvaluation<String> id) {
		return remove.y(id);
	}

}
