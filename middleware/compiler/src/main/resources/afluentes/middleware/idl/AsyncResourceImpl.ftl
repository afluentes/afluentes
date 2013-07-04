package ${resourceModel.packageName};

import javax.ws.rs.client.*;
import javax.ws.rs.client.Invocation.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.*;

import afluentes.core.api.*;
import afluentes.core.impl.*;
import afluentes.middleware.ws.rs.client.*;

<#macro class resourceModel>
@SuppressWarnings("unused")
public class ${resourceModel.className} implements ${resourceModel.interfaceName} {
	private final Client client;
	private final String uri;
	
<#list resourceModel.methods as method>
	private final IEvaluator${method.parameters?size}<<#list method.parameters as parameter>${parameter.type}, </#list>${method.responseType}> ${method.name}; 
</#list>	
	 	
	public ${resourceModel.className}(final Client client, final String uri) {
		this.client = client;
		this.uri = uri;
		
<#list resourceModel.methods as method>
		this.${method.name} = new AsynchronousEvaluator${method.parameters?size}<>(new IAsynchronousFunction${method.parameters?size}<<#list method.parameters as parameter>${parameter.type}, </#list>${method.responseType}>() {
			@Override
			public void y(<#list method.parameters as parameter>${parameter.type} ${parameter.name}, </#list>ICallback<${method.responseType}> callback) {
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
				AsyncInvoker invoker = request.async();				
				invoker.method("${method.httpMethod}"<#if method.requestEntity??>, Entity.json(${method.requestEntity.alias})</#if>, new ICallbackAdapter<${method.responseType}>(callback) {}); 			
			}		
		});
				  
</#list>	
	}
	
<#list resourceModel.methods as method>
	@Override
	public IEvaluation<${method.responseType}> ${method.name}(
	<#if method.parameters??>	
		<#list method.parameters as parameter>
			final ${parameter.type} ${parameter.alias}<#if parameter_has_next>,</#if>
		</#list>
	</#if>
	) {
		return ${method.name}(
		<#list method.parameters as parameter>
			new Constant<>(${parameter.alias})<#if parameter_has_next>,</#if>
		</#list>			
		);
	}

	<#if (method.parameters?size > 0)>
	@Override
	public IEvaluation<${method.responseType}> ${method.name}(
	<#if method.parameters??>	
		<#list method.parameters as parameter>
			final IEvaluation<${parameter.type}> ${parameter.alias}<#if parameter_has_next>,</#if>
		</#list>
	</#if>
	) {
		return ${method.name}.y(
		<#list method.parameters as parameter>
			${parameter.alias}<#if parameter_has_next>,</#if>
		</#list>
		);
	}
	</#if>
	
</#list>	
}
</#macro>

<@class resourceModel=resourceModel/>