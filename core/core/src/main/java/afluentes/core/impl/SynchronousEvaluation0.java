package afluentes.core.impl;

import afluentes.core.api.ISynchronousFunction0;

class SynchronousEvaluation0<Y> extends Evaluation0<Y> {
    private final ISynchronousFunction0<Y> f;

	SynchronousEvaluation0(final ISynchronousFunction0<Y> f) {     
        super();

        this.f = f;
    }

    @Override
    protected void evaluate() {
		try {
            y(f.y());
        } catch (final Throwable t) {
            t(t);
        }            
    }
}