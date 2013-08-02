package afluentes.core.impl;

import afluentes.core.api.ISynchronousFunction6;
import afluentes.core.api.IEvaluation;

class SynchronousEvaluation6<X1, X2, X3, X4, X5, X6, Y> extends Evaluation6<X1, X2, X3, X4, X5, X6, Y> {
    private final ISynchronousFunction6<X1, X2, X3, X4, X5, X6, Y> f;

	SynchronousEvaluation6(final ISynchronousFunction6<X1, X2, X3, X4, X5, X6, Y> f, final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3, final IEvaluation<X4> x4, final IEvaluation<X5> x5, final IEvaluation<X6> x6) {     
        super(x1, x2, x3, x4, x5, x6);

        this.f = f;
    }

    @Override
    protected void evaluate() {
		try {
            y(f.y(x1.y, x2.y, x3.y, x4.y, x5.y, x6.y));
        } catch (final Throwable t) {
            t(t);
        }            
    }
}