package afluentes.core.impl;

import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator2;
import afluentes.core.api.ISynchronousFunction2;


public class DeltaCalculator3 {
    public double calculateDelta(final double a, final double b, final double c) {
        return calculateDelta(new Constant<>(a), new Constant<>(b), new Constant<>(c)).y();
    }

    public IEvaluation<Double> calculateDelta(IEvaluation<Double> a, IEvaluation<Double> b, IEvaluation<Double> c) {
        return subtraction.y(multiplication.y(b, b), multiplication.y(new Constant<Double>(4.0), multiplication.y(a, c)));
    }

/*    
    private IEvaluator2<Double, Double, Double> subtraction = new SynchronousEvaluator2<>((x, y) -> x - y);

    private IEvaluator2<Double, Double, Double> multiplication = new SynchronousEvaluator2<>((x, y) -> x * y);
*/    
    
    private IEvaluator2<Double, Double, Double> subtraction = new SynchronousEvaluator2<>(new ISynchronousFunction2<Double, Double, Double>() {
		@Override
		public Double y(Double x1, Double x2) {
			return x1 - x2;
		}    	
	});
    
    private IEvaluator2<Double, Double, Double> multiplication = new SynchronousEvaluator2<>(new ISynchronousFunction2<Double, Double, Double>() {
		@Override
		public Double y(Double x1, Double x2) {
			return x1 * x2;
		}    	
	});    

    public static void main(final String args[]) {
        System.out.println(new DeltaCalculator3().calculateDelta(1, -3, 2));
    }
}