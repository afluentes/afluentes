package com.google.api.services.plus;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class Person {
	/**
	 * A short biography for this person.
	 */
	public final String aboutMe;

	/**
	 * The age range of the person.
	 */
	public final AgeRange ageRange;

	/**
	 * The person's date of birth, represented as YYYY-MM-DD.
	 */
	public final String birthday;

	/**
	 * The "bragging rights" line of this person.
	 */
	public final String braggingRights;

	/**
	 * If a Google+ Page and for followers who are visible, the number of people
	 * who have added this page to a circle.
	 */
	public final int circledByCount;

	/**
	 * The cover photo content.
	 */
	public final Cover cover;

	/**
	 * The current location for this person.
	 */
	public final String currentLocation;

	/**
	 * The name of this person, suitable for display.
	 */
	public final String displayName;

	/**
	 * A list of email addresses that this person has set to public on their
	 * Google+ profile. You can also use the userinfo.email scope to retrieve an
	 * authenticated user's email address.
	 */
	public final List<Emails> emails;

	/**
	 * ETag of this response for caching purposes.
	 */
	public final String etag;

	/**
	 * The person's gender. Possible values are: - "male" - Male gender. -
	 * "female" - Female gender. - "other" - Other.
	 */
	public final String gender;

	/**
	 * The ID of this person.
	 */
	public final String id;

	/**
	 * The representation of the person's profile photo.
	 */
	public final Image image;

	/**
	 * Whether this user has signed up for Google+.
	 */
	public final boolean isPlusUser;

	/**
	 * Identifies this resource as a person. Value: "plus#person".
	 */
	public final String kind;

	/**
	 * The user's preferred language for rendering.
	 */
	public final String language;

	/**
	 * An object representation of the individual components of a person's name.
	 */
	public final Name name;

	/**
	 * The nickname of this person.
	 */
	public final String nickname;

	/**
	 * Type of person within Google+. Possible values are: - "person" -
	 * represents an actual person. - "page" - represents a page.
	 */
	public final String objectType;

	/**
	 * A list of current or past organizations with which this person is
	 * associated.
	 */
	public final List<Organizations> organizations;

	/**
	 * A list of places where this person has lived.
	 */
	public final List<PlacesLived> placesLived;

	/**
	 * If a Google+ Page, the number of people who have +1'ed this page.
	 */
	public final int plusOneCount;

	/**
	 * The person's relationship status. Possible values are: - "single" -
	 * Person is single. - "in_a_relationship" - Person is in a relationship. -
	 * "engaged" - Person is engaged. - "married" - Person is married. -
	 * "its_complicated" - The relationship is complicated. -
	 * "open_relationship" - Person is in an open relationship. - "widowed" -
	 * Person is widowed. - "in_domestic_partnership" - Person is in a domestic
	 * partnership. - "in_civil_union" - Person is in a civil union.
	 */
	public final String relationshipStatus;

	/**
	 * The brief description (tagline) of this person.
	 */
	public final String tagline;

	/**
	 * The URL of this person's profile.
	 */
	public final String url;

	/**
	 * A list of URLs for this person.
	 */
	public final List<Urls> urls;

	/**
	 * Whether the person or Google+ Page has been verified.
	 */
	public final boolean verified;

	@JsonCreator
	public Person(
			@JsonProperty("aboutMe") final String aboutMe,
			@JsonProperty("ageRange") final AgeRange ageRange,
			@JsonProperty("birthday") final String birthday,
			@JsonProperty("braggingRights") final String braggingRights,
			@JsonProperty("circledByCount") final int circledByCount,
			@JsonProperty("cover") final Cover cover,
			@JsonProperty("currentLocation") final String currentLocation,
			@JsonProperty("displayName") final String displayName,
			@JsonProperty("emails") final List<Emails> emails,
			@JsonProperty("etag") final String etag,
			@JsonProperty("gender") final String gender,
			@JsonProperty("id") final String id,
			@JsonProperty("image") final Image image,
			@JsonProperty("isPlusUser") final boolean isPlusUser,
			@JsonProperty("kind") final String kind,
			@JsonProperty("language") final String language,
			@JsonProperty("name") final Name name,
			@JsonProperty("nickname") final String nickname,
			@JsonProperty("objectType") final String objectType,
			@JsonProperty("organizations") final List<Organizations> organizations,
			@JsonProperty("placesLived") final List<PlacesLived> placesLived,
			@JsonProperty("plusOneCount") final int plusOneCount,
			@JsonProperty("relationshipStatus") final String relationshipStatus,
			@JsonProperty("tagline") final String tagline,
			@JsonProperty("url") final String url,
			@JsonProperty("urls") final List<Urls> urls,
			@JsonProperty("verified") final boolean verified) {
		this.aboutMe = aboutMe;
		this.ageRange = ageRange;
		this.birthday = birthday;
		this.braggingRights = braggingRights;
		this.circledByCount = circledByCount;
		this.cover = cover;
		this.currentLocation = currentLocation;
		this.displayName = displayName;
		this.emails = emails;
		this.etag = etag;
		this.gender = gender;
		this.id = id;
		this.image = image;
		this.isPlusUser = isPlusUser;
		this.kind = kind;
		this.language = language;
		this.name = name;
		this.nickname = nickname;
		this.objectType = objectType;
		this.organizations = organizations;
		this.placesLived = placesLived;
		this.plusOneCount = plusOneCount;
		this.relationshipStatus = relationshipStatus;
		this.tagline = tagline;
		this.url = url;
		this.urls = urls;
		this.verified = verified;
	}

	/**
	 * The age range of the person.
	 */
	public static class AgeRange {
		/**
		 * The age range's upper bound, if any.
		 */
		public final int max;

		/**
		 * The age range's lower bound, if any.
		 */
		public final int min;

		@JsonCreator
		public AgeRange(@JsonProperty("max") final int max,
				@JsonProperty("min") final int min) {
			this.max = max;
			this.min = min;
		}

	}

	/**
	 * The cover photo content.
	 */
	public static class Cover {
		/**
		 * Extra information about the cover photo.
		 */
		public final CoverInfo coverInfo;

		/**
		 * The person's primary cover image.
		 */
		public final CoverPhoto coverPhoto;

		/**
		 * The layout of the cover art. Possible values are: - "banner" - One
		 * large image banner.
		 */
		public final String layout;

		@JsonCreator
		public Cover(@JsonProperty("coverInfo") final CoverInfo coverInfo,
				@JsonProperty("coverPhoto") final CoverPhoto coverPhoto,
				@JsonProperty("layout") final String layout) {
			this.coverInfo = coverInfo;
			this.coverPhoto = coverPhoto;
			this.layout = layout;
		}

		/**
		 * Extra information about the cover photo.
		 */
		public static class CoverInfo {
			/**
			 * The difference between the left position of the image cover and
			 * the actual displayed cover image. Only valid for BANNER layout.
			 */
			public final int leftImageOffset;

			/**
			 * The difference between the top position of the image cover and
			 * the actual displayed cover image. Only valid for BANNER layout.
			 */
			public final int topImageOffset;

			@JsonCreator
			public CoverInfo(
					@JsonProperty("leftImageOffset") final int leftImageOffset,
					@JsonProperty("topImageOffset") final int topImageOffset) {
				this.leftImageOffset = leftImageOffset;
				this.topImageOffset = topImageOffset;
			}

		}

		/**
		 * The person's primary cover image.
		 */
		public static class CoverPhoto {
			/**
			 * The height to the image.
			 */
			public final int height;

			/**
			 * The url to the image.
			 */
			public final String url;

			/**
			 * The width to the image.
			 */
			public final int width;

			@JsonCreator
			public CoverPhoto(@JsonProperty("height") final int height,
					@JsonProperty("url") final String url,
					@JsonProperty("width") final int width) {
				this.height = height;
				this.url = url;
				this.width = width;
			}

		}
	}

	public static class Emails {
		/**
		 * If "true", indicates this email address is the person's primary one.
		 */
		public final boolean primary;

		/**
		 * The type of address. Possible values are: - "home" - Home email
		 * address. - "work" - Work email address. - "other" - Other.
		 */
		public final String type;

		/**
		 * The email address.
		 */
		public final String value;

		@JsonCreator
		public Emails(@JsonProperty("primary") final boolean primary,
				@JsonProperty("type") final String type,
				@JsonProperty("value") final String value) {
			this.primary = primary;
			this.type = type;
			this.value = value;
		}

	}

	/**
	 * The representation of the person's profile photo.
	 */
	public static class Image {
		/**
		 * The URL of the person's profile photo. To re-size the image and crop
		 * it to a square, append the query string ?sz=x, where x is the
		 * dimension in pixels of each side.
		 */
		public final String url;

		@JsonCreator
		public Image(@JsonProperty("url") final String url) {
			this.url = url;
		}

	}

	/**
	 * An object representation of the individual components of a person's name.
	 */
	public static class Name {
		/**
		 * The family name (last name) of this person.
		 */
		public final String familyName;

		/**
		 * The full name of this person, including middle names, suffixes, etc.
		 */
		public final String formatted;

		/**
		 * The given name (first name) of this person.
		 */
		public final String givenName;

		/**
		 * The honorific prefixes (such as "Dr." or "Mrs.") for this person.
		 */
		public final String honorificPrefix;

		/**
		 * The honorific suffixes (such as "Jr.") for this person.
		 */
		public final String honorificSuffix;

		/**
		 * The middle name of this person.
		 */
		public final String middleName;

		@JsonCreator
		public Name(@JsonProperty("familyName") final String familyName,
				@JsonProperty("formatted") final String formatted,
				@JsonProperty("givenName") final String givenName,
				@JsonProperty("honorificPrefix") final String honorificPrefix,
				@JsonProperty("honorificSuffix") final String honorificSuffix,
				@JsonProperty("middleName") final String middleName) {
			this.familyName = familyName;
			this.formatted = formatted;
			this.givenName = givenName;
			this.honorificPrefix = honorificPrefix;
			this.honorificSuffix = honorificSuffix;
			this.middleName = middleName;
		}

	}

	public static class Organizations {
		/**
		 * The department within the organization. Deprecated.
		 */
		public final String department;

		/**
		 * A short description of the person's role in this organization.
		 * Deprecated.
		 */
		public final String description;

		/**
		 * The date the person left this organization.
		 */
		public final String endDate;

		/**
		 * The location of this organization. Deprecated.
		 */
		public final String location;

		/**
		 * The name of the organization.
		 */
		public final String name;

		/**
		 * If "true", indicates this organization is the person's primary one
		 * (typically interpreted as current one).
		 */
		public final boolean primary;

		/**
		 * The date the person joined this organization.
		 */
		public final String startDate;

		/**
		 * The person's job title or role within the organization.
		 */
		public final String title;

		/**
		 * The type of organization. Possible values are: - "work" - Work. -
		 * "school" - School.
		 */
		public final String type;

		@JsonCreator
		public Organizations(
				@JsonProperty("department") final String department,
				@JsonProperty("description") final String description,
				@JsonProperty("endDate") final String endDate,
				@JsonProperty("location") final String location,
				@JsonProperty("name") final String name,
				@JsonProperty("primary") final boolean primary,
				@JsonProperty("startDate") final String startDate,
				@JsonProperty("title") final String title,
				@JsonProperty("type") final String type) {
			this.department = department;
			this.description = description;
			this.endDate = endDate;
			this.location = location;
			this.name = name;
			this.primary = primary;
			this.startDate = startDate;
			this.title = title;
			this.type = type;
		}

	}

	public static class PlacesLived {
		/**
		 * If "true", this place of residence is this person's primary
		 * residence.
		 */
		public final boolean primary;

		/**
		 * A place where this person has lived. For example: "Seattle, WA",
		 * "Near Toronto".
		 */
		public final String value;

		@JsonCreator
		public PlacesLived(@JsonProperty("primary") final boolean primary,
				@JsonProperty("value") final String value) {
			this.primary = primary;
			this.value = value;
		}

	}

	public static class Urls {
		/**
		 * The label of the URL.
		 */
		public final String label;

		/**
		 * The type of URL. Possible values are: - "otherProfile" - URL for
		 * another profile. - "contributor" - URL for which this person is a
		 * contributor to. - "website" - URL for this Google+ Page's primary
		 * website. - "other" - Other.
		 */
		public final String type;

		/**
		 * The URL value.
		 */
		public final String value;

		@JsonCreator
		public Urls(@JsonProperty("label") final String label,
				@JsonProperty("type") final String type,
				@JsonProperty("value") final String value) {
			this.label = label;
			this.type = type;
			this.value = value;
		}

	}
}
