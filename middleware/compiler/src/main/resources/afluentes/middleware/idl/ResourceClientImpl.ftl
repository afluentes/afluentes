package ${resourceModel.packageName};

import java.util.*;

import javax.ws.rs.client.*;
import javax.ws.rs.client.Invocation.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.*;

import afluentes.middleware.ws.rs.*;

public class ${resourceModel.className} implements ${resourceModel.interfaceName} {
	private final Client client;
	private final String uri;
	
	public ${resourceModel.className}(final Client client, final String uri) {
		this.client = client;
		this.uri = uri;
	}

<#list resourceModel.methods as method>
	@Override
	public ${method.responseType} ${method.name}(
	<#if method.parameters??>	
		<#list method.parameters as parameter>
			final ${parameter.type} ${parameter.alias}<#if parameter_has_next>,</#if>
		</#list>
	</#if>
	) {
		WebTarget target = client.target(uri);
		<#if method.path??>
		target = target.path("${method.path}");
		</#if>

		<#list method.parameters as parameter>
			<#if parameter.annotation?? && parameter.annotation == "Path">
		target = target.path(${parameter.alias});
			</#if>
		</#list>		

		<#list method.parameters as parameter>
			<#if parameter.annotation?? && parameter.annotation == "Query">
		target = target.queryParam("${parameter.name}", ${parameter.alias});
			</#if>
		</#list>
		Builder request = target.request();

		Invocation invocation = request.build("${method.httpMethod}"<#if method.requestEntity??>, Entity.json(${method.requestEntity.alias})</#if>); 
		Response response = invocation.invoke(); 
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException();
		}
		<#if method.responseType == "void">
		<#else>		
		${method.responseType} responseEntity = response.readEntity(${method.responseType}.class);
		return responseEntity;
		</#if>
	}

</#list>
}