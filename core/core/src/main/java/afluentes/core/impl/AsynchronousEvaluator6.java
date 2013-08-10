package afluentes.core.impl;

import afluentes.core.api.IAsynchronousFunction6;
import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator6;

public class AsynchronousEvaluator6<X1, X2, X3, X4, X5, X6, Y> implements IEvaluator6<X1, X2, X3, X4, X5, X6, Y> {
    private final IAsynchronousFunction6<X1, X2, X3, X4, X5, X6, Y> f;

    public AsynchronousEvaluator6(final IAsynchronousFunction6<X1, X2, X3, X4, X5, X6, Y> f) {
        if (f == null) {
            throw new IllegalArgumentException("f == null");
        }
        this.f = f;
    }

    @Override
    public IEvaluation<Y> y(final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3, final IEvaluation<X4> x4, final IEvaluation<X5> x5, final IEvaluation<X6> x6) {
        return new AsynchronousEvaluation6<>(f, x1, x2, x3, x4, x5, x6);
    }
}