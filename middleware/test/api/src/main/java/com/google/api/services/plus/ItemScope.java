package com.google.api.services.plus;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class ItemScope {
	/**
	 * The subject matter of the content.
	 */
	public final ItemScope about;

	/**
	 * An additional name for a Person, can be used for a middle name.
	 */
	public final List<String> additionalName;

	/**
	 * Postal address.
	 */
	public final ItemScope address;

	/**
	 * Address country.
	 */
	public final String addressCountry;

	/**
	 * Address locality.
	 */
	public final String addressLocality;

	/**
	 * Address region.
	 */
	public final String addressRegion;

	/**
	 * The encoding.
	 */
	public final List<ItemScope> associated_media;

	/**
	 * Number of attendees.
	 */
	public final int attendeeCount;

	/**
	 * A person attending the event.
	 */
	public final List<ItemScope> attendees;

	/**
	 * From http://schema.org/MusicRecording, the audio file.
	 */
	public final ItemScope audio;

	/**
	 * The person or persons who created this result. In the example of
	 * restaurant reviews, this might be the reviewer's name.
	 */
	public final List<ItemScope> author;

	/**
	 * Best possible rating value that a result might obtain. This property
	 * defines the upper bound for the ratingValue. For example, you might have
	 * a 5 star rating scale, you would provide 5 as the value for this
	 * property.
	 */
	public final String bestRating;

	/**
	 * Date of birth.
	 */
	public final String birthDate;

	/**
	 * From http://schema.org/MusicRecording, the artist that performed this
	 * recording.
	 */
	public final ItemScope byArtist;

	/**
	 * The caption for this object.
	 */
	public final String caption;

	/**
	 * File size in (mega/kilo) bytes.
	 */
	public final String contentSize;

	/**
	 * Actual bytes of the media object, for example the image file or video
	 * file.
	 */
	public final String contentUrl;

	/**
	 * A list of contributors to this result.
	 */
	public final List<ItemScope> contributor;

	/**
	 * The date the result was created such as the date that a review was first
	 * created.
	 */
	public final String dateCreated;

	/**
	 * The date the result was last modified such as the date that a review was
	 * last edited.
	 */
	public final String dateModified;

	/**
	 * The initial date that the result was published. For example, a user
	 * writes a comment on a blog, which has a result.dateCreated of when they
	 * submit it. If the blog users comment moderation, the result.datePublished
	 * value would match the date when the owner approved the message.
	 */
	public final String datePublished;

	/**
	 * The string that describes the content of the result.
	 */
	public final String description;

	/**
	 * The duration of the item (movie, audio recording, event, etc.) in ISO
	 * 8601 date format.
	 */
	public final String duration;

	/**
	 * A URL pointing to a player for a specific video. In general, this is the
	 * information in the src element of an embed tag and should not be the same
	 * as the content of the loc tag.
	 */
	public final String embedUrl;

	/**
	 * The end date and time of the event (in ISO 8601 date format).
	 */
	public final String endDate;

	/**
	 * Family name. This property can be used with givenName instead of the name
	 * property.
	 */
	public final String familyName;

	/**
	 * Gender of the person.
	 */
	public final String gender;

	/**
	 * Geo coordinates.
	 */
	public final ItemScope geo;

	/**
	 * Given name. This property can be used with familyName instead of the name
	 * property.
	 */
	public final String givenName;

	/**
	 * The height of the media object.
	 */
	public final String height;

	/**
	 * An identifier for the target. Your app can choose how to identify
	 * targets. The target.id is required if you are writing an activity that
	 * does not have a corresponding web page or target.url property.
	 */
	public final String id;

	/**
	 * A URL to the image that represents this result. For example, if a user
	 * writes a review of a restaurant and attaches a photo of their meal, you
	 * might use that photo as the result.image.
	 */
	public final String image;

	/**
	 * From http://schema.org/MusicRecording, which album a song is in.
	 */
	public final ItemScope inAlbum;

	/**
	 * Identifies this resource as an itemScope.
	 */
	public final String kind;

	/**
	 * Latitude.
	 */
	public final double latitude;

	/**
	 * The location of the event or organization.
	 */
	public final ItemScope location;

	/**
	 * Longitude.
	 */
	public final double longitude;

	/**
	 * The name of the result. In the example of a restaurant review, this might
	 * be the summary the user gave their review such as
	 * "Great ambiance, but overpriced."
	 */
	public final String name;

	/**
	 * Property of http://schema.org/TVEpisode indicating which series the
	 * episode belongs to.
	 */
	public final ItemScope partOfTVSeries;

	/**
	 * The main performer or performers of the event-for example, a presenter,
	 * musician, or actor.
	 */
	public final List<ItemScope> performers;

	/**
	 * Player type that is required. For example: Flash or Silverlight.
	 */
	public final String playerType;

	/**
	 * Post office box number.
	 */
	public final String postOfficeBoxNumber;

	/**
	 * Postal code.
	 */
	public final String postalCode;

	/**
	 * Rating value.
	 */
	public final String ratingValue;

	/**
	 * Review rating.
	 */
	public final ItemScope reviewRating;

	/**
	 * The start date and time of the event (in ISO 8601 date format).
	 */
	public final String startDate;

	/**
	 * Street address.
	 */
	public final String streetAddress;

	/**
	 * The text that is the result of the app activity. For example, if a user
	 * leaves a review of a restaurant, this might be the text of the review.
	 */
	public final String text;

	/**
	 * Thumbnail image for an image or video.
	 */
	public final ItemScope thumbnail;

	/**
	 * A URL to a thumbnail image that represents this result.
	 */
	public final String thumbnailUrl;

	/**
	 * The exchange traded instrument associated with a Corporation object. The
	 * tickerSymbol is expressed as an exchange and an instrument name separated
	 * by a space character. For the exchange component of the tickerSymbol
	 * attribute, we reccommend using the controlled vocaulary of Market
	 * Identifier Codes (MIC) specified in ISO15022.
	 */
	public final String tickerSymbol;

	/**
	 * The schema.org URL that best describes the referenced target and matches
	 * the type of moment.
	 */
	public final String type;

	/**
	 * The URL that points to the result object. For example, a permalink
	 * directly to a restaurant reviewer's comment.
	 */
	public final String url;

	/**
	 * The width of the media object.
	 */
	public final String width;

	/**
	 * Worst possible rating value that a result might obtain. This property
	 * defines the lower bound for the ratingValue.
	 */
	public final String worstRating;

	@JsonCreator
	public ItemScope(
			@JsonProperty("about") final ItemScope about,
			@JsonProperty("additionalName") final List<String> additionalName,
			@JsonProperty("address") final ItemScope address,
			@JsonProperty("addressCountry") final String addressCountry,
			@JsonProperty("addressLocality") final String addressLocality,
			@JsonProperty("addressRegion") final String addressRegion,
			@JsonProperty("associated_media") final List<ItemScope> associated_media,
			@JsonProperty("attendeeCount") final int attendeeCount,
			@JsonProperty("attendees") final List<ItemScope> attendees,
			@JsonProperty("audio") final ItemScope audio,
			@JsonProperty("author") final List<ItemScope> author,
			@JsonProperty("bestRating") final String bestRating,
			@JsonProperty("birthDate") final String birthDate,
			@JsonProperty("byArtist") final ItemScope byArtist,
			@JsonProperty("caption") final String caption,
			@JsonProperty("contentSize") final String contentSize,
			@JsonProperty("contentUrl") final String contentUrl,
			@JsonProperty("contributor") final List<ItemScope> contributor,
			@JsonProperty("dateCreated") final String dateCreated,
			@JsonProperty("dateModified") final String dateModified,
			@JsonProperty("datePublished") final String datePublished,
			@JsonProperty("description") final String description,
			@JsonProperty("duration") final String duration,
			@JsonProperty("embedUrl") final String embedUrl,
			@JsonProperty("endDate") final String endDate,
			@JsonProperty("familyName") final String familyName,
			@JsonProperty("gender") final String gender,
			@JsonProperty("geo") final ItemScope geo,
			@JsonProperty("givenName") final String givenName,
			@JsonProperty("height") final String height,
			@JsonProperty("id") final String id,
			@JsonProperty("image") final String image,
			@JsonProperty("inAlbum") final ItemScope inAlbum,
			@JsonProperty("kind") final String kind,
			@JsonProperty("latitude") final double latitude,
			@JsonProperty("location") final ItemScope location,
			@JsonProperty("longitude") final double longitude,
			@JsonProperty("name") final String name,
			@JsonProperty("partOfTVSeries") final ItemScope partOfTVSeries,
			@JsonProperty("performers") final List<ItemScope> performers,
			@JsonProperty("playerType") final String playerType,
			@JsonProperty("postOfficeBoxNumber") final String postOfficeBoxNumber,
			@JsonProperty("postalCode") final String postalCode,
			@JsonProperty("ratingValue") final String ratingValue,
			@JsonProperty("reviewRating") final ItemScope reviewRating,
			@JsonProperty("startDate") final String startDate,
			@JsonProperty("streetAddress") final String streetAddress,
			@JsonProperty("text") final String text,
			@JsonProperty("thumbnail") final ItemScope thumbnail,
			@JsonProperty("thumbnailUrl") final String thumbnailUrl,
			@JsonProperty("tickerSymbol") final String tickerSymbol,
			@JsonProperty("type") final String type,
			@JsonProperty("url") final String url,
			@JsonProperty("width") final String width,
			@JsonProperty("worstRating") final String worstRating) {
		this.about = about;
		this.additionalName = additionalName;
		this.address = address;
		this.addressCountry = addressCountry;
		this.addressLocality = addressLocality;
		this.addressRegion = addressRegion;
		this.associated_media = associated_media;
		this.attendeeCount = attendeeCount;
		this.attendees = attendees;
		this.audio = audio;
		this.author = author;
		this.bestRating = bestRating;
		this.birthDate = birthDate;
		this.byArtist = byArtist;
		this.caption = caption;
		this.contentSize = contentSize;
		this.contentUrl = contentUrl;
		this.contributor = contributor;
		this.dateCreated = dateCreated;
		this.dateModified = dateModified;
		this.datePublished = datePublished;
		this.description = description;
		this.duration = duration;
		this.embedUrl = embedUrl;
		this.endDate = endDate;
		this.familyName = familyName;
		this.gender = gender;
		this.geo = geo;
		this.givenName = givenName;
		this.height = height;
		this.id = id;
		this.image = image;
		this.inAlbum = inAlbum;
		this.kind = kind;
		this.latitude = latitude;
		this.location = location;
		this.longitude = longitude;
		this.name = name;
		this.partOfTVSeries = partOfTVSeries;
		this.performers = performers;
		this.playerType = playerType;
		this.postOfficeBoxNumber = postOfficeBoxNumber;
		this.postalCode = postalCode;
		this.ratingValue = ratingValue;
		this.reviewRating = reviewRating;
		this.startDate = startDate;
		this.streetAddress = streetAddress;
		this.text = text;
		this.thumbnail = thumbnail;
		this.thumbnailUrl = thumbnailUrl;
		this.tickerSymbol = tickerSymbol;
		this.type = type;
		this.url = url;
		this.width = width;
		this.worstRating = worstRating;
	}

}
