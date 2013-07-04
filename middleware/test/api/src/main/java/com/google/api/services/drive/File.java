package com.google.api.services.drive;

import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The metadata for a file.
 */
public class File {
	/**
	 * A link for opening the file in using a relevant Google editor or viewer.
	 */
	public final String alternateLink;

	/**
	 * Whether this file is in the appdata folder.
	 */
	public final boolean appDataContents;

	/**
	 * Create time for this file (formatted ISO8601 timestamp).
	 */
	public final String createdDate;

	/**
	 * A link to open this file with the user's default app for this file. Only
	 * populated when the drive.apps.readonly scope is used.
	 */
	public final String defaultOpenWithLink;

	/**
	 * A short description of the file.
	 */
	public final String description;

	/**
	 * Short lived download URL for the file. This is only populated for files
	 * with content stored in Drive.
	 */
	public final String downloadUrl;

	/**
	 * Whether the file can be edited by the current user.
	 */
	public final boolean editable;

	/**
	 * A link for embedding the file.
	 */
	public final String embedLink;

	/**
	 * ETag of the file.
	 */
	public final String etag;

	/**
	 * Whether this file has been explicitly trashed, as opposed to recursively
	 * trashed. This will only be populated if the file is trashed.
	 */
	public final boolean explicitlyTrashed;

	/**
	 * Links for exporting Google Docs to specific formats.
	 */
	public final Map<String, String> exportLinks;

	/**
	 * The file extension used when downloading this file. This field is read
	 * only. To set the extension, include it in the title when creating the
	 * file. This is only populated for files with content stored in Drive.
	 */
	public final String fileExtension;

	/**
	 * The size of the file in bytes. This is only populated for files with
	 * content stored in Drive.
	 */
	public final String fileSize;

	/**
	 * A link to the file's icon.
	 */
	public final String iconLink;

	/**
	 * The ID of the file.
	 */
	public final String id;

	/**
	 * Metadata about image media. This will only be present for image types,
	 * and its contents will depend on what can be parsed from the image
	 * content.
	 */
	public final ImageMediaMetadata imageMediaMetadata;

	/**
	 * Indexable text attributes for the file (can only be written)
	 */
	public final IndexableText indexableText;

	/**
	 * The type of file. This is always drive#file.
	 */
	public final String kind;

	/**
	 * A group of labels for the file.
	 */
	public final Labels labels;

	/**
	 * The last user to modify this file.
	 */
	public final User lastModifyingUser;

	/**
	 * Name of the last user to modify this file.
	 */
	public final String lastModifyingUserName;

	/**
	 * Last time this file was viewed by the user (formatted RFC 3339
	 * timestamp).
	 */
	public final String lastViewedByMeDate;

	/**
	 * An MD5 checksum for the content of this file. This is populated only for
	 * files with content stored in Drive.
	 */
	public final String md5Checksum;

	/**
	 * The MIME type of the file. This is only mutable on update when uploading
	 * new content. This field can be left blank, and the mimetype will be
	 * determined from the uploaded content's MIME type.
	 */
	public final String mimeType;

	/**
	 * Last time this file was modified by the user (formatted RFC 3339
	 * timestamp). Note that setting modifiedDate will also update the
	 * modifiedByMe date for the user which set the date.
	 */
	public final String modifiedByMeDate;

	/**
	 * Last time this file was modified by anyone (formatted RFC 3339
	 * timestamp). This is only mutable on update when the setModifiedDate
	 * parameter is set.
	 */
	public final String modifiedDate;

	/**
	 * A map of the id of each of the user's apps to a link to open this file
	 * with that app. Only populated when the drive.apps.readonly scope is used.
	 */
	public final Map<String, String> openWithLinks;

	/**
	 * The original filename if the file was uploaded manually, or the original
	 * title if the file was inserted through the API. Note that renames of the
	 * title will not change the original filename. This will only be populated
	 * on files with content stored in Drive.
	 */
	public final String originalFilename;

	/**
	 * Name(s) of the owner(s) of this file.
	 */
	public final List<String> ownerNames;

	/**
	 * The owner(s) of this file.
	 */
	public final List<User> owners;

	/**
	 * Collection of parent folders which contain this file. Setting this field
	 * will put the file in all of the provided folders. On insert, if no
	 * folders are provided, the file will be placed in the default root folder.
	 */
	public final List<ParentReference> parents;

	/**
	 * The number of quota bytes used by this file.
	 */
	public final String quotaBytesUsed;

	/**
	 * A link back to this file.
	 */
	public final String selfLink;

	/**
	 * Whether the file has been shared.
	 */
	public final boolean shared;

	/**
	 * Time at which this file was shared with the user (formatted RFC 3339
	 * timestamp).
	 */
	public final String sharedWithMeDate;

	/**
	 * Thumbnail for the file. Only accepted on upload and for files that are
	 * not already thumbnailed by Google.
	 */
	public final Thumbnail thumbnail;

	/**
	 * A link to the file's thumbnail.
	 */
	public final String thumbnailLink;

	/**
	 * The title of this file.
	 */
	public final String title;

	/**
	 * The permissions for the authenticated user on this file.
	 */
	public final Permission userPermission;

	/**
	 * A link for downloading the content of the file in a browser using cookie
	 * based authentication. In cases where the content is shared publicly, the
	 * content can be downloaded without any credentials.
	 */
	public final String webContentLink;

	/**
	 * A link only available on public folders for viewing their static web
	 * assets (HTML, CSS, JS, etc) via Google Drive's Website Hosting.
	 */
	public final String webViewLink;

	/**
	 * Whether writers can share the document with other users.
	 */
	public final boolean writersCanShare;

	@JsonCreator
	public File(
			@JsonProperty("alternateLink") final String alternateLink,
			@JsonProperty("appDataContents") final boolean appDataContents,
			@JsonProperty("createdDate") final String createdDate,
			@JsonProperty("defaultOpenWithLink") final String defaultOpenWithLink,
			@JsonProperty("description") final String description,
			@JsonProperty("downloadUrl") final String downloadUrl,
			@JsonProperty("editable") final boolean editable,
			@JsonProperty("embedLink") final String embedLink,
			@JsonProperty("etag") final String etag,
			@JsonProperty("explicitlyTrashed") final boolean explicitlyTrashed,
			@JsonProperty("exportLinks") final Map<String, String> exportLinks,
			@JsonProperty("fileExtension") final String fileExtension,
			@JsonProperty("fileSize") final String fileSize,
			@JsonProperty("iconLink") final String iconLink,
			@JsonProperty("id") final String id,
			@JsonProperty("imageMediaMetadata") final ImageMediaMetadata imageMediaMetadata,
			@JsonProperty("indexableText") final IndexableText indexableText,
			@JsonProperty("kind") final String kind,
			@JsonProperty("labels") final Labels labels,
			@JsonProperty("lastModifyingUser") final User lastModifyingUser,
			@JsonProperty("lastModifyingUserName") final String lastModifyingUserName,
			@JsonProperty("lastViewedByMeDate") final String lastViewedByMeDate,
			@JsonProperty("md5Checksum") final String md5Checksum,
			@JsonProperty("mimeType") final String mimeType,
			@JsonProperty("modifiedByMeDate") final String modifiedByMeDate,
			@JsonProperty("modifiedDate") final String modifiedDate,
			@JsonProperty("openWithLinks") final Map<String, String> openWithLinks,
			@JsonProperty("originalFilename") final String originalFilename,
			@JsonProperty("ownerNames") final List<String> ownerNames,
			@JsonProperty("owners") final List<User> owners,
			@JsonProperty("parents") final List<ParentReference> parents,
			@JsonProperty("quotaBytesUsed") final String quotaBytesUsed,
			@JsonProperty("selfLink") final String selfLink,
			@JsonProperty("shared") final boolean shared,
			@JsonProperty("sharedWithMeDate") final String sharedWithMeDate,
			@JsonProperty("thumbnail") final Thumbnail thumbnail,
			@JsonProperty("thumbnailLink") final String thumbnailLink,
			@JsonProperty("title") final String title,
			@JsonProperty("userPermission") final Permission userPermission,
			@JsonProperty("webContentLink") final String webContentLink,
			@JsonProperty("webViewLink") final String webViewLink,
			@JsonProperty("writersCanShare") final boolean writersCanShare) {
		this.alternateLink = alternateLink;
		this.appDataContents = appDataContents;
		this.createdDate = createdDate;
		this.defaultOpenWithLink = defaultOpenWithLink;
		this.description = description;
		this.downloadUrl = downloadUrl;
		this.editable = editable;
		this.embedLink = embedLink;
		this.etag = etag;
		this.explicitlyTrashed = explicitlyTrashed;
		this.exportLinks = exportLinks;
		this.fileExtension = fileExtension;
		this.fileSize = fileSize;
		this.iconLink = iconLink;
		this.id = id;
		this.imageMediaMetadata = imageMediaMetadata;
		this.indexableText = indexableText;
		this.kind = kind;
		this.labels = labels;
		this.lastModifyingUser = lastModifyingUser;
		this.lastModifyingUserName = lastModifyingUserName;
		this.lastViewedByMeDate = lastViewedByMeDate;
		this.md5Checksum = md5Checksum;
		this.mimeType = mimeType;
		this.modifiedByMeDate = modifiedByMeDate;
		this.modifiedDate = modifiedDate;
		this.openWithLinks = openWithLinks;
		this.originalFilename = originalFilename;
		this.ownerNames = ownerNames;
		this.owners = owners;
		this.parents = parents;
		this.quotaBytesUsed = quotaBytesUsed;
		this.selfLink = selfLink;
		this.shared = shared;
		this.sharedWithMeDate = sharedWithMeDate;
		this.thumbnail = thumbnail;
		this.thumbnailLink = thumbnailLink;
		this.title = title;
		this.userPermission = userPermission;
		this.webContentLink = webContentLink;
		this.webViewLink = webViewLink;
		this.writersCanShare = writersCanShare;
	}

	/**
	 * A mapping from export format to URL
	 */
	public static class ExportLinks {

		@JsonCreator
		public ExportLinks() {
		}

	}

	/**
	 * Metadata about image media. This will only be present for image types,
	 * and its contents will depend on what can be parsed from the image
	 * content.
	 */
	public static class ImageMediaMetadata {
		/**
		 * The aperture used to create the photo (f-number).
		 */
		public final double aperture;

		/**
		 * The make of the camera used to create the photo.
		 */
		public final String cameraMake;

		/**
		 * The model of the camera used to create the photo.
		 */
		public final String cameraModel;

		/**
		 * The color space of the photo.
		 */
		public final String colorSpace;

		/**
		 * The date and time the photo was taken (EXIF format timestamp).
		 */
		public final String date;

		/**
		 * The exposure bias of the photo (APEX value).
		 */
		public final double exposureBias;

		/**
		 * The exposure mode used to create the photo.
		 */
		public final String exposureMode;

		/**
		 * The length of the exposure, in seconds.
		 */
		public final double exposureTime;

		/**
		 * Whether a flash was used to create the photo.
		 */
		public final boolean flashUsed;

		/**
		 * The focal length used to create the photo, in millimeters.
		 */
		public final double focalLength;

		/**
		 * The height of the image in pixels.
		 */
		public final int height;

		/**
		 * The ISO speed used to create the photo.
		 */
		public final int isoSpeed;

		/**
		 * The lens used to create the photo.
		 */
		public final String lens;

		/**
		 * Geographic location information stored in the image.
		 */
		public final Location location;

		/**
		 * The smallest f-number of the lens at the focal length used to create
		 * the photo (APEX value).
		 */
		public final double maxApertureValue;

		/**
		 * The metering mode used to create the photo.
		 */
		public final String meteringMode;

		/**
		 * The rotation in clockwise degrees from the image's original
		 * orientation.
		 */
		public final int rotation;

		/**
		 * The type of sensor used to create the photo.
		 */
		public final String sensor;

		/**
		 * The distance to the subject of the photo, in meters.
		 */
		public final int subjectDistance;

		/**
		 * The white balance mode used to create the photo.
		 */
		public final String whiteBalance;

		/**
		 * The width of the image in pixels.
		 */
		public final int width;

		@JsonCreator
		public ImageMediaMetadata(
				@JsonProperty("aperture") final double aperture,
				@JsonProperty("cameraMake") final String cameraMake,
				@JsonProperty("cameraModel") final String cameraModel,
				@JsonProperty("colorSpace") final String colorSpace,
				@JsonProperty("date") final String date,
				@JsonProperty("exposureBias") final double exposureBias,
				@JsonProperty("exposureMode") final String exposureMode,
				@JsonProperty("exposureTime") final double exposureTime,
				@JsonProperty("flashUsed") final boolean flashUsed,
				@JsonProperty("focalLength") final double focalLength,
				@JsonProperty("height") final int height,
				@JsonProperty("isoSpeed") final int isoSpeed,
				@JsonProperty("lens") final String lens,
				@JsonProperty("location") final Location location,
				@JsonProperty("maxApertureValue") final double maxApertureValue,
				@JsonProperty("meteringMode") final String meteringMode,
				@JsonProperty("rotation") final int rotation,
				@JsonProperty("sensor") final String sensor,
				@JsonProperty("subjectDistance") final int subjectDistance,
				@JsonProperty("whiteBalance") final String whiteBalance,
				@JsonProperty("width") final int width) {
			this.aperture = aperture;
			this.cameraMake = cameraMake;
			this.cameraModel = cameraModel;
			this.colorSpace = colorSpace;
			this.date = date;
			this.exposureBias = exposureBias;
			this.exposureMode = exposureMode;
			this.exposureTime = exposureTime;
			this.flashUsed = flashUsed;
			this.focalLength = focalLength;
			this.height = height;
			this.isoSpeed = isoSpeed;
			this.lens = lens;
			this.location = location;
			this.maxApertureValue = maxApertureValue;
			this.meteringMode = meteringMode;
			this.rotation = rotation;
			this.sensor = sensor;
			this.subjectDistance = subjectDistance;
			this.whiteBalance = whiteBalance;
			this.width = width;
		}

		/**
		 * Geographic location information stored in the image.
		 */
		public static class Location {
			/**
			 * The altitude stored in the image.
			 */
			public final double altitude;

			/**
			 * The latitude stored in the image.
			 */
			public final double latitude;

			/**
			 * The longitude stored in the image.
			 */
			public final double longitude;

			@JsonCreator
			public Location(@JsonProperty("altitude") final double altitude,
					@JsonProperty("latitude") final double latitude,
					@JsonProperty("longitude") final double longitude) {
				this.altitude = altitude;
				this.latitude = latitude;
				this.longitude = longitude;
			}

		}
	}

	/**
	 * Indexable text attributes for the file (can only be written)
	 */
	public static class IndexableText {
		/**
		 * The text to be indexed for this file.
		 */
		public final String text;

		@JsonCreator
		public IndexableText(@JsonProperty("text") final String text) {
			this.text = text;
		}

	}

	/**
	 * A group of labels for the file.
	 */
	public static class Labels {
		/**
		 * Whether this file is hidden from the user.
		 */
		public final boolean hidden;

		/**
		 * Whether viewers are prevented from downloading this file.
		 */
		public final boolean restricted;

		/**
		 * Whether this file is starred by the user.
		 */
		public final boolean starred;

		/**
		 * Whether this file has been trashed.
		 */
		public final boolean trashed;

		/**
		 * Whether this file has been viewed by this user.
		 */
		public final boolean viewed;

		@JsonCreator
		public Labels(@JsonProperty("hidden") final boolean hidden,
				@JsonProperty("restricted") final boolean restricted,
				@JsonProperty("starred") final boolean starred,
				@JsonProperty("trashed") final boolean trashed,
				@JsonProperty("viewed") final boolean viewed) {
			this.hidden = hidden;
			this.restricted = restricted;
			this.starred = starred;
			this.trashed = trashed;
			this.viewed = viewed;
		}

	}

	public static class OpenWithLinks {

		@JsonCreator
		public OpenWithLinks() {
		}

	}

	/**
	 * Thumbnail for the file. Only accepted on upload and for files that are
	 * not already thumbnailed by Google.
	 */
	public static class Thumbnail {
		/**
		 * The URL-safe Base64 encoded bytes of the thumbnail image.
		 */
		public final String image;

		/**
		 * The MIME type of the thumbnail.
		 */
		public final String mimeType;

		@JsonCreator
		public Thumbnail(@JsonProperty("image") final String image,
				@JsonProperty("mimeType") final String mimeType) {
			this.image = image;
			this.mimeType = mimeType;
		}

	}
}
