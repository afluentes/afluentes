package com.google.api.services.drive;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * A permission for a file.
 */
public class Permission {
	/**
	 * Additional roles for this user. Only commenter is currently allowed.
	 */
	public final List<String> additionalRoles;

	/**
	 * The authkey parameter required for this permission.
	 */
	public final String authKey;

	/**
	 * The ETag of the permission.
	 */
	public final String etag;

	/**
	 * The ID of the permission.
	 */
	public final String id;

	/**
	 * This is always drive#permission.
	 */
	public final String kind;

	/**
	 * The name for this permission.
	 */
	public final String name;

	/**
	 * A link to the profile photo, if available.
	 */
	public final String photoLink;

	/**
	 * The primary role for this user. Allowed values are: - owner - reader -
	 * writer
	 */
	public final String role;

	/**
	 * A link back to this permission.
	 */
	public final String selfLink;

	/**
	 * The account type. Allowed values are: - user - group - domain - anyone
	 */
	public final String type;

	/**
	 * The email address or domain name for the entity. This is not populated in
	 * responses.
	 */
	public final String value;

	/**
	 * Whether the link is required for this permission.
	 */
	public final boolean withLink;

	@JsonCreator
	public Permission(
			@JsonProperty("additionalRoles") final List<String> additionalRoles,
			@JsonProperty("authKey") final String authKey,
			@JsonProperty("etag") final String etag,
			@JsonProperty("id") final String id,
			@JsonProperty("kind") final String kind,
			@JsonProperty("name") final String name,
			@JsonProperty("photoLink") final String photoLink,
			@JsonProperty("role") final String role,
			@JsonProperty("selfLink") final String selfLink,
			@JsonProperty("type") final String type,
			@JsonProperty("value") final String value,
			@JsonProperty("withLink") final boolean withLink) {
		this.additionalRoles = additionalRoles;
		this.authKey = authKey;
		this.etag = etag;
		this.id = id;
		this.kind = kind;
		this.name = name;
		this.photoLink = photoLink;
		this.role = role;
		this.selfLink = selfLink;
		this.type = type;
		this.value = value;
		this.withLink = withLink;
	}

}
