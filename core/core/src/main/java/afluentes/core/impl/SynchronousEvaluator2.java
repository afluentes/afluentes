package afluentes.core.impl;

import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator2;
import afluentes.core.api.ISynchronousFunction2;

public class SynchronousEvaluator2<X1, X2, Y> implements IEvaluator2<X1, X2, Y> {
    private final ISynchronousFunction2<X1, X2, Y> f;

    public SynchronousEvaluator2(final ISynchronousFunction2<X1, X2, Y> f) {
        if (f == null) {
            throw new IllegalArgumentException("f == null");
        }
        this.f = f;
    }

    @Override
    public IEvaluation<Y> y(final IEvaluation<X1> x1, final IEvaluation<X2> x2) {
        return new SynchronousEvaluation2<>(f, x1, x2);
    }
}