package afluentes.impl;

import afluentes.api.IAsynchronousFunction1;
import afluentes.api.IEvaluation;
import afluentes.api.IEvaluator1;

public class AsynchronousEvaluator1<X1, Y> implements IEvaluator1<X1, Y> {
    private final IAsynchronousFunction1<X1, Y> f;

    public AsynchronousEvaluator1(final IAsynchronousFunction1<X1, Y> f) {
        if (f == null) {
            throw new IllegalArgumentException("f == null");
        }
        this.f = f;
    }

    @Override
    public IEvaluation<Y> y(IEvaluation<X1> x1) {
        return new AsynchronousEvaluation1<>(f, x1);
    }
}