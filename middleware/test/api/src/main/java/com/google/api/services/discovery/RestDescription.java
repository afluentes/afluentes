package com.google.api.services.discovery;

import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class RestDescription {
	/**
	 * Authentication information.
	 */
	public final Auth auth;

	/**
	 * [DEPRECATED] The base path for REST requests.
	 */
	public final String basePath;

	/**
	 * [DEPRECATED] The base URL for REST requests.
	 */
	public final String baseUrl;

	/**
	 * The path for REST batch requests.
	 */
	public final String batchPath;

	/**
	 * Indicates how the API name should be capitalized and split into various
	 * parts. Useful for generating pretty class names.
	 */
	public final String canonicalName;

	/**
	 * The description of this API.
	 */
	public final String description;

	/**
	 * Indicate the version of the Discovery API used to generate this doc.
	 */
	public final String discoveryVersion;

	/**
	 * A link to human readable documentation for the API.
	 */
	public final String documentationLink;

	/**
	 * The ETag for this response.
	 */
	public final String etag;

	/**
	 * A list of supported features for this API.
	 */
	public final List<String> features;

	/**
	 * Links to 16x16 and 32x32 icons representing the API.
	 */
	public final Icons icons;

	/**
	 * The ID of this API.
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
	 * API-level methods for this API.
	 */
	public final Map<String, RestMethod> methods;

	/**
	 * The name of this API.
	 */
	public final String name;

	/**
	 * The domain of the owner of this API. Together with the ownerName and a
	 * packagePath values, this can be used to generate a library for this API
	 * which would have a unique fully qualified name.
	 */
	public final String ownerDomain;

	/**
	 * The name of the owner of this API. See ownerDomain.
	 */
	public final String ownerName;

	/**
	 * The package of the owner of this API. See ownerDomain.
	 */
	public final String packagePath;

	/**
	 * Common parameters that apply across all apis.
	 */
	public final Map<String, JsonSchema> parameters;

	/**
	 * The protocol described by this document.
	 */
	public final String protocol;

	/**
	 * The resources in this API.
	 */
	public final Map<String, RestResource> resources;

	/**
	 * The version of this API.
	 */
	public final String revision;

	/**
	 * The root URL under which all API services live.
	 */
	public final String rootUrl;

	/**
	 * The schemas for this API.
	 */
	public final Map<String, JsonSchema> schemas;

	/**
	 * The base path for all REST requests.
	 */
	public final String servicePath;

	/**
	 * The title of this API.
	 */
	public final String title;

	/**
	 * The version of this API.
	 */
	public final String version;

	@JsonCreator
	public RestDescription(
			@JsonProperty("auth") final Auth auth,
			@JsonProperty("basePath") final String basePath,
			@JsonProperty("baseUrl") final String baseUrl,
			@JsonProperty("batchPath") final String batchPath,
			@JsonProperty("canonicalName") final String canonicalName,
			@JsonProperty("description") final String description,
			@JsonProperty("discoveryVersion") final String discoveryVersion,
			@JsonProperty("documentationLink") final String documentationLink,
			@JsonProperty("etag") final String etag,
			@JsonProperty("features") final List<String> features,
			@JsonProperty("icons") final Icons icons,
			@JsonProperty("id") final String id,
			@JsonProperty("kind") final String kind,
			@JsonProperty("labels") final List<String> labels,
			@JsonProperty("methods") final Map<String, RestMethod> methods,
			@JsonProperty("name") final String name,
			@JsonProperty("ownerDomain") final String ownerDomain,
			@JsonProperty("ownerName") final String ownerName,
			@JsonProperty("packagePath") final String packagePath,
			@JsonProperty("parameters") final Map<String, JsonSchema> parameters,
			@JsonProperty("protocol") final String protocol,
			@JsonProperty("resources") final Map<String, RestResource> resources,
			@JsonProperty("revision") final String revision,
			@JsonProperty("rootUrl") final String rootUrl,
			@JsonProperty("schemas") final Map<String, JsonSchema> schemas,
			@JsonProperty("servicePath") final String servicePath,
			@JsonProperty("title") final String title,
			@JsonProperty("version") final String version) {
		this.auth = auth;
		this.basePath = basePath;
		this.baseUrl = baseUrl;
		this.batchPath = batchPath;
		this.canonicalName = canonicalName;
		this.description = description;
		this.discoveryVersion = discoveryVersion;
		this.documentationLink = documentationLink;
		this.etag = etag;
		this.features = features;
		this.icons = icons;
		this.id = id;
		this.kind = kind;
		this.labels = labels;
		this.methods = methods;
		this.name = name;
		this.ownerDomain = ownerDomain;
		this.ownerName = ownerName;
		this.packagePath = packagePath;
		this.parameters = parameters;
		this.protocol = protocol;
		this.resources = resources;
		this.revision = revision;
		this.rootUrl = rootUrl;
		this.schemas = schemas;
		this.servicePath = servicePath;
		this.title = title;
		this.version = version;
	}

	/**
	 * Authentication information.
	 */
	public static class Auth {
		/**
		 * OAuth 2.0 authentication information.
		 */
		public final Oauth2 oauth2;

		@JsonCreator
		public Auth(@JsonProperty("oauth2") final Oauth2 oauth2) {
			this.oauth2 = oauth2;
		}

		/**
		 * OAuth 2.0 authentication information.
		 */
		public static class Oauth2 {
			/**
			 * Available OAuth 2.0 scopes.
			 */
			public final Map<String, Scopes> scopes;

			@JsonCreator
			public Oauth2(
					@JsonProperty("scopes") final Map<String, Scopes> scopes) {
				this.scopes = scopes;
			}

			/**
			 * The scope value.
			 */
			public static class Scopes {
				/**
				 * Description of scope.
				 */
				public final String description;

				@JsonCreator
				public Scopes(
						@JsonProperty("description") final String description) {
					this.description = description;
				}

			}
		}
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
