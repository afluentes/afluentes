package com.google.api.services.discovery;

import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;

import afluentes.core.api.IAsynchronousFunction2;
import afluentes.core.api.ICallback;
import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator2;
import afluentes.core.impl.AsynchronousEvaluator2;
import afluentes.core.impl.Constant;
import afluentes.middleware.ws.rs.client.ICallbackAdapter;

@SuppressWarnings("unused")
public class ApisAsyncResourceImpl implements IApisAsyncResource {
	private final Client client;
	private final String uri;

	private final IEvaluator2<String, String, RestDescription> getRest;
	private final IEvaluator2<String, Boolean, DirectoryList> list;

	public ApisAsyncResourceImpl(final Client client, final String uri) {
		this.client = client;
		this.uri = uri;

		this.getRest = new AsynchronousEvaluator2<>(
				new IAsynchronousFunction2<String, String, RestDescription>() {
					@Override
					public void y(String api, String version,
							ICallback<RestDescription> callback) {
						WebTarget target = client.target(uri);
						target = target.path("{api}/{version}/rest");

						target = target.path(api);
						target = target.path(version);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method(
								"GET",
								new ICallbackAdapter<RestDescription>(callback) {
								});
					}
				});

		this.list = new AsynchronousEvaluator2<>(
				new IAsynchronousFunction2<String, Boolean, DirectoryList>() {
					@Override
					public void y(String name, Boolean preferred,
							ICallback<DirectoryList> callback) {
						WebTarget target = client.target(uri);

						target = target.queryParam("name", name);
						target = target.queryParam("preferred", preferred);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("GET",
								new ICallbackAdapter<DirectoryList>(callback) {
								});
					}
				});

	}

	@Override
	public IEvaluation<RestDescription> getRest(final String api,
			final String version) {
		return getRest(new Constant<>(api), new Constant<>(version));
	}

	@Override
	public IEvaluation<RestDescription> getRest(final IEvaluation<String> api,
			final IEvaluation<String> version) {
		return getRest.y(api, version);
	}

	@Override
	public IEvaluation<DirectoryList> list(final String name,
			final Boolean preferred) {
		return list(new Constant<>(name), new Constant<>(preferred));
	}

	@Override
	public IEvaluation<DirectoryList> list(final IEvaluation<String> name,
			final IEvaluation<Boolean> preferred) {
		return list.y(name, preferred);
	}

}
