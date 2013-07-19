EvaluationN(arity, parameters) ::= 

<<package afluentes.core.impl;

import java.util.Stack;

import afluentes.core.api.IEvaluation;

abstract class Evaluation$arity$<$parameters:{parameter | $parameter.type$}; separator=", "$> extends Evaluation<Y> {
    $trunc(parameters):{parameter | protected Evaluation<$parameter.type$> $parameter.name$;}; separator="\n"$

    Evaluation$arity$($trunc(parameters):{parameter | final IEvaluation<$parameter.type$> $parameter.name$}; separator=", "$) {
    	$trunc(parameters):notNull(); separator="\n"$
        $trunc(parameters):{parameter | this.$parameter.name$ = (Evaluation<$parameter.type$>) $parameter.name$;}; separator="\n"$
    }

    @Override
    protected boolean isEvaluable() {    
        return true$trunc(parameters):{parameter |  && $parameter.name$.status == EVALUATED}$;        		
    }

    @Override
    protected void pushArguments(final Stack<Evaluation<?\>> stack) {
    	$trunc(parameters):{parameter | stack.push($parameter.name$);}; separator="\n"$
    }

    @Override
    protected void clearArguments() {
        $trunc(parameters):{parameter | $parameter.name$ = null;}; separator="\n"$
    }
}>>