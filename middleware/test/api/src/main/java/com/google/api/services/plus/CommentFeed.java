package com.google.api.services.plus;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class CommentFeed {
	/**
	 * ETag of this response for caching purposes.
	 */
	public final String etag;

	/**
	 * The ID of this collection of comments.
	 */
	public final String id;

	/**
	 * The comments in this page of results.
	 */
	public final List<Comment> items;

	/**
	 * Identifies this resource as a collection of comments. Value:
	 * "plus#commentFeed".
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
	 * The title of this collection of comments.
	 */
	public final String title;

	/**
	 * The time at which this collection of comments was last updated. Formatted
	 * as an RFC 3339 timestamp.
	 */
	public final String updated;

	@JsonCreator
	public CommentFeed(@JsonProperty("etag") final String etag,
			@JsonProperty("id") final String id,
			@JsonProperty("items") final List<Comment> items,
			@JsonProperty("kind") final String kind,
			@JsonProperty("nextLink") final String nextLink,
			@JsonProperty("nextPageToken") final String nextPageToken,
			@JsonProperty("title") final String title,
			@JsonProperty("updated") final String updated) {
		this.etag = etag;
		this.id = id;
		this.items = items;
		this.kind = kind;
		this.nextLink = nextLink;
		this.nextPageToken = nextPageToken;
		this.title = title;
		this.updated = updated;
	}

}
