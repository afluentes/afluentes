SynchronousEvaluator(arity, parameters) ::= 

<<package afluentes.core.impl;

import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator$arity$;
import afluentes.core.api.ISynchronousFunction$arity$;

public class SynchronousEvaluator$arity$<$parameters:{parameter | $parameter.type$}; separator=", "$> implements IEvaluator$arity$<$parameters:{parameter | $parameter.type$}; separator=", "$> {
    private final ISynchronousFunction$arity$<$parameters:{parameter | $parameter.type$}; separator=", "$> f;

    public SynchronousEvaluator$arity$(final ISynchronousFunction$arity$<$parameters:{parameter | $parameter.type$}; separator=", "$> f) {
        if (f == null) {
            throw new IllegalArgumentException("f == null");
        }
        this.f = f;
    }

    @Override
    public IEvaluation<Y> y($trunc(parameters):{parameter | final IEvaluation<$parameter.type$> $parameter.name$}; separator=", "$) {
        return new SynchronousEvaluation$arity$<>(f$trunc(parameters):{parameter | , $parameter.name$}$);
    }
}>>