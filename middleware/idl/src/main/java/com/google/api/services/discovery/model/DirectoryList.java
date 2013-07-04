package com.google.api.services.discovery.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class DirectoryList {
	/**
	 * Indicate the version of the Discovery API used to generate this doc.
	 */
	public final String discoveryVersion;

	/**
	 * The individual directory entries. One entry per api/version pair.
	 */
	public final List<Items> items;

	/**
	 * The kind for this response.
	 */
	public final String kind;

	@JsonCreator
	public DirectoryList(
			@JsonProperty("discoveryVersion") final String discoveryVersion,
			@JsonProperty("items") final List<Items> items,
			@JsonProperty("kind") final String kind) {
		this.discoveryVersion = discoveryVersion;
		this.items = items;
		this.kind = kind;
	}

	public static class Items {
		/**
		 * The description of this API.
		 */
		public final String description;

		/**
		 * A link to the discovery document.
		 */
		public final String discoveryLink;

		/**
		 * The URL for the discovery REST document.
		 */
		public final String discoveryRestUrl;

		/**
		 * A link to human readable documentation for the API.
		 */
		public final String documentationLink;

		/**
		 * Links to 16x16 and 32x32 icons representing the API.
		 */
		public final Icons icons;

		/**
		 * The id of this API.
		 */
		public final String id;

		/**
		 * The kind for this response.
		 */
		public final String kind;

		/**
		 * Labels for the status of this API, such as labs or deprecated.
		 */
		public final List<String> labels;

		/**
		 * The name of the API.
		 */
		public final String name;

		/**
		 * True if this version is the preferred version to use.
		 */
		public final Boolean preferred;

		/**
		 * The title of this API.
		 */
		public final String title;

		/**
		 * The version of the API.
		 */
		public final String version;

		@JsonCreator
		public Items(
				@JsonProperty("description") final String description,
				@JsonProperty("discoveryLink") final String discoveryLink,
				@JsonProperty("discoveryRestUrl") final String discoveryRestUrl,
				@JsonProperty("documentationLink") final String documentationLink,
				@JsonProperty("icons") final Icons icons,
				@JsonProperty("id") final String id,
				@JsonProperty("kind") final String kind,
				@JsonProperty("labels") final List<String> labels,
				@JsonProperty("name") final String name,
				@JsonProperty("preferred") final Boolean preferred,
				@JsonProperty("title") final String title,
				@JsonProperty("version") final String version) {
			this.description = description;
			this.discoveryLink = discoveryLink;
			this.discoveryRestUrl = discoveryRestUrl;
			this.documentationLink = documentationLink;
			this.icons = icons;
			this.id = id;
			this.kind = kind;
			this.labels = labels;
			this.name = name;
			this.preferred = preferred;
			this.title = title;
			this.version = version;
		}

		/**
		 * Links to 16x16 and 32x32 icons representing the API.
		 */
		public static class Icons {
			/**
			 * The URL of the 16x16 icon.
			 */
			public final String x16;

			/**
			 * The URL of the 32x32 icon.
			 */
			public final String x32;

			@JsonCreator
			public Icons(@JsonProperty("x16") final String x16,
					@JsonProperty("x32") final String x32) {
				this.x16 = x16;
				this.x32 = x32;
			}

		}
	}
}
