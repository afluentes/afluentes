package afluentes.core.article;

public class Calculator1 {
	public double delta(double a, double b, double c) {
		return sub(mul(b, b), mul(4, mul(a, c)));
	}
	
	private double sub(double x, double y) {
		return x - y;
	}	
	
	private double mul(double x, double y) {
		return x * y;
	}	
	
	public static void main(String[] args) {
		Calculator1 calculator = new Calculator1();
		double delta = calculator.delta(1, 3, 2);
		System.out.println(delta);
	}
}