package afluentes.impl;

import afluentes.api.IEvaluation;
import afluentes.api.IEvaluator2;
import afluentes.api.ISynchronousFunction2;

public class SynchronousEvaluator2<X1, X2, Y> implements IEvaluator2<X1, X2, Y> {
    private final ISynchronousFunction2<X1, X2, Y> f;

    public SynchronousEvaluator2(final ISynchronousFunction2<X1, X2, Y> f) {
        if (f == null) {
            throw new IllegalArgumentException("f == null");
        }
        this.f = f;
    }

    @Override
    public IEvaluation<Y> y(IEvaluation<X1> x1, IEvaluation<X2> x2) {
        return new SynchronousEvaluation2<>(f, x1, x2);
    }
}