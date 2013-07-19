package afluentes.core.api;

public interface IAsynchronousFunction3<X1, X2, X3, Y> {
    void y(X1 x1, X2 x2, X3 x3, ICallback<Y> callback);
}