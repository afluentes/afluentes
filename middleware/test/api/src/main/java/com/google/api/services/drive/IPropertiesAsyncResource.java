package com.google.api.services.drive;

import afluentes.core.api.IEvaluation;

public interface IPropertiesAsyncResource {
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
	IEvaluation<Void> delete(String fileId, String propertyKey,
			String visibility);

	IEvaluation<Void> delete(IEvaluation<String> fileId,
			IEvaluation<String> propertyKey, IEvaluation<String> visibility);

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
	IEvaluation<Property> get(String fileId, String propertyKey,
			String visibility);

	IEvaluation<Property> get(IEvaluation<String> fileId,
			IEvaluation<String> propertyKey, IEvaluation<String> visibility);

	/**
	 * Adds a property to a file.
	 * 
	 * @param fileId
	 *            The ID of the file.
	 */
	IEvaluation<Property> insert(String fileId, Property requestEntity);

	IEvaluation<Property> insert(IEvaluation<String> fileId,
			IEvaluation<Property> requestEntity);

	/**
	 * Lists a file's properties.
	 * 
	 * @param fileId
	 *            The ID of the file.
	 */
	IEvaluation<PropertyList> list(String fileId);

	IEvaluation<PropertyList> list(IEvaluation<String> fileId);

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
	IEvaluation<Property> patch(String fileId, String propertyKey,
			String visibility, Property requestEntity);

	IEvaluation<Property> patch(IEvaluation<String> fileId,
			IEvaluation<String> propertyKey, IEvaluation<String> visibility,
			IEvaluation<Property> requestEntity);

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
	IEvaluation<Property> update(String fileId, String propertyKey,
			String visibility, Property requestEntity);

	IEvaluation<Property> update(IEvaluation<String> fileId,
			IEvaluation<String> propertyKey, IEvaluation<String> visibility,
			IEvaluation<Property> requestEntity);

}
