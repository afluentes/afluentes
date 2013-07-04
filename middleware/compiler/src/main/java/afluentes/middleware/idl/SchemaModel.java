package afluentes.middleware.idl;

import java.util.List;

public class SchemaModel {
	public final String className;
	public final String description;
	public final List<SchemaModel> nestedClasses;
	public final String packageName;	
	public final List<PropertyModel> properties;	
	
	public SchemaModel(final String className,
					   final String description,
					   List<SchemaModel> nestedClasses,
					   final String packageName, 					   
					   final List<PropertyModel> properties) {
		this.className = className;
		this.description = description;
		this.nestedClasses = nestedClasses;
		this.packageName = packageName;		
		this.properties = properties;
	}
	
	public String getClassName() {
		return className;
	}
	
	public String getDescription() {
		return description;
	}
	
	public List<SchemaModel> getNestedClasses() {
		return nestedClasses;
	}
	
	public String getPackageName() {
		return packageName;
	}
	
	public List<PropertyModel> getProperties() {
		return properties;
	}
}