package afluentes.core.api;

public interface IEvaluation<Y> {
	Y y();
    IEvaluation<Void> then(IAsynchronousRunnable r);
    IEvaluation<Void> then(Runnable r);    
}