package afluentes.core.api;

public interface IEvaluation<Y> {
	Y y();
    IEvaluation<Void> then(IAsynchronousFunction0<Void> f);
    IEvaluation<Void> then(ISynchronousFunction0<Void> f);    
}