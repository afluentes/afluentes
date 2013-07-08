package afluentes.core.impl;

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
					try {
						b2.set(b * b);
						callback.y(null);
					} finally {
						callback.c();
					}						
				}			
			}),
			Evaluator.f(new IAsynchronousRunnable() {
				@Override
				public void run(ICallback<Void> callback) {
					try {					
						ac.set(a * c);
						callback.y(null);
					} finally {
						callback.c();
					}											
				}
			}).then(new IAsynchronousRunnable() {
				@Override
				public void run(ICallback<Void> callback) {
					try {					
						_4ac.set(4 * ac.get());
						callback.y(null);
					} finally {
						callback.c();
					}																	
				}
			})				
		).then(new IAsynchronousRunnable() {
			@Override
			public void run(ICallback<Void> callback) {
				try {
					b2_4ac.set(b2.get() - _4ac.get());
					callback.y(null);
				} finally {
					callback.c();
				}																
			}
		}).y();

		return b2_4ac.get();
    }

	public static void main(final String args[]) {
        System.out.println(new DeltaCalculator6().calculateDelta(1, -3, 2));
    }
}