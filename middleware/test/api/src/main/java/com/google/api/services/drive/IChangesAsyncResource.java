package com.google.api.services.drive;

import afluentes.core.api.IEvaluation;

public interface IChangesAsyncResource {
	/**
	 * Gets a specific change.
	 * 
	 * @param changeId
	 *            The ID of the change.
	 */
	IEvaluation<Change> get(String changeId);

	IEvaluation<Change> get(IEvaluation<String> changeId);

	/**
	 * Lists the changes for a user.
	 * 
	 * @param includeDeleted
	 *            Whether to include deleted items.
	 * @param includeSubscribed
	 *            Whether to include shared files and public files the user has
	 *            opened. When set to false, the list will include owned files
	 *            plus any shared or public files the user has explictly added
	 *            to a folder in Drive.
	 * @param maxResults
	 *            Maximum number of changes to return.
	 * @param pageToken
	 *            Page token for changes.
	 * @param startChangeId
	 *            Change ID to start listing changes from.
	 */
	IEvaluation<ChangeList> list(Boolean includeDeleted,
			Boolean includeSubscribed, Integer maxResults, String pageToken,
			String startChangeId);

	IEvaluation<ChangeList> list(IEvaluation<Boolean> includeDeleted,
			IEvaluation<Boolean> includeSubscribed,
			IEvaluation<Integer> maxResults, IEvaluation<String> pageToken,
			IEvaluation<String> startChangeId);

}
