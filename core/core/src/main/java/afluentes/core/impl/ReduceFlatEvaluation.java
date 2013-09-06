package afluentes.core.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import afluentes.core.api.IEvaluation;
import afluentes.core.api.ISynchronousFunction1;

class ReduceFlatEvaluation<X1, Y> extends Evaluation<Y> {
	private final ISynchronousFunction1<List<X1>, Y> f;
	private Evaluation<? extends List<Evaluation<? extends X1>>> x1s;

	@SuppressWarnings("unchecked")
	ReduceFlatEvaluation(final ISynchronousFunction1<List<X1>, Y> f, final IEvaluation<? extends List<? extends IEvaluation<? extends X1>>> x1s) {
		this.f = f;

    	if (x1s == null) {
    		throw new IllegalArgumentException("x1s == null");
    	}
    	this.x1s = (Evaluation<? extends List<Evaluation<? extends X1>>>) x1s;
	}

	@Override
	protected boolean isEvaluable() {
		if (x1s.status == EVALUATED) {
			for (Evaluation<? extends X1> x1 : x1s.y) {
				if (x1.status != EVALUATED) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected void pushArguments(Stack<Evaluation<?>> stack) {
		if (x1s.status == EVALUATED) {
			for (Evaluation<? extends X1> x1 : x1s.y) {
				stack.push(x1);
			}
		} else {
			stack.push(x1s);
		}
	}

	@Override
	protected void evaluate() {
		if (x1s.status == EVALUATED) {
			List<X1> ys = new ArrayList<>(x1s.y.size());
			for (Evaluation<? extends X1> x1 : x1s.y) {
				ys.add(x1.y);
			}
			try {
	            y(f.y(ys));
			} catch (final Throwable t) {
	            t(t);
	        }						
		} else {
			x1s.evaluate();
		}
	}

	@Override
	protected void clearArguments() {
		x1s = null;
	}
}