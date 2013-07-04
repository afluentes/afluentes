package afluentes.core.impl;

public class DeltaCalculator1 {
    public double calculateDelta(final double a, final double b, final double c) {
        return subtraction(multiplication(b, b), multiplication(4, multiplication(a, c)));
    }

    private double subtraction(final double x, final double y) {
        return x - y;
    }

    private double multiplication(final double x, final double y) {
        return x * y;
    }

    public static void main(final String args[]) {
        System.out.println(new DeltaCalculator1().calculateDelta(1, -3, 2));
    }
}