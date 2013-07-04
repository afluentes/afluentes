package com.google.api.services.plus;

import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;

import afluentes.core.api.IAsynchronousFunction1;
import afluentes.core.api.IAsynchronousFunction4;
import afluentes.core.api.IAsynchronousFunction5;
import afluentes.core.api.ICallback;
import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator1;
import afluentes.core.api.IEvaluator4;
import afluentes.core.api.IEvaluator5;
import afluentes.core.impl.AsynchronousEvaluator1;
import afluentes.core.impl.AsynchronousEvaluator4;
import afluentes.core.impl.AsynchronousEvaluator5;
import afluentes.core.impl.Constant;
import afluentes.middleware.ws.rs.client.ICallbackAdapter;

@SuppressWarnings("unused")
public class ActivitiesAsyncResourceImpl implements IActivitiesAsyncResource {
	private final Client client;
	private final String uri;

	private final IEvaluator1<String, Activity> get;
	private final IEvaluator4<String, Integer, String, String, ActivityFeed> list;
	private final IEvaluator5<String, Integer, String, String, String, ActivityFeed> search;

	public ActivitiesAsyncResourceImpl(final Client client, final String uri) {
		this.client = client;
		this.uri = uri;

		this.get = new AsynchronousEvaluator1<>(
				new IAsynchronousFunction1<String, Activity>() {
					@Override
					public void y(String activityId,
							ICallback<Activity> callback) {
						WebTarget target = client.target(uri);
						target = target.path("{activityId}");

						target = target.path(activityId);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("GET", new ICallbackAdapter<Activity>(
								callback) {
						});
					}
				});

		this.list = new AsynchronousEvaluator4<>(
				new IAsynchronousFunction4<String, Integer, String, String, ActivityFeed>() {
					@Override
					public void y(String collection, Integer maxResults,
							String pageToken, String userId,
							ICallback<ActivityFeed> callback) {
						WebTarget target = client.target(uri);
						target = target
								.path("{userId}/activities/{collection}");

						target = target.path(collection);
						target = target.path(userId);

						target = target.queryParam("maxResults", maxResults);
						target = target.queryParam("pageToken", pageToken);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("GET",
								new ICallbackAdapter<ActivityFeed>(callback) {
								});
					}
				});

		this.search = new AsynchronousEvaluator5<>(
				new IAsynchronousFunction5<String, Integer, String, String, String, ActivityFeed>() {
					@Override
					public void y(String language, Integer maxResults,
							String orderBy, String pageToken, String query,
							ICallback<ActivityFeed> callback) {
						WebTarget target = client.target(uri);

						target = target.queryParam("language", language);
						target = target.queryParam("maxResults", maxResults);
						target = target.queryParam("orderBy", orderBy);
						target = target.queryParam("pageToken", pageToken);
						target = target.queryParam("query", query);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("GET",
								new ICallbackAdapter<ActivityFeed>(callback) {
								});
					}
				});

	}

	@Override
	public IEvaluation<Activity> get(final String activityId) {
		return get(new Constant<>(activityId));
	}

	@Override
	public IEvaluation<Activity> get(final IEvaluation<String> activityId) {
		return get.y(activityId);
	}

	@Override
	public IEvaluation<ActivityFeed> list(final String collection,
			final Integer maxResults, final String pageToken,
			final String userId) {
		return list(new Constant<>(collection), new Constant<>(maxResults),
				new Constant<>(pageToken), new Constant<>(userId));
	}

	@Override
	public IEvaluation<ActivityFeed> list(final IEvaluation<String> collection,
			final IEvaluation<Integer> maxResults,
			final IEvaluation<String> pageToken,
			final IEvaluation<String> userId) {
		return list.y(collection, maxResults, pageToken, userId);
	}

	@Override
	public IEvaluation<ActivityFeed> search(final String language,
			final Integer maxResults, final String orderBy,
			final String pageToken, final String query) {
		return search(new Constant<>(language), new Constant<>(maxResults),
				new Constant<>(orderBy), new Constant<>(pageToken),
				new Constant<>(query));
	}

	@Override
	public IEvaluation<ActivityFeed> search(final IEvaluation<String> language,
			final IEvaluation<Integer> maxResults,
			final IEvaluation<String> orderBy,
			final IEvaluation<String> pageToken, final IEvaluation<String> query) {
		return search.y(language, maxResults, orderBy, pageToken, query);
	}

}
