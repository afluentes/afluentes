package afluentes.core.article.example;

import java.util.ArrayList;
import java.util.List;

class AveragePrice1 {

class Product {
  int id;
  double price;
}

double mean(List<Integer> ids) {
  return mean2(products(ids));
}

double mean2(List<Product> ps) {
  double s = 0;
  for (Product p : ps) {
    s += p.price;
  }
  return s / ps.size();
}

List<Product> products(List<Integer> ids) {
  List<Product> ps = new ArrayList<>();
  for (Integer id : ids) {
    Product p = product(id); 
    ps.add(p);
  }
  return ps;
}
	
Product product(int id) {
  /* ... */
  return null;
}

}