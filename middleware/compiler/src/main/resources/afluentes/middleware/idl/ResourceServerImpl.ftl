package ${resourceModel.packageName};

import java.util.*;

import javax.ws.rs.*;

import org.springframework.stereotype.*;

@Path("${resourceModel.path}")
@Component
public class ${resourceModel.className} implements ${resourceModel.interfaceName} {
<#list resourceModel.methods as method>
	@Override
	public ${method.responseType} ${method.name}(
	<#if method.parameters??>	
		<#list method.parameters as parameter>
			final ${parameter.type} ${parameter.alias}<#if parameter_has_next>,</#if>
		</#list>
	</#if>
	) {
		// TODO
		throw new UnsupportedOperationException();
	}
</#list>
}