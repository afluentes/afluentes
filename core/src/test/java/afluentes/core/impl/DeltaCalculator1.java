package afluentes.core.impl;

public class DeltaCalculator1 {
    public double calculateDelta(double a, double b, double c) {
        return subtraction(multiplication(b, b), multiplication(4, multiplication(a, c)));
    }

    private double subtraction(double x, double y) {
        return x - y;
    }

    private double multiplication(double x, double y) {
        return x * y;
    }

    public static void main(String args[]) {
        System.out.println(new DeltaCalculator1().calculateDelta(1, -3, 2));
    }
}