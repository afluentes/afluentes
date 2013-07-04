package com.google.api.services.plus;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class PlusAclentryResource {
	/**
	 * A descriptive name for this entry. Suitable for display.
	 */
	public final String displayName;

	/**
	 * The ID of the entry. For entries of type "person" or "circle", this is
	 * the ID of the resource. For other types, this property is not set.
	 */
	public final String id;

	/**
	 * The type of entry describing to whom access is granted. Possible values
	 * are: - "person" - Access to an individual. - "circle" - Access to members
	 * of a circle. - "myCircles" - Access to members of all the person's
	 * circles. - "extendedCircles" - Access to members of everyone in a
	 * person's circles, plus all of the people in their circles. - "domain" -
	 * Access to members of the person's Google Apps domain. - "public" - Access
	 * to anyone on the web.
	 */
	public final String type;

	@JsonCreator
	public PlusAclentryResource(
			@JsonProperty("displayName") final String displayName,
			@JsonProperty("id") final String id,
			@JsonProperty("type") final String type) {
		this.displayName = displayName;
		this.id = id;
		this.type = type;
	}

}
