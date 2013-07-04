package com.google.api.services.plus;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class PeopleFeed {
	/**
	 * ETag of this response for caching purposes.
	 */
	public final String etag;

	/**
	 * The people in this page of results. Each item includes the id,
	 * displayName, image, and url for the person. To retrieve additional
	 * profile data, see the people.get method.
	 */
	public final List<Person> items;

	/**
	 * Identifies this resource as a collection of people. Value:
	 * "plus#peopleFeed".
	 */
	public final String kind;

	/**
	 * The continuation token, which is used to page through large result sets.
	 * Provide this value in a subsequent request to return the next page of
	 * results.
	 */
	public final String nextPageToken;

	/**
	 * Link to this resource.
	 */
	public final String selfLink;

	/**
	 * The title of this collection of people.
	 */
	public final String title;

	/**
	 * The total number of people available in this list. The number of people
	 * in a response might be smaller due to paging. This might not be set for
	 * all collections.
	 */
	public final int totalItems;

	@JsonCreator
	public PeopleFeed(@JsonProperty("etag") final String etag,
			@JsonProperty("items") final List<Person> items,
			@JsonProperty("kind") final String kind,
			@JsonProperty("nextPageToken") final String nextPageToken,
			@JsonProperty("selfLink") final String selfLink,
			@JsonProperty("title") final String title,
			@JsonProperty("totalItems") final int totalItems) {
		this.etag = etag;
		this.items = items;
		this.kind = kind;
		this.nextPageToken = nextPageToken;
		this.selfLink = selfLink;
		this.title = title;
		this.totalItems = totalItems;
	}

}
