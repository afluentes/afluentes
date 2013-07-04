package com.google.api.services.drive;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * An item with user information and settings.
 */
public class About {
	/**
	 * Information about supported additional roles per file type. The most
	 * specific type takes precedence.
	 */
	public final List<AdditionalRoleInfo> additionalRoleInfo;

	/**
	 * The domain sharing policy for the current user.
	 */
	public final String domainSharingPolicy;

	/**
	 * The ETag of the item.
	 */
	public final String etag;

	/**
	 * The allowable export formats.
	 */
	public final List<ExportFormats> exportFormats;

	/**
	 * List of additional features enabled on this account.
	 */
	public final List<Features> features;

	/**
	 * The allowable import formats.
	 */
	public final List<ImportFormats> importFormats;

	/**
	 * A boolean indicating whether the authenticated app is installed by the
	 * authenticated user.
	 */
	public final boolean isCurrentAppInstalled;

	/**
	 * This is always drive#about.
	 */
	public final String kind;

	/**
	 * The largest change id.
	 */
	public final String largestChangeId;

	/**
	 * List of max upload sizes for each file type. The most specific type takes
	 * precedence.
	 */
	public final List<MaxUploadSizes> maxUploadSizes;

	/**
	 * The name of the current user.
	 */
	public final String name;

	/**
	 * The current user's ID as visible in the permissions collection.
	 */
	public final String permissionId;

	/**
	 * The total number of quota bytes.
	 */
	public final String quotaBytesTotal;

	/**
	 * The number of quota bytes used by Google Drive.
	 */
	public final String quotaBytesUsed;

	/**
	 * The number of quota bytes used by all Google apps (Drive, Picasa, etc.).
	 */
	public final String quotaBytesUsedAggregate;

	/**
	 * The number of quota bytes used by trashed items.
	 */
	public final String quotaBytesUsedInTrash;

	/**
	 * The number of remaining change ids.
	 */
	public final String remainingChangeIds;

	/**
	 * The id of the root folder.
	 */
	public final String rootFolderId;

	/**
	 * A link back to this item.
	 */
	public final String selfLink;

	/**
	 * The authenticated user.
	 */
	public final User user;

	@JsonCreator
	public About(
			@JsonProperty("additionalRoleInfo") final List<AdditionalRoleInfo> additionalRoleInfo,
			@JsonProperty("domainSharingPolicy") final String domainSharingPolicy,
			@JsonProperty("etag") final String etag,
			@JsonProperty("exportFormats") final List<ExportFormats> exportFormats,
			@JsonProperty("features") final List<Features> features,
			@JsonProperty("importFormats") final List<ImportFormats> importFormats,
			@JsonProperty("isCurrentAppInstalled") final boolean isCurrentAppInstalled,
			@JsonProperty("kind") final String kind,
			@JsonProperty("largestChangeId") final String largestChangeId,
			@JsonProperty("maxUploadSizes") final List<MaxUploadSizes> maxUploadSizes,
			@JsonProperty("name") final String name,
			@JsonProperty("permissionId") final String permissionId,
			@JsonProperty("quotaBytesTotal") final String quotaBytesTotal,
			@JsonProperty("quotaBytesUsed") final String quotaBytesUsed,
			@JsonProperty("quotaBytesUsedAggregate") final String quotaBytesUsedAggregate,
			@JsonProperty("quotaBytesUsedInTrash") final String quotaBytesUsedInTrash,
			@JsonProperty("remainingChangeIds") final String remainingChangeIds,
			@JsonProperty("rootFolderId") final String rootFolderId,
			@JsonProperty("selfLink") final String selfLink,
			@JsonProperty("user") final User user) {
		this.additionalRoleInfo = additionalRoleInfo;
		this.domainSharingPolicy = domainSharingPolicy;
		this.etag = etag;
		this.exportFormats = exportFormats;
		this.features = features;
		this.importFormats = importFormats;
		this.isCurrentAppInstalled = isCurrentAppInstalled;
		this.kind = kind;
		this.largestChangeId = largestChangeId;
		this.maxUploadSizes = maxUploadSizes;
		this.name = name;
		this.permissionId = permissionId;
		this.quotaBytesTotal = quotaBytesTotal;
		this.quotaBytesUsed = quotaBytesUsed;
		this.quotaBytesUsedAggregate = quotaBytesUsedAggregate;
		this.quotaBytesUsedInTrash = quotaBytesUsedInTrash;
		this.remainingChangeIds = remainingChangeIds;
		this.rootFolderId = rootFolderId;
		this.selfLink = selfLink;
		this.user = user;
	}

	public static class AdditionalRoleInfo {
		/**
		 * The supported additional roles per primary role.
		 */
		public final List<RoleSets> roleSets;

		/**
		 * The content type that this additional role info applies to.
		 */
		public final String type;

		@JsonCreator
		public AdditionalRoleInfo(
				@JsonProperty("roleSets") final List<RoleSets> roleSets,
				@JsonProperty("type") final String type) {
			this.roleSets = roleSets;
			this.type = type;
		}

		public static class RoleSets {
			/**
			 * The supported additional roles with the primary role.
			 */
			public final List<String> additionalRoles;

			/**
			 * A primary permission role.
			 */
			public final String primaryRole;

			@JsonCreator
			public RoleSets(
					@JsonProperty("additionalRoles") final List<String> additionalRoles,
					@JsonProperty("primaryRole") final String primaryRole) {
				this.additionalRoles = additionalRoles;
				this.primaryRole = primaryRole;
			}

		}
	}

	public static class ExportFormats {
		/**
		 * The content type to convert from.
		 */
		public final String source;

		/**
		 * The possible content types to convert to.
		 */
		public final List<String> targets;

		@JsonCreator
		public ExportFormats(@JsonProperty("source") final String source,
				@JsonProperty("targets") final List<String> targets) {
			this.source = source;
			this.targets = targets;
		}

	}

	public static class Features {
		/**
		 * The name of the feature.
		 */
		public final String featureName;

		/**
		 * The request limit rate for this feature, in queries per second.
		 */
		public final double featureRate;

		@JsonCreator
		public Features(@JsonProperty("featureName") final String featureName,
				@JsonProperty("featureRate") final double featureRate) {
			this.featureName = featureName;
			this.featureRate = featureRate;
		}

	}

	public static class ImportFormats {
		/**
		 * The imported file's content type to convert from.
		 */
		public final String source;

		/**
		 * The possible content types to convert to.
		 */
		public final List<String> targets;

		@JsonCreator
		public ImportFormats(@JsonProperty("source") final String source,
				@JsonProperty("targets") final List<String> targets) {
			this.source = source;
			this.targets = targets;
		}

	}

	public static class MaxUploadSizes {
		/**
		 * The max upload size for this type.
		 */
		public final String size;

		/**
		 * The file type.
		 */
		public final String type;

		@JsonCreator
		public MaxUploadSizes(@JsonProperty("size") final String size,
				@JsonProperty("type") final String type) {
			this.size = size;
			this.type = type;
		}

	}
}
