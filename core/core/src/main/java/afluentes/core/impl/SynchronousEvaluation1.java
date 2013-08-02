package afluentes.core.impl;

import afluentes.core.api.ISynchronousFunction1;
import afluentes.core.api.IEvaluation;

class SynchronousEvaluation1<X1, Y> extends Evaluation1<X1, Y> {
    private final ISynchronousFunction1<X1, Y> f;

	SynchronousEvaluation1(final ISynchronousFunction1<X1, Y> f, final IEvaluation<X1> x1) {     
        super(x1);

        this.f = f;
    }

    @Override
    protected void evaluate() {
		try {
            y(f.y(x1.y));
        } catch (final Throwable t) {
            t(t);
        }            
    }
}