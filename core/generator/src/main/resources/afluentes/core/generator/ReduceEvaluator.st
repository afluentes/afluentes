package afluentes.core.impl;

import java.util.List;

import afluentes.core.api.IEvaluation;
import afluentes.core.api.IReduce;
import afluentes.core.api.IReduceEvaluator;

public class ReduceEvaluator<X1, Y> implements IReduceEvaluator<X1, Y> {
	private final IReduce<X1, Y> f;
	
	public ReduceEvaluator(final IReduce<X1, Y> f) {
		if (f == null) {
        	throw new IllegalArgumentException("f == null");
        }
        this.f = f;		
	}

	@Override
	public IEvaluation<Y> y(final List<IEvaluation<X1>> x1s) {
		return new ReduceEvaluation<>(f, x1s);
	}
}