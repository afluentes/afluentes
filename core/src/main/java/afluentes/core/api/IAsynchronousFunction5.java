package afluentes.core.api;

public interface IAsynchronousFunction5<X1, X2, X3, X4, X5, Y> {
    void y(X1 x1, X2 x2, X3 x3, X4 x4, X5 x5, ICallback<Y> callback);
}