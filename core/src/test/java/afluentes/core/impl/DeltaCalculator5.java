package afluentes.core.impl;

import java.util.concurrent.atomic.AtomicReference;

public class DeltaCalculator5 {
	public double calculateDelta(final double a, final double b, final double c) {
		final AtomicReference<Double> b2 = new AtomicReference<>();
		final AtomicReference<Double> ac = new AtomicReference<>();
		final AtomicReference<Double> _4ac = new AtomicReference<>();
		final AtomicReference<Double> b2_4ac = new AtomicReference<>();

		Evaluator.join(
			Evaluator.f(new Runnable() {
				@Override
				public void run() {
					System.out.println("b2");
					b2.set(b * b);
				}			
			}),
			Evaluator.f(new Runnable() {
				@Override
				public void run() {
					System.out.println("ac");
					ac.set(a * c);
				}
			}).then(new Runnable() {
				@Override
				public void run() {
					System.out.println("4ac");
					_4ac.set(4 * ac.get());
				}
			})				
		).then(new Runnable() {
			@Override
			public void run() {
				b2_4ac.set(b2.get() - _4ac.get());
			}
		}).y();

		return b2_4ac.get();
    }

	public static void main(final String args[]) {
        System.out.println(new DeltaCalculator5().calculateDelta(1, -3, 2));
    }
}