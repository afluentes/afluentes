package afluentes.core.api;

import java.util.concurrent.TimeUnit;

public interface IEvaluation<Y> {
	Y y(long timeout, TimeUnit unit);
    IEvaluation<Void> then(IAsynchronousRunnable r);
    IEvaluation<Void> then(Runnable r);    
}