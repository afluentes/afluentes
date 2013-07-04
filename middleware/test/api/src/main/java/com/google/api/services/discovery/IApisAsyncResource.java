package com.google.api.services.discovery;

import afluentes.core.api.IEvaluation;

public interface IApisAsyncResource {
	/**
	 * Retrieve the description of a particular version of an api.
	 * 
	 * @param api
	 *            The name of the API.
	 * @param version
	 *            The version of the API.
	 */
	IEvaluation<RestDescription> getRest(String api, String version);

	IEvaluation<RestDescription> getRest(IEvaluation<String> api,
			IEvaluation<String> version);

	/**
	 * Retrieve the list of APIs supported at this endpoint.
	 * 
	 * @param name
	 *            Only include APIs with the given name.
	 * @param preferred
	 *            Return only the preferred version of an API.
	 */
	IEvaluation<DirectoryList> list(String name, Boolean preferred);

	IEvaluation<DirectoryList> list(IEvaluation<String> name,
			IEvaluation<Boolean> preferred);

}
