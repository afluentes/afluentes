package afluentes.core.article.example;

import afluentes.core.api.IAsyncFn1;
import afluentes.core.api.ICallback;
import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator1;
import afluentes.core.api.IEvaluator2;
import afluentes.core.api.ISyncFn1;
import afluentes.core.api.ISyncFn2;
import afluentes.core.impl.AsynchronousEvaluator1;
import afluentes.core.impl.Constant;
import afluentes.core.impl.SynchronousEvaluator1;
import afluentes.core.impl.SynchronousEvaluator2;

class MeanPrice1 {

class Product {
  Integer id;
  double price;
}

double mean(int id1, int id2) {
  return mean2(
    product(id1), 
    product(id2)
  );
}

double mean2(Product p1, Product p2) {
  return (p1.price + p2.price) / 2;
}

Product product(int id) {
  /* ... */
  return null;
}

}



class MeanPrice2 {

class Product {
  Integer id;
  double price;
}

double mean(int id1, int id2) {
  return mean2.y(
    product.y(id1), 
    product.y(id2)
  );
}

ISyncFn2<Product, Product, Double> mean2 =
  new ISyncFn2<Product, Product, Double>() {
    public Double y(Product p1, Product p2) {
      return (p1.price + p2.price) / 2;
    }
  };

ISyncFn1<Integer, Product> product = 
  new ISyncFn1<Integer, Product>() {
	public Product y(Integer x1) {		
	    /* ... */
	    return null;			
	}
  };

}



class MeanPrice3 {

class Product {
  Integer id;
  double price;
}

double mean(int id1, int id2) {
  IEvaluation<Double> e = mean2.y(
    product.y(new Constant<>(id1)), 
    product.y(new Constant<>(id2))
  ); 
  return e.y();
}

IEvaluator2<Product, Product, Double> mean2 =
new SynchronousEvaluator2<>(
  new ISyncFn2<Product, Product, Double>() {
	  public Double y(Product p1, Product p2) {
      return (p1.price + p2.price) / 2;
	  }
});

IEvaluator1<Integer, Product> product =
  new SynchronousEvaluator1<>(
    new ISyncFn1<Integer, Product>() {
	  public Product y(Integer x1) {		
	    /* ... */
	    return null;			
	  }
  });

}



class MeanPrice4 {

class Product {
  Integer id;
  double price;
}

double mean(int id1, int id2) {
  IEvaluation<Double> e = mean2.y(
    product.y(new Constant<>(id1)), 
    product.y(new Constant<>(id2))
  ); 
  return e.y();
}

IEvaluator2<Product, Product, Double> mean2 =
new SynchronousEvaluator2<>(
  new ISyncFn2<Product, Product, Double>() {
	  public Double y(Product p1, Product p2) {
      return (p1.price + p2.price) / 2;
	  }
});

IEvaluator1<Integer, Product> product =
  new AsynchronousEvaluator1<>(
    new IAsyncFn1<Integer, Product>() {
	  public void y(final Integer id, final ICallback<Product> c) {
        new Thread(new Runnable() {
          public void run() {
            try {
              Product p = null;
              /* ... */
              c.y(p);
        	} catch (Throwable t) {
              c.t(t);
            }
          }
        }).start();
	  }
  });

}