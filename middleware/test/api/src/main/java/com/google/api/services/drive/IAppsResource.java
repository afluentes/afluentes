package com.google.api.services.drive;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface IAppsResource {
	/**
	 * Gets a specific app.
	 * 
	 * @param appId
	 *            The ID of the app.
	 */
	@GET
	@Path("{appId}")
	App get(@PathParam("appId") String appId);

	/**
	 * Lists a user's installed apps.
	 * 
	 */
	@GET
	AppList list();

}
