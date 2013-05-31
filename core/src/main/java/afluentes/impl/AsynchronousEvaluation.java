package afluentes.impl;

import afluentes.api.IAsynchronousFunction0;
import afluentes.api.IAsynchronousFunction1;
import afluentes.api.IAsynchronousFunction2;
import afluentes.api.IEvaluation;

class AsynchronousEvaluation0<Y> extends Evaluation0<Y> {
    private final IAsynchronousFunction0<Y> f;

    public AsynchronousEvaluation0(final IAsynchronousFunction0<Y> f) {
        this.f = f;
    }
}

class AsynchronousEvaluation1<X1, Y> extends Evaluation1<X1, Y> {
    private final IAsynchronousFunction1<X1, Y> f;

    public AsynchronousEvaluation1(final IAsynchronousFunction1<X1, Y> f, final IEvaluation<X1> x1) {
        super(x1);

        this.f = f;
    }
}

class AsynchronousEvaluation2<X1, X2, Y> extends Evaluation2<X1, X2, Y> {
    private final IAsynchronousFunction2<X1, X2, Y> f;

    public AsynchronousEvaluation2(final IAsynchronousFunction2<X1, X2, Y> f, final IEvaluation<X1> x1, final IEvaluation<X2> x2) {
        super(x1, x2);

        this.f = f;
    }
}