package com.google.api.services.drive;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * A list of a file's parents.
 */
public class ParentList {
	/**
	 * The ETag of the list.
	 */
	public final String etag;

	/**
	 * The actual list of parents.
	 */
	public final List<ParentReference> items;

	/**
	 * This is always drive#parentList.
	 */
	public final String kind;

	/**
	 * A link back to this list.
	 */
	public final String selfLink;

	@JsonCreator
	public ParentList(@JsonProperty("etag") final String etag,
			@JsonProperty("items") final List<ParentReference> items,
			@JsonProperty("kind") final String kind,
			@JsonProperty("selfLink") final String selfLink) {
		this.etag = etag;
		this.items = items;
		this.kind = kind;
		this.selfLink = selfLink;
	}

}
