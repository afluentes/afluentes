package afluentes.core.impl;

import afluentes.core.api.IAsynchronousFunction0;
import afluentes.core.api.IAsynchronousFunction1;
import afluentes.core.api.IAsynchronousFunction10;
import afluentes.core.api.IAsynchronousFunction11;
import afluentes.core.api.IAsynchronousFunction12;
import afluentes.core.api.IAsynchronousFunction2;
import afluentes.core.api.IAsynchronousFunction3;
import afluentes.core.api.IAsynchronousFunction4;
import afluentes.core.api.IAsynchronousFunction5;
import afluentes.core.api.IAsynchronousFunction6;
import afluentes.core.api.IAsynchronousFunction7;
import afluentes.core.api.IAsynchronousFunction8;
import afluentes.core.api.IAsynchronousFunction9;
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
        } catch (final Throwable t) {
        	try {
        		t(t);
        	} finally {
        		c();
        	}
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
        } catch (final Throwable t) {
        	try {
        		t(t);
        	} finally {
        		c();
        	}
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
        } catch (final Throwable t) {
        	try {
        		t(t);
        	} finally {
        		c();
        	}
        }
    }
}

class AsynchronousEvaluation3<X1, X2, X3, Y> extends Evaluation3<X1, X2, X3, Y> {
    private final IAsynchronousFunction3<X1, X2, X3, Y> f;

    AsynchronousEvaluation3(final IAsynchronousFunction3<X1, X2, X3, Y> f, final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3) {
        super(x1, x2, x3);

        this.f = f;
    }

    @Override
    protected void evaluate() {
        try {
            f.y(x1.y, x2.y, x3.y, this);
        } catch (final Throwable t) {
        	try {
        		t(t);
        	} finally {
        		c();
        	}
        }
    }
}

class AsynchronousEvaluation4<X1, X2, X3, X4, Y> extends Evaluation4<X1, X2, X3, X4, Y> {
    private final IAsynchronousFunction4<X1, X2, X3, X4, Y> f;

    AsynchronousEvaluation4(final IAsynchronousFunction4<X1, X2, X3, X4, Y> f, final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3, final IEvaluation<X4> x4) {
        super(x1, x2, x3, x4);

        this.f = f;
    }

    @Override
    protected void evaluate() {
        try {
            f.y(x1.y, x2.y, x3.y, x4.y, this);
        } catch (final Throwable t) {
        	try {
        		t(t);
        	} finally {
        		c();
        	}
        }
    }
}

class AsynchronousEvaluation5<X1, X2, X3, X4, X5, Y> extends Evaluation5<X1, X2, X3, X4, X5, Y> {
    private final IAsynchronousFunction5<X1, X2, X3, X4, X5, Y> f;

    AsynchronousEvaluation5(final IAsynchronousFunction5<X1, X2, X3, X4, X5, Y> f, final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3, final IEvaluation<X4> x4, final IEvaluation<X5> x5) {
        super(x1, x2, x3, x4, x5);

        this.f = f;
    }

    @Override
    protected void evaluate() {
        try {
            f.y(x1.y, x2.y, x3.y, x4.y, x5.y, this);
        } catch (final Throwable t) {
        	try {
        		t(t);
        	} finally {
        		c();
        	}
        }
    }
}

class AsynchronousEvaluation6<X1, X2, X3, X4, X5, X6, Y> extends Evaluation6<X1, X2, X3, X4, X5, X6, Y> {
    private final IAsynchronousFunction6<X1, X2, X3, X4, X5, X6, Y> f;

    AsynchronousEvaluation6(final IAsynchronousFunction6<X1, X2, X3, X4, X5, X6, Y> f, final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3, final IEvaluation<X4> x4, final IEvaluation<X5> x5, final IEvaluation<X6> x6) {
        super(x1, x2, x3, x4, x5, x6);

        this.f = f;
    }

    @Override
    protected void evaluate() {
        try {
            f.y(x1.y, x2.y, x3.y, x4.y, x5.y, x6.y, this);
        } catch (final Throwable t) {
        	try {
        		t(t);
        	} finally {
        		c();
        	}
        }
    }
}

class AsynchronousEvaluation7<X1, X2, X3, X4, X5, X6, X7, Y> extends Evaluation7<X1, X2, X3, X4, X5, X6, X7, Y> {
    private final IAsynchronousFunction7<X1, X2, X3, X4, X5, X6, X7, Y> f;

    AsynchronousEvaluation7(final IAsynchronousFunction7<X1, X2, X3, X4, X5, X6, X7, Y> f, final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3, final IEvaluation<X4> x4, final IEvaluation<X5> x5, final IEvaluation<X6> x6, final IEvaluation<X7> x7) {
        super(x1, x2, x3, x4, x5, x6, x7);

        this.f = f;
    }

    @Override
    protected void evaluate() {
        try {
            f.y(x1.y, x2.y, x3.y, x4.y, x5.y, x6.y, x7.y, this);
        } catch (final Throwable t) {
        	try {
        		t(t);
        	} finally {
        		c();
        	}
        }
    }
}

class AsynchronousEvaluation8<X1, X2, X3, X4, X5, X6, X7, X8, Y> extends Evaluation8<X1, X2, X3, X4, X5, X6, X7, X8, Y> {
    private final IAsynchronousFunction8<X1, X2, X3, X4, X5, X6, X7, X8, Y> f;

    AsynchronousEvaluation8(final IAsynchronousFunction8<X1, X2, X3, X4, X5, X6, X7, X8, Y> f, final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3, final IEvaluation<X4> x4, final IEvaluation<X5> x5, final IEvaluation<X6> x6, final IEvaluation<X7> x7, final IEvaluation<X8> x8) {
        super(x1, x2, x3, x4, x5, x6, x7, x8);

        this.f = f;
    }

    @Override
    protected void evaluate() {
        try {
            f.y(x1.y, x2.y, x3.y, x4.y, x5.y, x6.y, x7.y, x8.y, this);
        } catch (final Throwable t) {
        	try {
        		t(t);
        	} finally {
        		c();
        	}
        }
    }
}

class AsynchronousEvaluation9<X1, X2, X3, X4, X5, X6, X7, X8, X9, Y> extends Evaluation9<X1, X2, X3, X4, X5, X6, X7, X8, X9, Y> {
    private final IAsynchronousFunction9<X1, X2, X3, X4, X5, X6, X7, X8, X9, Y> f;

    AsynchronousEvaluation9(final IAsynchronousFunction9<X1, X2, X3, X4, X5, X6, X7, X8, X9, Y> f, final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3, final IEvaluation<X4> x4, final IEvaluation<X5> x5, final IEvaluation<X6> x6, final IEvaluation<X7> x7, final IEvaluation<X8> x8, final IEvaluation<X9> x9) {
        super(x1, x2, x3, x4, x5, x6, x7, x8, x9);

        this.f = f;
    }

    @Override
    protected void evaluate() {
        try {
            f.y(x1.y, x2.y, x3.y, x4.y, x5.y, x6.y, x7.y, x8.y, x9.y, this);
        } catch (final Throwable t) {
        	try {
        		t(t);
        	} finally {
        		c();
        	}
        }
    }
}

class AsynchronousEvaluation10<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, Y> extends Evaluation10<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, Y> {
    private final IAsynchronousFunction10<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, Y> f;

    AsynchronousEvaluation10(final IAsynchronousFunction10<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, Y> f, final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3, final IEvaluation<X4> x4, final IEvaluation<X5> x5, final IEvaluation<X6> x6, final IEvaluation<X7> x7, final IEvaluation<X8> x8, final IEvaluation<X9> x9, final IEvaluation<X10> x10) {
        super(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10);

        this.f = f;
    }

    @Override
    protected void evaluate() {
        try {
            f.y(x1.y, x2.y, x3.y, x4.y, x5.y, x6.y, x7.y, x8.y, x9.y, x10.y, this);
        } catch (final Throwable t) {
        	try {
        		t(t);
        	} finally {
        		c();
        	}
        }
    }
}

class AsynchronousEvaluation11<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, X11, Y> extends Evaluation11<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, X11, Y> {
    private final IAsynchronousFunction11<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, X11, Y> f;

    AsynchronousEvaluation11(final IAsynchronousFunction11<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, X11, Y> f, final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3, final IEvaluation<X4> x4, final IEvaluation<X5> x5, final IEvaluation<X6> x6, final IEvaluation<X7> x7, final IEvaluation<X8> x8, final IEvaluation<X9> x9, final IEvaluation<X10> x10, final IEvaluation<X11> x11) {
        super(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11);

        this.f = f;
    }

    @Override
    protected void evaluate() {
        try {
            f.y(x1.y, x2.y, x3.y, x4.y, x5.y, x6.y, x7.y, x8.y, x9.y, x10.y, x11.y, this);
        } catch (final Throwable t) {
        	try {
        		t(t);
        	} finally {
        		c();
        	}
        }
    }
}

class AsynchronousEvaluation12<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, X11, X12, Y> extends Evaluation12<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, X11, X12, Y> {
    private final IAsynchronousFunction12<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, X11, X12, Y> f;

    AsynchronousEvaluation12(final IAsynchronousFunction12<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, X11, X12, Y> f, final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3, final IEvaluation<X4> x4, final IEvaluation<X5> x5, final IEvaluation<X6> x6, final IEvaluation<X7> x7, final IEvaluation<X8> x8, final IEvaluation<X9> x9, final IEvaluation<X10> x10, final IEvaluation<X11> x11, final IEvaluation<X12> x12) {
        super(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12);

        this.f = f;
    }

    @Override
    protected void evaluate() {
        try {
            f.y(x1.y, x2.y, x3.y, x4.y, x5.y, x6.y, x7.y, x8.y, x9.y, x10.y, x11.y, x12.y, this);
        } catch (final Throwable t) {
        	try {
        		t(t);
        	} finally {
        		c();
        	}
        }
    }
}