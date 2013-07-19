package afluentes.core.impl;

import afluentes.core.api.IAsynchronousFunction10;
import afluentes.core.api.IEvaluation;

class AsynchronousEvaluation10<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, Y> extends Evaluation10<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, Y> {
    private final IAsynchronousFunction10<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, Y> f;

	AsynchronousEvaluation10(final IAsynchronousFunction10<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, Y> f, final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3, final IEvaluation<X4> x4, final IEvaluation<X5> x5, final IEvaluation<X6> x6, final IEvaluation<X7> x7, final IEvaluation<X8> x8, final IEvaluation<X9> x9, final IEvaluation<X10> x10) {     
        super(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10);

        this.f = f;
    }

    @Override
    protected void evaluate() {
        try {
            f.y(x1.y, x2.y, x3.y, x4.y, x5.y, x6.y, x7.y, x8.y, x9.y, x10.y, this);
        } catch (final Throwable t) {
        	try {
        		t(t);
        	} finally {
        		c();
        	}
        }
    }
}