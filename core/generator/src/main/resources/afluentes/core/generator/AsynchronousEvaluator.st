AsynchronousEvaluator(arity, parameters) ::= 

<<package afluentes.core.impl;

import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator$arity$;
import afluentes.core.api.IAsynchronousFunction$arity$;

public class AsynchronousEvaluator$arity$<$parameters:{parameter | $parameter.type$}; separator=", "$> implements IEvaluator$arity$<$parameters:{parameter | $parameter.type$}; separator=", "$> {
    private final IAsynchronousFunction$arity$<$parameters:{parameter | $parameter.type$}; separator=", "$> f;

    public AsynchronousEvaluator$arity$(final IAsynchronousFunction$arity$<$parameters:{parameter | $parameter.type$}; separator=", "$> f) {
        if (f == null) {
            throw new IllegalArgumentException("f == null");
        }
        this.f = f;
    }

    @Override
    public IEvaluation<Y> y($trunc(parameters):{parameter | final IEvaluation<$parameter.type$> $parameter.name$}; separator=", "$) {
        return new AsynchronousEvaluation$arity$<>(f$trunc(parameters):{parameter | , $parameter.name$}$);
    }
}>>