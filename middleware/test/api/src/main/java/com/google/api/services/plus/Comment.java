package com.google.api.services.plus;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class Comment {
	/**
	 * The person who posted this comment.
	 */
	public final Actor actor;

	/**
	 * ETag of this response for caching purposes.
	 */
	public final String etag;

	/**
	 * The ID of this comment.
	 */
	public final String id;

	/**
	 * The activity this comment replied to.
	 */
	public final List<InReplyTo> inReplyTo;

	/**
	 * Identifies this resource as a comment. Value: "plus#comment".
	 */
	public final String kind;

	/**
	 * The object of this comment.
	 */
	public final Object object;

	/**
	 * People who +1'd this comment.
	 */
	public final Plusoners plusoners;

	/**
	 * The time at which this comment was initially published. Formatted as an
	 * RFC 3339 timestamp.
	 */
	public final String published;

	/**
	 * Link to this comment resource.
	 */
	public final String selfLink;

	/**
	 * The time at which this comment was last updated. Formatted as an RFC 3339
	 * timestamp.
	 */
	public final String updated;

	/**
	 * This comment's verb, indicating what action was performed. Possible
	 * values are: - "post" - Publish content to the stream.
	 */
	public final String verb;

	@JsonCreator
	public Comment(@JsonProperty("actor") final Actor actor,
			@JsonProperty("etag") final String etag,
			@JsonProperty("id") final String id,
			@JsonProperty("inReplyTo") final List<InReplyTo> inReplyTo,
			@JsonProperty("kind") final String kind,
			@JsonProperty("object") final Object object,
			@JsonProperty("plusoners") final Plusoners plusoners,
			@JsonProperty("published") final String published,
			@JsonProperty("selfLink") final String selfLink,
			@JsonProperty("updated") final String updated,
			@JsonProperty("verb") final String verb) {
		this.actor = actor;
		this.etag = etag;
		this.id = id;
		this.inReplyTo = inReplyTo;
		this.kind = kind;
		this.object = object;
		this.plusoners = plusoners;
		this.published = published;
		this.selfLink = selfLink;
		this.updated = updated;
		this.verb = verb;
	}

	/**
	 * The person who posted this comment.
	 */
	public static class Actor {
		/**
		 * The name of this actor, suitable for display.
		 */
		public final String displayName;

		/**
		 * The ID of the actor.
		 */
		public final String id;

		/**
		 * The image representation of this actor.
		 */
		public final Image image;

		/**
		 * A link to the person resource for this actor.
		 */
		public final String url;

		@JsonCreator
		public Actor(@JsonProperty("displayName") final String displayName,
				@JsonProperty("id") final String id,
				@JsonProperty("image") final Image image,
				@JsonProperty("url") final String url) {
			this.displayName = displayName;
			this.id = id;
			this.image = image;
			this.url = url;
		}

		/**
		 * The image representation of this actor.
		 */
		public static class Image {
			/**
			 * The URL of the actor's profile photo. To re-size the image and
			 * crop it to a square, append the query string ?sz=x, where x is
			 * the dimension in pixels of each side.
			 */
			public final String url;

			@JsonCreator
			public Image(@JsonProperty("url") final String url) {
				this.url = url;
			}

		}
	}

	public static class InReplyTo {
		/**
		 * The ID of the activity.
		 */
		public final String id;

		/**
		 * The URL of the activity.
		 */
		public final String url;

		@JsonCreator
		public InReplyTo(@JsonProperty("id") final String id,
				@JsonProperty("url") final String url) {
			this.id = id;
			this.url = url;
		}

	}

	/**
	 * The object of this comment.
	 */
	public static class Object {
		/**
		 * The HTML-formatted content, suitable for display.
		 */
		public final String content;

		/**
		 * The object type of this comment. Possible values are: - "comment" - A
		 * comment in reply to an activity.
		 */
		public final String objectType;

		/**
		 * The content (text) as provided by the author, stored without any HTML
		 * formatting. When creating or updating a comment, this value must be
		 * supplied as plain text in the request.
		 */
		public final String originalContent;

		@JsonCreator
		public Object(@JsonProperty("content") final String content,
				@JsonProperty("objectType") final String objectType,
				@JsonProperty("originalContent") final String originalContent) {
			this.content = content;
			this.objectType = objectType;
			this.originalContent = originalContent;
		}

	}

	/**
	 * People who +1'd this comment.
	 */
	public static class Plusoners {
		/**
		 * Total number of people who +1'd this comment.
		 */
		public final int totalItems;

		@JsonCreator
		public Plusoners(@JsonProperty("totalItems") final int totalItems) {
			this.totalItems = totalItems;
		}

	}
}
