package afluentes.core.impl;

import java.util.concurrent.atomic.AtomicReference;

import afluentes.core.api.IAsynchronousFunction0;
import afluentes.core.api.ICallback;

public class DeltaCalculator6 {
	public double calculateDelta(final double a, final double b, final double c) {
		final AtomicReference<Double> b2 = new AtomicReference<>();
		final AtomicReference<Double> ac = new AtomicReference<>();
		final AtomicReference<Double> _4ac = new AtomicReference<>();		

		Evaluator.f(new IAsynchronousFunction0<Void>() {
			@Override
			public void y(ICallback<Void> callback) {
				System.out.println("ac");
				try {
					ac.set(a * c);
					callback.y(null);
				} finally {
					callback.c();
				}
			}
		}).then(new IAsynchronousFunction0<Void>() {
			@Override
			public void y(ICallback<Void> callback) {
				System.out.println("4ac");
				try {
					_4ac.set(4 * ac.get());
					callback.y(null);
				} finally {
					callback.c();
				}					
			}
		}).y();

		return _4ac.get();
    }

	public static void main(final String args[]) {
        System.out.println(new DeltaCalculator6().calculateDelta(1, -3, 2));
    }
}