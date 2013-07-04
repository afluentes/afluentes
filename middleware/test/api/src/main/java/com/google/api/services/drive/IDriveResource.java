package com.google.api.services.drive;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface IDriveResource {

	@Path("about")
	IAboutResource getAbout();

	@Path("apps")
	IAppsResource getApps();

	@Path("changes")
	IChangesResource getChanges();

	@Path("files")
	IChildrenResource getChildren();

	@Path("files")
	ICommentsResource getComments();

	@Path("files")
	IFilesResource getFiles();

	@Path("files")
	IParentsResource getParents();

	@Path("files")
	IPermissionsResource getPermissions();

	@Path("files")
	IPropertiesResource getProperties();

	@Path("files")
	IRepliesResource getReplies();

	@Path("files")
	IRevisionsResource getRevisions();

}
