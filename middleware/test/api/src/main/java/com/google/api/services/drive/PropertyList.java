package com.google.api.services.drive;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * A collection of properties, key-value pairs that are either public or private
 * to an application.
 */
public class PropertyList {
	/**
	 * The ETag of the list.
	 */
	public final String etag;

	/**
	 * The list of properties.
	 */
	public final List<Property> items;

	/**
	 * This is always drive#propertyList.
	 */
	public final String kind;

	/**
	 * The link back to this list.
	 */
	public final String selfLink;

	@JsonCreator
	public PropertyList(@JsonProperty("etag") final String etag,
			@JsonProperty("items") final List<Property> items,
			@JsonProperty("kind") final String kind,
			@JsonProperty("selfLink") final String selfLink) {
		this.etag = etag;
		this.items = items;
		this.kind = kind;
		this.selfLink = selfLink;
	}

}
