package afluentes.api;

public interface IAsynchronousFunction2<X1, X2, Y> {
    public void y(X1 x1, X2 x2, ICallback<Y> callback);
}