package com.google.api.services.plus;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class ActivityFeed {
	/**
	 * ETag of this response for caching purposes.
	 */
	public final String etag;

	/**
	 * The ID of this collection of activities. Deprecated.
	 */
	public final String id;

	/**
	 * The activities in this page of results.
	 */
	public final List<Activity> items;

	/**
	 * Identifies this resource as a collection of activities. Value:
	 * "plus#activityFeed".
	 */
	public final String kind;

	/**
	 * Link to the next page of activities.
	 */
	public final String nextLink;

	/**
	 * The continuation token, which is used to page through large result sets.
	 * Provide this value in a subsequent request to return the next page of
	 * results.
	 */
	public final String nextPageToken;

	/**
	 * Link to this activity resource.
	 */
	public final String selfLink;

	/**
	 * The title of this collection of activities.
	 */
	public final String title;

	/**
	 * The time at which this collection of activities was last updated.
	 * Formatted as an RFC 3339 timestamp.
	 */
	public final String updated;

	@JsonCreator
	public ActivityFeed(@JsonProperty("etag") final String etag,
			@JsonProperty("id") final String id,
			@JsonProperty("items") final List<Activity> items,
			@JsonProperty("kind") final String kind,
			@JsonProperty("nextLink") final String nextLink,
			@JsonProperty("nextPageToken") final String nextPageToken,
			@JsonProperty("selfLink") final String selfLink,
			@JsonProperty("title") final String title,
			@JsonProperty("updated") final String updated) {
		this.etag = etag;
		this.id = id;
		this.items = items;
		this.kind = kind;
		this.nextLink = nextLink;
		this.nextPageToken = nextPageToken;
		this.selfLink = selfLink;
		this.title = title;
		this.updated = updated;
	}

}
