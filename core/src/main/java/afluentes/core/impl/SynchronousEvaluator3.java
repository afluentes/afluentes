package afluentes.core.impl;

import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator3;
import afluentes.core.api.ISynchronousFunction3;

public class SynchronousEvaluator3<X1, X2, X3, Y> implements IEvaluator3<X1, X2, X3, Y> {
    private final ISynchronousFunction3<X1, X2, X3, Y> f;

    public SynchronousEvaluator3(final ISynchronousFunction3<X1, X2, X3, Y> f) {
        if (f == null) {
            throw new IllegalArgumentException("f == null");
        }
        this.f = f;
    }

    @Override
    public IEvaluation<Y> y(final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3) {
        return new SynchronousEvaluation3<>(f, x1, x2, x3);
    }
}