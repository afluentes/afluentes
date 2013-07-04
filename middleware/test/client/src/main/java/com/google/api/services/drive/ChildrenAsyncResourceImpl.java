package com.google.api.services.drive;

import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;

import afluentes.core.api.IAsynchronousFunction2;
import afluentes.core.api.IAsynchronousFunction4;
import afluentes.core.api.ICallback;
import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator2;
import afluentes.core.api.IEvaluator4;
import afluentes.core.impl.AsynchronousEvaluator2;
import afluentes.core.impl.AsynchronousEvaluator4;
import afluentes.core.impl.Constant;
import afluentes.middleware.ws.rs.client.ICallbackAdapter;

@SuppressWarnings("unused")
public class ChildrenAsyncResourceImpl implements IChildrenAsyncResource {
	private final Client client;
	private final String uri;

	private final IEvaluator2<String, String, Void> delete;
	private final IEvaluator2<String, String, ChildReference> get;
	private final IEvaluator2<String, ChildReference, ChildReference> insert;
	private final IEvaluator4<String, Integer, String, String, ChildList> list;

	public ChildrenAsyncResourceImpl(final Client client, final String uri) {
		this.client = client;
		this.uri = uri;

		this.delete = new AsynchronousEvaluator2<>(
				new IAsynchronousFunction2<String, String, Void>() {
					@Override
					public void y(String childId, String folderId,
							ICallback<Void> callback) {
						WebTarget target = client.target(uri);
						target = target.path("{folderId}/children/{childId}");

						target = target.path(childId);
						target = target.path(folderId);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("DELETE", new ICallbackAdapter<Void>(
								callback) {
						});
					}
				});

		this.get = new AsynchronousEvaluator2<>(
				new IAsynchronousFunction2<String, String, ChildReference>() {
					@Override
					public void y(String childId, String folderId,
							ICallback<ChildReference> callback) {
						WebTarget target = client.target(uri);
						target = target.path("{folderId}/children/{childId}");

						target = target.path(childId);
						target = target.path(folderId);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("GET",
								new ICallbackAdapter<ChildReference>(callback) {
								});
					}
				});

		this.insert = new AsynchronousEvaluator2<>(
				new IAsynchronousFunction2<String, ChildReference, ChildReference>() {
					@Override
					public void y(String folderId,
							ChildReference requestEntity,
							ICallback<ChildReference> callback) {
						WebTarget target = client.target(uri);
						target = target.path("{folderId}/children");

						target = target.path(folderId);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("POST", Entity.json(requestEntity),
								new ICallbackAdapter<ChildReference>(callback) {
								});
					}
				});

		this.list = new AsynchronousEvaluator4<>(
				new IAsynchronousFunction4<String, Integer, String, String, ChildList>() {
					@Override
					public void y(String folderId, Integer maxResults,
							String pageToken, String q,
							ICallback<ChildList> callback) {
						WebTarget target = client.target(uri);
						target = target.path("{folderId}/children");

						target = target.path(folderId);

						target = target.queryParam("maxResults", maxResults);
						target = target.queryParam("pageToken", pageToken);
						target = target.queryParam("q", q);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("GET", new ICallbackAdapter<ChildList>(
								callback) {
						});
					}
				});

	}

	@Override
	public IEvaluation<Void> delete(final String childId, final String folderId) {
		return delete(new Constant<>(childId), new Constant<>(folderId));
	}

	@Override
	public IEvaluation<Void> delete(final IEvaluation<String> childId,
			final IEvaluation<String> folderId) {
		return delete.y(childId, folderId);
	}

	@Override
	public IEvaluation<ChildReference> get(final String childId,
			final String folderId) {
		return get(new Constant<>(childId), new Constant<>(folderId));
	}

	@Override
	public IEvaluation<ChildReference> get(final IEvaluation<String> childId,
			final IEvaluation<String> folderId) {
		return get.y(childId, folderId);
	}

	@Override
	public IEvaluation<ChildReference> insert(final String folderId,
			final ChildReference requestEntity) {
		return insert(new Constant<>(folderId), new Constant<>(requestEntity));
	}

	@Override
	public IEvaluation<ChildReference> insert(
			final IEvaluation<String> folderId,
			final IEvaluation<ChildReference> requestEntity) {
		return insert.y(folderId, requestEntity);
	}

	@Override
	public IEvaluation<ChildList> list(final String folderId,
			final Integer maxResults, final String pageToken, final String q) {
		return list(new Constant<>(folderId), new Constant<>(maxResults),
				new Constant<>(pageToken), new Constant<>(q));
	}

	@Override
	public IEvaluation<ChildList> list(final IEvaluation<String> folderId,
			final IEvaluation<Integer> maxResults,
			final IEvaluation<String> pageToken, final IEvaluation<String> q) {
		return list.y(folderId, maxResults, pageToken, q);
	}

}
