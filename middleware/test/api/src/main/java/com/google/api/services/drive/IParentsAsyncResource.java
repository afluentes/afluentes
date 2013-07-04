package com.google.api.services.drive;

import afluentes.core.api.IEvaluation;

public interface IParentsAsyncResource {
	/**
	 * Removes a parent from a file.
	 * 
	 * @param fileId
	 *            The ID of the file.
	 * @param parentId
	 *            The ID of the parent.
	 */
	IEvaluation<Void> delete(String fileId, String parentId);

	IEvaluation<Void> delete(IEvaluation<String> fileId,
			IEvaluation<String> parentId);

	/**
	 * Gets a specific parent reference.
	 * 
	 * @param fileId
	 *            The ID of the file.
	 * @param parentId
	 *            The ID of the parent.
	 */
	IEvaluation<ParentReference> get(String fileId, String parentId);

	IEvaluation<ParentReference> get(IEvaluation<String> fileId,
			IEvaluation<String> parentId);

	/**
	 * Adds a parent folder for a file.
	 * 
	 * @param fileId
	 *            The ID of the file.
	 */
	IEvaluation<ParentReference> insert(String fileId,
			ParentReference requestEntity);

	IEvaluation<ParentReference> insert(IEvaluation<String> fileId,
			IEvaluation<ParentReference> requestEntity);

	/**
	 * Lists a file's parents.
	 * 
	 * @param fileId
	 *            The ID of the file.
	 */
	IEvaluation<ParentList> list(String fileId);

	IEvaluation<ParentList> list(IEvaluation<String> fileId);

}
