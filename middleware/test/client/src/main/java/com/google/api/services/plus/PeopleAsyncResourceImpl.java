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
public class PeopleAsyncResourceImpl implements IPeopleAsyncResource {
	private final Client client;
	private final String uri;

	private final IEvaluator1<String, Person> get;
	private final IEvaluator5<String, Integer, String, String, String, PeopleFeed> list;
	private final IEvaluator4<String, String, Integer, String, PeopleFeed> listByActivity;
	private final IEvaluator4<String, Integer, String, String, PeopleFeed> search;

	public PeopleAsyncResourceImpl(final Client client, final String uri) {
		this.client = client;
		this.uri = uri;

		this.get = new AsynchronousEvaluator1<>(
				new IAsynchronousFunction1<String, Person>() {
					@Override
					public void y(String userId, ICallback<Person> callback) {
						WebTarget target = client.target(uri);
						target = target.path("{userId}");

						target = target.path(userId);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("GET", new ICallbackAdapter<Person>(
								callback) {
						});
					}
				});

		this.list = new AsynchronousEvaluator5<>(
				new IAsynchronousFunction5<String, Integer, String, String, String, PeopleFeed>() {
					@Override
					public void y(String collection, Integer maxResults,
							String orderBy, String pageToken, String userId,
							ICallback<PeopleFeed> callback) {
						WebTarget target = client.target(uri);
						target = target.path("{userId}/people/{collection}");

						target = target.path(collection);
						target = target.path(userId);

						target = target.queryParam("maxResults", maxResults);
						target = target.queryParam("orderBy", orderBy);
						target = target.queryParam("pageToken", pageToken);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("GET", new ICallbackAdapter<PeopleFeed>(
								callback) {
						});
					}
				});

		this.listByActivity = new AsynchronousEvaluator4<>(
				new IAsynchronousFunction4<String, String, Integer, String, PeopleFeed>() {
					@Override
					public void y(String activityId, String collection,
							Integer maxResults, String pageToken,
							ICallback<PeopleFeed> callback) {
						WebTarget target = client.target(uri);
						target = target
								.path("{activityId}/people/{collection}");

						target = target.path(activityId);
						target = target.path(collection);

						target = target.queryParam("maxResults", maxResults);
						target = target.queryParam("pageToken", pageToken);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("GET", new ICallbackAdapter<PeopleFeed>(
								callback) {
						});
					}
				});

		this.search = new AsynchronousEvaluator4<>(
				new IAsynchronousFunction4<String, Integer, String, String, PeopleFeed>() {
					@Override
					public void y(String language, Integer maxResults,
							String pageToken, String query,
							ICallback<PeopleFeed> callback) {
						WebTarget target = client.target(uri);

						target = target.queryParam("language", language);
						target = target.queryParam("maxResults", maxResults);
						target = target.queryParam("pageToken", pageToken);
						target = target.queryParam("query", query);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("GET", new ICallbackAdapter<PeopleFeed>(
								callback) {
						});
					}
				});

	}

	@Override
	public IEvaluation<Person> get(final String userId) {
		return get(new Constant<>(userId));
	}

	@Override
	public IEvaluation<Person> get(final IEvaluation<String> userId) {
		return get.y(userId);
	}

	@Override
	public IEvaluation<PeopleFeed> list(final String collection,
			final Integer maxResults, final String orderBy,
			final String pageToken, final String userId) {
		return list(new Constant<>(collection), new Constant<>(maxResults),
				new Constant<>(orderBy), new Constant<>(pageToken),
				new Constant<>(userId));
	}

	@Override
	public IEvaluation<PeopleFeed> list(final IEvaluation<String> collection,
			final IEvaluation<Integer> maxResults,
			final IEvaluation<String> orderBy,
			final IEvaluation<String> pageToken,
			final IEvaluation<String> userId) {
		return list.y(collection, maxResults, orderBy, pageToken, userId);
	}

	@Override
	public IEvaluation<PeopleFeed> listByActivity(final String activityId,
			final String collection, final Integer maxResults,
			final String pageToken) {
		return listByActivity(new Constant<>(activityId), new Constant<>(
				collection), new Constant<>(maxResults), new Constant<>(
				pageToken));
	}

	@Override
	public IEvaluation<PeopleFeed> listByActivity(
			final IEvaluation<String> activityId,
			final IEvaluation<String> collection,
			final IEvaluation<Integer> maxResults,
			final IEvaluation<String> pageToken) {
		return listByActivity.y(activityId, collection, maxResults, pageToken);
	}

	@Override
	public IEvaluation<PeopleFeed> search(final String language,
			final Integer maxResults, final String pageToken, final String query) {
		return search(new Constant<>(language), new Constant<>(maxResults),
				new Constant<>(pageToken), new Constant<>(query));
	}

	@Override
	public IEvaluation<PeopleFeed> search(final IEvaluation<String> language,
			final IEvaluation<Integer> maxResults,
			final IEvaluation<String> pageToken, final IEvaluation<String> query) {
		return search.y(language, maxResults, pageToken, query);
	}

}
