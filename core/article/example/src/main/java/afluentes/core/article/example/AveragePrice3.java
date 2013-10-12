package afluentes.core.article.example;

import java.util.ArrayList;
import java.util.List;

import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator1;
import afluentes.core.api.ISyncFn1;
import afluentes.core.impl.Constant;
import afluentes.core.impl.SynchronousEvaluator1;

class AveragePrice3 {

class Product {
  int id;
  double price;
}

double mean(List<Integer> ids) {
  IEvaluation<Double> e = mean2.y(products.y(new Constant<>(ids))); 
  return e.y();
}

IEvaluator1<List<Product>, Double> mean2 = 
  new SynchronousEvaluator1<>(
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

IEvaluator1<List<Integer>, List<Product>> products =
  new SynchronousEvaluator1<>(
    new ISyncFn1<List<Integer>, List<Product>>() {
      public List<Product> y(List<Integer> ids) {
        /* ... */
        List<Product> ps = new ArrayList<>();
        for (Integer id : ids) {
          Product p = product.y(id);
          ps.add(p);
        }
        return ps;
      }
  });

ISyncFn1<Integer, Product> product = 
  new ISyncFn1<Integer, Product>() {
	public Product y(Integer id) {
		/* ... */
		return null;
	}
  };
  
}