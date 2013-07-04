package afluentes.middleware.idl;

import java.util.List;

public class ResourceModel {
	public final String className;
	public final String interfaceName;
	public final String description;
	public final List<MethodModel> methods;
	public final List<ResourceModel> nestedClasses;
	public final String packageName;	
	public final String path;
	
	public ResourceModel(final String className,
						 final String interfaceName,
					   	 final String description,
					   	 final List<MethodModel> methods,
					   	 final List<ResourceModel> nestedClasses,
					   	 final String packageName,
					   	 final String path) {
		this.className = className;
		this.interfaceName = interfaceName;
		this.description = description;
		this.methods = methods;
		this.nestedClasses = nestedClasses;
		this.packageName = packageName;
		this.path = path;
	}	
	
	public String getClassName() {
		return className;
	}
	
	public String getInterfaceName() {
		return interfaceName;
	}
	
	public String getDescription() {
		return description;
	}
	
	public List<MethodModel> getMethods() {
		return methods;
	}
	
	public List<ResourceModel> getNestedClasses() {
		return nestedClasses;
	}
	
	public String getPackageName() {
		return packageName;
	}	
	
	public String getPath() {
		return path;
	}	
}