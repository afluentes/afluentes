package com.google.api.services.drive;

import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;

import afluentes.core.api.IAsynchronousFunction1;
import afluentes.core.api.IAsynchronousFunction2;
import afluentes.core.api.IAsynchronousFunction3;
import afluentes.core.api.IAsynchronousFunction4;
import afluentes.core.api.ICallback;
import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator1;
import afluentes.core.api.IEvaluator2;
import afluentes.core.api.IEvaluator3;
import afluentes.core.api.IEvaluator4;
import afluentes.core.impl.AsynchronousEvaluator1;
import afluentes.core.impl.AsynchronousEvaluator2;
import afluentes.core.impl.AsynchronousEvaluator3;
import afluentes.core.impl.AsynchronousEvaluator4;
import afluentes.core.impl.Constant;
import afluentes.middleware.ws.rs.client.ICallbackAdapter;

@SuppressWarnings("unused")
public class PropertiesAsyncResourceImpl implements IPropertiesAsyncResource {
	private final Client client;
	private final String uri;

	private final IEvaluator3<String, String, String, Void> delete;
	private final IEvaluator3<String, String, String, Property> get;
	private final IEvaluator2<String, Property, Property> insert;
	private final IEvaluator1<String, PropertyList> list;
	private final IEvaluator4<String, String, String, Property, Property> patch;
	private final IEvaluator4<String, String, String, Property, Property> update;

	public PropertiesAsyncResourceImpl(final Client client, final String uri) {
		this.client = client;
		this.uri = uri;

		this.delete = new AsynchronousEvaluator3<>(
				new IAsynchronousFunction3<String, String, String, Void>() {
					@Override
					public void y(String fileId, String propertyKey,
							String visibility, ICallback<Void> callback) {
						WebTarget target = client.target(uri);
						target = target
								.path("{fileId}/properties/{propertyKey}");

						target = target.path(fileId);
						target = target.path(propertyKey);

						target = target.queryParam("visibility", visibility);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("DELETE", new ICallbackAdapter<Void>(
								callback) {
						});
					}
				});

		this.get = new AsynchronousEvaluator3<>(
				new IAsynchronousFunction3<String, String, String, Property>() {
					@Override
					public void y(String fileId, String propertyKey,
							String visibility, ICallback<Property> callback) {
						WebTarget target = client.target(uri);
						target = target
								.path("{fileId}/properties/{propertyKey}");

						target = target.path(fileId);
						target = target.path(propertyKey);

						target = target.queryParam("visibility", visibility);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("GET", new ICallbackAdapter<Property>(
								callback) {
						});
					}
				});

		this.insert = new AsynchronousEvaluator2<>(
				new IAsynchronousFunction2<String, Property, Property>() {
					@Override
					public void y(String fileId, Property requestEntity,
							ICallback<Property> callback) {
						WebTarget target = client.target(uri);
						target = target.path("{fileId}/properties");

						target = target.path(fileId);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("POST", Entity.json(requestEntity),
								new ICallbackAdapter<Property>(callback) {
								});
					}
				});

		this.list = new AsynchronousEvaluator1<>(
				new IAsynchronousFunction1<String, PropertyList>() {
					@Override
					public void y(String fileId,
							ICallback<PropertyList> callback) {
						WebTarget target = client.target(uri);
						target = target.path("{fileId}/properties");

						target = target.path(fileId);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("GET",
								new ICallbackAdapter<PropertyList>(callback) {
								});
					}
				});

		this.patch = new AsynchronousEvaluator4<>(
				new IAsynchronousFunction4<String, String, String, Property, Property>() {
					@Override
					public void y(String fileId, String propertyKey,
							String visibility, Property requestEntity,
							ICallback<Property> callback) {
						WebTarget target = client.target(uri);
						target = target
								.path("{fileId}/properties/{propertyKey}");

						target = target.path(fileId);
						target = target.path(propertyKey);

						target = target.queryParam("visibility", visibility);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("PATCH", Entity.json(requestEntity),
								new ICallbackAdapter<Property>(callback) {
								});
					}
				});

		this.update = new AsynchronousEvaluator4<>(
				new IAsynchronousFunction4<String, String, String, Property, Property>() {
					@Override
					public void y(String fileId, String propertyKey,
							String visibility, Property requestEntity,
							ICallback<Property> callback) {
						WebTarget target = client.target(uri);
						target = target
								.path("{fileId}/properties/{propertyKey}");

						target = target.path(fileId);
						target = target.path(propertyKey);

						target = target.queryParam("visibility", visibility);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("PUT", Entity.json(requestEntity),
								new ICallbackAdapter<Property>(callback) {
								});
					}
				});

	}

	@Override
	public IEvaluation<Void> delete(final String fileId,
			final String propertyKey, final String visibility) {
		return delete(new Constant<>(fileId), new Constant<>(propertyKey),
				new Constant<>(visibility));
	}

	@Override
	public IEvaluation<Void> delete(final IEvaluation<String> fileId,
			final IEvaluation<String> propertyKey,
			final IEvaluation<String> visibility) {
		return delete.y(fileId, propertyKey, visibility);
	}

	@Override
	public IEvaluation<Property> get(final String fileId,
			final String propertyKey, final String visibility) {
		return get(new Constant<>(fileId), new Constant<>(propertyKey),
				new Constant<>(visibility));
	}

	@Override
	public IEvaluation<Property> get(final IEvaluation<String> fileId,
			final IEvaluation<String> propertyKey,
			final IEvaluation<String> visibility) {
		return get.y(fileId, propertyKey, visibility);
	}

	@Override
	public IEvaluation<Property> insert(final String fileId,
			final Property requestEntity) {
		return insert(new Constant<>(fileId), new Constant<>(requestEntity));
	}

	@Override
	public IEvaluation<Property> insert(final IEvaluation<String> fileId,
			final IEvaluation<Property> requestEntity) {
		return insert.y(fileId, requestEntity);
	}

	@Override
	public IEvaluation<PropertyList> list(final String fileId) {
		return list(new Constant<>(fileId));
	}

	@Override
	public IEvaluation<PropertyList> list(final IEvaluation<String> fileId) {
		return list.y(fileId);
	}

	@Override
	public IEvaluation<Property> patch(final String fileId,
			final String propertyKey, final String visibility,
			final Property requestEntity) {
		return patch(new Constant<>(fileId), new Constant<>(propertyKey),
				new Constant<>(visibility), new Constant<>(requestEntity));
	}

	@Override
	public IEvaluation<Property> patch(final IEvaluation<String> fileId,
			final IEvaluation<String> propertyKey,
			final IEvaluation<String> visibility,
			final IEvaluation<Property> requestEntity) {
		return patch.y(fileId, propertyKey, visibility, requestEntity);
	}

	@Override
	public IEvaluation<Property> update(final String fileId,
			final String propertyKey, final String visibility,
			final Property requestEntity) {
		return update(new Constant<>(fileId), new Constant<>(propertyKey),
				new Constant<>(visibility), new Constant<>(requestEntity));
	}

	@Override
	public IEvaluation<Property> update(final IEvaluation<String> fileId,
			final IEvaluation<String> propertyKey,
			final IEvaluation<String> visibility,
			final IEvaluation<Property> requestEntity) {
		return update.y(fileId, propertyKey, visibility, requestEntity);
	}

}
