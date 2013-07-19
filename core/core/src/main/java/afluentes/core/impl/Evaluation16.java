package afluentes.core.impl;

import java.util.Stack;

import afluentes.core.api.IEvaluation;

abstract class Evaluation16<X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, X11, X12, X13, X14, X15, X16, Y> extends Evaluation<Y> {
    protected Evaluation<X1> x1;
    protected Evaluation<X2> x2;
    protected Evaluation<X3> x3;
    protected Evaluation<X4> x4;
    protected Evaluation<X5> x5;
    protected Evaluation<X6> x6;
    protected Evaluation<X7> x7;
    protected Evaluation<X8> x8;
    protected Evaluation<X9> x9;
    protected Evaluation<X10> x10;
    protected Evaluation<X11> x11;
    protected Evaluation<X12> x12;
    protected Evaluation<X13> x13;
    protected Evaluation<X14> x14;
    protected Evaluation<X15> x15;
    protected Evaluation<X16> x16;

    Evaluation16(final IEvaluation<X1> x1, final IEvaluation<X2> x2, final IEvaluation<X3> x3, final IEvaluation<X4> x4, final IEvaluation<X5> x5, final IEvaluation<X6> x6, final IEvaluation<X7> x7, final IEvaluation<X8> x8, final IEvaluation<X9> x9, final IEvaluation<X10> x10, final IEvaluation<X11> x11, final IEvaluation<X12> x12, final IEvaluation<X13> x13, final IEvaluation<X14> x14, final IEvaluation<X15> x15, final IEvaluation<X16> x16) {
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
    	if (x5 == null) {
    		throw new IllegalArgumentException("x5 == null");
    	}
    	if (x6 == null) {
    		throw new IllegalArgumentException("x6 == null");
    	}
    	if (x7 == null) {
    		throw new IllegalArgumentException("x7 == null");
    	}
    	if (x8 == null) {
    		throw new IllegalArgumentException("x8 == null");
    	}
    	if (x9 == null) {
    		throw new IllegalArgumentException("x9 == null");
    	}
    	if (x10 == null) {
    		throw new IllegalArgumentException("x10 == null");
    	}
    	if (x11 == null) {
    		throw new IllegalArgumentException("x11 == null");
    	}
    	if (x12 == null) {
    		throw new IllegalArgumentException("x12 == null");
    	}
    	if (x13 == null) {
    		throw new IllegalArgumentException("x13 == null");
    	}
    	if (x14 == null) {
    		throw new IllegalArgumentException("x14 == null");
    	}
    	if (x15 == null) {
    		throw new IllegalArgumentException("x15 == null");
    	}
    	if (x16 == null) {
    		throw new IllegalArgumentException("x16 == null");
    	}
        this.x1 = (Evaluation<X1>) x1;
        this.x2 = (Evaluation<X2>) x2;
        this.x3 = (Evaluation<X3>) x3;
        this.x4 = (Evaluation<X4>) x4;
        this.x5 = (Evaluation<X5>) x5;
        this.x6 = (Evaluation<X6>) x6;
        this.x7 = (Evaluation<X7>) x7;
        this.x8 = (Evaluation<X8>) x8;
        this.x9 = (Evaluation<X9>) x9;
        this.x10 = (Evaluation<X10>) x10;
        this.x11 = (Evaluation<X11>) x11;
        this.x12 = (Evaluation<X12>) x12;
        this.x13 = (Evaluation<X13>) x13;
        this.x14 = (Evaluation<X14>) x14;
        this.x15 = (Evaluation<X15>) x15;
        this.x16 = (Evaluation<X16>) x16;
    }

    @Override
    protected boolean isEvaluable() {    
        return true && x1.status == EVALUATED && x2.status == EVALUATED && x3.status == EVALUATED && x4.status == EVALUATED && x5.status == EVALUATED && x6.status == EVALUATED && x7.status == EVALUATED && x8.status == EVALUATED && x9.status == EVALUATED && x10.status == EVALUATED && x11.status == EVALUATED && x12.status == EVALUATED && x13.status == EVALUATED && x14.status == EVALUATED && x15.status == EVALUATED && x16.status == EVALUATED;        		
    }

    @Override
    protected void pushArguments(final Stack<Evaluation<?>> stack) {
    	stack.push(x1);
    	stack.push(x2);
    	stack.push(x3);
    	stack.push(x4);
    	stack.push(x5);
    	stack.push(x6);
    	stack.push(x7);
    	stack.push(x8);
    	stack.push(x9);
    	stack.push(x10);
    	stack.push(x11);
    	stack.push(x12);
    	stack.push(x13);
    	stack.push(x14);
    	stack.push(x15);
    	stack.push(x16);
    }

    @Override
    protected void clearArguments() {
        x1 = null;
        x2 = null;
        x3 = null;
        x4 = null;
        x5 = null;
        x6 = null;
        x7 = null;
        x8 = null;
        x9 = null;
        x10 = null;
        x11 = null;
        x12 = null;
        x13 = null;
        x14 = null;
        x15 = null;
        x16 = null;
    }
}