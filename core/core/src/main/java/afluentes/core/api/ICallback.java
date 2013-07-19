package afluentes.core.api;

public interface ICallback<Y> {
    void y(Y y);
    void t(Throwable t);
}