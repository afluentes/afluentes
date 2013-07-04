package com.google.api.services.drive;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Representation of a change to a file.
 */
public class Change {
	/**
	 * Whether the file has been deleted.
	 */
	public final boolean deleted;

	/**
	 * The updated state of the file. Present if the file has not been deleted.
	 */
	public final File file;

	/**
	 * The ID of the file associated with this change.
	 */
	public final String fileId;

	/**
	 * The ID of the change.
	 */
	public final String id;

	/**
	 * This is always drive#change.
	 */
	public final String kind;

	/**
	 * A link back to this change.
	 */
	public final String selfLink;

	@JsonCreator
	public Change(@JsonProperty("deleted") final boolean deleted,
			@JsonProperty("file") final File file,
			@JsonProperty("fileId") final String fileId,
			@JsonProperty("id") final String id,
			@JsonProperty("kind") final String kind,
			@JsonProperty("selfLink") final String selfLink) {
		this.deleted = deleted;
		this.file = file;
		this.fileId = fileId;
		this.id = id;
		this.kind = kind;
		this.selfLink = selfLink;
	}

}
