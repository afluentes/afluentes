package ${resourceModel.packageName};

import java.util.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import afluentes.middleware.ws.rs.*;

<#macro interface resourceModel>
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ${resourceModel.className} {
<#list resourceModel.methods as method>
	<#if method.description??>
	/**
 	 * ${method.description}
 	 *	 
<#list method.parameters as parameter>	 
	<#if parameter.description??>
 	 * @param ${parameter.name} ${parameter.description}
	</#if>
</#list>
 	 */
	</#if>	 
	<#if method.httpMethod??>@${method.httpMethod}</#if>
	<#if method.path??>@Path("${method.path}")</#if>
	${method.responseType} ${method.name}(
	<#if method.parameters??>	
		<#list method.parameters as parameter>
			<#if parameter.annotation??>@${parameter.annotation}Param("${parameter.name}")</#if> ${parameter.type} ${parameter.alias}<#if parameter_has_next>,</#if>
		</#list>
	</#if>
	);	
</#list>

<#if resourceModel.nestedClasses??>
<#list resourceModel.nestedClasses as nestedClass>
	<@interface resourceModel=nestedClass/>
</#list>
</#if>
}
</#macro>

<@interface resourceModel=resourceModel/>