package afluentes.core.article.example;

import java.util.concurrent.atomic.AtomicReference;

abstract class ICallback<Y> implements afluentes.core.api.ICallback<Y> {
  @Override
  public void t(Throwable t) {}  
}

class AsyncCalc1 {

void delta(double a, double b, final double c, final ICallback<Double> cb) {
  final AtomicReference<Double> b2 = new AtomicReference<>();
  final AtomicReference<Double> _4ac = new AtomicReference<>();
  
  mul(b, b, new ICallback<Double>() {
    public void y(Double y) {
      b2.set(y);
      delta(b2, _4ac, cb);
    }
  });
  
  mul(4, a, new ICallback<Double>() {
    public void y(Double y) {
      mul(y, c, new ICallback<Double>() {
        public void y(Double y) {
          _4ac.set(y);
          delta(b2, _4ac, cb);
        }
      });
    }
  });
}

void delta(AtomicReference<Double> b2, AtomicReference<Double> _4ac, ICallback<Double> cb) {
  if (b2.get() != null && _4ac.get() != null) {
    sub(b2.get(), _4ac.get(), cb);
  }
}

void sub(final double x, final double y, final ICallback<Double> cb) {
  /* ... */ 
	new Thread(new Runnable() {			
		public void run() {
		  cb.y(x - y);
		}
	}).start();
}

void mul(final double x, final double y, final ICallback<Double> cb) {
	new Thread(new Runnable() {			
		public void run() {
		  cb.y(x * y);
		}	
	}).start();		
}

public static void main(String[] args) {
  new AsyncCalc1().delta(1, -3, 2, new ICallback<Double>() {
    public void y(Double y) {
      System.out.println(y);
    }
  });
}

}