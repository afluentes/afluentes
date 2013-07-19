package afluentes.core.api;

public interface IAsynchronousFunction2<X1, X2, Y> {
    void y(X1 x1, X2 x2, ICallback<Y> callback);
}