package afluentes.api;

public interface IAsynchronousFunction1<X1, Y> {
    public void y(X1 x1, ICallback<Y> callback);
}