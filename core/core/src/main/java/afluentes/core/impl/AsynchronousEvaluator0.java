package afluentes.core.impl;

import afluentes.core.api.IAsynchronousFunction0;
import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator0;

public class AsynchronousEvaluator0<Y> implements IEvaluator0<Y> {
    private final IAsynchronousFunction0<Y> f;

    public AsynchronousEvaluator0(final IAsynchronousFunction0<Y> f) {
        if (f == null) {
            throw new IllegalArgumentException("f == null");
        }
        this.f = f;
    }

    @Override
    public IEvaluation<Y> y() {
        return new AsynchronousEvaluation0<>(f);
    }
}