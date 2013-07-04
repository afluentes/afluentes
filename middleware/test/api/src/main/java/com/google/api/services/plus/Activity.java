package com.google.api.services.plus;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class Activity {
	/**
	 * Identifies who has access to see this activity.
	 */
	public final Acl access;

	/**
	 * The person who performed this activity.
	 */
	public final Actor actor;

	/**
	 * Street address where this activity occurred.
	 */
	public final String address;

	/**
	 * Additional content added by the person who shared this activity,
	 * applicable only when resharing an activity.
	 */
	public final String annotation;

	/**
	 * If this activity is a crosspost from another system, this property
	 * specifies the ID of the original activity.
	 */
	public final String crosspostSource;

	/**
	 * ETag of this response for caching purposes.
	 */
	public final String etag;

	/**
	 * Latitude and longitude where this activity occurred. Format is latitude
	 * followed by longitude, space separated.
	 */
	public final String geocode;

	/**
	 * The ID of this activity.
	 */
	public final String id;

	/**
	 * Identifies this resource as an activity. Value: "plus#activity".
	 */
	public final String kind;

	/**
	 * The location where this activity occurred.
	 */
	public final Place location;

	/**
	 * The object of this activity.
	 */
	public final Object object;

	/**
	 * ID of the place where this activity occurred.
	 */
	public final String placeId;

	/**
	 * Name of the place where this activity occurred.
	 */
	public final String placeName;

	/**
	 * The service provider that initially published this activity.
	 */
	public final Provider provider;

	/**
	 * The time at which this activity was initially published. Formatted as an
	 * RFC 3339 timestamp.
	 */
	public final String published;

	/**
	 * Radius, in meters, of the region where this activity occurred, centered
	 * at the latitude and longitude identified in geocode.
	 */
	public final String radius;

	/**
	 * Title of this activity.
	 */
	public final String title;

	/**
	 * The time at which this activity was last updated. Formatted as an RFC
	 * 3339 timestamp.
	 */
	public final String updated;

	/**
	 * The link to this activity.
	 */
	public final String url;

	/**
	 * This activity's verb, indicating what action was performed. Possible
	 * values are: - "post" - Publish content to the stream. - "share" - Reshare
	 * an activity.
	 */
	public final String verb;

	@JsonCreator
	public Activity(@JsonProperty("access") final Acl access,
			@JsonProperty("actor") final Actor actor,
			@JsonProperty("address") final String address,
			@JsonProperty("annotation") final String annotation,
			@JsonProperty("crosspostSource") final String crosspostSource,
			@JsonProperty("etag") final String etag,
			@JsonProperty("geocode") final String geocode,
			@JsonProperty("id") final String id,
			@JsonProperty("kind") final String kind,
			@JsonProperty("location") final Place location,
			@JsonProperty("object") final Object object,
			@JsonProperty("placeId") final String placeId,
			@JsonProperty("placeName") final String placeName,
			@JsonProperty("provider") final Provider provider,
			@JsonProperty("published") final String published,
			@JsonProperty("radius") final String radius,
			@JsonProperty("title") final String title,
			@JsonProperty("updated") final String updated,
			@JsonProperty("url") final String url,
			@JsonProperty("verb") final String verb) {
		this.access = access;
		this.actor = actor;
		this.address = address;
		this.annotation = annotation;
		this.crosspostSource = crosspostSource;
		this.etag = etag;
		this.geocode = geocode;
		this.id = id;
		this.kind = kind;
		this.location = location;
		this.object = object;
		this.placeId = placeId;
		this.placeName = placeName;
		this.provider = provider;
		this.published = published;
		this.radius = radius;
		this.title = title;
		this.updated = updated;
		this.url = url;
		this.verb = verb;
	}

	/**
	 * The person who performed this activity.
	 */
	public static class Actor {
		/**
		 * The name of the actor, suitable for display.
		 */
		public final String displayName;

		/**
		 * The ID of the actor's person resource.
		 */
		public final String id;

		/**
		 * The image representation of the actor.
		 */
		public final Image image;

		/**
		 * An object representation of the individual components of name.
		 */
		public final Name name;

		/**
		 * The link to the actor's Google profile.
		 */
		public final String url;

		@JsonCreator
		public Actor(@JsonProperty("displayName") final String displayName,
				@JsonProperty("id") final String id,
				@JsonProperty("image") final Image image,
				@JsonProperty("name") final Name name,
				@JsonProperty("url") final String url) {
			this.displayName = displayName;
			this.id = id;
			this.image = image;
			this.name = name;
			this.url = url;
		}

		/**
		 * The image representation of the actor.
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

		/**
		 * An object representation of the individual components of name.
		 */
		public static class Name {
			/**
			 * The family name (last name) of the actor.
			 */
			public final String familyName;

			/**
			 * The given name (first name) of the actor.
			 */
			public final String givenName;

			@JsonCreator
			public Name(@JsonProperty("familyName") final String familyName,
					@JsonProperty("givenName") final String givenName) {
				this.familyName = familyName;
				this.givenName = givenName;
			}

		}
	}

	/**
	 * The object of this activity.
	 */
	public static class Object {
		/**
		 * If this activity's object is itself another activity (for example,
		 * when a person reshares an activity), this property specifies the
		 * original activity's actor.
		 */
		public final Actor actor;

		/**
		 * The media objects attached to this activity.
		 */
		public final List<Attachments> attachments;

		/**
		 * The HTML-formatted content, suitable for display.
		 */
		public final String content;

		/**
		 * The ID of the object. When resharing an activity, this is the ID of
		 * the activity being reshared.
		 */
		public final String id;

		/**
		 * The type of the object. Possible values are: - "note" - Textual
		 * content. - "activity" - A Google+ activity.
		 */
		public final String objectType;

		/**
		 * The content (text) as provided by the author, stored without any HTML
		 * formatting. When creating or updating an activity, this value must be
		 * supplied as plain text in the request.
		 */
		public final String originalContent;

		/**
		 * People who +1'd this activity.
		 */
		public final Plusoners plusoners;

		/**
		 * Comments in reply to this activity.
		 */
		public final Replies replies;

		/**
		 * People who reshared this activity.
		 */
		public final Resharers resharers;

		/**
		 * The URL that points to the linked resource.
		 */
		public final String url;

		@JsonCreator
		public Object(
				@JsonProperty("actor") final Actor actor,
				@JsonProperty("attachments") final List<Attachments> attachments,
				@JsonProperty("content") final String content,
				@JsonProperty("id") final String id,
				@JsonProperty("objectType") final String objectType,
				@JsonProperty("originalContent") final String originalContent,
				@JsonProperty("plusoners") final Plusoners plusoners,
				@JsonProperty("replies") final Replies replies,
				@JsonProperty("resharers") final Resharers resharers,
				@JsonProperty("url") final String url) {
			this.actor = actor;
			this.attachments = attachments;
			this.content = content;
			this.id = id;
			this.objectType = objectType;
			this.originalContent = originalContent;
			this.plusoners = plusoners;
			this.replies = replies;
			this.resharers = resharers;
			this.url = url;
		}

		/**
		 * If this activity's object is itself another activity (for example,
		 * when a person reshares an activity), this property specifies the
		 * original activity's actor.
		 */
		public static class Actor {
			/**
			 * The original actor's name, suitable for display.
			 */
			public final String displayName;

			/**
			 * ID of the original actor.
			 */
			public final String id;

			/**
			 * The image representation of the original actor.
			 */
			public final Image image;

			/**
			 * A link to the original actor's Google profile.
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
			 * The image representation of the original actor.
			 */
			public static class Image {
				/**
				 * A URL that points to a thumbnail photo of the original actor.
				 */
				public final String url;

				@JsonCreator
				public Image(@JsonProperty("url") final String url) {
					this.url = url;
				}

			}
		}

		public static class Attachments {
			/**
			 * If the attachment is an article, this property contains a snippet
			 * of text from the article. It can also include descriptions for
			 * other types.
			 */
			public final String content;

			/**
			 * The title of the attachment (such as a photo caption or an
			 * article title).
			 */
			public final String displayName;

			/**
			 * If the attachment is a video, the embeddable link.
			 */
			public final Embed embed;

			/**
			 * The full image URL for photo attachments.
			 */
			public final FullImage fullImage;

			/**
			 * The ID of the attachment.
			 */
			public final String id;

			/**
			 * The preview image for photos or videos.
			 */
			public final Image image;

			/**
			 * The type of media object. Possible values are: - "photo" - A
			 * photo. - "album" - A photo album. - "video" - A video. -
			 * "article" - An article, specified by a link.
			 */
			public final String objectType;

			/**
			 * If the attachment is an album, potential additional thumbnails
			 * from the album.
			 */
			public final List<Thumbnails> thumbnails;

			/**
			 * The link to the attachment, should be of type text/html.
			 */
			public final String url;

			@JsonCreator
			public Attachments(
					@JsonProperty("content") final String content,
					@JsonProperty("displayName") final String displayName,
					@JsonProperty("embed") final Embed embed,
					@JsonProperty("fullImage") final FullImage fullImage,
					@JsonProperty("id") final String id,
					@JsonProperty("image") final Image image,
					@JsonProperty("objectType") final String objectType,
					@JsonProperty("thumbnails") final List<Thumbnails> thumbnails,
					@JsonProperty("url") final String url) {
				this.content = content;
				this.displayName = displayName;
				this.embed = embed;
				this.fullImage = fullImage;
				this.id = id;
				this.image = image;
				this.objectType = objectType;
				this.thumbnails = thumbnails;
				this.url = url;
			}

			/**
			 * If the attachment is a video, the embeddable link.
			 */
			public static class Embed {
				/**
				 * Media type of the link.
				 */
				public final String type;

				/**
				 * URL of the link.
				 */
				public final String url;

				@JsonCreator
				public Embed(@JsonProperty("type") final String type,
						@JsonProperty("url") final String url) {
					this.type = type;
					this.url = url;
				}

			}

			/**
			 * The full image URL for photo attachments.
			 */
			public static class FullImage {
				/**
				 * The height, in pixels, of the linked resource.
				 */
				public final int height;

				/**
				 * Media type of the link.
				 */
				public final String type;

				/**
				 * URL to the image.
				 */
				public final String url;

				/**
				 * The width, in pixels, of the linked resource.
				 */
				public final int width;

				@JsonCreator
				public FullImage(@JsonProperty("height") final int height,
						@JsonProperty("type") final String type,
						@JsonProperty("url") final String url,
						@JsonProperty("width") final int width) {
					this.height = height;
					this.type = type;
					this.url = url;
					this.width = width;
				}

			}

			/**
			 * The preview image for photos or videos.
			 */
			public static class Image {
				/**
				 * The height, in pixels, of the linked resource.
				 */
				public final int height;

				/**
				 * Media type of the link.
				 */
				public final String type;

				/**
				 * Image url.
				 */
				public final String url;

				/**
				 * The width, in pixels, of the linked resource.
				 */
				public final int width;

				@JsonCreator
				public Image(@JsonProperty("height") final int height,
						@JsonProperty("type") final String type,
						@JsonProperty("url") final String url,
						@JsonProperty("width") final int width) {
					this.height = height;
					this.type = type;
					this.url = url;
					this.width = width;
				}

			}

			public static class Thumbnails {
				/**
				 * Potential name of the thumbnail.
				 */
				public final String description;

				/**
				 * Image resource.
				 */
				public final Image image;

				/**
				 * URL to the webpage containing the image.
				 */
				public final String url;

				@JsonCreator
				public Thumbnails(
						@JsonProperty("description") final String description,
						@JsonProperty("image") final Image image,
						@JsonProperty("url") final String url) {
					this.description = description;
					this.image = image;
					this.url = url;
				}

				/**
				 * Image resource.
				 */
				public static class Image {
					/**
					 * The height, in pixels, of the linked resource.
					 */
					public final int height;

					/**
					 * Media type of the link.
					 */
					public final String type;

					/**
					 * Image url.
					 */
					public final String url;

					/**
					 * The width, in pixels, of the linked resource.
					 */
					public final int width;

					@JsonCreator
					public Image(@JsonProperty("height") final int height,
							@JsonProperty("type") final String type,
							@JsonProperty("url") final String url,
							@JsonProperty("width") final int width) {
						this.height = height;
						this.type = type;
						this.url = url;
						this.width = width;
					}

				}
			}
		}

		/**
		 * People who +1'd this activity.
		 */
		public static class Plusoners {
			/**
			 * The URL for the collection of people who +1'd this activity.
			 */
			public final String selfLink;

			/**
			 * Total number of people who +1'd this activity.
			 */
			public final int totalItems;

			@JsonCreator
			public Plusoners(@JsonProperty("selfLink") final String selfLink,
					@JsonProperty("totalItems") final int totalItems) {
				this.selfLink = selfLink;
				this.totalItems = totalItems;
			}

		}

		/**
		 * Comments in reply to this activity.
		 */
		public static class Replies {
			/**
			 * The URL for the collection of comments in reply to this activity.
			 */
			public final String selfLink;

			/**
			 * Total number of comments on this activity.
			 */
			public final int totalItems;

			@JsonCreator
			public Replies(@JsonProperty("selfLink") final String selfLink,
					@JsonProperty("totalItems") final int totalItems) {
				this.selfLink = selfLink;
				this.totalItems = totalItems;
			}

		}

		/**
		 * People who reshared this activity.
		 */
		public static class Resharers {
			/**
			 * The URL for the collection of resharers.
			 */
			public final String selfLink;

			/**
			 * Total number of people who reshared this activity.
			 */
			public final int totalItems;

			@JsonCreator
			public Resharers(@JsonProperty("selfLink") final String selfLink,
					@JsonProperty("totalItems") final int totalItems) {
				this.selfLink = selfLink;
				this.totalItems = totalItems;
			}

		}
	}

	/**
	 * The service provider that initially published this activity.
	 */
	public static class Provider {
		/**
		 * Name of the service provider.
		 */
		public final String title;

		@JsonCreator
		public Provider(@JsonProperty("title") final String title) {
			this.title = title;
		}

	}
}
