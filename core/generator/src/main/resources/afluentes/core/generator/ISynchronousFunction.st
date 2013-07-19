ISynchronousFunction(arity, parameters) ::= 

<<package afluentes.core.api;

public interface ISynchronousFunction$arity$<$parameters:{parameter | $parameter.type$}; separator=", "$> {
    Y y($trunc(parameters):{parameter | $parameter.type$ $parameter.name$}; separator=", "$);
}>>