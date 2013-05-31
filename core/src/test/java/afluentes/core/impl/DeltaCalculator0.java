package afluentes.core.impl;

public class DeltaCalculator0 {
    public double calculateDelta(double a, double b, double c) {
        return b * b - 4 * a * c;
    }

    public static void main(String args[]) {
        System.out.println(new DeltaCalculator0().calculateDelta(1, -3, 2));
    }
}