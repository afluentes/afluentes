IEvaluator(arity, parameters) ::= 

<<package afluentes.core.api;

public interface IEvaluator$arity$<$parameters:{parameter | $parameter.type$}; separator=", "$> extends ISyncFn$arity$<$parameters:{parameter | IEvaluation<$parameter.type$>}; separator=", "$> {
}>>