package com.google.api.services.drive;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * A JSON representation of a list of replies to a comment on a file in Google
 * Drive.
 */
public class CommentReplyList {
	/**
	 * List of reply.
	 */
	public final List<CommentReply> items;

	/**
	 * This is always drive#commentReplyList.
	 */
	public final String kind;

	/**
	 * A link to the next page of replies.
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
	public CommentReplyList(
			@JsonProperty("items") final List<CommentReply> items,
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
