package com.google.api.services.plus;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class Acl {
	/**
	 * Description of the access granted, suitable for display.
	 */
	public final String description;

	/**
	 * The list of access entries.
	 */
	public final List<PlusAclentryResource> items;

	/**
	 * Identifies this resource as a collection of access controls. Value:
	 * "plus#acl".
	 */
	public final String kind;

	@JsonCreator
	public Acl(@JsonProperty("description") final String description,
			@JsonProperty("items") final List<PlusAclentryResource> items,
			@JsonProperty("kind") final String kind) {
		this.description = description;
		this.items = items;
		this.kind = kind;
	}

}
