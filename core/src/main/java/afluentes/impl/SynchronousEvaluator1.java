package afluentes.impl;

import afluentes.api.IEvaluation;
import afluentes.api.IEvaluator1;
import afluentes.api.ISynchronousFunction1;

public class SynchronousEvaluator1<X1, Y> implements IEvaluator1<X1, Y> {
    private final ISynchronousFunction1<X1, Y> f;

    public SynchronousEvaluator1(final ISynchronousFunction1<X1, Y> f) {
        if (f == null) {
            throw new IllegalArgumentException("f == null");
        }
        this.f = f;
    }

    @Override
    public IEvaluation<Y> y(IEvaluation<X1> x1) {
        return new SynchronousEvaluation1<>(f, x1);
    }
}