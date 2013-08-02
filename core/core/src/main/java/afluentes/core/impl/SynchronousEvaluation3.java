package afluentes.core.impl;

import afluentes.core.api.ISynchronousFunction3;
import afluentes.core.api.IEvaluation;

class SynchronousEvaluation3<X1, X2, X3, Y> extends Evaluation3<X1, X2, X3, Y> {
    private final ISynchronousFunction3<X1, X2, X3, Y> f;

	SynchronousEvaluation3(final ISynchronousFunction3<X1, X2, X3, Y> f, final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3) {     
        super(x1, x2, x3);

        this.f = f;
    }

    @Override
    protected void evaluate() {
		try {
            y(f.y(x1.y, x2.y, x3.y));
        } catch (final Throwable t) {
            t(t);
        }            
    }
}