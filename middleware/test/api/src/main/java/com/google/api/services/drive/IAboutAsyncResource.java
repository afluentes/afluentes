package com.google.api.services.drive;

import afluentes.core.api.IEvaluation;

public interface IAboutAsyncResource {
	/**
	 * Gets the information about the current user along with Drive API settings
	 * 
	 * @param includeSubscribed
	 *            When calculating the number of remaining change IDs, whether
	 *            to include shared files and public files the user has opened.
	 *            When set to false, this counts only change IDs for owned files
	 *            and any shared or public files that the user has explictly
	 *            added to a folder in Drive.
	 * @param maxChangeIdCount
	 *            Maximum number of remaining change IDs to count
	 * @param startChangeId
	 *            Change ID to start counting from when calculating number of
	 *            remaining change IDs
	 */
	IEvaluation<About> get(Boolean includeSubscribed, String maxChangeIdCount,
			String startChangeId);

	IEvaluation<About> get(IEvaluation<Boolean> includeSubscribed,
			IEvaluation<String> maxChangeIdCount,
			IEvaluation<String> startChangeId);

}
