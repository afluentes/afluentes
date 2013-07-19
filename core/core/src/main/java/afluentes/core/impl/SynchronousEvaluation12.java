package afluentes.core.impl;

import afluentes.core.api.IEvaluation;
import afluentes.core.api.ISynchronousFunction12;

class SynchronousEvaluation12<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, X11, X12, Y> extends Evaluation12<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, X11, X12, Y> {
    private final ISynchronousFunction12<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, X11, X12, Y> f;

	SynchronousEvaluation12(final ISynchronousFunction12<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, X11, X12, Y> f, final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3, final IEvaluation<X4> x4, final IEvaluation<X5> x5, final IEvaluation<X6> x6, final IEvaluation<X7> x7, final IEvaluation<X8> x8, final IEvaluation<X9> x9, final IEvaluation<X10> x10, final IEvaluation<X11> x11, final IEvaluation<X12> x12) {     
        super(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12);

        this.f = f;
    }

    @Override
    protected void evaluate() {
		try {
            y(f.y(x1.y, x2.y, x3.y, x4.y, x5.y, x6.y, x7.y, x8.y, x9.y, x10.y, x11.y, x12.y));
        } catch (final Throwable t) {
            t(t);
        }            
    }
}