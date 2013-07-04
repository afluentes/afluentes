package com.google.api.services.drive;

import afluentes.core.api.IEvaluation;

public interface IAppsAsyncResource {
	/**
	 * Gets a specific app.
	 * 
	 * @param appId
	 *            The ID of the app.
	 */
	IEvaluation<App> get(String appId);

	IEvaluation<App> get(IEvaluation<String> appId);

	/**
	 * Lists a user's installed apps.
	 * 
	 */
	IEvaluation<AppList> list();

}
