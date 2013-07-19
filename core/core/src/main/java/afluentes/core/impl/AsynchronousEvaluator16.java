package afluentes.core.impl;

import afluentes.core.api.IAsynchronousFunction16;
import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator16;

public class AsynchronousEvaluator16<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, X11, X12, X13, X14, X15, X16, Y> implements IEvaluator16<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, X11, X12, X13, X14, X15, X16, Y> {
    private final IAsynchronousFunction16<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, X11, X12, X13, X14, X15, X16, Y> f;

    public AsynchronousEvaluator16(final IAsynchronousFunction16<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, X11, X12, X13, X14, X15, X16, Y> f) {
        if (f == null) {
            throw new IllegalArgumentException("f == null");
        }
        this.f = f;
    }

    @Override
    public IEvaluation<Y> y(final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3, final IEvaluation<X4> x4, final IEvaluation<X5> x5, final IEvaluation<X6> x6, final IEvaluation<X7> x7, final IEvaluation<X8> x8, final IEvaluation<X9> x9, final IEvaluation<X10> x10, final IEvaluation<X11> x11, final IEvaluation<X12> x12, final IEvaluation<X13> x13, final IEvaluation<X14> x14, final IEvaluation<X15> x15, final IEvaluation<X16> x16) {
        return new AsynchronousEvaluation16<>(f, x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13, x14, x15, x16);
    }
}