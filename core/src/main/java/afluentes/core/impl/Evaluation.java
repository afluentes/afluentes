package afluentes.core.impl;

import afluentes.core.api.ICallback;
import afluentes.core.api.IEvaluation;

import java.util.Stack;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

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
            Stack<Evaluation<?>> stack = new Stack<Evaluation<?>>();
            queue = new LinkedBlockingQueue<Evaluation<?>>();
            while (status != EVALUATED) {
                stack.push(this);
                while (!stack.isEmpty()) {
                    Evaluation<?> evaluation = stack.pop();
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
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (evaluation.t != null) {
                        throw new RuntimeException(evaluation.t);
                    }
                    while (!queue.isEmpty()) {
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

    protected abstract void pushArguments(Stack<Evaluation<?>> stack);

    protected abstract void evaluate();

    protected abstract void clearArguments();

    @Override
    public void y(Y y) {
        this.y = y;
    }

    @Override
    public void t(Throwable t) {
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
    protected void pushArguments(Stack<Evaluation<?>> stack) {}

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
    protected void pushArguments(Stack<Evaluation<?>> stack) {
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
    protected void pushArguments(Stack<Evaluation<?>> stack) {
        stack.push(x1);
        stack.push(x2);
    }

    @Override
    protected void clearArguments() {
        x1 = null;
        x2 = null;
    }
}