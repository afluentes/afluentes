package com.google.api.services.drive;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import afluentes.middleware.ws.rs.PATCH;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface IFilesResource {
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
	@POST
	@Path("{fileId}/copy")
	File copy(@QueryParam("convert") boolean convert,
			@PathParam("fileId") String fileId, @QueryParam("ocr") boolean ocr,
			@QueryParam("ocrLanguage") String ocrLanguage,
			@QueryParam("pinned") boolean pinned,
			@QueryParam("timedTextLanguage") String timedTextLanguage,
			@QueryParam("timedTextTrackName") String timedTextTrackName,
			File requestEntity);

	/**
	 * Permanently deletes a file by ID. Skips the trash.
	 * 
	 * @param fileId
	 *            The ID of the file to delete.
	 */
	@DELETE
	@Path("{fileId}")
	void delete(@PathParam("fileId") String fileId);

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
	@GET
	@Path("{fileId}")
	File get(@PathParam("fileId") String fileId,
			@QueryParam("projection") String projection,
			@QueryParam("updateViewedDate") boolean updateViewedDate);

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
	@POST
	File insert(
			@QueryParam("convert") boolean convert,
			@QueryParam("ocr") boolean ocr,
			@QueryParam("ocrLanguage") String ocrLanguage,
			@QueryParam("pinned") boolean pinned,
			@QueryParam("timedTextLanguage") String timedTextLanguage,
			@QueryParam("timedTextTrackName") String timedTextTrackName,
			@QueryParam("useContentAsIndexableText") boolean useContentAsIndexableText,
			File requestEntity);

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
	@GET
	FileList list(@QueryParam("maxResults") int maxResults,
			@QueryParam("pageToken") String pageToken,
			@QueryParam("projection") String projection,
			@QueryParam("q") String q);

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
	@PATCH
	@Path("{fileId}")
	File patch(
			@QueryParam("convert") boolean convert,
			@PathParam("fileId") String fileId,
			@QueryParam("newRevision") boolean newRevision,
			@QueryParam("ocr") boolean ocr,
			@QueryParam("ocrLanguage") String ocrLanguage,
			@QueryParam("pinned") boolean pinned,
			@QueryParam("setModifiedDate") boolean setModifiedDate,
			@QueryParam("timedTextLanguage") String timedTextLanguage,
			@QueryParam("timedTextTrackName") String timedTextTrackName,
			@QueryParam("updateViewedDate") boolean updateViewedDate,
			@QueryParam("useContentAsIndexableText") boolean useContentAsIndexableText,
			File requestEntity);

	/**
	 * Set the file's updated time to the current server time.
	 * 
	 * @param fileId
	 *            The ID of the file to update.
	 */
	@POST
	@Path("{fileId}/touch")
	File touch(@PathParam("fileId") String fileId);

	/**
	 * Moves a file to the trash.
	 * 
	 * @param fileId
	 *            The ID of the file to trash.
	 */
	@POST
	@Path("{fileId}/trash")
	File trash(@PathParam("fileId") String fileId);

	/**
	 * Restores a file from the trash.
	 * 
	 * @param fileId
	 *            The ID of the file to untrash.
	 */
	@POST
	@Path("{fileId}/untrash")
	File untrash(@PathParam("fileId") String fileId);

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
	@PUT
	@Path("{fileId}")
	File update(
			@QueryParam("convert") boolean convert,
			@PathParam("fileId") String fileId,
			@QueryParam("newRevision") boolean newRevision,
			@QueryParam("ocr") boolean ocr,
			@QueryParam("ocrLanguage") String ocrLanguage,
			@QueryParam("pinned") boolean pinned,
			@QueryParam("setModifiedDate") boolean setModifiedDate,
			@QueryParam("timedTextLanguage") String timedTextLanguage,
			@QueryParam("timedTextTrackName") String timedTextTrackName,
			@QueryParam("updateViewedDate") boolean updateViewedDate,
			@QueryParam("useContentAsIndexableText") boolean useContentAsIndexableText,
			File requestEntity);

}
