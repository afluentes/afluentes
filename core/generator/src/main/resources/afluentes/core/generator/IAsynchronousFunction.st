IAsynchronousFunction(arity, parameters) ::= 

<<package afluentes.core.api;

public interface IAsynchronousFunction$arity$<$parameters:{parameter | $parameter.type$}; separator=", "$> {
    void y($trunc(parameters):{parameter | $parameter.type$ $parameter.name$, }$ICallback<Y> callback);
}>>