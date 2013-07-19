package afluentes.core.impl;

import java.util.concurrent.atomic.AtomicReference;

public class DeltaCalculator7 {
	public double calculateDelta(final double a, final double b, final double c) {
		final AtomicReference<Double> b2 = new AtomicReference<>();
		final AtomicReference<Double> ac = new AtomicReference<>();
		final AtomicReference<Double> _4ac = new AtomicReference<>();
		final AtomicReference<Double> b2_4ac = new AtomicReference<>();

/*		
		Evaluator.join(
			Evaluator.f(() -> b2.set(b * b)),
			Evaluator.f(() -> ac.set(a * c)).then(() -> _4ac.set(4 * ac.get()))
		).then(() -> b2_4ac.set(b2.get() - _4ac.get())).y();
*/
		
		return b2_4ac.get();
    }

	public static void main(final String args[]) {
        System.out.println(new DeltaCalculator7().calculateDelta(1, -3, 2));
    }
}