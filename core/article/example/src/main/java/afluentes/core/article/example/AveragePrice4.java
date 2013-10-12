package afluentes.core.article.example;

import java.util.ArrayList;
import java.util.List;

import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator1;
import afluentes.core.api.IReduceFlatEvaluator;
import afluentes.core.api.ISyncFn1;
import afluentes.core.impl.Constant;
import afluentes.core.impl.ReduceFlatEvaluator;
import afluentes.core.impl.SynchronousEvaluator1;

class AveragePrice4 {

class Product {
  int id;
  double price;
}

double mean(List<Integer> ids) {  
  IEvaluation<Double> e = mean2.y(products.y(new Constant<>(ids))); 
  return e.y();
}

IReduceFlatEvaluator<Product, Double> mean2 = 
  new ReduceFlatEvaluator<>(
    new ISyncFn1<List<Product>, Double>() {
	  public Double y(List<Product> ps) {
        /* ... */
        double s = 0;
        for (Product p : ps) {
          s += p.price;
        }
        return s / ps.size();	
	  }
  });

IEvaluator1<List<Integer>, List<IEvaluation<? extends Product>>> products =
  new SynchronousEvaluator1<>(
    new ISyncFn1<List<Integer>, List<IEvaluation<? extends Product>>>() {
      public List<IEvaluation<? extends Product>> y(List<Integer> ids) {
        /* ... */
        List<IEvaluation<? extends Product>> ps = new ArrayList<>();
        for (Integer id : ids) {
          IEvaluation<Product> p = product.y(new Constant<>(id));
          ps.add(p);
        }
        return ps;
      }
  });

IEvaluator1<Integer, Product> product =
  new SynchronousEvaluator1<>(
    new ISyncFn1<Integer, Product>() {
	  public Product y(Integer id) {
		/* ... */
		return null;
	  }
  });
  
}