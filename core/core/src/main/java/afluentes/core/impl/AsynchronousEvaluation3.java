package afluentes.core.impl;

import afluentes.core.api.IAsynchronousFunction3;
import afluentes.core.api.IEvaluation;

class AsynchronousEvaluation3<X1, X2, X3, Y> extends Evaluation3<X1, X2, X3, Y> {
    private final IAsynchronousFunction3<X1, X2, X3, Y> f;

	AsynchronousEvaluation3(final IAsynchronousFunction3<X1, X2, X3, Y> f, final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3) {     
        super(x1, x2, x3);

        this.f = f;
    }

    @Override
    protected void evaluate() {
        try {
            f.y(x1.y, x2.y, x3.y, this);
        } catch (final Throwable t) {
        	try {
        		t(t);
        	} finally {
        		c();
        	}
        }
    }
}