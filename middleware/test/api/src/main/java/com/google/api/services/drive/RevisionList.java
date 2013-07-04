package com.google.api.services.drive;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * A list of revisions of a file.
 */
public class RevisionList {
	/**
	 * The ETag of the list.
	 */
	public final String etag;

	/**
	 * The actual list of revisions.
	 */
	public final List<Revision> items;

	/**
	 * This is always drive#revisionList.
	 */
	public final String kind;

	/**
	 * A link back to this list.
	 */
	public final String selfLink;

	@JsonCreator
	public RevisionList(@JsonProperty("etag") final String etag,
			@JsonProperty("items") final List<Revision> items,
			@JsonProperty("kind") final String kind,
			@JsonProperty("selfLink") final String selfLink) {
		this.etag = etag;
		this.items = items;
		this.kind = kind;
		this.selfLink = selfLink;
	}

}
