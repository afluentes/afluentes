package com.google.api.services.drive;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The JSON template for a user.
 */
public class User {
	/**
	 * A plain text displayable name for this user.
	 */
	public final String displayName;

	/**
	 * Whether this user is the same as the authenticated user for whom the
	 * request was made.
	 */
	public final boolean isAuthenticatedUser;

	/**
	 * This is always drive#user.
	 */
	public final String kind;

	/**
	 * The user's ID as visible in the permissions collection.
	 */
	public final String permissionId;

	/**
	 * The user's profile picture.
	 */
	public final Picture picture;

	@JsonCreator
	public User(
			@JsonProperty("displayName") final String displayName,
			@JsonProperty("isAuthenticatedUser") final boolean isAuthenticatedUser,
			@JsonProperty("kind") final String kind,
			@JsonProperty("permissionId") final String permissionId,
			@JsonProperty("picture") final Picture picture) {
		this.displayName = displayName;
		this.isAuthenticatedUser = isAuthenticatedUser;
		this.kind = kind;
		this.permissionId = permissionId;
		this.picture = picture;
	}

	/**
	 * The user's profile picture.
	 */
	public static class Picture {
		/**
		 * A URL that points to a profile picture of this user.
		 */
		public final String url;

		@JsonCreator
		public Picture(@JsonProperty("url") final String url) {
			this.url = url;
		}

	}
}
