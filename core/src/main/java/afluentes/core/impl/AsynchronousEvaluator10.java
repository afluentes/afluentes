package afluentes.core.impl;

import afluentes.core.api.IAsynchronousFunction10;
import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator10;

public class AsynchronousEvaluator10<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, Y> implements IEvaluator10<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, Y> {
    private final IAsynchronousFunction10<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, Y> f;

    public AsynchronousEvaluator10(final IAsynchronousFunction10<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, Y> f) {
        if (f == null) {
            throw new IllegalArgumentException("f == null");
        }
        this.f = f;
    }

    @Override
    public IEvaluation<Y> y(final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3, final IEvaluation<X4> x4, final IEvaluation<X5> x5, final IEvaluation<X6> x6, final IEvaluation<X7> x7, final IEvaluation<X8> x8, final IEvaluation<X9> x9, final IEvaluation<X10> x10) {    	
        return new AsynchronousEvaluation10<>(f, x1, x2, x3, x4, x5, x6, x7, x8, x9, x10);
    }
}