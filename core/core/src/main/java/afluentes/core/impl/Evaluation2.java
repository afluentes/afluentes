package afluentes.core.impl;

import java.util.Stack;

import afluentes.core.api.IEvaluation;

abstract class Evaluation2<X1, X2, Y> extends Evaluation<Y> {
    protected Evaluation<X1> x1;
    protected Evaluation<X2> x2;

    Evaluation2(final IEvaluation<X1> x1, final IEvaluation<X2> x2) {
    	if (x1 == null) {
    		throw new IllegalArgumentException("x1 == null");
    	}
    	if (x2 == null) {
    		throw new IllegalArgumentException("x2 == null");
    	}
        this.x1 = (Evaluation<X1>) x1;
        this.x2 = (Evaluation<X2>) x2;
    }

    @Override
    protected boolean isEvaluable() {    
        return true && x1.status == EVALUATED && x2.status == EVALUATED;        		
    }

    @Override
    protected void pushArguments(final Stack<Evaluation<?>> stack) {
    	stack.push(x1);
    	stack.push(x2);
    }

    @Override
    protected void clearArguments() {
        x1 = null;
        x2 = null;
    }
}