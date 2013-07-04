package com.google.api.services.drive;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * A list of changes for a user.
 */
public class ChangeList {
	/**
	 * The ETag of the list.
	 */
	public final String etag;

	/**
	 * The actual list of changes.
	 */
	public final List<Change> items;

	/**
	 * This is always drive#changeList.
	 */
	public final String kind;

	/**
	 * The current largest change ID.
	 */
	public final String largestChangeId;

	/**
	 * A link to the next page of changes.
	 */
	public final String nextLink;

	/**
	 * The page token for the next page of changes.
	 */
	public final String nextPageToken;

	/**
	 * A link back to this list.
	 */
	public final String selfLink;

	@JsonCreator
	public ChangeList(@JsonProperty("etag") final String etag,
			@JsonProperty("items") final List<Change> items,
			@JsonProperty("kind") final String kind,
			@JsonProperty("largestChangeId") final String largestChangeId,
			@JsonProperty("nextLink") final String nextLink,
			@JsonProperty("nextPageToken") final String nextPageToken,
			@JsonProperty("selfLink") final String selfLink) {
		this.etag = etag;
		this.items = items;
		this.kind = kind;
		this.largestChangeId = largestChangeId;
		this.nextLink = nextLink;
		this.nextPageToken = nextPageToken;
		this.selfLink = selfLink;
	}

}
