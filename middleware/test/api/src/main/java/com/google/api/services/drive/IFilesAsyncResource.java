package com.google.api.services.drive;

import afluentes.core.api.IEvaluation;

public interface IFilesAsyncResource {
	/**
	 * Creates a copy of the specified file.
	 * 
	 * @param convert
	 *            Whether to convert this file to the corresponding Google Docs
	 *            format.
	 * @param fileId
	 *            The ID of the file to copy.
	 * @param ocr
	 *            Whether to attempt OCR on .jpg, .png, .gif, or .pdf uploads.
	 * @param ocrLanguage
	 *            If ocr is true, hints at the language to use. Valid values are
	 *            ISO 639-1 codes.
	 * @param pinned
	 *            Whether to pin the head revision of the new copy.
	 * @param timedTextLanguage
	 *            The language of the timed text.
	 * @param timedTextTrackName
	 *            The timed text track name.
	 */
	IEvaluation<File> copy(Boolean convert, String fileId, Boolean ocr,
			String ocrLanguage, Boolean pinned, String timedTextLanguage,
			String timedTextTrackName, File requestEntity);

	IEvaluation<File> copy(IEvaluation<Boolean> convert,
			IEvaluation<String> fileId, IEvaluation<Boolean> ocr,
			IEvaluation<String> ocrLanguage, IEvaluation<Boolean> pinned,
			IEvaluation<String> timedTextLanguage,
			IEvaluation<String> timedTextTrackName,
			IEvaluation<File> requestEntity);

	/**
	 * Permanently deletes a file by ID. Skips the trash.
	 * 
	 * @param fileId
	 *            The ID of the file to delete.
	 */
	IEvaluation<Void> delete(String fileId);

	IEvaluation<Void> delete(IEvaluation<String> fileId);

	/**
	 * Gets a file's metadata by ID.
	 * 
	 * @param fileId
	 *            The ID for the file in question.
	 * @param projection
	 *            This parameter is deprecated and has no function.
	 * @param updateViewedDate
	 *            Whether to update the view date after successfully retrieving
	 *            the file.
	 */
	IEvaluation<File> get(String fileId, String projection,
			Boolean updateViewedDate);

	IEvaluation<File> get(IEvaluation<String> fileId,
			IEvaluation<String> projection,
			IEvaluation<Boolean> updateViewedDate);

	/**
	 * Insert a new file.
	 * 
	 * @param convert
	 *            Whether to convert this file to the corresponding Google Docs
	 *            format.
	 * @param ocr
	 *            Whether to attempt OCR on .jpg, .png, .gif, or .pdf uploads.
	 * @param ocrLanguage
	 *            If ocr is true, hints at the language to use. Valid values are
	 *            ISO 639-1 codes.
	 * @param pinned
	 *            Whether to pin the head revision of the uploaded file.
	 * @param timedTextLanguage
	 *            The language of the timed text.
	 * @param timedTextTrackName
	 *            The timed text track name.
	 * @param useContentAsIndexableText
	 *            Whether to use the content as indexable text.
	 */
	IEvaluation<File> insert(Boolean convert, Boolean ocr, String ocrLanguage,
			Boolean pinned, String timedTextLanguage,
			String timedTextTrackName, Boolean useContentAsIndexableText,
			File requestEntity);

	IEvaluation<File> insert(IEvaluation<Boolean> convert,
			IEvaluation<Boolean> ocr, IEvaluation<String> ocrLanguage,
			IEvaluation<Boolean> pinned, IEvaluation<String> timedTextLanguage,
			IEvaluation<String> timedTextTrackName,
			IEvaluation<Boolean> useContentAsIndexableText,
			IEvaluation<File> requestEntity);

	/**
	 * Lists the user's files.
	 * 
	 * @param maxResults
	 *            Maximum number of files to return.
	 * @param pageToken
	 *            Page token for files.
	 * @param projection
	 *            This parameter is deprecated and has no function.
	 * @param q
	 *            Query string for searching files.
	 */
	IEvaluation<FileList> list(Integer maxResults, String pageToken,
			String projection, String q);

	IEvaluation<FileList> list(IEvaluation<Integer> maxResults,
			IEvaluation<String> pageToken, IEvaluation<String> projection,
			IEvaluation<String> q);

	/**
	 * Updates file metadata and/or content. This method supports patch
	 * semantics.
	 * 
	 * @param convert
	 *            Whether to convert this file to the corresponding Google Docs
	 *            format.
	 * @param fileId
	 *            The ID of the file to update.
	 * @param newRevision
	 *            Whether a blob upload should create a new revision. If not set
	 *            or false, the blob data in the current head revision is
	 *            replaced. If true, a new blob is created as head revision, and
	 *            previous revisions are preserved (causing increased use of the
	 *            user's data storage quota).
	 * @param ocr
	 *            Whether to attempt OCR on .jpg, .png, .gif, or .pdf uploads.
	 * @param ocrLanguage
	 *            If ocr is true, hints at the language to use. Valid values are
	 *            ISO 639-1 codes.
	 * @param pinned
	 *            Whether to pin the new revision.
	 * @param setModifiedDate
	 *            Whether to set the modified date with the supplied modified
	 *            date.
	 * @param timedTextLanguage
	 *            The language of the timed text.
	 * @param timedTextTrackName
	 *            The timed text track name.
	 * @param updateViewedDate
	 *            Whether to update the view date after successfully updating
	 *            the file.
	 * @param useContentAsIndexableText
	 *            Whether to use the content as indexable text.
	 */
	IEvaluation<File> patch(Boolean convert, String fileId,
			Boolean newRevision, Boolean ocr, String ocrLanguage,
			Boolean pinned, Boolean setModifiedDate, String timedTextLanguage,
			String timedTextTrackName, Boolean updateViewedDate,
			Boolean useContentAsIndexableText, File requestEntity);

	IEvaluation<File> patch(IEvaluation<Boolean> convert,
			IEvaluation<String> fileId, IEvaluation<Boolean> newRevision,
			IEvaluation<Boolean> ocr, IEvaluation<String> ocrLanguage,
			IEvaluation<Boolean> pinned, IEvaluation<Boolean> setModifiedDate,
			IEvaluation<String> timedTextLanguage,
			IEvaluation<String> timedTextTrackName,
			IEvaluation<Boolean> updateViewedDate,
			IEvaluation<Boolean> useContentAsIndexableText,
			IEvaluation<File> requestEntity);

	/**
	 * Set the file's updated time to the current server time.
	 * 
	 * @param fileId
	 *            The ID of the file to update.
	 */
	IEvaluation<File> touch(String fileId);

	IEvaluation<File> touch(IEvaluation<String> fileId);

	/**
	 * Moves a file to the trash.
	 * 
	 * @param fileId
	 *            The ID of the file to trash.
	 */
	IEvaluation<File> trash(String fileId);

	IEvaluation<File> trash(IEvaluation<String> fileId);

	/**
	 * Restores a file from the trash.
	 * 
	 * @param fileId
	 *            The ID of the file to untrash.
	 */
	IEvaluation<File> untrash(String fileId);

	IEvaluation<File> untrash(IEvaluation<String> fileId);

	/**
	 * Updates file metadata and/or content.
	 * 
	 * @param convert
	 *            Whether to convert this file to the corresponding Google Docs
	 *            format.
	 * @param fileId
	 *            The ID of the file to update.
	 * @param newRevision
	 *            Whether a blob upload should create a new revision. If not set
	 *            or false, the blob data in the current head revision is
	 *            replaced. If true, a new blob is created as head revision, and
	 *            previous revisions are preserved (causing increased use of the
	 *            user's data storage quota).
	 * @param ocr
	 *            Whether to attempt OCR on .jpg, .png, .gif, or .pdf uploads.
	 * @param ocrLanguage
	 *            If ocr is true, hints at the language to use. Valid values are
	 *            ISO 639-1 codes.
	 * @param pinned
	 *            Whether to pin the new revision.
	 * @param setModifiedDate
	 *            Whether to set the modified date with the supplied modified
	 *            date.
	 * @param timedTextLanguage
	 *            The language of the timed text.
	 * @param timedTextTrackName
	 *            The timed text track name.
	 * @param updateViewedDate
	 *            Whether to update the view date after successfully updating
	 *            the file.
	 * @param useContentAsIndexableText
	 *            Whether to use the content as indexable text.
	 */
	IEvaluation<File> update(Boolean convert, String fileId,
			Boolean newRevision, Boolean ocr, String ocrLanguage,
			Boolean pinned, Boolean setModifiedDate, String timedTextLanguage,
			String timedTextTrackName, Boolean updateViewedDate,
			Boolean useContentAsIndexableText, File requestEntity);

	IEvaluation<File> update(IEvaluation<Boolean> convert,
			IEvaluation<String> fileId, IEvaluation<Boolean> newRevision,
			IEvaluation<Boolean> ocr, IEvaluation<String> ocrLanguage,
			IEvaluation<Boolean> pinned, IEvaluation<Boolean> setModifiedDate,
			IEvaluation<String> timedTextLanguage,
			IEvaluation<String> timedTextTrackName,
			IEvaluation<Boolean> updateViewedDate,
			IEvaluation<Boolean> useContentAsIndexableText,
			IEvaluation<File> requestEntity);

}
