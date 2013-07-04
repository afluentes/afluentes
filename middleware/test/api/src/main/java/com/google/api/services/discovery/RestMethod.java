package com.google.api.services.discovery;

import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class RestMethod {
	/**
	 * Description of this method.
	 */
	public final String description;

	/**
	 * Whether this method requires an ETag to be specified. The ETag is sent as
	 * an HTTP If-Match or If-None-Match header.
	 */
	public final boolean etagRequired;

	/**
	 * HTTP method used by this method.
	 */
	public final String httpMethod;

	/**
	 * A unique ID for this method. This property can be used to match methods
	 * between different versions of Discovery.
	 */
	public final String id;

	/**
	 * Media upload parameters.
	 */
	public final MediaUpload mediaUpload;

	/**
	 * Ordered list of required parameters, serves as a hint to clients on how
	 * to structure their method signatures. The array is ordered such that the
	 * "most-significant" parameter appears first.
	 */
	public final List<String> parameterOrder;

	/**
	 * Details for all parameters in this method.
	 */
	public final Map<String, JsonSchema> parameters;

	/**
	 * The URI path of this REST method. Should be used in conjunction with the
	 * basePath property at the api-level.
	 */
	public final String path;

	/**
	 * The schema for the request.
	 */
	public final Request request;

	/**
	 * The schema for the response.
	 */
	public final Response response;

	/**
	 * OAuth 2.0 scopes applicable to this method.
	 */
	public final List<String> scopes;

	/**
	 * Whether this method supports media downloads.
	 */
	public final boolean supportsMediaDownload;

	/**
	 * Whether this method supports media uploads.
	 */
	public final boolean supportsMediaUpload;

	/**
	 * Whether this method supports subscriptions.
	 */
	public final boolean supportsSubscription;

	@JsonCreator
	public RestMethod(
			@JsonProperty("description") final String description,
			@JsonProperty("etagRequired") final boolean etagRequired,
			@JsonProperty("httpMethod") final String httpMethod,
			@JsonProperty("id") final String id,
			@JsonProperty("mediaUpload") final MediaUpload mediaUpload,
			@JsonProperty("parameterOrder") final List<String> parameterOrder,
			@JsonProperty("parameters") final Map<String, JsonSchema> parameters,
			@JsonProperty("path") final String path,
			@JsonProperty("request") final Request request,
			@JsonProperty("response") final Response response,
			@JsonProperty("scopes") final List<String> scopes,
			@JsonProperty("supportsMediaDownload") final boolean supportsMediaDownload,
			@JsonProperty("supportsMediaUpload") final boolean supportsMediaUpload,
			@JsonProperty("supportsSubscription") final boolean supportsSubscription) {
		this.description = description;
		this.etagRequired = etagRequired;
		this.httpMethod = httpMethod;
		this.id = id;
		this.mediaUpload = mediaUpload;
		this.parameterOrder = parameterOrder;
		this.parameters = parameters;
		this.path = path;
		this.request = request;
		this.response = response;
		this.scopes = scopes;
		this.supportsMediaDownload = supportsMediaDownload;
		this.supportsMediaUpload = supportsMediaUpload;
		this.supportsSubscription = supportsSubscription;
	}

	/**
	 * Media upload parameters.
	 */
	public static class MediaUpload {
		/**
		 * MIME Media Ranges for acceptable media uploads to this method.
		 */
		public final List<String> accept;

		/**
		 * Maximum size of a media upload, such as "1MB", "2GB" or "3TB".
		 */
		public final String maxSize;

		/**
		 * Supported upload protocols.
		 */
		public final Protocols protocols;

		@JsonCreator
		public MediaUpload(@JsonProperty("accept") final List<String> accept,
				@JsonProperty("maxSize") final String maxSize,
				@JsonProperty("protocols") final Protocols protocols) {
			this.accept = accept;
			this.maxSize = maxSize;
			this.protocols = protocols;
		}

		/**
		 * Supported upload protocols.
		 */
		public static class Protocols {
			/**
			 * Supports the Resumable Media Upload protocol.
			 */
			public final Resumable resumable;

			/**
			 * Supports uploading as a single HTTP request.
			 */
			public final Simple simple;

			@JsonCreator
			public Protocols(
					@JsonProperty("resumable") final Resumable resumable,
					@JsonProperty("simple") final Simple simple) {
				this.resumable = resumable;
				this.simple = simple;
			}

			/**
			 * Supports the Resumable Media Upload protocol.
			 */
			public static class Resumable {
				/**
				 * True if this endpoint supports uploading multipart media.
				 */
				public final boolean multipart;

				/**
				 * The URI path to be used for upload. Should be used in
				 * conjunction with the basePath property at the api-level.
				 */
				public final String path;

				@JsonCreator
				public Resumable(
						@JsonProperty("multipart") final boolean multipart,
						@JsonProperty("path") final String path) {
					this.multipart = multipart;
					this.path = path;
				}

			}

			/**
			 * Supports uploading as a single HTTP request.
			 */
			public static class Simple {
				/**
				 * True if this endpoint supports upload multipart media.
				 */
				public final boolean multipart;

				/**
				 * The URI path to be used for upload. Should be used in
				 * conjunction with the basePath property at the api-level.
				 */
				public final String path;

				@JsonCreator
				public Simple(
						@JsonProperty("multipart") final boolean multipart,
						@JsonProperty("path") final String path) {
					this.multipart = multipart;
					this.path = path;
				}

			}
		}
	}

	/**
	 * The schema for the request.
	 */
	public static class Request {
		/**
		 * Schema ID for the request schema.
		 */
		public final String $ref;

		@JsonCreator
		public Request(@JsonProperty("$ref") final String $ref) {
			this.$ref = $ref;
		}

	}

	/**
	 * The schema for the response.
	 */
	public static class Response {
		/**
		 * Schema ID for the response schema.
		 */
		public final String $ref;

		@JsonCreator
		public Response(@JsonProperty("$ref") final String $ref) {
			this.$ref = $ref;
		}

	}
}
