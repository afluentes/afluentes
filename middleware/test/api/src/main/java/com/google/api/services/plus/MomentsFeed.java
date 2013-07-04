package com.google.api.services.plus;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class MomentsFeed {
	/**
	 * ETag of this response for caching purposes.
	 */
	public final String etag;

	/**
	 * The moments in this page of results.
	 */
	public final List<Moment> items;

	/**
	 * Identifies this resource as a collection of moments. Value:
	 * "plus#momentsFeed".
	 */
	public final String kind;

	/**
	 * Link to the next page of moments.
	 */
	public final String nextLink;

	/**
	 * The continuation token, which is used to page through large result sets.
	 * Provide this value in a subsequent request to return the next page of
	 * results.
	 */
	public final String nextPageToken;

	/**
	 * Link to this page of moments.
	 */
	public final String selfLink;

	/**
	 * The title of this collection of moments.
	 */
	public final String title;

	/**
	 * The RFC 339 timestamp for when this collection of moments was last
	 * updated.
	 */
	public final String updated;

	@JsonCreator
	public MomentsFeed(@JsonProperty("etag") final String etag,
			@JsonProperty("items") final List<Moment> items,
			@JsonProperty("kind") final String kind,
			@JsonProperty("nextLink") final String nextLink,
			@JsonProperty("nextPageToken") final String nextPageToken,
			@JsonProperty("selfLink") final String selfLink,
			@JsonProperty("title") final String title,
			@JsonProperty("updated") final String updated) {
		this.etag = etag;
		this.items = items;
		this.kind = kind;
		this.nextLink = nextLink;
		this.nextPageToken = nextPageToken;
		this.selfLink = selfLink;
		this.title = title;
		this.updated = updated;
	}

}
