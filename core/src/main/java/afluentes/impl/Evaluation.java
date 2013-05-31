package afluentes.impl;

import afluentes.api.IEvaluation;

abstract class Evaluation<Y> implements IEvaluation<Y> {
    @Override
    public Y y() {
        throw new UnsupportedOperationException();
    }
}

class Evaluation0<Y> extends Evaluation<Y> {
}

class Evaluation1<X1, Y> extends Evaluation<Y> {
    private final Evaluation<X1> x1;

    Evaluation1(final IEvaluation<X1> x1) {
        if (x1 == null) {
            throw new IllegalArgumentException("x1 == null");
        }
        this.x1 = (Evaluation<X1>) x1;
    }
}

class Evaluation2<X1, X2, Y> extends Evaluation<Y> {
    private final Evaluation<X1> x1;
    private final Evaluation<X2> x2;

    Evaluation2(final IEvaluation<X1> x1, final IEvaluation<X2> x2) {
        if (x1 == null) {
            throw new IllegalArgumentException("x1 == null");
        }
        if (x2 == null) {
            throw new IllegalArgumentException("x2 == null");
        }
        this.x1 = (Evaluation<X1>) x1;
        this.x2 = (Evaluation<X2>) x2;
    }
}