package com.google.api.services.drive;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * A key-value pair that is either public or private to an application.
 */
public class Property {
	/**
	 * ETag of the property.
	 */
	public final String etag;

	/**
	 * The key of this property.
	 */
	public final String key;

	/**
	 * This is always drive#property.
	 */
	public final String kind;

	/**
	 * The link back to this property.
	 */
	public final String selfLink;

	/**
	 * The value of this property.
	 */
	public final String value;

	/**
	 * The visibility of this property.
	 */
	public final String visibility;

	@JsonCreator
	public Property(@JsonProperty("etag") final String etag,
			@JsonProperty("key") final String key,
			@JsonProperty("kind") final String kind,
			@JsonProperty("selfLink") final String selfLink,
			@JsonProperty("value") final String value,
			@JsonProperty("visibility") final String visibility) {
		this.etag = etag;
		this.key = key;
		this.kind = kind;
		this.selfLink = selfLink;
		this.value = value;
		this.visibility = visibility;
	}

}
