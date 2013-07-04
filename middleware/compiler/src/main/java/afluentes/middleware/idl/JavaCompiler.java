package afluentes.middleware.idl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.api.services.discovery.model.JsonSchema;
import com.google.api.services.discovery.model.RestDescription;
import com.google.api.services.discovery.model.RestMethod;
import com.google.api.services.discovery.model.RestResource;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class JavaCompiler {
	private static final Set<String> KEYWORDS = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(new String[] {
		"abstract",
		"assert",
		"boolean",
		"break",
		"byte",
		"case",
		"catch",
		"char",
		"class",
		"const",
		"continue",
		"default",
		"do",
		"double",
		"else",
		"enum",
		"extends",
		"false",
		"final",
		"finally",
		"float",
		"for",
		"goto",
		"if",
		"implements",
		"import",
		"instanceof",
		"int",
		"interface",
		"long",
		"native",
		"new",
		"null",
		"package",
		"private",
		"protected",
		"public",
		"return",
		"short",
		"static",
		"strictfp",
		"super",
		"switch",
		"synchronized",
		"this",
		"throw",
		"throws",
		"transient",
		"true",
		"try",
		"void",
		"volatile",
		"while"			
	})));
	
	private Configuration configuration;

	public JavaCompiler() {
		Locale locale = Locale.getDefault();

		configuration = new Configuration();
		configuration.setEncoding(locale, "UTF-8");
		configuration.setLocale(locale);
		configuration.setObjectWrapper(new DefaultObjectWrapper());
		configuration.setTemplateLoader(new ClassTemplateLoader(getClass(), getPackageName()));
	}

	private String getPackageName() {
		String name = getClass().getName();
		int index = name.lastIndexOf(".");
		name = name.substring(0, index);
		name = name.replace('.', '/');
		name = "/" + name;
		return name;
	}
	
	public void compileRestDescription(File directory,
									   String groupId,
									   String artifactId,
									   String version,
									   String packageName, 
									   RestDescription description) throws IOException, TemplateException {
		compileModulesPom(directory, groupId, artifactId, version);
		compileApiPom(directory, groupId, artifactId, version);
		compileClientPom(directory, groupId, artifactId, version);
		compileImplPom(directory, groupId, artifactId, version);
		if (description.schemas != null) {
			for (Entry<String, JsonSchema> schemaEntry : description.schemas.entrySet()) {
				compileJsonSchema(directory, packageName, schemaEntry.getKey(), schemaEntry.getValue());
			}
		}			
		compileResources(directory, packageName, description);
		compileWeb(directory);
		compileApplicationContext(directory, packageName);
		compileLog4j(directory);
	}

	private void compileModulesPom(File directory, String groupId, String artifactId, String version) throws IOException, TemplateException {
		Map<String, Object> rootModel = new HashMap<>();
		rootModel.put("groupId", groupId);
		rootModel.put("artifactId", artifactId);
		rootModel.put("version", version);

		File pomDirectory = directory;
		pomDirectory.mkdirs();
		File pomFile = new File(pomDirectory, "pom.xml");
		try (OutputStream output = new FileOutputStream(pomFile);
				Writer writer = new OutputStreamWriter(output, "UTF-8")) {
			Template template = configuration.getTemplate("modules-pom.ftl");
			template.process(rootModel, writer);
		}
	}

	private void compileApiPom(File directory, String groupId, String artifactId, String version) throws IOException, TemplateException {
		Map<String, Object> rootModel = new HashMap<>();
		rootModel.put("groupId", groupId);
		rootModel.put("artifactId", artifactId);
		rootModel.put("version", version);

		File pomDirectory = new File(directory, "api");
		pomDirectory.mkdirs();
		File pomFile = new File(pomDirectory, "pom.xml");
		try (OutputStream output = new FileOutputStream(pomFile);
				Writer writer = new OutputStreamWriter(output, "UTF-8")) {
			Template template = configuration.getTemplate("api-pom.ftl");
			template.process(rootModel, writer);
		}
	}
	
	private void compileClientPom(File directory, String groupId, String artifactId, String version) throws IOException, TemplateException {
		Map<String, Object> rootModel = new HashMap<>();
		rootModel.put("groupId", groupId);
		rootModel.put("artifactId", artifactId);
		rootModel.put("version", version);

		File pomDirectory = new File(directory, "client");
		pomDirectory.mkdirs();
		File pomFile = new File(pomDirectory, "pom.xml");
		try (OutputStream output = new FileOutputStream(pomFile);
				Writer writer = new OutputStreamWriter(output, "UTF-8")) {
			Template template = configuration.getTemplate("client-pom.ftl");
			template.process(rootModel, writer);
		}
	}	
	
	private void compileImplPom(File directory, String groupId, String artifactId, String version) throws IOException, TemplateException {
		Map<String, Object> rootModel = new HashMap<>();
		rootModel.put("groupId", groupId);
		rootModel.put("artifactId", artifactId);
		rootModel.put("version", version);

		File pomDirectory = new File(directory, "impl");
		pomDirectory.mkdirs();
		File pomFile = new File(pomDirectory, "pom.xml");
		try (OutputStream output = new FileOutputStream(pomFile);
				Writer writer = new OutputStreamWriter(output, "UTF-8")) {
			Template template = configuration.getTemplate("impl-pom.ftl");
			template.process(rootModel, writer);
		}
	}

	private void compileJsonSchema(File directory, String packageName, String className, JsonSchema schema) throws IOException, TemplateException {
		SchemaModel schemaModel = getSchemaModel(packageName, className, schema);

		Map<String, Object> rootModel = new HashMap<>();
		rootModel.put("schemaModel", schemaModel);

		File classDirectory = new File(new File(directory, "api/src/main/java"), schemaModel.packageName.replace('.', '/'));
		classDirectory.mkdirs();
		File classFile = new File(classDirectory, schemaModel.className + ".java");
		try (OutputStream output = new FileOutputStream(classFile);
				Writer writer = new OutputStreamWriter(output, "UTF-8")) {
			Template template = configuration.getTemplate("schema.ftl");
			template.process(rootModel, writer);
		}
	}

	private SchemaModel getSchemaModel(String packageName, String className, JsonSchema schema) {
		List<SchemaModel> nestedClasses = new ArrayList<>();

		List<PropertyModel> propertyModels = new ArrayList<>();		
		if (schema.properties != null) {
			for (Entry<String, JsonSchema> propertyEntry : schema.properties.entrySet()) {
				String name = propertyEntry.getKey();
	
				String alias;
				if (KEYWORDS.contains(name)) {
					alias = name + "_";
				} else {
					alias = name;
				}
				String aliasCamelCased = camelCase(alias);
				
				JsonSchema property = propertyEntry.getValue();			
				PropertyModel propertyModel = new PropertyModel(
					alias,
					property.description,
					name, 
					getType(alias, property)
				);
				propertyModels.add(propertyModel);
	
				if ("array".equals(property.type)
						&& property.items != null
						&& "object".equals(property.items.type)) {				
					nestedClasses.add(getSchemaModel(packageName, aliasCamelCased, property.items));
				}
				if ("object".equals(property.type)) {
					if (property.additionalProperties == null) {
						nestedClasses.add(getSchemaModel(packageName, aliasCamelCased, property));
					} else if (property.additionalProperties.$ref == null) {
						nestedClasses.add(getSchemaModel(packageName, aliasCamelCased, property.additionalProperties));
					}
				}
			}
			Collections.sort(propertyModels, new Comparator<PropertyModel>() {
				@Override
				public int compare(PropertyModel propertyModel1, PropertyModel propertyModel2) {
					return propertyModel1.name.compareTo(propertyModel2.name);
				}
			});
		}
		
		Collections.sort(nestedClasses, new Comparator<SchemaModel>() {
			@Override
			public int compare(SchemaModel schemaModel1, SchemaModel schemaModel2) {
				return schemaModel1.className.compareTo(schemaModel2.className);
			}
		});

		return new SchemaModel(
			className,
			schema.description,
			nestedClasses,
			packageName, 			
			propertyModels
		);
	}
	
	private String camelCase(String string) {
		String stringCamelCased;
		if (string == null) {
			stringCamelCased = string;
		} else if (string.isEmpty()) {
			stringCamelCased = string;
		} else {
			stringCamelCased = string.substring(0, 1).toUpperCase() + string.substring(1);
		}
		return stringCamelCased;
	}

	private String getType(String propertyAlias, JsonSchema property) {
		String type;
		if (property.type == null) {
			type = property.$ref;
		} else {
			if ("any".equals(property.type)) {
				type = "Object";
			} else if ("array".equals(property.type)) {
				JsonSchema items = property.items;
				type = "List<" + getType(propertyAlias, items) + ">";
			} else if ("boolean".equals(property.type)) {
				if (property.required == null || property.required) {
					type = "boolean";
				} else {
					type = "Boolean";
				}
			} else if ("integer".equals(property.type)) {
				if (property.required == null || property.required) {
					type = "int";
				} else {
					type = "Integer";
				}
			} else if ("null".equals(property.type)) {
				type = "Object";
			} else if ("number".equals(property.type)) {
				if (property.required == null || property.required) {
					type = "double";
				} else {
					type = "Double";
				}
			} else if ("object".equals(property.type)) {
				if (property.additionalProperties == null) {
					type = propertyAlias.substring(0, 1).toUpperCase() + propertyAlias.substring(1);
				} else {
					if (property.additionalProperties.$ref == null) {
						type = "Map<String, " + getType(propertyAlias, property.additionalProperties) + ">";
					} else {
						type = "Map<String, " + property.additionalProperties.$ref + ">";
					}					
				}				
			} else if ("string".equals(property.type)) {
				type = "String";
			} else {
				type = property.type;
			}
		}
		return type;
	}

	private void compileResources(File directory, String packageName, RestDescription description) throws IOException, TemplateException {
		List<MethodModel> methods = new ArrayList<>();
		if (description.resources != null) {
			for (Entry<String, RestResource> subResourceEntry : description.resources.entrySet()) {
				String className = subResourceEntry.getKey();
				RestResource subResource = subResourceEntry.getValue();
				ResourceModel subResourceModel = getResourceModel(packageName, className, subResource);									
				compileResource(directory, packageName, subResourceModel);
				
				ResourceModel asyncSubResourceModel = getAsyncResourceModel(className, subResourceModel); 
				compileAsyncResource(directory, packageName, asyncSubResourceModel);

				ResourceModel asyncSubResourceImplModel = new ResourceModel(
					camelCase(className) + "AsyncResourceImpl", 
					"I" + camelCase(className) + "AsyncResource", 
					asyncSubResourceModel.description, 
					asyncSubResourceModel.methods, 
					asyncSubResourceModel.nestedClasses, 
					asyncSubResourceModel.packageName, 
					asyncSubResourceModel.path
				); 
				compileAsyncResourceImpl(directory, packageName, asyncSubResourceImplModel);
				
				ResourceModel subResourceImplModel = new ResourceModel(
					camelCase(className) + "ResourceImpl",
					subResourceModel.className,
					subResourceModel.description, 
					subResourceModel.methods, 
					subResourceModel.nestedClasses, 
					subResourceModel.packageName, 
					subResourceModel.path
				);
				compileResourceServerImpl(directory, packageName, className, subResourceImplModel);
				compileResourceClientImpl(directory, packageName, className, subResourceImplModel);
				
				MethodModel methodModel = new MethodModel(
					null, 
					null, 
					"get" + camelCase(className), 
					null, 
					subResourceModel.path, 
					null, 
					subResourceModel.className
				);
				methods.add(methodModel);				
			}
			ResourceModel resourceModel = new ResourceModel(
				"I" + camelCase(description.name) + "Resource",
				null,
				description.description, 
				methods, 
				null, 
				packageName,
				description.rootUrl
			);		
			compileResource(directory, packageName, resourceModel);
		}
	}

	private void compileResource(File directory, String packageName, ResourceModel resourceModel) throws IOException, TemplateException {
		Map<String, Object> rootModel = new HashMap<>();
		rootModel.put("resourceModel", resourceModel);

		File classDirectory = new File(new File(directory, "api/src/main/java"), resourceModel.packageName.replace('.', '/'));
		classDirectory.mkdirs();
		File classFile = new File(classDirectory, resourceModel.className + ".java");
		try (OutputStream output = new FileOutputStream(classFile);
				Writer writer = new OutputStreamWriter(output, "UTF-8")) {
			Template template = configuration.getTemplate("IResource.ftl");
			template.process(rootModel, writer);
		}
	}

	private ResourceModel getAsyncResourceModel(String className, ResourceModel resourceModel) {
		List<MethodModel> asyncMethods;

		asyncMethods = new ArrayList<>();
		for (MethodModel syncMethod : resourceModel.methods) {
			List<ParameterModel> asyncParameters = new ArrayList<>();
			for (ParameterModel syncParameter : syncMethod.parameters) {
				ParameterModel asyncParameter = new ParameterModel(
					syncParameter.annotation, 
					syncParameter.alias, 
					syncParameter.description, 
					syncParameter.name,
					getWrapperType(syncParameter.type)					
				);
				asyncParameters.add(asyncParameter);
			}
			
			MethodModel asyncMethod = new MethodModel(
				syncMethod.description, 
				syncMethod.httpMethod, 
				syncMethod.name, 
				asyncParameters, 
				syncMethod.path, 
				getAsyncRequestEntity(syncMethod.requestEntity),
				getWrapperType(syncMethod.responseType)
			);
			asyncMethods.add(asyncMethod);
		}
		return new ResourceModel(
			"I" + camelCase(className) + "AsyncResource", 
			resourceModel.interfaceName, 
			resourceModel.description, 
			asyncMethods, 
			resourceModel.nestedClasses, 
			resourceModel.packageName, 
			resourceModel.path
		);
	}

	private ParameterModel getAsyncRequestEntity(ParameterModel requestEntity) {
		if (requestEntity == null) {
			return null;
		} else {
			return new ParameterModel(
				requestEntity.annotation, 
				requestEntity.alias, 
				requestEntity.description, 
				requestEntity.name, 
				getWrapperType(requestEntity.type)
			);
		}
	}
	
	private String getWrapperType(String type) {
		switch (type) {
		case "boolean":
			return "Boolean";
		case "byte":
			return "Byte";
		case "char":
			return "Character";
		case "double":
			return "Double";
		case "float":
			return "Float";			
		case "int":
			return "Integer";			
		case "long":
			return "Long";			
		case "short":
			return "Short";
		case "void":
			return "Void";						
		default:
			return type;
		}
	}

	private void compileAsyncResource(File directory, String packageName, ResourceModel resourceModel) throws IOException, TemplateException {
		Map<String, Object> rootModel = new HashMap<>();
		rootModel.put("resourceModel", resourceModel);

		File classDirectory = new File(new File(directory, "api/src/main/java"), resourceModel.packageName.replace('.', '/'));
		classDirectory.mkdirs();
		File classFile = new File(classDirectory, resourceModel.className + ".java");
		try (OutputStream output = new FileOutputStream(classFile);
				Writer writer = new OutputStreamWriter(output, "UTF-8")) {
			Template template = configuration.getTemplate("IAsyncResource.ftl");
			template.process(rootModel, writer);
		}
	}
	
	private void compileAsyncResourceImpl(File directory, String packageName, ResourceModel resourceModel) throws IOException, TemplateException {
		Map<String, Object> rootModel = new HashMap<>();
		rootModel.put("resourceModel", resourceModel);

		File classDirectory = new File(new File(directory, "client/src/main/java"), resourceModel.packageName.replace('.', '/'));
		classDirectory.mkdirs();
		File classFile = new File(classDirectory, resourceModel.className + ".java");
		try (OutputStream output = new FileOutputStream(classFile);
				Writer writer = new OutputStreamWriter(output, "UTF-8")) {
			Template template = configuration.getTemplate("AsyncResourceImpl.ftl");
			template.process(rootModel, writer);
		}
	}		
	
	private ResourceModel getResourceModel(String packageName, String className, RestResource resource) {
		List<ResourceModel> nestedClasses = new ArrayList<>();

		List<MethodModel> methods = new ArrayList<>();
		for (Entry<String, RestMethod> entry : resource.methods.entrySet()) {
			String methodName = entry.getKey();
			RestMethod restMethod = entry.getValue();
			MethodModel methodModel = getMethodModel(methodName, restMethod);
			methods.add(methodModel);
		}

		String path;
		RestMethod method = resource.methods.values().iterator().next();
		int index = method.path.indexOf('/');
		if (index == -1) {
			path = method.path;
		} else {
			path = method.path.substring(0, index);
		}

		return new ResourceModel(
			"I" + camelCase(className) + "Resource",
			null,
			null,
			methods,
			nestedClasses,
			packageName,
			path
		);
	}

	private MethodModel getMethodModel(String methodName, RestMethod method) {
		List<ParameterModel> parameters = new ArrayList<>();
		if (method.parameters != null) {
			for (Entry<String, JsonSchema> methodEntry : method.parameters.entrySet()) {
				String parameterName = methodEntry.getKey();
				JsonSchema parameter = methodEntry.getValue();
				ParameterModel parameterModel = getParameterModel(parameterName, parameter);
				parameters.add(parameterModel);
			}
		}

		String path;
		int index = method.path.indexOf('/');
		if (index == -1) {
			path = null;
		} else {
			path = method.path.substring(index + 1); 
		}

		ParameterModel requestEntity;
		if (method.request == null) {
			requestEntity = null;
		} else {
			requestEntity = new ParameterModel(
				null,
				"requestEntity",
				null, 
				"requestEntity", 
				method.request.$ref
			);
			parameters.add(requestEntity);
		}
		
		String responseType;
		if (method.response == null) {
			responseType = "void";
		} else {
			responseType = method.response.$ref;
		}		

		return new MethodModel(
			method.description,
			method.httpMethod, 
			methodName, 
			parameters, 
			path, 
			requestEntity, 
			responseType
		);
	}

	private ParameterModel getParameterModel(String parameterName, JsonSchema parameter) {
		String annotation = camelCase(parameter.location);
		String alias = parameterName;		
		return new ParameterModel(
			annotation, 
			alias, 
			parameter.description, 
			parameterName, 
			getType(parameterName, parameter)
		);
	}

	private void compileResourceServerImpl(File directory, String packageName, String className, ResourceModel resourceModel) throws IOException, TemplateException {
		Map<String, Object> rootModel = new HashMap<>();
		rootModel.put("resourceModel", resourceModel);

		File classDirectory = new File(new File(directory, "impl/src/main/java"), resourceModel.packageName.replace('.', '/'));
		classDirectory.mkdirs();
		File classFile = new File(classDirectory, resourceModel.className + ".java");
		try (OutputStream output = new FileOutputStream(classFile);
				Writer writer = new OutputStreamWriter(output, "UTF-8")) {
			Template template = configuration.getTemplate("ResourceServerImpl.ftl");
			template.process(rootModel, writer);
		}
	}

	private void compileResourceClientImpl(File directory, String packageName, String className, ResourceModel resourceModel) throws IOException, TemplateException {
		Map<String, Object> rootModel = new HashMap<>();
		rootModel.put("resourceModel", resourceModel);

		File classDirectory = new File(new File(directory, "client/src/main/java"), resourceModel.packageName.replace('.', '/'));
		classDirectory.mkdirs();
		File classFile = new File(classDirectory, resourceModel.className + ".java");
		try (OutputStream output = new FileOutputStream(classFile);
				Writer writer = new OutputStreamWriter(output, "UTF-8")) {
			Template template = configuration.getTemplate("ResourceClientImpl.ftl");
			template.process(rootModel, writer);
		}
	}	
	
	private void compileWeb(File directory) throws IOException, TemplateException {
		Map<String, Object> rootModel = new HashMap<>();

		File classDirectory = new File(directory, "impl/src/main/webapp/WEB-INF");
		classDirectory.mkdirs();
		File classFile = new File(classDirectory, "web.xml");
		try (OutputStream output = new FileOutputStream(classFile);
				Writer writer = new OutputStreamWriter(output, "UTF-8")) {
			Template template = configuration.getTemplate("web.ftl");
			template.process(rootModel, writer);
		}		
	}
	
	private void compileApplicationContext(File directory, String packageName) throws IOException, TemplateException {
		Map<String, Object> rootModel = new HashMap<>();
		rootModel.put("packageName", packageName);

		File classDirectory = new File(directory, "impl/src/main/webapp/WEB-INF");
		classDirectory.mkdirs();
		File classFile = new File(classDirectory, "applicationContext.xml");
		try (OutputStream output = new FileOutputStream(classFile);
				Writer writer = new OutputStreamWriter(output, "UTF-8")) {
			Template template = configuration.getTemplate("applicationContext.ftl");
			template.process(rootModel, writer);
		}				
	}	
	
	private void compileLog4j(File directory) throws IOException, TemplateException {
		Map<String, Object> rootModel = new HashMap<>();

		File classDirectory = new File(directory, "impl/src/main/resources");
		classDirectory.mkdirs();
		File classFile = new File(classDirectory, "log4j.properties");
		try (OutputStream output = new FileOutputStream(classFile);
				Writer writer = new OutputStreamWriter(output, "UTF-8")) {
			Template template = configuration.getTemplate("log4j.ftl");
			template.process(rootModel, writer);
		}						
	}	
}