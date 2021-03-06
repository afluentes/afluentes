package afluentes.core.impl;

import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator4;
import afluentes.core.api.ISynchronousFunction4;

public class SynchronousEvaluator4<X1, X2, X3, X4, Y> implements IEvaluator4<X1, X2, X3, X4, Y> {
    private final ISynchronousFunction4<X1, X2, X3, X4, Y> f;

    public SynchronousEvaluator4(final ISynchronousFunction4<X1, X2, X3, X4, Y> f) {
        if (f == null) {
            throw new IllegalArgumentException("f == null");
        }
        this.f = f;
    }

    @Override
    public IEvaluation<Y> y(final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3, final IEvaluation<X4> x4) {
        return new SynchronousEvaluation4<>(f, x1, x2, x3, x4);
    }
}