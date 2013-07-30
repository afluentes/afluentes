Evaluation() ::= 

<<package afluentes.core.impl;

import java.util.Stack;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

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

    private volatile BlockingQueue<Evaluation<?\>> queue;
    private volatile Throwable t;

    Evaluation() {
        status = UNEVALUATED;
    }
    
    @Override
    public Y y(long timeout, TimeUnit unit) {
        if (status == EVALUATING) {
            throw new IllegalStateException("status == Status.EVALUATING");
        }
        if (status == UNEVALUATED) {
            final Stack<Evaluation<?\>> stack = new Stack<Evaluation<?\>>();
            final LinkedBlockingQueue<Evaluation<?\>> queue = new LinkedBlockingQueue<>();
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
                        evaluation = queue.poll(timeout, unit);
                        if (evaluation == null) {
                        	throw new RuntimeException("Timed out");
                        }
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

    protected abstract void pushArguments(final Stack<Evaluation<?\>> stack);

    protected abstract void evaluate();

    protected abstract void clearArguments();

    @Override
	public IEvaluation<Void> then(final IAsynchronousRunnable r) {
		if (r == null) {
			throw new IllegalArgumentException("r == null");
		}
		final IAsynchronousFunction0<Void> f = new IAsynchronousRunnableAdapter(r);
		return new AsynchronousEvaluation1<>(new IAsynchronousFunction0Adapter<Y>(f), this);
	}

    @Override
	public IEvaluation<Void> then(final Runnable r) {
		if (r == null) {
			throw new IllegalArgumentException("r == null");
		}
		final ISynchronousFunction0<Void> f = new RunnableAdapter(r);
		return new SynchronousEvaluation1<>(new ISynchronousFunction0Adapter<Y>(f), this);
    }

    @Override
    public void y(final Y y) {
        this.y = y;
        c();
    }

    @Override
    public void t(final Throwable t) {
        this.t = t;
        c();
    }

    protected void c() {
        status = EVALUATED;
        try {
            queue.add(this);
        } finally {
            queue = null;
        }
    }
}>>