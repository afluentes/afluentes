package afluentes.core.impl;

import java.util.Stack;

import afluentes.core.api.IEvaluation;

abstract class Evaluation4<X1, X2, X3, X4, Y> extends Evaluation<Y> {
    protected Evaluation<X1> x1;
    protected Evaluation<X2> x2;
    protected Evaluation<X3> x3;
    protected Evaluation<X4> x4;

    Evaluation4(final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3, final IEvaluation<X4> x4) {
    	if (x1 == null) {
    		throw new IllegalArgumentException("x1 == null");
    	}
    	if (x2 == null) {
    		throw new IllegalArgumentException("x2 == null");
    	}
    	if (x3 == null) {
    		throw new IllegalArgumentException("x3 == null");
    	}
    	if (x4 == null) {
    		throw new IllegalArgumentException("x4 == null");
    	}
        this.x1 = (Evaluation<X1>) x1;
        this.x2 = (Evaluation<X2>) x2;
        this.x3 = (Evaluation<X3>) x3;
        this.x4 = (Evaluation<X4>) x4;
    }

    @Override
    protected boolean isEvaluable() {    
        return true && x1.status == EVALUATED && x2.status == EVALUATED && x3.status == EVALUATED && x4.status == EVALUATED;        		
    }

    @Override
    protected void pushArguments(final Stack<Evaluation<?>> stack) {
    	stack.push(x1);
    	stack.push(x2);
    	stack.push(x3);
    	stack.push(x4);
    }

    @Override
    protected void clearArguments() {
        x1 = null;
        x2 = null;
        x3 = null;
        x4 = null;
    }
}