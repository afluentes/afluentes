package afluentes.core.impl;

import java.util.List;

import afluentes.core.api.IEvaluation;
import afluentes.core.api.IReduceEvaluator2;
import afluentes.core.api.ISynchronousFunction1;

public class ReduceEvaluator2<X1, Y> implements IReduceEvaluator2<X1, Y> {	
	private final ISynchronousFunction1<List<X1>, Y> f;

	public ReduceEvaluator2(final ISynchronousFunction1<List<X1>, Y> f) {
		if (f == null) {
        	throw new IllegalArgumentException("f == null");
        }
        this.f = f;
	}

	@Override
	public IEvaluation<Y> y(final IEvaluation<List<IEvaluation<? extends X1>>> x1) {
		return new ReduceEvaluation2<>(f, x1);
	}	
}