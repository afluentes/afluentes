package afluentes.core.impl;

import afluentes.core.api.IAsynchronousFunction8;
import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator8;

public class AsynchronousEvaluator8<X1, X2, X3, X4, X5, X6, X7, X8, Y> implements IEvaluator8<X1, X2, X3, X4, X5, X6, X7, X8, Y> {
    private final IAsynchronousFunction8<X1, X2, X3, X4, X5, X6, X7, X8, Y> f;

    public AsynchronousEvaluator8(final IAsynchronousFunction8<X1, X2, X3, X4, X5, X6, X7, X8, Y> f) {
        if (f == null) {
            throw new IllegalArgumentException("f == null");
        }
        this.f = f;
    }

    @Override
    public IEvaluation<Y> y(final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3, final IEvaluation<X4> x4, final IEvaluation<X5> x5, final IEvaluation<X6> x6, final IEvaluation<X7> x7, final IEvaluation<X8> x8) {
        return new AsynchronousEvaluation8<>(f, x1, x2, x3, x4, x5, x6, x7, x8);
    }
}