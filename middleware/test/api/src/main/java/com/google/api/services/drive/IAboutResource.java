package com.google.api.services.drive;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface IAboutResource {
	/**
	 * Gets the information about the current user along with Drive API settings
	 * 
	 * @param includeSubscribed
	 *            When calculating the number of remaining change IDs, whether
	 *            to include shared files and public files the user has opened.
	 *            When set to false, this counts only change IDs for owned files
	 *            and any shared or public files that the user has explictly
	 *            added to a folder in Drive.
	 * @param maxChangeIdCount
	 *            Maximum number of remaining change IDs to count
	 * @param startChangeId
	 *            Change ID to start counting from when calculating number of
	 *            remaining change IDs
	 */
	@GET
	About get(@QueryParam("includeSubscribed") boolean includeSubscribed,
			@QueryParam("maxChangeIdCount") String maxChangeIdCount,
			@QueryParam("startChangeId") String startChangeId);

}
