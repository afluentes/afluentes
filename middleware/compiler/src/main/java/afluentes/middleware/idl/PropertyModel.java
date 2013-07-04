package afluentes.middleware.idl;

public class PropertyModel {
	public final String alias;
	public final String description;
	public final String name;
	public final String type;
	
	public PropertyModel(final String alias,
						 final String description, 
						 final String name,
						 final String type) {		
		this.alias = alias;
		this.description = description;
		this.name = name;		
		this.type = type;
	}
	
	public String getAlias() {
		return alias;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
}