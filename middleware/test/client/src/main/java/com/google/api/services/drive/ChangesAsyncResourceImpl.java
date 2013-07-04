package com.google.api.services.drive;

import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;

import afluentes.core.api.IAsynchronousFunction1;
import afluentes.core.api.IAsynchronousFunction5;
import afluentes.core.api.ICallback;
import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator1;
import afluentes.core.api.IEvaluator5;
import afluentes.core.impl.AsynchronousEvaluator1;
import afluentes.core.impl.AsynchronousEvaluator5;
import afluentes.core.impl.Constant;
import afluentes.middleware.ws.rs.client.ICallbackAdapter;

@SuppressWarnings("unused")
public class ChangesAsyncResourceImpl implements IChangesAsyncResource {
	private final Client client;
	private final String uri;

	private final IEvaluator1<String, Change> get;
	private final IEvaluator5<Boolean, Boolean, Integer, String, String, ChangeList> list;

	public ChangesAsyncResourceImpl(final Client client, final String uri) {
		this.client = client;
		this.uri = uri;

		this.get = new AsynchronousEvaluator1<>(
				new IAsynchronousFunction1<String, Change>() {
					@Override
					public void y(String changeId, ICallback<Change> callback) {
						WebTarget target = client.target(uri);
						target = target.path("{changeId}");

						target = target.path(changeId);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("GET", new ICallbackAdapter<Change>(
								callback) {
						});
					}
				});

		this.list = new AsynchronousEvaluator5<>(
				new IAsynchronousFunction5<Boolean, Boolean, Integer, String, String, ChangeList>() {
					@Override
					public void y(Boolean includeDeleted,
							Boolean includeSubscribed, Integer maxResults,
							String pageToken, String startChangeId,
							ICallback<ChangeList> callback) {
						WebTarget target = client.target(uri);

						target = target.queryParam("includeDeleted",
								includeDeleted);
						target = target.queryParam("includeSubscribed",
								includeSubscribed);
						target = target.queryParam("maxResults", maxResults);
						target = target.queryParam("pageToken", pageToken);
						target = target.queryParam("startChangeId",
								startChangeId);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("GET", new ICallbackAdapter<ChangeList>(
								callback) {
						});
					}
				});

	}

	@Override
	public IEvaluation<Change> get(final String changeId) {
		return get(new Constant<>(changeId));
	}

	@Override
	public IEvaluation<Change> get(final IEvaluation<String> changeId) {
		return get.y(changeId);
	}

	@Override
	public IEvaluation<ChangeList> list(final Boolean includeDeleted,
			final Boolean includeSubscribed, final Integer maxResults,
			final String pageToken, final String startChangeId) {
		return list(new Constant<>(includeDeleted), new Constant<>(
				includeSubscribed), new Constant<>(maxResults), new Constant<>(
				pageToken), new Constant<>(startChangeId));
	}

	@Override
	public IEvaluation<ChangeList> list(
			final IEvaluation<Boolean> includeDeleted,
			final IEvaluation<Boolean> includeSubscribed,
			final IEvaluation<Integer> maxResults,
			final IEvaluation<String> pageToken,
			final IEvaluation<String> startChangeId) {
		return list.y(includeDeleted, includeSubscribed, maxResults, pageToken,
				startChangeId);
	}

}
