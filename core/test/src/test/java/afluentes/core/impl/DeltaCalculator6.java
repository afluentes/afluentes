package afluentes.core.impl;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import afluentes.core.api.IAsynchronousRunnable;
import afluentes.core.api.ICallback;

public class DeltaCalculator6 {
	public double calculateDelta(final double a, final double b, final double c) {
		final AtomicReference<Double> b2 = new AtomicReference<>();
		final AtomicReference<Double> ac = new AtomicReference<>();
		final AtomicReference<Double> _4ac = new AtomicReference<>();
		final AtomicReference<Double> b2_4ac = new AtomicReference<>();

		Evaluator.join(
			Evaluator.f(new IAsynchronousRunnable() {
				@Override
				public void run(ICallback<Void> callback) {
					b2.set(b * b);
					callback.y(null);
				}			
			}),
			Evaluator.f(new IAsynchronousRunnable() {
				@Override
				public void run(ICallback<Void> callback) {
					ac.set(a * c);
					callback.y(null);
				}
			}).then(new IAsynchronousRunnable() {
				@Override
				public void run(final ICallback<Void> callback) {
					new Thread() {
						@Override
						public void run() {
							_4ac.set(4 * ac.get());
							callback.y(null);							
						}
					}.start();
				}
			})				
		).then(new IAsynchronousRunnable() {
			@Override
			public void run(final ICallback<Void> callback) {
				new Thread() {
					@Override
					public void run() {
						b2_4ac.set(b2.get() - _4ac.get());
						callback.y(null);
					}
				}.start();						
			}
		}).y(10, TimeUnit.SECONDS);

		return b2_4ac.get();
    }

	public static void main(final String args[]) {
        System.out.println(new DeltaCalculator6().calculateDelta(1, -3, 2));
    }
}