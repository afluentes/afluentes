package afluentes.middleware.idl;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.google.api.services.discovery.model.RestDescription;

import freemarker.template.TemplateException;

public class JavaCompilerTest {
	@Test
	public void discovery() throws JsonParseException, JsonMappingException, IOException, TemplateException {
		RestDescription description = new ObjectMapper().readValue(new File("src/test/resources/discovery.v1.json"), RestDescription.class);
		JavaCompiler compiler = new JavaCompiler();
		compiler.compileRestDescription(
			new File("../test"), 
			"afluentes",
			"afluentes-middleware-test",
			"1.0.0-SNAPSHOT",
			"com.google.api.services.discovery", 
			description
		);
	}
	
	@Test
	public void drive() throws JsonParseException, JsonMappingException, IOException, TemplateException {
		RestDescription description = new ObjectMapper().readValue(new File("src/test/resources/drive.v2.json"), RestDescription.class);
		JavaCompiler compiler = new JavaCompiler();
		compiler.compileRestDescription(
			new File("../test"),
			"afluentes",
			"afluentes-middleware-test",
			"1.0.0-SNAPSHOT",
			"com.google.api.services.drive", 
			description
		);
	}	
	
	@Test
	public void plus() throws JsonParseException, JsonMappingException, IOException, TemplateException {
		RestDescription description = new ObjectMapper().readValue(new File("src/test/resources/plus.v1.json"), RestDescription.class);
		JavaCompiler compiler = new JavaCompiler();
		compiler.compileRestDescription(
			new File("../test"),
			"afluentes",
			"afluentes-middleware-test",
			"1.0.0-SNAPSHOT",			
			"com.google.api.services.plus",
			description
		);
	}
	
	@Test
	public void calculator() throws JsonParseException, JsonMappingException, IOException, TemplateException {
		RestDescription description = new ObjectMapper().readValue(new File("src/test/resources/calculator.js"), RestDescription.class);
		JavaCompiler compiler = new JavaCompiler();
		compiler.compileRestDescription(
			new File("../test"),
			"afluentes",
			"afluentes-middleware-test",
			"1.0.0-SNAPSHOT",			
			"afluentes.middleware.test",
			description
		);
	}
}