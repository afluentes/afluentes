package com.google.api.services.drive;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The apps resource provides a list of the apps that a user has installed, with
 * information about each app's supported MIME types, file extensions, and other
 * details.
 */
public class App {
	/**
	 * Whether the app is authorized to access data on the user's Drive.
	 */
	public final boolean authorized;

	/**
	 * The various icons for the app.
	 */
	public final List<Icons> icons;

	/**
	 * The ID of the app.
	 */
	public final String id;

	/**
	 * Whether the app is installed.
	 */
	public final boolean installed;

	/**
	 * This is always drive#app.
	 */
	public final String kind;

	/**
	 * A long description of the app.
	 */
	public final String longDescription;

	/**
	 * The name of the app.
	 */
	public final String name;

	/**
	 * The type of object this app creates (e.g. Chart). If empty, the app name
	 * should be used instead.
	 */
	public final String objectType;

	/**
	 * The template url for opening files with this app. The template will
	 * contain {ids} and/or {exportIds} to be replaced by the actual file ids.
	 */
	public final String openUrlTemplate;

	/**
	 * The list of primary file extensions.
	 */
	public final List<String> primaryFileExtensions;

	/**
	 * The list of primary mime types.
	 */
	public final List<String> primaryMimeTypes;

	/**
	 * The ID of the product listing for this app.
	 */
	public final String productId;

	/**
	 * A link to the product listing for this app.
	 */
	public final String productUrl;

	/**
	 * The list of secondary file extensions.
	 */
	public final List<String> secondaryFileExtensions;

	/**
	 * The list of secondary mime types.
	 */
	public final List<String> secondaryMimeTypes;

	/**
	 * A short description of the app.
	 */
	public final String shortDescription;

	/**
	 * Whether this app supports creating new objects.
	 */
	public final boolean supportsCreate;

	/**
	 * Whether this app supports importing Google Docs.
	 */
	public final boolean supportsImport;

	/**
	 * Whether this app supports opening more than one file.
	 */
	public final boolean supportsMultiOpen;

	/**
	 * Whether the app is selected as the default handler for the types it
	 * supports.
	 */
	public final boolean useByDefault;

	@JsonCreator
	public App(
			@JsonProperty("authorized") final boolean authorized,
			@JsonProperty("icons") final List<Icons> icons,
			@JsonProperty("id") final String id,
			@JsonProperty("installed") final boolean installed,
			@JsonProperty("kind") final String kind,
			@JsonProperty("longDescription") final String longDescription,
			@JsonProperty("name") final String name,
			@JsonProperty("objectType") final String objectType,
			@JsonProperty("openUrlTemplate") final String openUrlTemplate,
			@JsonProperty("primaryFileExtensions") final List<String> primaryFileExtensions,
			@JsonProperty("primaryMimeTypes") final List<String> primaryMimeTypes,
			@JsonProperty("productId") final String productId,
			@JsonProperty("productUrl") final String productUrl,
			@JsonProperty("secondaryFileExtensions") final List<String> secondaryFileExtensions,
			@JsonProperty("secondaryMimeTypes") final List<String> secondaryMimeTypes,
			@JsonProperty("shortDescription") final String shortDescription,
			@JsonProperty("supportsCreate") final boolean supportsCreate,
			@JsonProperty("supportsImport") final boolean supportsImport,
			@JsonProperty("supportsMultiOpen") final boolean supportsMultiOpen,
			@JsonProperty("useByDefault") final boolean useByDefault) {
		this.authorized = authorized;
		this.icons = icons;
		this.id = id;
		this.installed = installed;
		this.kind = kind;
		this.longDescription = longDescription;
		this.name = name;
		this.objectType = objectType;
		this.openUrlTemplate = openUrlTemplate;
		this.primaryFileExtensions = primaryFileExtensions;
		this.primaryMimeTypes = primaryMimeTypes;
		this.productId = productId;
		this.productUrl = productUrl;
		this.secondaryFileExtensions = secondaryFileExtensions;
		this.secondaryMimeTypes = secondaryMimeTypes;
		this.shortDescription = shortDescription;
		this.supportsCreate = supportsCreate;
		this.supportsImport = supportsImport;
		this.supportsMultiOpen = supportsMultiOpen;
		this.useByDefault = useByDefault;
	}

	public static class Icons {
		/**
		 * Category of the icon. Allowed values are: - application - icon for
		 * the application - document - icon for a file associated with the app
		 * - documentShared - icon for a shared file associated with the app
		 */
		public final String category;

		/**
		 * URL for the icon.
		 */
		public final String iconUrl;

		/**
		 * Size of the icon. Represented as the maximum of the width and height.
		 */
		public final int size;

		@JsonCreator
		public Icons(@JsonProperty("category") final String category,
				@JsonProperty("iconUrl") final String iconUrl,
				@JsonProperty("size") final int size) {
			this.category = category;
			this.iconUrl = iconUrl;
			this.size = size;
		}

	}
}
