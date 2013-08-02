package afluentes.core.impl;

import afluentes.core.api.ISynchronousFunction4;
import afluentes.core.api.IEvaluation;

class SynchronousEvaluation4<X1, X2, X3, X4, Y> extends Evaluation4<X1, X2, X3, X4, Y> {
    private final ISynchronousFunction4<X1, X2, X3, X4, Y> f;

	SynchronousEvaluation4(final ISynchronousFunction4<X1, X2, X3, X4, Y> f, final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3, final IEvaluation<X4> x4) {     
        super(x1, x2, x3, x4);

        this.f = f;
    }

    @Override
    protected void evaluate() {
		try {
            y(f.y(x1.y, x2.y, x3.y, x4.y));
        } catch (final Throwable t) {
            t(t);
        }            
    }
}