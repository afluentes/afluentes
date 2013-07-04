package ${resourceModel.packageName};

import afluentes.core.api.*;

<#macro interface resourceModel>
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
	IEvaluation<${method.responseType}> ${method.name}(
	<#if method.parameters??>	
		<#list method.parameters as parameter>
			${parameter.type} ${parameter.alias}<#if parameter_has_next>,</#if>
		</#list>
	</#if>
	);	
		 
	<#if (method.parameters?size > 0)>		 
	IEvaluation<${method.responseType}> ${method.name}(
	<#if method.parameters??>	
		<#list method.parameters as parameter>
			IEvaluation<${parameter.type}> ${parameter.alias}<#if parameter_has_next>,</#if>
		</#list>
	);
	</#if>
	</#if>	
</#list>

<#if resourceModel.nestedClasses??>
<#list resourceModel.nestedClasses as nestedClass>
	<@interface resourceModel=nestedClass/>
</#list>
</#if>
}
</#macro>

<@interface resourceModel=resourceModel/>