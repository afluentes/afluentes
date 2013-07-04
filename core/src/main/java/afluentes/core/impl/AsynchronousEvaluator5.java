package afluentes.core.impl;

import afluentes.core.api.IAsynchronousFunction5;
import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator5;

public class AsynchronousEvaluator5<X1, X2, X3, X4, X5, Y> implements IEvaluator5<X1, X2, X3, X4, X5, Y> {
    private final IAsynchronousFunction5<X1, X2, X3, X4, X5, Y> f;

    public AsynchronousEvaluator5(final IAsynchronousFunction5<X1, X2, X3, X4, X5, Y> f) {
        if (f == null) {
            throw new IllegalArgumentException("f == null");
        }
        this.f = f;
    }

    @Override
    public IEvaluation<Y> y(final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3, final IEvaluation<X4> x4, final IEvaluation<X5> x5) {    	
        return new AsynchronousEvaluation5<>(f, x1, x2, x3, x4, x5);
    }
}