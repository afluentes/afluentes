package com.google.api.services.drive;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface IChangesResource {
	/**
	 * Gets a specific change.
	 * 
	 * @param changeId
	 *            The ID of the change.
	 */
	@GET
	@Path("{changeId}")
	Change get(@PathParam("changeId") String changeId);

	/**
	 * Lists the changes for a user.
	 * 
	 * @param includeDeleted
	 *            Whether to include deleted items.
	 * @param includeSubscribed
	 *            Whether to include shared files and public files the user has
	 *            opened. When set to false, the list will include owned files
	 *            plus any shared or public files the user has explictly added
	 *            to a folder in Drive.
	 * @param maxResults
	 *            Maximum number of changes to return.
	 * @param pageToken
	 *            Page token for changes.
	 * @param startChangeId
	 *            Change ID to start listing changes from.
	 */
	@GET
	ChangeList list(@QueryParam("includeDeleted") boolean includeDeleted,
			@QueryParam("includeSubscribed") boolean includeSubscribed,
			@QueryParam("maxResults") int maxResults,
			@QueryParam("pageToken") String pageToken,
			@QueryParam("startChangeId") String startChangeId);

}
