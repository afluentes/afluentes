package afluentes.core.impl;

import java.util.Stack;

import afluentes.core.api.IEvaluation;

abstract class Evaluation1<X1, Y> extends Evaluation<Y> {
    protected Evaluation<X1> x1;

    Evaluation1(final IEvaluation<X1> x1) {
    	if (x1 == null) {
    		throw new IllegalArgumentException("x1 == null");
    	}
        this.x1 = (Evaluation<X1>) x1;
    }

    @Override
    protected boolean isEvaluable() {    
        return true && x1.status == EVALUATED;        		
    }

    @Override
    protected void pushArguments(final Stack<Evaluation<?>> stack) {
    	stack.push(x1);
    }

    @Override
    protected void clearArguments() {
        x1 = null;
    }
}