package afluentes.core.article.example;

public class Calculator0 {
	public double delta(double a, double b, double c) {
		return b * b - 4 * a * c;
	}
	
	public static void main(String[] args) {
		Calculator0 calculator = new Calculator0();
		double delta = calculator.delta(1, 3, 2);
		System.out.println(delta);
	}
}