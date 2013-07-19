package afluentes.core.impl;

import afluentes.core.api.ISynchronousFunction2;


public class DeltaCalculator2 {
    public double calculateDelta(final double a, final double b, final double c) {
        return subtraction.y(multiplication.y(b, b), multiplication.y(4.0, multiplication.y(a, c)));
    }

/*    
    private ISynchronousFunction2<Double, Double, Double> subtraction = (x, y) -> x - y;

    private ISynchronousFunction2<Double, Double, Double> multiplication = (x, y) -> x * y;
*/
    
    private ISynchronousFunction2<Double, Double, Double> subtraction = new ISynchronousFunction2<Double, Double, Double>() {
		@Override
		public Double y(Double x1, Double x2) {
			return x1 - x2;
		}    	
	};

    private ISynchronousFunction2<Double, Double, Double> multiplication = new ISynchronousFunction2<Double, Double, Double>() {
		@Override
		public Double y(Double x1, Double x2) {
			return x1 * x2;
		}    	
	};
    
    public static void main(final String args[]) {
        System.out.println(new DeltaCalculator2().calculateDelta(1, -3, 2));
    }
}