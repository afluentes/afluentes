package afluentes.core.impl;

import afluentes.core.api.IAsynchronousFunction1;
import afluentes.core.api.IEvaluation;

class AsynchronousEvaluation1<X1, Y> extends Evaluation1<X1, Y> {
    private final IAsynchronousFunction1<X1, Y> f;

	AsynchronousEvaluation1(final IAsynchronousFunction1<X1, Y> f, final IEvaluation<X1> x1) {     
        super(x1);

        this.f = f;
    }

    @Override
    protected void evaluate() {
        try {
            f.y(x1.y, this);
        } catch (final Throwable t) {
        	try {
        		t(t);
        	} finally {
        		c();
        	}
        }
    }
}