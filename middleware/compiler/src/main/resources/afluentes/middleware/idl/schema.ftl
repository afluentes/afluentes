package ${schemaModel.packageName};

import java.util.*;

import org.codehaus.jackson.annotate.*;

<#macro class schemaModel static>
<#if schemaModel.description??>
/**
 * ${schemaModel.description}
 */
</#if>
public <#if static>static</#if> class ${schemaModel.className} {
<#list schemaModel.properties as property>
	/**
	 * ${property.description}
	 */
	public final ${property.type} ${property.alias};

</#list>  

	@JsonCreator
	public ${schemaModel.className}(
<#list schemaModel.properties as property>
		@JsonProperty("${property.name}") final ${property.type} ${property.alias}<#if property_has_next>,</#if>
</#list>  	
	) {
<#list schemaModel.properties as property>
		this.${property.alias} = ${property.alias};
</#list>  		
	}

<#list schemaModel.nestedClasses as nestedClass>
	<@class schemaModel=nestedClass static=true/>
</#list>
}
</#macro>

<@class schemaModel=schemaModel static=false/>