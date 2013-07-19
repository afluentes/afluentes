package afluentes.core.api;

public interface IAsynchronousFunction1<X1, Y> {
    void y(X1 x1, ICallback<Y> callback);
}