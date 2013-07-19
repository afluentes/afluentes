package afluentes.core.impl;

import afluentes.core.api.IAsynchronousFunction5;
import afluentes.core.api.IEvaluation;

class AsynchronousEvaluation5<X1, X2, X3, X4, X5, Y> extends Evaluation5<X1, X2, X3, X4, X5, Y> {
    private final IAsynchronousFunction5<X1, X2, X3, X4, X5, Y> f;

	AsynchronousEvaluation5(final IAsynchronousFunction5<X1, X2, X3, X4, X5, Y> f, final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3, final IEvaluation<X4> x4, final IEvaluation<X5> x5) {     
        super(x1, x2, x3, x4, x5);

        this.f = f;
    }

    @Override
    protected void evaluate() {
        try {
            f.y(x1.y, x2.y, x3.y, x4.y, x5.y, this);
        } catch (final Throwable t) {
        	try {
        		t(t);
        	} finally {
        		c();
        	}
        }
    }
}