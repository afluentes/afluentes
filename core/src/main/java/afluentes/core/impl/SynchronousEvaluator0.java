package afluentes.core.impl;

import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator0;
import afluentes.core.api.ISynchronousFunction0;

public class SynchronousEvaluator0<Y> implements IEvaluator0<Y> {
    private final ISynchronousFunction0<Y> f;

    public SynchronousEvaluator0(final ISynchronousFunction0<Y> f) {
        if (f == null) {
            throw new IllegalArgumentException("f == null");
        }
        this.f = f;
    }

    @Override
    public IEvaluation<Y> y() {
        return new SynchronousEvaluation0<Y>(f);
    }
}