package afluentes.core.impl;

import afluentes.core.api.IAsynchronousFunction0;

class AsynchronousEvaluation0<Y> extends Evaluation0<Y> {
    private final IAsynchronousFunction0<Y> f;

	AsynchronousEvaluation0(final IAsynchronousFunction0<Y> f) {     
        super();

        this.f = f;
    }

    @Override
    protected void evaluate() {
        try {
            f.y(this);
        } catch (final Throwable t) {
        	try {
        		t(t);
        	} finally {
        		c();
        	}
        }
    }
}