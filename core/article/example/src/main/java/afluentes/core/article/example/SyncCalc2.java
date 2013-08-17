package afluentes.core.article.example;

import afluentes.core.api.ISynchronousFunction2;

class SyncCalc2 {

double delta(double a, double b, double c) {
  return sub.y(mul.y(b, b), mul.y(4.0, mul.y(a, c)));
}

ISynchronousFunction2<Double, Double, Double> sub =
  new ISynchronousFunction2<Double, Double, Double>() {
    public Double y(Double x1, Double x2) {
      return x1 - x2;
    }
  };

ISynchronousFunction2<Double, Double, Double> mul =
  new ISynchronousFunction2<Double, Double, Double>() {
    public Double y(Double x1, Double x2) {
      return x1 - x2;
    }
  };

public static void main(String[] args) {
  System.out.println(new SyncCalc2().delta(1, -3, 2));
}
  
}