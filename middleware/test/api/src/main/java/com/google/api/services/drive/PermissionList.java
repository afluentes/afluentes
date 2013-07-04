package com.google.api.services.drive;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * A list of permissions associated with a file.
 */
public class PermissionList {
	/**
	 * The ETag of the list.
	 */
	public final String etag;

	/**
	 * The actual list of permissions.
	 */
	public final List<Permission> items;

	/**
	 * This is always drive#permissionList.
	 */
	public final String kind;

	/**
	 * A link back to this list.
	 */
	public final String selfLink;

	@JsonCreator
	public PermissionList(@JsonProperty("etag") final String etag,
			@JsonProperty("items") final List<Permission> items,
			@JsonProperty("kind") final String kind,
			@JsonProperty("selfLink") final String selfLink) {
		this.etag = etag;
		this.items = items;
		this.kind = kind;
		this.selfLink = selfLink;
	}

}
