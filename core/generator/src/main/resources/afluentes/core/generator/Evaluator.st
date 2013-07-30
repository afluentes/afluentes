Evaluator() ::= 

<<package afluentes.core.impl;

import afluentes.core.api.IAsynchronousRunnable;
import afluentes.core.api.IEvaluation;
import afluentes.core.api.ISynchronousFunction2;

public class Evaluator {
	public static IEvaluation<Void> f(final IAsynchronousRunnable r) {
		if (r == null) {
			throw new IllegalArgumentException("r == null");
		}
		return new AsynchronousEvaluation0<>(new IAsynchronousRunnableAdapter(r));
	}

	public static IEvaluation<Void> f(final Runnable r) {
		if (r == null) {
			throw new IllegalArgumentException("r == null");
		}
		return new SynchronousEvaluation0<>(new RunnableAdapter(r));
	}
	
	private static ISynchronousFunction2<Void, Void, Void> join = new ISynchronousFunction2<Void, Void, Void>() {
		@Override
		public Void y(final Void x1, final Void x2) {
			return null;
		}
	};

	@SafeVarargs
	public static IEvaluation<Void> join(final IEvaluation<Void>... evaluations) {
		return join(0, evaluations);
	}

	private static IEvaluation<Void> join(final int head, final IEvaluation<Void>[] evaluations) {
        if (head < evaluations.length) {
            return new SynchronousEvaluation2<>(join, evaluations[head], join(head + 1, evaluations));
		} else {
            return new SynchronousEvaluation2<>(join, new Constant<Void>(null), new Constant<Void>(null));
		}
    }
}>>