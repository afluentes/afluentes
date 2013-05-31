package afluentes.impl;

import afluentes.api.IEvaluation;
import afluentes.api.IEvaluator2;

public class DeltaCalculator3 {
    public double calculateDelta(double a, double b, double c) {
        return calculateDelta(new Constant<>(a), new Constant<>(b), new Constant<>(c)).y();
    }

    public IEvaluation<Double> calculateDelta(IEvaluation<Double> a, IEvaluation<Double> b, IEvaluation<Double> c) {
        return subtraction.y(multiplication.y(b, b), multiplication.y(new Constant<Double>(4.0), multiplication.y(a, c)));
    }

    private IEvaluator2<Double, Double, Double> subtraction = new SynchronousEvaluator2<>((x, y) -> x - y);

    private IEvaluator2<Double, Double, Double> multiplication = new SynchronousEvaluator2<>((x, y) -> x * y);

    public static void main(String args[]) {
        System.out.println(new DeltaCalculator3().calculateDelta(1, -3, 2));
    }
}