package afluentes.impl;

import afluentes.api.IEvaluation;
import afluentes.api.ISynchronousFunction0;
import afluentes.api.ISynchronousFunction1;
import afluentes.api.ISynchronousFunction2;

class SynchronousEvaluation0<Y> extends Evaluation0<Y> {
    private final ISynchronousFunction0<Y> f;

    public SynchronousEvaluation0(final ISynchronousFunction0<Y> f) {
        this.f = f;
    }
}

class SynchronousEvaluation1<X1, Y> extends Evaluation1<X1, Y> {
    private final ISynchronousFunction1<X1, Y> f;

    public SynchronousEvaluation1(final ISynchronousFunction1<X1, Y> f, final IEvaluation<X1> x1) {
        super(x1);

        this.f = f;
    }
}

class SynchronousEvaluation2<X1, X2, Y> extends Evaluation2<X1, X2, Y> {
    private final ISynchronousFunction2<X1, X2, Y> f;

    public SynchronousEvaluation2(final ISynchronousFunction2<X1, X2, Y> f, final IEvaluation<X1> x1, final IEvaluation<X2> x2) {
        super(x1, x2);

        this.f = f;
    }
}