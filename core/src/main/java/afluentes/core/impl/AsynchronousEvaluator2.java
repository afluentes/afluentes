package afluentes.core.impl;

import afluentes.core.api.IAsynchronousFunction2;
import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator2;

public class AsynchronousEvaluator2<X1, X2, Y> implements IEvaluator2<X1, X2, Y> {
    private final IAsynchronousFunction2<X1, X2, Y> f;

    public AsynchronousEvaluator2(final IAsynchronousFunction2<X1, X2, Y> f) {
        if (f == null) {
            throw new IllegalArgumentException("f == null");
        }
        this.f = f;
    }

    @Override
    public IEvaluation<Y> y(IEvaluation<X1> x1, IEvaluation<X2> x2) {
        return new AsynchronousEvaluation2<>(f, x1, x2);
    }
}