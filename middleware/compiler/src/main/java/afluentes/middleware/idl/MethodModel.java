package afluentes.middleware.idl;

import java.util.List;

public class MethodModel {
	public final String description;
	public final String httpMethod;
	public final String name;
	public final List<ParameterModel> parameters;
	public final String path;
	public final ParameterModel requestEntity;
	public final String responseType;	

	public MethodModel(final String description, 
					   final String httpMethod,
					   final String name,
					   final List<ParameterModel> parameters,
					   final String path,
					   final ParameterModel requestEntity,
					   final String responseType) {
		this.description = description;
		this.httpMethod = httpMethod;
		this.name = name;
		this.parameters = parameters;
		this.path = path;
		this.requestEntity = requestEntity;
		this.responseType = responseType;		
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getHttpMethod() {
		return httpMethod;
	}
	
	public String getName() {
		return name;
	}
	
	public List<ParameterModel> getParameters() {
		return parameters;
	}
	
	public String getPath() {
		return path;
	}
	
	public ParameterModel getRequestEntity() {
		return requestEntity;
	}
	
	public String getResponseType() {
		return responseType;
	}
}