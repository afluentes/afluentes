package afluentes.impl;

import afluentes.api.IEvaluation;
import afluentes.api.IEvaluator2;

public class DeltaCalculator4 {
    public double calculateDelta(double a, double b, double c) {
        return delta(new Constant<>(a), new Constant<>(b), new Constant<>(c)).y();
    }

    public IEvaluation<Double> delta(IEvaluation<Double> a, IEvaluation<Double> b, IEvaluation<Double> c) {
        return subtraction.y(multiplication.y(b, b), multiplication.y(new Constant<Double>(4.0), multiplication.y(a, c)));
    }

    private IEvaluator2<Double, Double, Double> subtraction = new AsynchronousEvaluator2<>((x1, x2, callback) -> callback.y(x1 - x2));

    private IEvaluator2<Double, Double, Double> multiplication = new AsynchronousEvaluator2<>((x1, x2, callback) -> callback.y(x1 * x2));

    public static void main(String args[]) {
        System.out.println(new DeltaCalculator4().calculateDelta(1, -3, 2));
    }
}