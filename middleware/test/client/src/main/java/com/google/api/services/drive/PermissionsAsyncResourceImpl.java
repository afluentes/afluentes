package com.google.api.services.drive;

import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;

import afluentes.core.api.IAsynchronousFunction1;
import afluentes.core.api.IAsynchronousFunction2;
import afluentes.core.api.IAsynchronousFunction4;
import afluentes.core.api.ICallback;
import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator1;
import afluentes.core.api.IEvaluator2;
import afluentes.core.api.IEvaluator4;
import afluentes.core.impl.AsynchronousEvaluator1;
import afluentes.core.impl.AsynchronousEvaluator2;
import afluentes.core.impl.AsynchronousEvaluator4;
import afluentes.core.impl.Constant;
import afluentes.middleware.ws.rs.client.ICallbackAdapter;

@SuppressWarnings("unused")
public class PermissionsAsyncResourceImpl implements IPermissionsAsyncResource {
	private final Client client;
	private final String uri;

	private final IEvaluator2<String, String, Void> delete;
	private final IEvaluator2<String, String, Permission> get;
	private final IEvaluator4<String, String, Boolean, Permission, Permission> insert;
	private final IEvaluator1<String, PermissionList> list;
	private final IEvaluator4<String, String, Boolean, Permission, Permission> patch;
	private final IEvaluator4<String, String, Boolean, Permission, Permission> update;

	public PermissionsAsyncResourceImpl(final Client client, final String uri) {
		this.client = client;
		this.uri = uri;

		this.delete = new AsynchronousEvaluator2<>(
				new IAsynchronousFunction2<String, String, Void>() {
					@Override
					public void y(String fileId, String permissionId,
							ICallback<Void> callback) {
						WebTarget target = client.target(uri);
						target = target
								.path("{fileId}/permissions/{permissionId}");

						target = target.path(fileId);
						target = target.path(permissionId);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("DELETE", new ICallbackAdapter<Void>(
								callback) {
						});
					}
				});

		this.get = new AsynchronousEvaluator2<>(
				new IAsynchronousFunction2<String, String, Permission>() {
					@Override
					public void y(String fileId, String permissionId,
							ICallback<Permission> callback) {
						WebTarget target = client.target(uri);
						target = target
								.path("{fileId}/permissions/{permissionId}");

						target = target.path(fileId);
						target = target.path(permissionId);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("GET", new ICallbackAdapter<Permission>(
								callback) {
						});
					}
				});

		this.insert = new AsynchronousEvaluator4<>(
				new IAsynchronousFunction4<String, String, Boolean, Permission, Permission>() {
					@Override
					public void y(String emailMessage, String fileId,
							Boolean sendNotificationEmails,
							Permission requestEntity,
							ICallback<Permission> callback) {
						WebTarget target = client.target(uri);
						target = target.path("{fileId}/permissions");

						target = target.path(fileId);

						target = target
								.queryParam("emailMessage", emailMessage);
						target = target.queryParam("sendNotificationEmails",
								sendNotificationEmails);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("POST", Entity.json(requestEntity),
								new ICallbackAdapter<Permission>(callback) {
								});
					}
				});

		this.list = new AsynchronousEvaluator1<>(
				new IAsynchronousFunction1<String, PermissionList>() {
					@Override
					public void y(String fileId,
							ICallback<PermissionList> callback) {
						WebTarget target = client.target(uri);
						target = target.path("{fileId}/permissions");

						target = target.path(fileId);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("GET",
								new ICallbackAdapter<PermissionList>(callback) {
								});
					}
				});

		this.patch = new AsynchronousEvaluator4<>(
				new IAsynchronousFunction4<String, String, Boolean, Permission, Permission>() {
					@Override
					public void y(String fileId, String permissionId,
							Boolean transferOwnership,
							Permission requestEntity,
							ICallback<Permission> callback) {
						WebTarget target = client.target(uri);
						target = target
								.path("{fileId}/permissions/{permissionId}");

						target = target.path(fileId);
						target = target.path(permissionId);

						target = target.queryParam("transferOwnership",
								transferOwnership);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("PATCH", Entity.json(requestEntity),
								new ICallbackAdapter<Permission>(callback) {
								});
					}
				});

		this.update = new AsynchronousEvaluator4<>(
				new IAsynchronousFunction4<String, String, Boolean, Permission, Permission>() {
					@Override
					public void y(String fileId, String permissionId,
							Boolean transferOwnership,
							Permission requestEntity,
							ICallback<Permission> callback) {
						WebTarget target = client.target(uri);
						target = target
								.path("{fileId}/permissions/{permissionId}");

						target = target.path(fileId);
						target = target.path(permissionId);

						target = target.queryParam("transferOwnership",
								transferOwnership);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("PUT", Entity.json(requestEntity),
								new ICallbackAdapter<Permission>(callback) {
								});
					}
				});

	}

	@Override
	public IEvaluation<Void> delete(final String fileId,
			final String permissionId) {
		return delete(new Constant<>(fileId), new Constant<>(permissionId));
	}

	@Override
	public IEvaluation<Void> delete(final IEvaluation<String> fileId,
			final IEvaluation<String> permissionId) {
		return delete.y(fileId, permissionId);
	}

	@Override
	public IEvaluation<Permission> get(final String fileId,
			final String permissionId) {
		return get(new Constant<>(fileId), new Constant<>(permissionId));
	}

	@Override
	public IEvaluation<Permission> get(final IEvaluation<String> fileId,
			final IEvaluation<String> permissionId) {
		return get.y(fileId, permissionId);
	}

	@Override
	public IEvaluation<Permission> insert(final String emailMessage,
			final String fileId, final Boolean sendNotificationEmails,
			final Permission requestEntity) {
		return insert(new Constant<>(emailMessage), new Constant<>(fileId),
				new Constant<>(sendNotificationEmails), new Constant<>(
						requestEntity));
	}

	@Override
	public IEvaluation<Permission> insert(
			final IEvaluation<String> emailMessage,
			final IEvaluation<String> fileId,
			final IEvaluation<Boolean> sendNotificationEmails,
			final IEvaluation<Permission> requestEntity) {
		return insert.y(emailMessage, fileId, sendNotificationEmails,
				requestEntity);
	}

	@Override
	public IEvaluation<PermissionList> list(final String fileId) {
		return list(new Constant<>(fileId));
	}

	@Override
	public IEvaluation<PermissionList> list(final IEvaluation<String> fileId) {
		return list.y(fileId);
	}

	@Override
	public IEvaluation<Permission> patch(final String fileId,
			final String permissionId, final Boolean transferOwnership,
			final Permission requestEntity) {
		return patch(new Constant<>(fileId), new Constant<>(permissionId),
				new Constant<>(transferOwnership),
				new Constant<>(requestEntity));
	}

	@Override
	public IEvaluation<Permission> patch(final IEvaluation<String> fileId,
			final IEvaluation<String> permissionId,
			final IEvaluation<Boolean> transferOwnership,
			final IEvaluation<Permission> requestEntity) {
		return patch.y(fileId, permissionId, transferOwnership, requestEntity);
	}

	@Override
	public IEvaluation<Permission> update(final String fileId,
			final String permissionId, final Boolean transferOwnership,
			final Permission requestEntity) {
		return update(new Constant<>(fileId), new Constant<>(permissionId),
				new Constant<>(transferOwnership),
				new Constant<>(requestEntity));
	}

	@Override
	public IEvaluation<Permission> update(final IEvaluation<String> fileId,
			final IEvaluation<String> permissionId,
			final IEvaluation<Boolean> transferOwnership,
			final IEvaluation<Permission> requestEntity) {
		return update.y(fileId, permissionId, transferOwnership, requestEntity);
	}

}
