package afluentes.core.impl;

import afluentes.core.api.IAsynchronousFunction2;
import afluentes.core.api.IEvaluation;

class AsynchronousEvaluation2<X1, X2, Y> extends Evaluation2<X1, X2, Y> {
    private final IAsynchronousFunction2<X1, X2, Y> f;

	AsynchronousEvaluation2(final IAsynchronousFunction2<X1, X2, Y> f, final IEvaluation<X1> x1, final IEvaluation<X2> x2) {     
        super(x1, x2);

        this.f = f;
    }

    @Override
    protected void evaluate() {
        try {
            f.y(x1.y, x2.y, this);
        } catch (final Throwable t) {
        	try {
        		t(t);
        	} finally {
        		c();
        	}
        }
    }
}