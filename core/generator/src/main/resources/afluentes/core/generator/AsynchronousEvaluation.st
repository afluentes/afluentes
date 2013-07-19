AsynchronousEvaluation(arity, parameters) ::= 

<<package afluentes.core.impl;

import afluentes.core.api.IAsynchronousFunction$arity$;
import afluentes.core.api.IEvaluation;

class AsynchronousEvaluation$arity$<$parameters:{parameter | $parameter.type$}; separator=", "$> extends Evaluation$arity$<$parameters:{parameter | $parameter.type$}; separator=", "$> {
    private final IAsynchronousFunction$arity$<$parameters:{parameter | $parameter.type$}; separator=", "$> f;

	AsynchronousEvaluation$arity$(final IAsynchronousFunction$arity$<$parameters:{parameter | $parameter.type$}; separator=", "$> f$trunc(parameters):{parameter | , final IEvaluation<$parameter.type$> $parameter.name$}$) {     
        super($trunc(parameters):{parameter | $parameter.name$}; separator=", "$);

        this.f = f;
    }

    @Override
    protected void evaluate() {
        try {
            f.y($trunc(parameters):{parameter | $parameter.name$.y, }$this);
        } catch (final Throwable t) {
        	try {
        		t(t);
        	} finally {
        		c();
        	}
        }
    }
}>>