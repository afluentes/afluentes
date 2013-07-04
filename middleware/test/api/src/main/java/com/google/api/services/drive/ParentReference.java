package com.google.api.services.drive;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * A reference to a file's parent.
 */
public class ParentReference {
	/**
	 * The ID of the parent.
	 */
	public final String id;

	/**
	 * Whether or not the parent is the root folder.
	 */
	public final boolean isRoot;

	/**
	 * This is always drive#parentReference.
	 */
	public final String kind;

	/**
	 * A link to the parent.
	 */
	public final String parentLink;

	/**
	 * A link back to this reference.
	 */
	public final String selfLink;

	@JsonCreator
	public ParentReference(@JsonProperty("id") final String id,
			@JsonProperty("isRoot") final boolean isRoot,
			@JsonProperty("kind") final String kind,
			@JsonProperty("parentLink") final String parentLink,
			@JsonProperty("selfLink") final String selfLink) {
		this.id = id;
		this.isRoot = isRoot;
		this.kind = kind;
		this.parentLink = parentLink;
		this.selfLink = selfLink;
	}

}
