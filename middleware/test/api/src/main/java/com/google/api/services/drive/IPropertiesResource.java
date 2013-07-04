package com.google.api.services.drive;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import afluentes.middleware.ws.rs.PATCH;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface IPropertiesResource {
	/**
	 * Deletes a property.
	 * 
	 * @param fileId
	 *            The ID of the file.
	 * @param propertyKey
	 *            The key of the property.
	 * @param visibility
	 *            The visibility of the property.
	 */
	@DELETE
	@Path("{fileId}/properties/{propertyKey}")
	void delete(@PathParam("fileId") String fileId,
			@PathParam("propertyKey") String propertyKey,
			@QueryParam("visibility") String visibility);

	/**
	 * Gets a property by its key.
	 * 
	 * @param fileId
	 *            The ID of the file.
	 * @param propertyKey
	 *            The key of the property.
	 * @param visibility
	 *            The visibility of the property.
	 */
	@GET
	@Path("{fileId}/properties/{propertyKey}")
	Property get(@PathParam("fileId") String fileId,
			@PathParam("propertyKey") String propertyKey,
			@QueryParam("visibility") String visibility);

	/**
	 * Adds a property to a file.
	 * 
	 * @param fileId
	 *            The ID of the file.
	 */
	@POST
	@Path("{fileId}/properties")
	Property insert(@PathParam("fileId") String fileId, Property requestEntity);

	/**
	 * Lists a file's properties.
	 * 
	 * @param fileId
	 *            The ID of the file.
	 */
	@GET
	@Path("{fileId}/properties")
	PropertyList list(@PathParam("fileId") String fileId);

	/**
	 * Updates a property. This method supports patch semantics.
	 * 
	 * @param fileId
	 *            The ID of the file.
	 * @param propertyKey
	 *            The key of the property.
	 * @param visibility
	 *            The visibility of the property.
	 */
	@PATCH
	@Path("{fileId}/properties/{propertyKey}")
	Property patch(@PathParam("fileId") String fileId,
			@PathParam("propertyKey") String propertyKey,
			@QueryParam("visibility") String visibility, Property requestEntity);

	/**
	 * Updates a property.
	 * 
	 * @param fileId
	 *            The ID of the file.
	 * @param propertyKey
	 *            The key of the property.
	 * @param visibility
	 *            The visibility of the property.
	 */
	@PUT
	@Path("{fileId}/properties/{propertyKey}")
	Property update(@PathParam("fileId") String fileId,
			@PathParam("propertyKey") String propertyKey,
			@QueryParam("visibility") String visibility, Property requestEntity);

}
