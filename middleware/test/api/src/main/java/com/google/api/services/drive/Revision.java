package com.google.api.services.drive;

import java.util.Map;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * A revision of a file.
 */
public class Revision {
	/**
	 * Short term download URL for the file. This will only be populated on
	 * files with content stored in Drive.
	 */
	public final String downloadUrl;

	/**
	 * The ETag of the revision.
	 */
	public final String etag;

	/**
	 * Links for exporting Google Docs to specific formats.
	 */
	public final Map<String, String> exportLinks;

	/**
	 * The size of the revision in bytes. This will only be populated on files
	 * with content stored in Drive.
	 */
	public final String fileSize;

	/**
	 * The ID of the revision.
	 */
	public final String id;

	/**
	 * This is always drive#revision.
	 */
	public final String kind;

	/**
	 * The last user to modify this revision.
	 */
	public final User lastModifyingUser;

	/**
	 * Name of the last user to modify this revision.
	 */
	public final String lastModifyingUserName;

	/**
	 * An MD5 checksum for the content of this revision. This will only be
	 * populated on files with content stored in Drive.
	 */
	public final String md5Checksum;

	/**
	 * The MIME type of the revision.
	 */
	public final String mimeType;

	/**
	 * Last time this revision was modified (formatted RFC 3339 timestamp).
	 */
	public final String modifiedDate;

	/**
	 * The original filename when this revision was created. This will only be
	 * populated on files with content stored in Drive.
	 */
	public final String originalFilename;

	/**
	 * Whether this revision is pinned to prevent automatic purging. This will
	 * only be populated and can only be modified on files with content stored
	 * in Drive which are not Google Docs. Revisions can also be pinned when
	 * they are created through the drive.files.insert/update/copy by using the
	 * pinned query parameter.
	 */
	public final boolean pinned;

	/**
	 * Whether subsequent revisions will be automatically republished. This is
	 * only populated and can only be modified for Google Docs.
	 */
	public final boolean publishAuto;

	/**
	 * Whether this revision is published. This is only populated and can only
	 * be modified for Google Docs.
	 */
	public final boolean published;

	/**
	 * A link to the published revision.
	 */
	public final String publishedLink;

	/**
	 * Whether this revision is published outside the domain. This is only
	 * populated and can only be modified for Google Docs.
	 */
	public final boolean publishedOutsideDomain;

	/**
	 * A link back to this revision.
	 */
	public final String selfLink;

	@JsonCreator
	public Revision(
			@JsonProperty("downloadUrl") final String downloadUrl,
			@JsonProperty("etag") final String etag,
			@JsonProperty("exportLinks") final Map<String, String> exportLinks,
			@JsonProperty("fileSize") final String fileSize,
			@JsonProperty("id") final String id,
			@JsonProperty("kind") final String kind,
			@JsonProperty("lastModifyingUser") final User lastModifyingUser,
			@JsonProperty("lastModifyingUserName") final String lastModifyingUserName,
			@JsonProperty("md5Checksum") final String md5Checksum,
			@JsonProperty("mimeType") final String mimeType,
			@JsonProperty("modifiedDate") final String modifiedDate,
			@JsonProperty("originalFilename") final String originalFilename,
			@JsonProperty("pinned") final boolean pinned,
			@JsonProperty("publishAuto") final boolean publishAuto,
			@JsonProperty("published") final boolean published,
			@JsonProperty("publishedLink") final String publishedLink,
			@JsonProperty("publishedOutsideDomain") final boolean publishedOutsideDomain,
			@JsonProperty("selfLink") final String selfLink) {
		this.downloadUrl = downloadUrl;
		this.etag = etag;
		this.exportLinks = exportLinks;
		this.fileSize = fileSize;
		this.id = id;
		this.kind = kind;
		this.lastModifyingUser = lastModifyingUser;
		this.lastModifyingUserName = lastModifyingUserName;
		this.md5Checksum = md5Checksum;
		this.mimeType = mimeType;
		this.modifiedDate = modifiedDate;
		this.originalFilename = originalFilename;
		this.pinned = pinned;
		this.publishAuto = publishAuto;
		this.published = published;
		this.publishedLink = publishedLink;
		this.publishedOutsideDomain = publishedOutsideDomain;
		this.selfLink = selfLink;
	}

	/**
	 * A mapping from export format to URL
	 */
	public static class ExportLinks {

		@JsonCreator
		public ExportLinks() {
		}

	}
}
