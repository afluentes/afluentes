SynchronousEvaluation(arity, parameters) ::= 

<<package afluentes.core.impl;

import afluentes.core.api.ISynchronousFunction$arity$;
import afluentes.core.api.IEvaluation;

class SynchronousEvaluation$arity$<$parameters:{parameter | $parameter.type$}; separator=", "$> extends Evaluation$arity$<$parameters:{parameter | $parameter.type$}; separator=", "$> {
    private final ISynchronousFunction$arity$<$parameters:{parameter | $parameter.type$}; separator=", "$> f;

	SynchronousEvaluation$arity$(final ISynchronousFunction$arity$<$parameters:{parameter | $parameter.type$}; separator=", "$> f$trunc(parameters):{parameter | , final IEvaluation<$parameter.type$> $parameter.name$}$) {     
        super($trunc(parameters):{parameter | $parameter.name$}; separator=", "$);

        this.f = f;
    }

    @Override
    protected void evaluate() {
		try {
            y(f.y($trunc(parameters):{parameter | $parameter.name$.y}; separator=", "$));
        } catch (final Throwable t) {
            t(t);
        }            
    }
}>>