package afluentes.loader.impl;

import java.util.List;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;

import afluentes.core.api.IEvaluation;
import afluentes.loader.api.ILoader;

public class LoaderImpl<Y> implements ILoader<Y> {
	private final Class<? super Y> rootClass;
	private final CommonTree route;

	public LoaderImpl(Class<? super Y> rootClass, String route) throws RecognitionException {
		if (rootClass == null) {
			throw new IllegalArgumentException("rootClass == null");
		}
		if (route == null) {
			throw new IllegalArgumentException("route == null");
		}		
		this.rootClass = rootClass;

		CharStream input = new ANTLRStringStream(route);
		LoaderLexer lexer = new LoaderLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		LoaderParser parser = new LoaderParser(tokens);
		this.route = parser.start().tree;
	}
	
	@Override
	public void load(IEvaluation<Y> root) {
		load(root, rootClass, route);
	}

	private void load(Object node, Class<?> nodeClass, CommonTree route) {
		if (List.class.isAssignableFrom(nodeClass)) {
			System.out.println("Lista");
		} else {
			System.out.println("Objeto");
		}
	}
}