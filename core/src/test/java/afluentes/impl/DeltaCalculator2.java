package afluentes.impl;

import afluentes.api.ISynchronousFunction2;

public class DeltaCalculator2 {
    public double calculateDelta(double a, double b, double c) {
        return subtraction.y(multiplication.y(b, b), multiplication.y(4.0, multiplication.y(a, c)));
    }

    private ISynchronousFunction2<Double, Double, Double> subtraction = (x, y) -> x - y;

    private ISynchronousFunction2<Double, Double, Double> multiplication = (x, y) -> x * y;

    public static void main(String args[]) {
        System.out.println(new DeltaCalculator2().calculateDelta(1, -3, 2));
    }
}