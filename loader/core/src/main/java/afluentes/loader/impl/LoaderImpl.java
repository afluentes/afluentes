package afluentes.loader.impl;

import org.antlr.runtime.RecognitionException;

import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator1;
import afluentes.core.impl.Constant;
import afluentes.loader.api.ILoader;

import com.google.common.reflect.TypeToken;

public abstract class LoaderImpl<Y> implements ILoader<Y> {
	private IEvaluator1<Y, Y> load;

	public LoaderImpl(String route) {
		TypeToken<Y> token = new TypeToken<Y>(getClass()) {};
		Compiler<Y> compiler = new Compiler<>();
		try {
			load = compiler.compile(token, route);
		} catch (RecognitionException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void load(Y root) {
		load(new Constant<>(root));
	}	
	
	@Override
	public void load(IEvaluation<Y> root) {
		load.y(root).y();
	}
}