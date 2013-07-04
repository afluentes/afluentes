package com.google.api.services.plus;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class Moment {
	/**
	 * The moment ID.
	 */
	public final String id;

	/**
	 * Identifies this resource as a moment.
	 */
	public final String kind;

	/**
	 * The object generated by performing the action on the target. For example,
	 * a user writes a review of a restaurant, the target is the restaurant and
	 * the result is the review.
	 */
	public final ItemScope result;

	/**
	 * Time stamp of when the action occurred in RFC3339 format.
	 */
	public final String startDate;

	/**
	 * The object on which the action was performed.
	 */
	public final ItemScope target;

	/**
	 * The Google schema for the type of moment to write. For example,
	 * http://schemas.google.com/AddActivity.
	 */
	public final String type;

	@JsonCreator
	public Moment(@JsonProperty("id") final String id,
			@JsonProperty("kind") final String kind,
			@JsonProperty("result") final ItemScope result,
			@JsonProperty("startDate") final String startDate,
			@JsonProperty("target") final ItemScope target,
			@JsonProperty("type") final String type) {
		this.id = id;
		this.kind = kind;
		this.result = result;
		this.startDate = startDate;
		this.target = target;
		this.type = type;
	}

}
