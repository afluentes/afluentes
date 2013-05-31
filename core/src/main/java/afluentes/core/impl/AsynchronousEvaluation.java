package afluentes.core.impl;

import afluentes.core.api.IAsynchronousFunction0;
import afluentes.core.api.IAsynchronousFunction1;
import afluentes.core.api.IAsynchronousFunction2;
import afluentes.core.api.IEvaluation;

class AsynchronousEvaluation0<Y> extends Evaluation0<Y> {
    private final IAsynchronousFunction0<Y> f;

    AsynchronousEvaluation0(final IAsynchronousFunction0<Y> f) {
        this.f = f;
    }

    @Override
    protected void evaluate() {
        try {
            f.y(this);
        } catch (Throwable t) {
            t(t);
        } finally {
            c();
        }
    }
}

class AsynchronousEvaluation1<X1, Y> extends Evaluation1<X1, Y> {
    private final IAsynchronousFunction1<X1, Y> f;

    AsynchronousEvaluation1(final IAsynchronousFunction1<X1, Y> f, final IEvaluation<X1> x1) {
        super(x1);

        this.f = f;
    }

    @Override
    protected void evaluate() {
        try {
            f.y(x1.y, this);
        } catch (Throwable t) {
            t(t);
        } finally {
            c();
        }
    }
}

class AsynchronousEvaluation2<X1, X2, Y> extends Evaluation2<X1, X2, Y> {
    private final IAsynchronousFunction2<X1, X2, Y> f;

    AsynchronousEvaluation2(final IAsynchronousFunction2<X1, X2, Y> f, final IEvaluation<X1> x1, final IEvaluation<X2> x2) {
        super(x1, x2);

        this.f = f;
    }

    @Override
    protected void evaluate() {
        try {
            f.y(x1.y, x2.y, this);
        } catch (Throwable t) {
            t(t);
        } finally {
            c();
        }
    }
}