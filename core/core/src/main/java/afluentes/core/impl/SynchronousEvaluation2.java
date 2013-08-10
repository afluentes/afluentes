package afluentes.core.impl;

import afluentes.core.api.IEvaluation;
import afluentes.core.api.ISynchronousFunction2;

class SynchronousEvaluation2<X1, X2, Y> extends Evaluation2<X1, X2, Y> {
    private final ISynchronousFunction2<X1, X2, Y> f;

	SynchronousEvaluation2(final ISynchronousFunction2<X1, X2, Y> f, final IEvaluation<X1> x1, final IEvaluation<X2> x2) {     
        super(x1, x2);

        this.f = f;
    }

    @Override
    protected void evaluate() {
		try {
            y(f.y(x1.y, x2.y));
        } catch (final Throwable t) {
            t(t);
        }            
    }
}