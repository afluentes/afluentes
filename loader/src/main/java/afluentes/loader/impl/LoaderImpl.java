package afluentes.loader.impl;

import org.antlr.runtime.RecognitionException;

import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator1;
import afluentes.loader.api.ILoader;

import com.google.common.reflect.TypeToken;

public abstract class LoaderImpl<Y> implements ILoader<Y> {
	private IEvaluator1<Y, Y> load;

	public LoaderImpl(String route) throws RecognitionException {
		TypeToken<Y> token = new TypeToken<Y>(getClass()) {};
		Compiler<Y> compiler = new Compiler<>(); 
		load = compiler.compile(token, route);
	}
	
	@Override
	public void load(IEvaluation<Y> root) {
		load.y(root).y();
	}
}