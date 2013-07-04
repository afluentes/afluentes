package afluentes.core.api;

public interface IAsynchronousFunction4<X1, X2, X3, X4, Y> {
    void y(X1 x1, X2 x2, X3 x3, X4 x4, ICallback<Y> callback);
}