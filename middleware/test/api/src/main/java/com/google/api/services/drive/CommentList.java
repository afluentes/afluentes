package com.google.api.services.drive;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * A JSON representation of a list of comments on a file in Google Drive.
 */
public class CommentList {
	/**
	 * List of comments.
	 */
	public final List<Comment> items;

	/**
	 * This is always drive#commentList.
	 */
	public final String kind;

	/**
	 * A link to the next page of comments.
	 */
	public final String nextLink;

	/**
	 * The token to use to request the next page of results.
	 */
	public final String nextPageToken;

	/**
	 * A link back to this list.
	 */
	public final String selfLink;

	@JsonCreator
	public CommentList(@JsonProperty("items") final List<Comment> items,
			@JsonProperty("kind") final String kind,
			@JsonProperty("nextLink") final String nextLink,
			@JsonProperty("nextPageToken") final String nextPageToken,
			@JsonProperty("selfLink") final String selfLink) {
		this.items = items;
		this.kind = kind;
		this.nextLink = nextLink;
		this.nextPageToken = nextPageToken;
		this.selfLink = selfLink;
	}

}
