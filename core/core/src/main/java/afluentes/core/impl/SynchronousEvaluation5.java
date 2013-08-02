package afluentes.core.impl;

import afluentes.core.api.ISynchronousFunction5;
import afluentes.core.api.IEvaluation;

class SynchronousEvaluation5<X1, X2, X3, X4, X5, Y> extends Evaluation5<X1, X2, X3, X4, X5, Y> {
    private final ISynchronousFunction5<X1, X2, X3, X4, X5, Y> f;

	SynchronousEvaluation5(final ISynchronousFunction5<X1, X2, X3, X4, X5, Y> f, final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3, final IEvaluation<X4> x4, final IEvaluation<X5> x5) {     
        super(x1, x2, x3, x4, x5);

        this.f = f;
    }

    @Override
    protected void evaluate() {
		try {
            y(f.y(x1.y, x2.y, x3.y, x4.y, x5.y));
        } catch (final Throwable t) {
            t(t);
        }            
    }
}