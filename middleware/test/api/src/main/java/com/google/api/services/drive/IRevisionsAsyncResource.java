package com.google.api.services.drive;

import afluentes.core.api.IEvaluation;

public interface IRevisionsAsyncResource {
	/**
	 * Removes a revision.
	 * 
	 * @param fileId
	 *            The ID of the file.
	 * @param revisionId
	 *            The ID of the revision.
	 */
	IEvaluation<Void> delete(String fileId, String revisionId);

	IEvaluation<Void> delete(IEvaluation<String> fileId,
			IEvaluation<String> revisionId);

	/**
	 * Gets a specific revision.
	 * 
	 * @param fileId
	 *            The ID of the file.
	 * @param revisionId
	 *            The ID of the revision.
	 */
	IEvaluation<Revision> get(String fileId, String revisionId);

	IEvaluation<Revision> get(IEvaluation<String> fileId,
			IEvaluation<String> revisionId);

	/**
	 * Lists a file's revisions.
	 * 
	 * @param fileId
	 *            The ID of the file.
	 */
	IEvaluation<RevisionList> list(String fileId);

	IEvaluation<RevisionList> list(IEvaluation<String> fileId);

	/**
	 * Updates a revision. This method supports patch semantics.
	 * 
	 * @param fileId
	 *            The ID for the file.
	 * @param revisionId
	 *            The ID for the revision.
	 */
	IEvaluation<Revision> patch(String fileId, String revisionId,
			Revision requestEntity);

	IEvaluation<Revision> patch(IEvaluation<String> fileId,
			IEvaluation<String> revisionId, IEvaluation<Revision> requestEntity);

	/**
	 * Updates a revision.
	 * 
	 * @param fileId
	 *            The ID for the file.
	 * @param revisionId
	 *            The ID for the revision.
	 */
	IEvaluation<Revision> update(String fileId, String revisionId,
			Revision requestEntity);

	IEvaluation<Revision> update(IEvaluation<String> fileId,
			IEvaluation<String> revisionId, IEvaluation<Revision> requestEntity);

}
