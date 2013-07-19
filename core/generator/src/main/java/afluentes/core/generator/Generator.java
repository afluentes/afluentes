package afluentes.core.generator;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupDir;

public class Generator {
	private static final String ENCODING = "UTF-8";

	public void generate(int maximum) throws IOException {
		STGroupDir templates = new STGroupDir(getDirectory(), ENCODING, '$', '$');

		// API
		generateIAsynchronousRunnable(templates);
		generateICallback(templates);
		generateIEvaluation(templates);
		
		// Implementation
		generateConstant(templates);		
		generateEvaluation(templates);
		generateEvaluator(templates);
		generateIAsynchronousFunction0Adapter(templates);
		generateIAsynchronousRunnableAdapter(templates);
		generateISynchronousFunction0Adapter(templates);
		generateRunnableAdapter(templates);		
		
		for (int arity = 0; arity <= maximum; ++arity) {
			// API			
			generateIAsynchronousFunction(templates, arity);
			generateIEvaluator(templates, arity);			
			generateISynchronousFunction(templates, arity);
			
			// Implementation
			generateAsynchronousEvaluation(templates, arity);
			generateAsynchronousEvaluator(templates, arity);
			generateEvaluationN(templates, arity);
			generateSynchronousEvaluation(templates, arity);
			generateSynchronousEvaluator(templates, arity);
		}		
	}
	
	private String getDirectory() {
		String name = getClass().getName();
		int index = name.lastIndexOf('.');
		if (index == -1) {
			return "";
		} else {
			return name.substring(0, index).replace('.', '/');	
		}		
	}	
	
	private void addParameters(ST template, int arity) {
		for (int i = 1; i <= arity; ++i) {
			template.addAggr("parameters.{type, name}", new Object[] {"X" + i, "x" + i});			
		}
		template.addAggr("parameters.{type, name}", new Object[] {"Y", "y"});		
	}	
	
	private File newFile(String name) {
		return new File("../core/src/main/java", name);
	}
		
	private void generateIAsynchronousRunnableAdapter(STGroupDir templates) throws IOException {
		ST template = templates.getInstanceOf("IAsynchronousRunnableAdapter");		

		File file = newFile("afluentes/core/impl/IAsynchronousRunnableAdapter.java");
		FileUtils.write(file, template.render(), ENCODING);
	}				
	
	private void generateICallback(STGroupDir templates) throws IOException {
		ST template = templates.getInstanceOf("ICallback");
		
		File file = newFile("afluentes/core/api/ICallback.java");
		FileUtils.write(file, template.render(), ENCODING);		
	}
	
	private void generateIEvaluation(STGroupDir templates) throws IOException {
		ST template = templates.getInstanceOf("IEvaluation");		

		File file = newFile("afluentes/core/api/IEvaluation.java");
		FileUtils.write(file, template.render(), ENCODING);
	}
	
	private void generateConstant(STGroupDir templates) throws IOException {
		ST template = templates.getInstanceOf("Constant");

		File file = newFile("afluentes/core/impl/Constant.java");
		FileUtils.write(file, template.render(), ENCODING);
	}	
	
	private void generateEvaluation(STGroupDir templates) throws IOException {
		ST template = templates.getInstanceOf("Evaluation");

		File file = newFile("afluentes/core/impl/Evaluation.java");
		FileUtils.write(file, template.render(), ENCODING);
	}
	
	private void generateEvaluator(STGroupDir templates) throws IOException {
		ST template = templates.getInstanceOf("Evaluator");

		File file = newFile("afluentes/core/impl/Evaluator.java");
		FileUtils.write(file, template.render(), ENCODING);
	}	
	
	private void generateIAsynchronousFunction0Adapter(STGroupDir templates) throws IOException {
		ST template = templates.getInstanceOf("IAsynchronousFunction0Adapter");		

		File file = newFile("afluentes/core/impl/IAsynchronousFunction0Adapter.java");
		FileUtils.write(file, template.render(), ENCODING);
	}			
	
	private void generateIAsynchronousRunnable(STGroupDir templates) throws IOException {
		ST template = templates.getInstanceOf("IAsynchronousRunnable");		

		File file = newFile("afluentes/core/api/IAsynchronousRunnable.java");
		FileUtils.write(file, template.render(), ENCODING);
	}	

	private void generateISynchronousFunction0Adapter(STGroupDir templates) throws IOException {
		ST template = templates.getInstanceOf("ISynchronousFunction0Adapter");

		File file = newFile("afluentes/core/impl/ISynchronousFunction0Adapter.java");
		FileUtils.write(file, template.render(), ENCODING);
	}
	
	private void generateRunnableAdapter(STGroupDir templates) throws IOException {
		ST template = templates.getInstanceOf("RunnableAdapter");

		File file = newFile("afluentes/core/impl/RunnableAdapter.java");
		FileUtils.write(file, template.render(), ENCODING);
	}	
			
	private void generateIAsynchronousFunction(STGroupDir templates, int arity) throws IOException {
		ST template = templates.getInstanceOf("IAsynchronousFunction");		
		template.add("arity", arity);

		addParameters(template, arity);

		File file = newFile("afluentes/core/api/IAsynchronousFunction" + arity + ".java");
		FileUtils.write(file, template.render(), ENCODING);
	}		
		
	private void generateIEvaluator(STGroupDir templates, int arity) throws IOException {
		ST template = templates.getInstanceOf("IEvaluator");
		template.add("arity", arity);
		
		addParameters(template, arity);

		File file = newFile("afluentes/core/api/IEvaluator" + arity + ".java");
		FileUtils.write(file, template.render(), ENCODING);
	}
	
	private void generateISynchronousFunction(STGroupDir templates, int arity) throws IOException {
		ST template = templates.getInstanceOf("ISynchronousFunction");		
		template.add("arity", arity);
		
		addParameters(template, arity);

		File file = newFile("afluentes/core/api/ISynchronousFunction" + arity + ".java");
		FileUtils.write(file, template.render(), ENCODING);
	}	
							
	private void generateAsynchronousEvaluation(STGroupDir templates, int arity) throws IOException {
		ST template = templates.getInstanceOf("AsynchronousEvaluation");		
		template.add("arity", arity);

		addParameters(template, arity);

		File file = newFile("afluentes/core/impl/AsynchronousEvaluation" + arity + ".java");
		FileUtils.write(file, template.render(), ENCODING);
	}
	
	private void generateAsynchronousEvaluator(STGroupDir templates, int arity) throws IOException {
		ST template = templates.getInstanceOf("AsynchronousEvaluator");		
		template.add("arity", arity);

		addParameters(template, arity);

		File file = newFile("afluentes/core/impl/AsynchronousEvaluator" + arity + ".java");
		FileUtils.write(file, template.render(), ENCODING);
	}	
	
	private void generateEvaluationN(STGroupDir templates, int arity) throws IOException {
		ST template = templates.getInstanceOf("EvaluationN");
		template.add("arity", arity);
		
		addParameters(template, arity);		

		File file = newFile("afluentes/core/impl/Evaluation" + arity + ".java");
		FileUtils.write(file, template.render(), ENCODING);
	}
	
	private void generateSynchronousEvaluation(STGroupDir templates, int arity) throws IOException {
		ST template = templates.getInstanceOf("SynchronousEvaluation");		
		template.add("arity", arity);

		addParameters(template, arity);

		File file = newFile("afluentes/core/impl/SynchronousEvaluation" + arity + ".java");
		FileUtils.write(file, template.render(), ENCODING);
	}

	private void generateSynchronousEvaluator(STGroupDir templates, int arity) throws IOException {
		ST template = templates.getInstanceOf("SynchronousEvaluator");		
		template.add("arity", arity);

		addParameters(template, arity);

		File file = newFile("afluentes/core/impl/SynchronousEvaluator" + arity + ".java");
		FileUtils.write(file, template.render(), ENCODING);
	}

	public static void main(String[] args) throws IOException {
		int maximum = Integer.parseInt(args[0]);
		Generator generator = new Generator();
		generator.generate(maximum);
	}
}