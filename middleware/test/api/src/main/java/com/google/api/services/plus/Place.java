package com.google.api.services.plus;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class Place {
	/**
	 * The physical address of the place.
	 */
	public final Address address;

	/**
	 * The display name of the place.
	 */
	public final String displayName;

	/**
	 * Identifies this resource as a place. Value: "plus#place".
	 */
	public final String kind;

	/**
	 * The position of the place.
	 */
	public final Position position;

	@JsonCreator
	public Place(@JsonProperty("address") final Address address,
			@JsonProperty("displayName") final String displayName,
			@JsonProperty("kind") final String kind,
			@JsonProperty("position") final Position position) {
		this.address = address;
		this.displayName = displayName;
		this.kind = kind;
		this.position = position;
	}

	/**
	 * The physical address of the place.
	 */
	public static class Address {
		/**
		 * The formatted address for display.
		 */
		public final String formatted;

		@JsonCreator
		public Address(@JsonProperty("formatted") final String formatted) {
			this.formatted = formatted;
		}

	}

	/**
	 * The position of the place.
	 */
	public static class Position {
		/**
		 * The latitude of this position.
		 */
		public final double latitude;

		/**
		 * The longitude of this position.
		 */
		public final double longitude;

		@JsonCreator
		public Position(@JsonProperty("latitude") final double latitude,
				@JsonProperty("longitude") final double longitude) {
			this.latitude = latitude;
			this.longitude = longitude;
		}

	}
}
