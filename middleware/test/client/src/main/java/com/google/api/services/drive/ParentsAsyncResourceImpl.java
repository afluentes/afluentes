package com.google.api.services.drive;

import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;

import afluentes.core.api.IAsynchronousFunction1;
import afluentes.core.api.IAsynchronousFunction2;
import afluentes.core.api.ICallback;
import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator1;
import afluentes.core.api.IEvaluator2;
import afluentes.core.impl.AsynchronousEvaluator1;
import afluentes.core.impl.AsynchronousEvaluator2;
import afluentes.core.impl.Constant;
import afluentes.middleware.ws.rs.client.ICallbackAdapter;

@SuppressWarnings("unused")
public class ParentsAsyncResourceImpl implements IParentsAsyncResource {
	private final Client client;
	private final String uri;

	private final IEvaluator2<String, String, Void> delete;
	private final IEvaluator2<String, String, ParentReference> get;
	private final IEvaluator2<String, ParentReference, ParentReference> insert;
	private final IEvaluator1<String, ParentList> list;

	public ParentsAsyncResourceImpl(final Client client, final String uri) {
		this.client = client;
		this.uri = uri;

		this.delete = new AsynchronousEvaluator2<>(
				new IAsynchronousFunction2<String, String, Void>() {
					@Override
					public void y(String fileId, String parentId,
							ICallback<Void> callback) {
						WebTarget target = client.target(uri);
						target = target.path("{fileId}/parents/{parentId}");

						target = target.path(fileId);
						target = target.path(parentId);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("DELETE", new ICallbackAdapter<Void>(
								callback) {
						});
					}
				});

		this.get = new AsynchronousEvaluator2<>(
				new IAsynchronousFunction2<String, String, ParentReference>() {
					@Override
					public void y(String fileId, String parentId,
							ICallback<ParentReference> callback) {
						WebTarget target = client.target(uri);
						target = target.path("{fileId}/parents/{parentId}");

						target = target.path(fileId);
						target = target.path(parentId);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method(
								"GET",
								new ICallbackAdapter<ParentReference>(callback) {
								});
					}
				});

		this.insert = new AsynchronousEvaluator2<>(
				new IAsynchronousFunction2<String, ParentReference, ParentReference>() {
					@Override
					public void y(String fileId, ParentReference requestEntity,
							ICallback<ParentReference> callback) {
						WebTarget target = client.target(uri);
						target = target.path("{fileId}/parents");

						target = target.path(fileId);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method(
								"POST",
								Entity.json(requestEntity),
								new ICallbackAdapter<ParentReference>(callback) {
								});
					}
				});

		this.list = new AsynchronousEvaluator1<>(
				new IAsynchronousFunction1<String, ParentList>() {
					@Override
					public void y(String fileId, ICallback<ParentList> callback) {
						WebTarget target = client.target(uri);
						target = target.path("{fileId}/parents");

						target = target.path(fileId);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("GET", new ICallbackAdapter<ParentList>(
								callback) {
						});
					}
				});

	}

	@Override
	public IEvaluation<Void> delete(final String fileId, final String parentId) {
		return delete(new Constant<>(fileId), new Constant<>(parentId));
	}

	@Override
	public IEvaluation<Void> delete(final IEvaluation<String> fileId,
			final IEvaluation<String> parentId) {
		return delete.y(fileId, parentId);
	}

	@Override
	public IEvaluation<ParentReference> get(final String fileId,
			final String parentId) {
		return get(new Constant<>(fileId), new Constant<>(parentId));
	}

	@Override
	public IEvaluation<ParentReference> get(final IEvaluation<String> fileId,
			final IEvaluation<String> parentId) {
		return get.y(fileId, parentId);
	}

	@Override
	public IEvaluation<ParentReference> insert(final String fileId,
			final ParentReference requestEntity) {
		return insert(new Constant<>(fileId), new Constant<>(requestEntity));
	}

	@Override
	public IEvaluation<ParentReference> insert(
			final IEvaluation<String> fileId,
			final IEvaluation<ParentReference> requestEntity) {
		return insert.y(fileId, requestEntity);
	}

	@Override
	public IEvaluation<ParentList> list(final String fileId) {
		return list(new Constant<>(fileId));
	}

	@Override
	public IEvaluation<ParentList> list(final IEvaluation<String> fileId) {
		return list.y(fileId);
	}

}
