package afluentes.core.article.example;

class SyncCalc1 {

double delta(double a, double b, double c) {
  return sub(mul(b, b), mul(4, mul(a, c)));
}

double sub(double x, double y) {
	return x - y;
}

double mul(double x, double y) {
  return x * y;
}

public static void main(String[] args) {
  System.out.println(new SyncCalc1().delta(1, -3, 2));
}
  
}