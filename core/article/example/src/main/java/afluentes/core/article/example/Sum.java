package afluentes.core.article.example;

import java.util.ArrayList;
import java.util.List;

import afluentes.core.api.IEvaluation;
import afluentes.core.api.IReduce;
import afluentes.core.api.IReduceEvaluator;
import afluentes.core.impl.Constant;
import afluentes.core.impl.ReduceEvaluator;

class Sum {		
public static void main(String[] args) {
/*		
		IEvaluator1<Integer, Integer> identity = new AsynchronousEvaluator1<>(new IAsyncFn1<Integer, Integer>() {
			@Override
			public void y(final Integer x1, final ICallback<Integer> callback) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							throw new RuntimeException(e);
						}
						callback.y(x1);
					}
				}).start();
			}
		});

		IEvaluator1<Integer, Integer> identity = new SynchronousEvaluator1<>(new ISyncFn1<Integer, Integer>() {
			@Override
			public Integer y(Integer x1) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				return x1;
			}
		});
		
		List<IEvaluation<Integer>> x1s = new ArrayList<>();
		x1s.add(identity.y(new Constant<Integer>(1)));
		x1s.add(identity.y(new Constant<Integer>(2)));
		x1s.add(identity.y(new Constant<Integer>(3)));				
*/		

List<IEvaluation<? extends Integer>> x1s = new ArrayList<>();
x1s.add(new Constant<Integer>(1));
x1s.add(new Constant<Integer>(2));
x1s.add(new Constant<Integer>(3));		
		
IReduce<Integer, Integer> sum = new IReduce<Integer, Integer>() {
  @Override
  public Integer y(List<Integer> x1s) {
    if (x1s == null || x1s.size() == 0) {
      return null;
    } else {
      int y = 0;
      for (Integer x1 : x1s) {
        y += x1;
      }
      return y;
    }
  }
};

IReduceEvaluator<Integer, Integer> sumEvaluator = new ReduceEvaluator<>(sum);
IEvaluation<Integer> evaluation = sumEvaluator.y(x1s);

}

}