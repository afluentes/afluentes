package com.google.api.services.drive;

import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;

import afluentes.core.api.IAsynchronousFunction0;
import afluentes.core.api.IAsynchronousFunction1;
import afluentes.core.api.ICallback;
import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator0;
import afluentes.core.api.IEvaluator1;
import afluentes.core.impl.AsynchronousEvaluator0;
import afluentes.core.impl.AsynchronousEvaluator1;
import afluentes.core.impl.Constant;
import afluentes.middleware.ws.rs.client.ICallbackAdapter;

@SuppressWarnings("unused")
public class AppsAsyncResourceImpl implements IAppsAsyncResource {
	private final Client client;
	private final String uri;

	private final IEvaluator1<String, App> get;
	private final IEvaluator0<AppList> list;

	public AppsAsyncResourceImpl(final Client client, final String uri) {
		this.client = client;
		this.uri = uri;

		this.get = new AsynchronousEvaluator1<>(
				new IAsynchronousFunction1<String, App>() {
					@Override
					public void y(String appId, ICallback<App> callback) {
						WebTarget target = client.target(uri);
						target = target.path("{appId}");

						target = target.path(appId);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("GET", new ICallbackAdapter<App>(
								callback) {
						});
					}
				});

		this.list = new AsynchronousEvaluator0<>(
				new IAsynchronousFunction0<AppList>() {
					@Override
					public void y(ICallback<AppList> callback) {
						WebTarget target = client.target(uri);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("GET", new ICallbackAdapter<AppList>(
								callback) {
						});
					}
				});

	}

	@Override
	public IEvaluation<App> get(final String appId) {
		return get(new Constant<>(appId));
	}

	@Override
	public IEvaluation<App> get(final IEvaluation<String> appId) {
		return get.y(appId);
	}

	@Override
	public IEvaluation<AppList> list() {
		return list();
	}

}
