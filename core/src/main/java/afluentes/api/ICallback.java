package afluentes.api;

public interface ICallback<Y> {
    public void y(Y y);
    public void t(Throwable t);
    public void c();
}