package afluentes.middleware.idl;

public class ParameterModel extends PropertyModel {
	public final String annotation;
	
	public ParameterModel(final String annotation,
						  final String alias, 
						  final String description, 
						  final String name, 
						  final String type) {
		super(alias, description, name, type);
		
		this.annotation = annotation;
	}
	
	public String getAnnotation() {
		return annotation;
	}
}