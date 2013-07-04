package com.google.api.services.discovery;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface IApisResource {
	/**
	 * Retrieve the description of a particular version of an api.
	 * 
	 * @param api
	 *            The name of the API.
	 * @param version
	 *            The version of the API.
	 */
	@GET
	@Path("{api}/{version}/rest")
	RestDescription getRest(@PathParam("api") String api,
			@PathParam("version") String version);

	/**
	 * Retrieve the list of APIs supported at this endpoint.
	 * 
	 * @param name
	 *            Only include APIs with the given name.
	 * @param preferred
	 *            Return only the preferred version of an API.
	 */
	@GET
	DirectoryList list(@QueryParam("name") String name,
			@QueryParam("preferred") boolean preferred);

}
