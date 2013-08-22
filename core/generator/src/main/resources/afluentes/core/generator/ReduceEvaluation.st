ReduceEvaluation() ::=

<<package afluentes.core.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import afluentes.core.api.IEvaluation;
import afluentes.core.api.ISynchronousFunction1;

class ReduceEvaluation<X1, Y> extends Evaluation<Y> {
	private final ISynchronousFunction1<List<X1>, Y> f;
	private List<Evaluation<X1\>> x1s;

	@SuppressWarnings("unchecked")
	ReduceEvaluation(final ISynchronousFunction1<List<X1>, Y> f, final List<? extends IEvaluation<X1\>> x1s) {
		this.f = f;

    	if (x1s == null) {
    		throw new IllegalArgumentException("x1s == null");
    	}
    	this.x1s = (List<Evaluation<X1\>>) x1s;
	}

	@Override
	protected boolean isEvaluable() {
		for (Evaluation<X1> x1 : x1s) {
			if (x1.status != EVALUATED) {
				return false;
			}
		}
		return true;
	}

	@Override
	protected void pushArguments(Stack<Evaluation<?\>> stack) {
		for (Evaluation<X1> x1 : x1s) {
			stack.push(x1);
		}
	}

	@Override
	protected void evaluate() {
		List<X1> ys = new ArrayList<>(x1s.size());
		for (Evaluation<X1> x1 : x1s) {
			ys.add(x1.y);
		}
		try {
            y(f.y(ys));
		} catch (final Throwable t) {
            t(t);
        }
	}

	@Override
	protected void clearArguments() {
		x1s = null;
	}
}>>