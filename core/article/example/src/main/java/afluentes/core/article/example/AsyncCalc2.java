package afluentes.core.article.example;

import afluentes.core.api.IAsynchronousFunction2;
import afluentes.core.api.ICallback;
import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator2;
import afluentes.core.impl.AsynchronousEvaluator2;
import afluentes.core.impl.Constant;

class AsyncCalc2 {
  IEvaluation<Double> a = new Constant<>(1.0);
  IEvaluation<Double> b = new Constant<>(-3.0);
  IEvaluation<Double> c = new Constant<>(2.0);
  IEvaluation<Double> evaluation = delta(a, b, c);
  double result = evaluation.y();
  
  IEvaluation<Double> delta(IEvaluation<Double> a, IEvaluation<Double> b, IEvaluation<Double> c) {
    return sub.y(mul.y(b, b), mul.y(new Constant<>(4.0), mul.y(a, c)));
  }  

IEvaluator2<Double, Double, Double> sub =
  new AsynchronousEvaluator2<>(
    new IAsynchronousFunction2<Double, Double, Double>() {
      public void y(final Double x1, final Double x2, final ICallback<Double> cb) {
        new Thread(new Runnable() {
          public void run() {
            cb.y(x1 - x2);
          }          
        }).start();        
      }
    });

/* ... */  
IEvaluator2<Double, Double, Double> mul =
  new AsynchronousEvaluator2<>(
    new IAsynchronousFunction2<Double, Double, Double>() {
      public void y(final Double x1, final Double x2, final ICallback<Double> cb) {
        new Thread(new Runnable() {
          public void run() {
            cb.y(x1 * x2);
          }          
        }).start();        
      }
    });

  public static void main(String[] args) {
    System.out.println(new SyncCalc2().delta(1, -3, 2));
  }
}