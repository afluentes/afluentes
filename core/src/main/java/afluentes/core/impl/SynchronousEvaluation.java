package afluentes.core.impl;

import afluentes.core.api.IEvaluation;
import afluentes.core.api.ISynchronousFunction0;
import afluentes.core.api.ISynchronousFunction1;
import afluentes.core.api.ISynchronousFunction10;
import afluentes.core.api.ISynchronousFunction11;
import afluentes.core.api.ISynchronousFunction12;
import afluentes.core.api.ISynchronousFunction2;
import afluentes.core.api.ISynchronousFunction3;
import afluentes.core.api.ISynchronousFunction4;
import afluentes.core.api.ISynchronousFunction5;
import afluentes.core.api.ISynchronousFunction6;
import afluentes.core.api.ISynchronousFunction7;
import afluentes.core.api.ISynchronousFunction8;
import afluentes.core.api.ISynchronousFunction9;

class SynchronousEvaluation0<Y> extends Evaluation0<Y> {
    private final ISynchronousFunction0<Y> f;

    SynchronousEvaluation0(final ISynchronousFunction0<Y> f) {
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

class SynchronousEvaluation2<X1, X2, Y> extends Evaluation2<X1, X2, Y> {
    private final ISynchronousFunction2<X1, X2, Y> f;

    SynchronousEvaluation2(final ISynchronousFunction2<X1, X2, Y> f, final IEvaluation<X1> x1, final IEvaluation<X2> x2) {
        super(x1, x2);

        this.f = f;
    }

    @Override
    protected void evaluate() {
        try {
            y(f.y(x1.y, x2.y));
        } catch (final Throwable t) {
            t(t);
        }
    }
}

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

class SynchronousEvaluation4<X1, X2, X3, X4, Y> extends Evaluation4<X1, X2, X3, X4, Y> {
    private final ISynchronousFunction4<X1, X2, X3, X4, Y> f;

    SynchronousEvaluation4(final ISynchronousFunction4<X1, X2, X3, X4, Y> f, final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3, final IEvaluation<X4> x4) {
       super(x1, x2, x3, x4);

        this.f = f;
    }

    @Override
    protected void evaluate() {
        try {
            y(f.y(x1.y, x2.y, x3.y, x4.y));
        } catch (final Throwable t) {
            t(t);
        }
    }
}

class SynchronousEvaluation5<X1, X2, X3, X4, X5, Y> extends Evaluation5<X1, X2, X3, X4, X5, Y> {
    private final ISynchronousFunction5<X1, X2, X3, X4, X5, Y> f;

    SynchronousEvaluation5(final ISynchronousFunction5<X1, X2, X3, X4, X5, Y> f, final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3, final IEvaluation<X4> x4, final IEvaluation<X5> x5) {
       super(x1, x2, x3, x4, x5);

        this.f = f;
    }

    @Override
    protected void evaluate() {
        try {
            y(f.y(x1.y, x2.y, x3.y, x4.y, x5.y));
        } catch (final Throwable t) {
            t(t);
        }
    }
}

class SynchronousEvaluation6<X1, X2, X3, X4, X5, X6, Y> extends Evaluation6<X1, X2, X3, X4, X5, X6, Y> {
    private final ISynchronousFunction6<X1, X2, X3, X4, X5, X6, Y> f;

    SynchronousEvaluation6(final ISynchronousFunction6<X1, X2, X3, X4, X5, X6, Y> f, final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3, final IEvaluation<X4> x4, final IEvaluation<X5> x5, final IEvaluation<X6> x6) {
       super(x1, x2, x3, x4, x5, x6);

        this.f = f;
    }

    @Override
    protected void evaluate() {
        try {
            y(f.y(x1.y, x2.y, x3.y, x4.y, x5.y, x6.y));
        } catch (final Throwable t) {
            t(t);
        }
    }
}

class SynchronousEvaluation7<X1, X2, X3, X4, X5, X6, X7, Y> extends Evaluation7<X1, X2, X3, X4, X5, X6, X7, Y> {
    private final ISynchronousFunction7<X1, X2, X3, X4, X5, X6, X7, Y> f;

    SynchronousEvaluation7(final ISynchronousFunction7<X1, X2, X3, X4, X5, X6, X7, Y> f, final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3, final IEvaluation<X4> x4, final IEvaluation<X5> x5, final IEvaluation<X6> x6, final IEvaluation<X7> x7) {
       super(x1, x2, x3, x4, x5, x6, x7);

        this.f = f;
    }

    @Override
    protected void evaluate() {
        try {
            y(f.y(x1.y, x2.y, x3.y, x4.y, x5.y, x6.y, x7.y));
        } catch (final Throwable t) {
            t(t);
        }
    }
}

class SynchronousEvaluation8<X1, X2, X3, X4, X5, X6, X7, X8, Y> extends Evaluation8<X1, X2, X3, X4, X5, X6, X7, X8, Y> {
    private final ISynchronousFunction8<X1, X2, X3, X4, X5, X6, X7, X8, Y> f;

    SynchronousEvaluation8(final ISynchronousFunction8<X1, X2, X3, X4, X5, X6, X7, X8, Y> f, final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3, final IEvaluation<X4> x4, final IEvaluation<X5> x5, final IEvaluation<X6> x6, final IEvaluation<X7> x7, final IEvaluation<X8> x8) {
       super(x1, x2, x3, x4, x5, x6, x7, x8);

        this.f = f;
    }

    @Override
    protected void evaluate() {
        try {
            y(f.y(x1.y, x2.y, x3.y, x4.y, x5.y, x6.y, x7.y, x8.y));
        } catch (final Throwable t) {
            t(t);
        }
    }
}

class SynchronousEvaluation9<X1, X2, X3, X4, X5, X6, X7, X8, X9, Y> extends Evaluation9<X1, X2, X3, X4, X5, X6, X7, X8, X9, Y> {
    private final ISynchronousFunction9<X1, X2, X3, X4, X5, X6, X7, X8, X9, Y> f;

    SynchronousEvaluation9(final ISynchronousFunction9<X1, X2, X3, X4, X5, X6, X7, X8, X9, Y> f, final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3, final IEvaluation<X4> x4, final IEvaluation<X5> x5, final IEvaluation<X6> x6, final IEvaluation<X7> x7, final IEvaluation<X8> x8, final IEvaluation<X9> x9) {
       super(x1, x2, x3, x4, x5, x6, x7, x8, x9);

        this.f = f;
    }

    @Override
    protected void evaluate() {
        try {
            y(f.y(x1.y, x2.y, x3.y, x4.y, x5.y, x6.y, x7.y, x8.y, x9.y));
        } catch (final Throwable t) {
            t(t);
        }
    }
}

class SynchronousEvaluation10<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, Y> extends Evaluation10<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, Y> {
    private final ISynchronousFunction10<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, Y> f;

    SynchronousEvaluation10(final ISynchronousFunction10<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, Y> f, final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3, final IEvaluation<X4> x4, final IEvaluation<X5> x5, final IEvaluation<X6> x6, final IEvaluation<X7> x7, final IEvaluation<X8> x8, final IEvaluation<X9> x9, final IEvaluation<X10> x10) {
       super(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10);

        this.f = f;
    }

    @Override
    protected void evaluate() {
        try {
            y(f.y(x1.y, x2.y, x3.y, x4.y, x5.y, x6.y, x7.y, x8.y, x9.y, x10.y));
        } catch (final Throwable t) {
            t(t);
        }
    }
}

class SynchronousEvaluation11<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, X11, Y> extends Evaluation11<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, X11, Y> {
    private final ISynchronousFunction11<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, X11, Y> f;

    SynchronousEvaluation11(final ISynchronousFunction11<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, X11, Y> f, final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3, final IEvaluation<X4> x4, final IEvaluation<X5> x5, final IEvaluation<X6> x6, final IEvaluation<X7> x7, final IEvaluation<X8> x8, final IEvaluation<X9> x9, final IEvaluation<X10> x10, final IEvaluation<X11> x11) {
       super(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11);

        this.f = f;
    }

    @Override
    protected void evaluate() {
        try {
            y(f.y(x1.y, x2.y, x3.y, x4.y, x5.y, x6.y, x7.y, x8.y, x9.y, x10.y, x11.y));
        } catch (final Throwable t) {
            t(t);
        }
    }
}

class SynchronousEvaluation12<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, X11, X12, Y> extends Evaluation12<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, X11, X12, Y> {
    private final ISynchronousFunction12<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, X11, X12, Y> f;

    SynchronousEvaluation12(final ISynchronousFunction12<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, X11, X12, Y> f, final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3, final IEvaluation<X4> x4, final IEvaluation<X5> x5, final IEvaluation<X6> x6, final IEvaluation<X7> x7, final IEvaluation<X8> x8, final IEvaluation<X9> x9, final IEvaluation<X10> x10, final IEvaluation<X11> x11, final IEvaluation<X12> x12) {
       super(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12);

        this.f = f;
    }

    @Override
    protected void evaluate() {
        try {
            y(f.y(x1.y, x2.y, x3.y, x4.y, x5.y, x6.y, x7.y, x8.y, x9.y, x10.y, x11.y, x12.y));
        } catch (final Throwable t) {
            t(t);
        }
    }
}