package com.google.api.services.discovery;

import java.util.Map;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class RestResource {
	/**
	 * Methods on this resource.
	 */
	public final Map<String, RestMethod> methods;

	/**
	 * Sub-resources on this resource.
	 */
	public final Map<String, RestResource> resources;

	@JsonCreator
	public RestResource(
			@JsonProperty("methods") final Map<String, RestMethod> methods,
			@JsonProperty("resources") final Map<String, RestResource> resources) {
		this.methods = methods;
		this.resources = resources;
	}

}
