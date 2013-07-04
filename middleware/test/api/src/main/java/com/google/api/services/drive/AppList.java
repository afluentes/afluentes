package com.google.api.services.drive;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * A list of third-party applications which the user has installed or given
 * access to Google Drive.
 */
public class AppList {
	/**
	 * The ETag of the list.
	 */
	public final String etag;

	/**
	 * The actual list of apps.
	 */
	public final List<App> items;

	/**
	 * This is always drive#appList.
	 */
	public final String kind;

	/**
	 * A link back to this list.
	 */
	public final String selfLink;

	@JsonCreator
	public AppList(@JsonProperty("etag") final String etag,
			@JsonProperty("items") final List<App> items,
			@JsonProperty("kind") final String kind,
			@JsonProperty("selfLink") final String selfLink) {
		this.etag = etag;
		this.items = items;
		this.kind = kind;
		this.selfLink = selfLink;
	}

}
