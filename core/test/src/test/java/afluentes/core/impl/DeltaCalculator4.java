package afluentes.core.impl;

import afluentes.core.api.IAsynchronousFunction2;
import afluentes.core.api.ICallback;
import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator2;

public class DeltaCalculator4 {
	public double calculateDelta(final double a, final double b, final double c) {
        return delta(new Constant<>(a), new Constant<>(b), new Constant<>(c)).y();
    }

    public IEvaluation<Double> delta(final IEvaluation<Double> a, final IEvaluation<Double> b, final IEvaluation<Double> c) {
        return subtraction.y(multiplication.y(b, b), multiplication.y(new Constant<Double>(4.0), multiplication.y(a, c)));
    }

/*
    private IEvaluator2<Double, Double, Double> subtraction = new AsynchronousEvaluator2<>((x1, x2, callback) -> callback.y(x1 - x2));

    private IEvaluator2<Double, Double, Double> multiplication = new AsynchronousEvaluator2<>((x1, x2, callback) -> callback.y(x1 * x2));
*/
    
    private IEvaluator2<Double, Double, Double> subtraction = new AsynchronousEvaluator2<>(new IAsynchronousFunction2<Double, Double, Double>() {
		@Override
		public void y(final Double x1, final Double x2, final ICallback<Double> callback) {
			new Thread() {
				public void run() {
					callback.y(x1 - x2);					
				}
			}.start();			
		}
	});

    private IEvaluator2<Double, Double, Double> sub2 = 
    	    new AsynchronousEvaluator2<>(
    	      new IAsynchronousFunction2<Double, Double, Double>() {
    	        @Override
    	        public void y(final Double x1, final Double x2, final ICallback<Double> callback) {
    	          new Thread() {
    	            public void run() {
    	              callback.y(x1 - x2);					
    	            }
    	          }.start();			
    	        }
    	      }
    	    );  
    	    
    private IEvaluator2<Double, Double, Double> multiplication = new AsynchronousEvaluator2<>(new IAsynchronousFunction2<Double, Double, Double>() {
		@Override
		public void y(final Double x1, final Double x2, final ICallback<Double> callback) {
			new Thread(new Runnable() {
				public void run() {
					callback.y(x1 * x2);					
				}
			}).start();			
		}
	});
    
    public static void main(final String args[]) {
        System.out.println(new DeltaCalculator4().calculateDelta(1, -3, 2));
    }
}