package com.google.api.services.drive;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * A reference to a folder's child.
 */
public class ChildReference {
	/**
	 * A link to the child.
	 */
	public final String childLink;

	/**
	 * The ID of the child.
	 */
	public final String id;

	/**
	 * This is always drive#childReference.
	 */
	public final String kind;

	/**
	 * A link back to this reference.
	 */
	public final String selfLink;

	@JsonCreator
	public ChildReference(@JsonProperty("childLink") final String childLink,
			@JsonProperty("id") final String id,
			@JsonProperty("kind") final String kind,
			@JsonProperty("selfLink") final String selfLink) {
		this.childLink = childLink;
		this.id = id;
		this.kind = kind;
		this.selfLink = selfLink;
	}

}
