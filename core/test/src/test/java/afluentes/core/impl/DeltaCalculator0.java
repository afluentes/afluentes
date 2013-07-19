package afluentes.core.impl;

public class DeltaCalculator0 {
    public double calculateDelta(final double a, final double b, final double c) {
        return b * b - 4 * a * c;
    }

    public static void main(final String args[]) {
        System.out.println(new DeltaCalculator0().calculateDelta(1, -3, 2));
    }
}