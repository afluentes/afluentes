package afluentes.core.impl;

import afluentes.core.api.IAsynchronousFunction7;
import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator7;

public class AsynchronousEvaluator7<X1, X2, X3, X4, X5, X6, X7, Y> implements IEvaluator7<X1, X2, X3, X4, X5, X6, X7, Y> {
    private final IAsynchronousFunction7<X1, X2, X3, X4, X5, X6, X7, Y> f;

    public AsynchronousEvaluator7(final IAsynchronousFunction7<X1, X2, X3, X4, X5, X6, X7, Y> f) {
        if (f == null) {
            throw new IllegalArgumentException("f == null");
        }
        this.f = f;
    }

    @Override
    public IEvaluation<Y> y(final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3, final IEvaluation<X4> x4, final IEvaluation<X5> x5, final IEvaluation<X6> x6, final IEvaluation<X7> x7) {    	
        return new AsynchronousEvaluation7<>(f, x1, x2, x3, x4, x5, x6, x7);
    }
}