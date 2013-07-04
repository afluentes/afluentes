package com.google.api.services.drive;

import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;

import afluentes.core.api.IAsynchronousFunction3;
import afluentes.core.api.ICallback;
import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator3;
import afluentes.core.impl.AsynchronousEvaluator3;
import afluentes.core.impl.Constant;
import afluentes.middleware.ws.rs.client.ICallbackAdapter;

@SuppressWarnings("unused")
public class AboutAsyncResourceImpl implements IAboutAsyncResource {
	private final Client client;
	private final String uri;

	private final IEvaluator3<Boolean, String, String, About> get;

	public AboutAsyncResourceImpl(final Client client, final String uri) {
		this.client = client;
		this.uri = uri;

		this.get = new AsynchronousEvaluator3<>(
				new IAsynchronousFunction3<Boolean, String, String, About>() {
					@Override
					public void y(Boolean includeSubscribed,
							String maxChangeIdCount, String startChangeId,
							ICallback<About> callback) {
						WebTarget target = client.target(uri);

						target = target.queryParam("includeSubscribed",
								includeSubscribed);
						target = target.queryParam("maxChangeIdCount",
								maxChangeIdCount);
						target = target.queryParam("startChangeId",
								startChangeId);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("GET", new ICallbackAdapter<About>(
								callback) {
						});
					}
				});

	}

	@Override
	public IEvaluation<About> get(final Boolean includeSubscribed,
			final String maxChangeIdCount, final String startChangeId) {
		return get(new Constant<>(includeSubscribed), new Constant<>(
				maxChangeIdCount), new Constant<>(startChangeId));
	}

	@Override
	public IEvaluation<About> get(final IEvaluation<Boolean> includeSubscribed,
			final IEvaluation<String> maxChangeIdCount,
			final IEvaluation<String> startChangeId) {
		return get.y(includeSubscribed, maxChangeIdCount, startChangeId);
	}

}
