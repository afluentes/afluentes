package com.google.api.services.drive;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * A list of files.
 */
public class FileList {
	/**
	 * The ETag of the list.
	 */
	public final String etag;

	/**
	 * The actual list of files.
	 */
	public final List<File> items;

	/**
	 * This is always drive#fileList.
	 */
	public final String kind;

	/**
	 * A link to the next page of files.
	 */
	public final String nextLink;

	/**
	 * The page token for the next page of files.
	 */
	public final String nextPageToken;

	/**
	 * A link back to this list.
	 */
	public final String selfLink;

	@JsonCreator
	public FileList(@JsonProperty("etag") final String etag,
			@JsonProperty("items") final List<File> items,
			@JsonProperty("kind") final String kind,
			@JsonProperty("nextLink") final String nextLink,
			@JsonProperty("nextPageToken") final String nextPageToken,
			@JsonProperty("selfLink") final String selfLink) {
		this.etag = etag;
		this.items = items;
		this.kind = kind;
		this.nextLink = nextLink;
		this.nextPageToken = nextPageToken;
		this.selfLink = selfLink;
	}

}
