package afluentes.core.impl;

import java.util.Stack;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import afluentes.core.api.IAsynchronousFunction0;
import afluentes.core.api.IAsynchronousRunnable;
import afluentes.core.api.ICallback;
import afluentes.core.api.IEvaluation;
import afluentes.core.api.ISynchronousFunction0;

abstract class Evaluation<Y> implements IEvaluation<Y>, ICallback<Y> {
    protected static final int UNEVALUATED = 0;
    protected static final int EVALUATING = 1;
    protected static final int EVALUATED = 2;

    protected volatile int status;
    protected volatile Y y;

    private volatile BlockingQueue<Evaluation<?>> queue;
    private volatile Throwable t;

    Evaluation() {
        status = UNEVALUATED;
    }
    
    @Override
    public Y y() {
        if (status == EVALUATING) {
            throw new IllegalStateException("status == Status.EVALUATING");
        }
        if (status == UNEVALUATED) {
            final Stack<Evaluation<?>> stack = new Stack<Evaluation<?>>();
            final LinkedBlockingQueue<Evaluation<?>> queue = new LinkedBlockingQueue<>();
            while (status != EVALUATED) {
                stack.push(this);
                while (!stack.isEmpty()) {
                    final Evaluation<?> evaluation = stack.pop();
                    if (evaluation.status == UNEVALUATED) {
                        if (evaluation.isEvaluable()) {
                            evaluation.status = EVALUATING;
                            evaluation.queue = queue;
                            evaluation.evaluate();
                            evaluation.clearArguments();
                        } else {
                            evaluation.pushArguments(stack);
                        }
                    }
                }
                if (queue != null) {
                    Evaluation<?> evaluation;
                    try {
                        evaluation = queue.take();
                    } catch (final InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (evaluation.t != null) {
                        throw new RuntimeException(evaluation.t);
                    }
                    while (queue != null && !queue.isEmpty()) {
                        evaluation = queue.remove();
                        if (evaluation.t != null) {
                            throw new RuntimeException(evaluation.t);
                        }
                    }
                }
            }
        }
        return y;
    }

    protected abstract boolean isEvaluable();

    protected abstract void pushArguments(final Stack<Evaluation<?>> stack);

    protected abstract void evaluate();

    protected abstract void clearArguments();

    @Override
	public IEvaluation<Void> then(final IAsynchronousRunnable r) {
		if (r == null) {
			throw new IllegalArgumentException("r == null");
		}
		IAsynchronousFunction0<Void> f = new IAsynchronousRunnableAdapter(r);
		return new AsynchronousEvaluation1<>(new IAsynchronousFunction0Adapter<Y>(f), this);
	}

    @Override
	public IEvaluation<Void> then(final Runnable r) {
		if (r == null) {
			throw new IllegalArgumentException("r == null");
		}
		ISynchronousFunction0<Void> f = new RunnableAdapter(r);
		return new SynchronousEvaluation1<>(new ISynchronousFunction0Adapter<Y>(f), this);
    }

    @Override
    public void y(final Y y) {
        this.y = y;
    }

    @Override
    public void t(final Throwable t) {
        this.t = t;
    }

    @Override
    public void c() {
        status = EVALUATED;
        try {
            queue.add(this);
        } finally {
            queue = null;
        }
    }
}

abstract class Evaluation0<Y> extends Evaluation<Y> {	
    @Override
    protected boolean isEvaluable() {
        return true;
    }

    @Override
    protected void pushArguments(final Stack<Evaluation<?>> stack) {}

    @Override
    protected void clearArguments() {}
}

abstract class Evaluation1<X1, Y> extends Evaluation<Y> {
    protected Evaluation<X1> x1;

    Evaluation1(final IEvaluation<X1> x1) {
        if (x1 == null) {
            throw new IllegalArgumentException("x1 == null");
        }
        this.x1 = (Evaluation<X1>) x1;
    }

    @Override
    protected boolean isEvaluable() {
        return x1.status == EVALUATED;
    }

    @Override
    protected void pushArguments(final Stack<Evaluation<?>> stack) {
        stack.push(x1);
    }

    @Override
    protected void clearArguments() {
        x1 = null;
    }
}

abstract class Evaluation2<X1, X2, Y> extends Evaluation<Y> {
    protected Evaluation<X1> x1;
    protected Evaluation<X2> x2;

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

    @Override
    protected boolean isEvaluable() {
        return x1.status == EVALUATED && x2.status == EVALUATED;
    }

    @Override
    protected void pushArguments(final Stack<Evaluation<?>> stack) {
        stack.push(x1);
        stack.push(x2);
    }

    @Override
    protected void clearArguments() {
        x1 = null;
        x2 = null;
    }
}

abstract class Evaluation3<X1, X2, X3, Y> extends Evaluation<Y> {
    protected Evaluation<X1> x1;
    protected Evaluation<X2> x2;
    protected Evaluation<X3> x3;

    Evaluation3(final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3) {
        if (x1 == null) {
            throw new IllegalArgumentException("x1 == null");
        }
        if (x2 == null) {
            throw new IllegalArgumentException("x2 == null");
        }
        if (x3 == null) {
            throw new IllegalArgumentException("x3 == null");
        }
        this.x1 = (Evaluation<X1>) x1;
        this.x2 = (Evaluation<X2>) x2;
        this.x3 = (Evaluation<X3>) x3;
    }

    @Override
    protected boolean isEvaluable() {
        return x1.status == EVALUATED && x2.status == EVALUATED && x3.status == EVALUATED;
    }

    @Override
    protected void pushArguments(final Stack<Evaluation<?>> stack) {
        stack.push(x1);
        stack.push(x2);
        stack.push(x3);
    }

    @Override
    protected void clearArguments() {
        x1 = null;
        x2 = null;
        x3 = null;
    }
}

abstract class Evaluation4<X1, X2, X3, X4, Y> extends Evaluation<Y> {
    protected Evaluation<X1> x1;
    protected Evaluation<X2> x2;
    protected Evaluation<X3> x3;
    protected Evaluation<X4> x4;

    Evaluation4(final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3, final IEvaluation<X4> x4) {
        if (x1 == null) {
            throw new IllegalArgumentException("x1 == null");
        }
        if (x2 == null) {
            throw new IllegalArgumentException("x2 == null");
        }
        if (x3 == null) {
            throw new IllegalArgumentException("x3 == null");
        }
        if (x4 == null) {
            throw new IllegalArgumentException("x4 == null");
        }        
        this.x1 = (Evaluation<X1>) x1;
        this.x2 = (Evaluation<X2>) x2;
        this.x3 = (Evaluation<X3>) x3;
        this.x4 = (Evaluation<X4>) x4;
    }

    @Override
    protected boolean isEvaluable() {
        return x1.status == EVALUATED && x2.status == EVALUATED && x3.status == EVALUATED && x4.status == EVALUATED;
    }

    @Override
    protected void pushArguments(final Stack<Evaluation<?>> stack) {
        stack.push(x1);
        stack.push(x2);
        stack.push(x3);
        stack.push(x4);        
    }

    @Override
    protected void clearArguments() {
        x1 = null;
        x2 = null;
        x3 = null;
        x4 = null;
    }
}

abstract class Evaluation5<X1, X2, X3, X4, X5, Y> extends Evaluation<Y> {
    protected Evaluation<X1> x1;
    protected Evaluation<X2> x2;
    protected Evaluation<X3> x3;
    protected Evaluation<X4> x4;
    protected Evaluation<X5> x5;

    Evaluation5(final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3, final IEvaluation<X4> x4, final IEvaluation<X5> x5) {
        if (x1 == null) {
            throw new IllegalArgumentException("x1 == null");
        }
        if (x2 == null) {
            throw new IllegalArgumentException("x2 == null");
        }
        if (x3 == null) {
            throw new IllegalArgumentException("x3 == null");
        }
        if (x4 == null) {
            throw new IllegalArgumentException("x4 == null");
        }
        if (x5 == null) {
            throw new IllegalArgumentException("x5 == null");
        }                
        this.x1 = (Evaluation<X1>) x1;
        this.x2 = (Evaluation<X2>) x2;
        this.x3 = (Evaluation<X3>) x3;
        this.x4 = (Evaluation<X4>) x4;
        this.x5 = (Evaluation<X5>) x5;
    }

    @Override
    protected boolean isEvaluable() {
        return x1.status == EVALUATED && x2.status == EVALUATED && x3.status == EVALUATED && x4.status == EVALUATED && x5.status == EVALUATED;
    }

    @Override
    protected void pushArguments(final Stack<Evaluation<?>> stack) {
        stack.push(x1);
        stack.push(x2);
        stack.push(x3);
        stack.push(x4);
        stack.push(x5);
    }

    @Override
    protected void clearArguments() {
        x1 = null;
        x2 = null;
        x3 = null;
        x4 = null;
        x5 = null;
    }
}

abstract class Evaluation6<X1, X2, X3, X4, X5, X6, Y> extends Evaluation<Y> {
    protected Evaluation<X1> x1;
    protected Evaluation<X2> x2;
    protected Evaluation<X3> x3;
    protected Evaluation<X4> x4;
    protected Evaluation<X5> x5;
    protected Evaluation<X6> x6;

    Evaluation6(final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3, final IEvaluation<X4> x4, final IEvaluation<X5> x5, final IEvaluation<X6> x6) {
        if (x1 == null) {
            throw new IllegalArgumentException("x1 == null");
        }
        if (x2 == null) {
            throw new IllegalArgumentException("x2 == null");
        }
        if (x3 == null) {
            throw new IllegalArgumentException("x3 == null");
        }
        if (x4 == null) {
            throw new IllegalArgumentException("x4 == null");
        }
        if (x5 == null) {
            throw new IllegalArgumentException("x5 == null");
        }
        if (x6 == null) {
            throw new IllegalArgumentException("x6 == null");
        }                
        this.x1 = (Evaluation<X1>) x1;
        this.x2 = (Evaluation<X2>) x2;
        this.x3 = (Evaluation<X3>) x3;
        this.x4 = (Evaluation<X4>) x4;
        this.x5 = (Evaluation<X5>) x5;
        this.x6 = (Evaluation<X6>) x6;
    }

    @Override
    protected boolean isEvaluable() {
    	return x1.status == EVALUATED && x2.status == EVALUATED && x3.status == EVALUATED && x4.status == EVALUATED && x5.status == EVALUATED && x6.status == EVALUATED;
    }

    @Override
    protected void pushArguments(final Stack<Evaluation<?>> stack) {
        stack.push(x1);
        stack.push(x2);
        stack.push(x3);
        stack.push(x4);
        stack.push(x5);
        stack.push(x6);
    }

    @Override
    protected void clearArguments() {
        x1 = null;
        x2 = null;
        x3 = null;
        x4 = null;
        x5 = null;
        x6 = null;
    }
}

abstract class Evaluation7<X1, X2, X3, X4, X5, X6, X7, Y> extends Evaluation<Y> {
    protected Evaluation<X1> x1;
    protected Evaluation<X2> x2;
    protected Evaluation<X3> x3;
    protected Evaluation<X4> x4;
    protected Evaluation<X5> x5;
    protected Evaluation<X6> x6;
    protected Evaluation<X7> x7;

    Evaluation7(final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3, final IEvaluation<X4> x4, final IEvaluation<X5> x5, final IEvaluation<X6> x6, final IEvaluation<X7> x7) {
        if (x1 == null) {
            throw new IllegalArgumentException("x1 == null");
        }
        if (x2 == null) {
            throw new IllegalArgumentException("x2 == null");
        }
        if (x3 == null) {
            throw new IllegalArgumentException("x3 == null");
        }
        if (x4 == null) {
            throw new IllegalArgumentException("x4 == null");
        }
        if (x5 == null) {
            throw new IllegalArgumentException("x5 == null");
        }
        if (x6 == null) {
            throw new IllegalArgumentException("x6 == null");
        }                
        if (x7 == null) {
            throw new IllegalArgumentException("x7 == null");
        }                
        this.x1 = (Evaluation<X1>) x1;
        this.x2 = (Evaluation<X2>) x2;
        this.x3 = (Evaluation<X3>) x3;
        this.x4 = (Evaluation<X4>) x4;
        this.x5 = (Evaluation<X5>) x5;
        this.x6 = (Evaluation<X6>) x6;
        this.x7 = (Evaluation<X7>) x7;
    }

    @Override
    protected boolean isEvaluable() {
    	return x1.status == EVALUATED && x2.status == EVALUATED && x3.status == EVALUATED && x4.status == EVALUATED && x5.status == EVALUATED && x6.status == EVALUATED && x7.status == EVALUATED;
    }

    @Override
    protected void pushArguments(final Stack<Evaluation<?>> stack) {
        stack.push(x1);
        stack.push(x2);
        stack.push(x3);
        stack.push(x4);
        stack.push(x5);
        stack.push(x6);
        stack.push(x7);
    }

    @Override
    protected void clearArguments() {
        x1 = null;
        x2 = null;
        x3 = null;
        x4 = null;
        x5 = null;
        x6 = null;
        x7 = null;
    }
}

abstract class Evaluation8<X1, X2, X3, X4, X5, X6, X7, X8, Y> extends Evaluation<Y> {
    protected Evaluation<X1> x1;
    protected Evaluation<X2> x2;
    protected Evaluation<X3> x3;
    protected Evaluation<X4> x4;
    protected Evaluation<X5> x5;
    protected Evaluation<X6> x6;
    protected Evaluation<X7> x7;
    protected Evaluation<X8> x8;

    Evaluation8(final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3, final IEvaluation<X4> x4, final IEvaluation<X5> x5, final IEvaluation<X6> x6, final IEvaluation<X7> x7, final IEvaluation<X8> x8) {
        if (x1 == null) {
            throw new IllegalArgumentException("x1 == null");
        }
        if (x2 == null) {
            throw new IllegalArgumentException("x2 == null");
        }
        if (x3 == null) {
            throw new IllegalArgumentException("x3 == null");
        }
        if (x4 == null) {
            throw new IllegalArgumentException("x4 == null");
        }
        if (x5 == null) {
            throw new IllegalArgumentException("x5 == null");
        }
        if (x6 == null) {
            throw new IllegalArgumentException("x6 == null");
        }                
        if (x7 == null) {
            throw new IllegalArgumentException("x7 == null");
        }                
        if (x8 == null) {
            throw new IllegalArgumentException("x8 == null");
        }                
        this.x1 = (Evaluation<X1>) x1;
        this.x2 = (Evaluation<X2>) x2;
        this.x3 = (Evaluation<X3>) x3;
        this.x4 = (Evaluation<X4>) x4;
        this.x5 = (Evaluation<X5>) x5;
        this.x6 = (Evaluation<X6>) x6;
        this.x7 = (Evaluation<X7>) x7;
        this.x8 = (Evaluation<X8>) x8;
    }

    @Override
    protected boolean isEvaluable() {
    	return x1.status == EVALUATED && x2.status == EVALUATED && x3.status == EVALUATED && x4.status == EVALUATED && x5.status == EVALUATED && x6.status == EVALUATED && x7.status == EVALUATED && x8.status == EVALUATED;
    }

    @Override
    protected void pushArguments(final Stack<Evaluation<?>> stack) {
        stack.push(x1);
        stack.push(x2);
        stack.push(x3);
        stack.push(x4);
        stack.push(x5);
        stack.push(x6);
        stack.push(x7);
        stack.push(x8);
    }

    @Override
    protected void clearArguments() {
        x1 = null;
        x2 = null;
        x3 = null;
        x4 = null;
        x5 = null;
        x6 = null;
        x7 = null;
        x8 = null;
    }
}

abstract class Evaluation9<X1, X2, X3, X4, X5, X6, X7, X8, X9, Y> extends Evaluation<Y> {
    protected Evaluation<X1> x1;
    protected Evaluation<X2> x2;
    protected Evaluation<X3> x3;
    protected Evaluation<X4> x4;
    protected Evaluation<X5> x5;
    protected Evaluation<X6> x6;
    protected Evaluation<X7> x7;
    protected Evaluation<X8> x8;
    protected Evaluation<X9> x9;

    Evaluation9(final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3, final IEvaluation<X4> x4, final IEvaluation<X5> x5, final IEvaluation<X6> x6, final IEvaluation<X7> x7, final IEvaluation<X8> x8, final IEvaluation<X9> x9) {
        if (x1 == null) {
            throw new IllegalArgumentException("x1 == null");
        }
        if (x2 == null) {
            throw new IllegalArgumentException("x2 == null");
        }
        if (x3 == null) {
            throw new IllegalArgumentException("x3 == null");
        }
        if (x4 == null) {
            throw new IllegalArgumentException("x4 == null");
        }
        if (x5 == null) {
            throw new IllegalArgumentException("x5 == null");
        }
        if (x6 == null) {
            throw new IllegalArgumentException("x6 == null");
        }                
        if (x7 == null) {
            throw new IllegalArgumentException("x7 == null");
        }                
        if (x8 == null) {
            throw new IllegalArgumentException("x8 == null");
        }                
        if (x9 == null) {
            throw new IllegalArgumentException("x9 == null");
        }                
        this.x1 = (Evaluation<X1>) x1;
        this.x2 = (Evaluation<X2>) x2;
        this.x3 = (Evaluation<X3>) x3;
        this.x4 = (Evaluation<X4>) x4;
        this.x5 = (Evaluation<X5>) x5;
        this.x6 = (Evaluation<X6>) x6;
        this.x7 = (Evaluation<X7>) x7;
        this.x8 = (Evaluation<X8>) x8;
        this.x9 = (Evaluation<X9>) x9;
    }

    @Override
    protected boolean isEvaluable() {
    	return x1.status == EVALUATED && x2.status == EVALUATED && x3.status == EVALUATED && x4.status == EVALUATED && x5.status == EVALUATED && x6.status == EVALUATED && x7.status == EVALUATED && x8.status == EVALUATED && x9.status == EVALUATED;
    }

    @Override
    protected void pushArguments(final Stack<Evaluation<?>> stack) {
        stack.push(x1);
        stack.push(x2);
        stack.push(x3);
        stack.push(x4);
        stack.push(x5);
        stack.push(x6);
        stack.push(x7);
        stack.push(x8);
        stack.push(x9);
    }

    @Override
    protected void clearArguments() {
        x1 = null;
        x2 = null;
        x3 = null;
        x4 = null;
        x5 = null;
        x6 = null;
        x7 = null;
        x8 = null;
        x9 = null;
    }
}

abstract class Evaluation10<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, Y> extends Evaluation<Y> {
    protected Evaluation<X1> x1;
    protected Evaluation<X2> x2;
    protected Evaluation<X3> x3;
    protected Evaluation<X4> x4;
    protected Evaluation<X5> x5;
    protected Evaluation<X6> x6;
    protected Evaluation<X7> x7;
    protected Evaluation<X8> x8;
    protected Evaluation<X9> x9;
    protected Evaluation<X10> x10;

    Evaluation10(final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3, final IEvaluation<X4> x4, final IEvaluation<X5> x5, final IEvaluation<X6> x6, final IEvaluation<X7> x7, final IEvaluation<X8> x8, final IEvaluation<X9> x9, final IEvaluation<X10> x10) {
        if (x1 == null) {
            throw new IllegalArgumentException("x1 == null");
        }
        if (x2 == null) {
            throw new IllegalArgumentException("x2 == null");
        }
        if (x3 == null) {
            throw new IllegalArgumentException("x3 == null");
        }
        if (x4 == null) {
            throw new IllegalArgumentException("x4 == null");
        }
        if (x5 == null) {
            throw new IllegalArgumentException("x5 == null");
        }
        if (x6 == null) {
            throw new IllegalArgumentException("x6 == null");
        }                
        if (x7 == null) {
            throw new IllegalArgumentException("x7 == null");
        }                
        if (x8 == null) {
            throw new IllegalArgumentException("x8 == null");
        }                
        if (x9 == null) {
            throw new IllegalArgumentException("x9 == null");
        }                
        if (x10 == null) {
            throw new IllegalArgumentException("x10 == null");
        }                
        this.x1 = (Evaluation<X1>) x1;
        this.x2 = (Evaluation<X2>) x2;
        this.x3 = (Evaluation<X3>) x3;
        this.x4 = (Evaluation<X4>) x4;
        this.x5 = (Evaluation<X5>) x5;
        this.x6 = (Evaluation<X6>) x6;
        this.x7 = (Evaluation<X7>) x7;
        this.x8 = (Evaluation<X8>) x8;
        this.x9 = (Evaluation<X9>) x9;
        this.x10 = (Evaluation<X10>) x10;
    }

    @Override
    protected boolean isEvaluable() {
    	return x1.status == EVALUATED && x2.status == EVALUATED && x3.status == EVALUATED && x4.status == EVALUATED && x5.status == EVALUATED && x6.status == EVALUATED && x7.status == EVALUATED && x8.status == EVALUATED && x9.status == EVALUATED && x10.status == EVALUATED;
    }

    @Override
    protected void pushArguments(final Stack<Evaluation<?>> stack) {
        stack.push(x1);
        stack.push(x2);
        stack.push(x3);
        stack.push(x4);
        stack.push(x5);
        stack.push(x6);
        stack.push(x7);
        stack.push(x8);
        stack.push(x9);
        stack.push(x10);
    }

    @Override
    protected void clearArguments() {
        x1 = null;
        x2 = null;
        x3 = null;
        x4 = null;
        x5 = null;
        x6 = null;
        x7 = null;
        x8 = null;
        x9 = null;
        x10 = null;
    }
}

abstract class Evaluation11<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, X11, Y> extends Evaluation<Y> {
    protected Evaluation<X1> x1;
    protected Evaluation<X2> x2;
    protected Evaluation<X3> x3;
    protected Evaluation<X4> x4;
    protected Evaluation<X5> x5;
    protected Evaluation<X6> x6;
    protected Evaluation<X7> x7;
    protected Evaluation<X8> x8;
    protected Evaluation<X9> x9;
    protected Evaluation<X10> x10;
    protected Evaluation<X11> x11;

    Evaluation11(final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3, final IEvaluation<X4> x4, final IEvaluation<X5> x5, final IEvaluation<X6> x6, final IEvaluation<X7> x7, final IEvaluation<X8> x8, final IEvaluation<X9> x9, final IEvaluation<X10> x10, final IEvaluation<X11> x11) {
        if (x1 == null) {
            throw new IllegalArgumentException("x1 == null");
        }
        if (x2 == null) {
            throw new IllegalArgumentException("x2 == null");
        }
        if (x3 == null) {
            throw new IllegalArgumentException("x3 == null");
        }
        if (x4 == null) {
            throw new IllegalArgumentException("x4 == null");
        }
        if (x5 == null) {
            throw new IllegalArgumentException("x5 == null");
        }
        if (x6 == null) {
            throw new IllegalArgumentException("x6 == null");
        }                
        if (x7 == null) {
            throw new IllegalArgumentException("x7 == null");
        }                
        if (x8 == null) {
            throw new IllegalArgumentException("x8 == null");
        }                
        if (x9 == null) {
            throw new IllegalArgumentException("x9 == null");
        }                
        if (x10 == null) {
            throw new IllegalArgumentException("x10 == null");
        }                
        if (x11 == null) {
            throw new IllegalArgumentException("x11 == null");
        }                
        this.x1 = (Evaluation<X1>) x1;
        this.x2 = (Evaluation<X2>) x2;
        this.x3 = (Evaluation<X3>) x3;
        this.x4 = (Evaluation<X4>) x4;
        this.x5 = (Evaluation<X5>) x5;
        this.x6 = (Evaluation<X6>) x6;
        this.x7 = (Evaluation<X7>) x7;
        this.x8 = (Evaluation<X8>) x8;
        this.x9 = (Evaluation<X9>) x9;
        this.x10 = (Evaluation<X10>) x10;
        this.x11 = (Evaluation<X11>) x11;
    }

    @Override
    protected boolean isEvaluable() {
    	return x1.status == EVALUATED && x2.status == EVALUATED && x3.status == EVALUATED && x4.status == EVALUATED && x5.status == EVALUATED && x6.status == EVALUATED && x7.status == EVALUATED && x8.status == EVALUATED && x9.status == EVALUATED && x10.status == EVALUATED && x11.status == EVALUATED;
    }

    @Override
    protected void pushArguments(final Stack<Evaluation<?>> stack) {
        stack.push(x1);
        stack.push(x2);
        stack.push(x3);
        stack.push(x4);
        stack.push(x5);
        stack.push(x6);
        stack.push(x7);
        stack.push(x8);
        stack.push(x9);
        stack.push(x10);
        stack.push(x11);
    }

    @Override
    protected void clearArguments() {
        x1 = null;
        x2 = null;
        x3 = null;
        x4 = null;
        x5 = null;
        x6 = null;
        x7 = null;
        x8 = null;
        x9 = null;
        x10 = null;
        x11 = null;
    }
}

abstract class Evaluation12<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, X11, X12, Y> extends Evaluation<Y> {
    protected Evaluation<X1> x1;
    protected Evaluation<X2> x2;
    protected Evaluation<X3> x3;
    protected Evaluation<X4> x4;
    protected Evaluation<X5> x5;
    protected Evaluation<X6> x6;
    protected Evaluation<X7> x7;
    protected Evaluation<X8> x8;
    protected Evaluation<X9> x9;
    protected Evaluation<X10> x10;
    protected Evaluation<X11> x11;
    protected Evaluation<X12> x12;

    Evaluation12(final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3, final IEvaluation<X4> x4, final IEvaluation<X5> x5, final IEvaluation<X6> x6, final IEvaluation<X7> x7, final IEvaluation<X8> x8, final IEvaluation<X9> x9, final IEvaluation<X10> x10, final IEvaluation<X11> x11, final IEvaluation<X12> x12) {
        if (x1 == null) {
            throw new IllegalArgumentException("x1 == null");
        }
        if (x2 == null) {
            throw new IllegalArgumentException("x2 == null");
        }
        if (x3 == null) {
            throw new IllegalArgumentException("x3 == null");
        }
        if (x4 == null) {
            throw new IllegalArgumentException("x4 == null");
        }
        if (x5 == null) {
            throw new IllegalArgumentException("x5 == null");
        }
        if (x6 == null) {
            throw new IllegalArgumentException("x6 == null");
        }                
        if (x7 == null) {
            throw new IllegalArgumentException("x7 == null");
        }                
        if (x8 == null) {
            throw new IllegalArgumentException("x8 == null");
        }                
        if (x9 == null) {
            throw new IllegalArgumentException("x9 == null");
        }                
        if (x10 == null) {
            throw new IllegalArgumentException("x10 == null");
        }                
        if (x11 == null) {
            throw new IllegalArgumentException("x11 == null");
        }                
        if (x12 == null) {
            throw new IllegalArgumentException("x12 == null");
        }                        
        this.x1 = (Evaluation<X1>) x1;
        this.x2 = (Evaluation<X2>) x2;
        this.x3 = (Evaluation<X3>) x3;
        this.x4 = (Evaluation<X4>) x4;
        this.x5 = (Evaluation<X5>) x5;
        this.x6 = (Evaluation<X6>) x6;
        this.x7 = (Evaluation<X7>) x7;
        this.x8 = (Evaluation<X8>) x8;
        this.x9 = (Evaluation<X9>) x9;
        this.x10 = (Evaluation<X10>) x10;
        this.x11 = (Evaluation<X11>) x11;
        this.x12 = (Evaluation<X12>) x12;
    }

    @Override
    protected boolean isEvaluable() {
    	return x1.status == EVALUATED && x2.status == EVALUATED && x3.status == EVALUATED && x4.status == EVALUATED && x5.status == EVALUATED && x6.status == EVALUATED && x7.status == EVALUATED && x8.status == EVALUATED && x9.status == EVALUATED && x10.status == EVALUATED && x11.status == EVALUATED && x12.status == EVALUATED;
    }

    @Override
    protected void pushArguments(final Stack<Evaluation<?>> stack) {
        stack.push(x1);
        stack.push(x2);
        stack.push(x3);
        stack.push(x4);
        stack.push(x5);
        stack.push(x6);
        stack.push(x7);
        stack.push(x8);
        stack.push(x9);
        stack.push(x10);
        stack.push(x11);
        stack.push(x12);
    }

    @Override
    protected void clearArguments() {
        x1 = null;
        x2 = null;
        x3 = null;
        x4 = null;
        x5 = null;
        x6 = null;
        x7 = null;
        x8 = null;
        x9 = null;
        x10 = null;
        x11 = null;
        x12 = null;
    }
}