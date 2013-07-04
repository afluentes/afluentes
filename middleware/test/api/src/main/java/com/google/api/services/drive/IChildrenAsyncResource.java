package com.google.api.services.drive;

import afluentes.core.api.IEvaluation;

public interface IChildrenAsyncResource {
	/**
	 * Removes a child from a folder.
	 * 
	 * @param childId
	 *            The ID of the child.
	 * @param folderId
	 *            The ID of the folder.
	 */
	IEvaluation<Void> delete(String childId, String folderId);

	IEvaluation<Void> delete(IEvaluation<String> childId,
			IEvaluation<String> folderId);

	/**
	 * Gets a specific child reference.
	 * 
	 * @param childId
	 *            The ID of the child.
	 * @param folderId
	 *            The ID of the folder.
	 */
	IEvaluation<ChildReference> get(String childId, String folderId);

	IEvaluation<ChildReference> get(IEvaluation<String> childId,
			IEvaluation<String> folderId);

	/**
	 * Inserts a file into a folder.
	 * 
	 * @param folderId
	 *            The ID of the folder.
	 */
	IEvaluation<ChildReference> insert(String folderId,
			ChildReference requestEntity);

	IEvaluation<ChildReference> insert(IEvaluation<String> folderId,
			IEvaluation<ChildReference> requestEntity);

	/**
	 * Lists a folder's children.
	 * 
	 * @param folderId
	 *            The ID of the folder.
	 * @param maxResults
	 *            Maximum number of children to return.
	 * @param pageToken
	 *            Page token for children.
	 * @param q
	 *            Query string for searching children.
	 */
	IEvaluation<ChildList> list(String folderId, Integer maxResults,
			String pageToken, String q);

	IEvaluation<ChildList> list(IEvaluation<String> folderId,
			IEvaluation<Integer> maxResults, IEvaluation<String> pageToken,
			IEvaluation<String> q);

}
