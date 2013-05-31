package afluentes.impl;

import afluentes.api.IEvaluation;
import afluentes.api.IEvaluator2;
import afluentes.impl.Constant;

public class DeltaCalculator5 {
    private ICalculatorStub calculator;

    public DeltaCalculator5(ICalculatorStub calculator) {
        if (calculator == null) {
            throw new IllegalArgumentException("calculator == null");
        }
        this.subtraction = calculator.subtraction();
        this.multiplication = calculator.multiplication();
    }

    public double calculateDelta(double a, double b, double c) {
        return delta(new Constant<>(a), new Constant<>(b), new Constant<>(c)).y();
    }

    public IEvaluation<Double> delta(IEvaluation<Double> a, IEvaluation<Double> b, IEvaluation<Double> c) {
        return subtraction.y(multiplication.y(b, b), multiplication.y(new Constant<Double>(4.0), multiplication.y(a, c)));
    }

    private IEvaluator2<Double, Double, Double> subtraction;

    private IEvaluator2<Double, Double, Double> multiplication;

    public static void main(String args[]) {
        System.out.println(new DeltaCalculator5(null).calculateDelta(1, -3, 2));
    }
}