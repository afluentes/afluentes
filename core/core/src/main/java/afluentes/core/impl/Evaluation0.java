package afluentes.core.impl;

import java.util.Stack;

abstract class Evaluation0<Y> extends Evaluation<Y> {

    Evaluation0() {
    }

    @Override
    protected boolean isEvaluable() {    
        return true;        		
    }

    @Override
    protected void pushArguments(final Stack<Evaluation<?>> stack) {
    }

    @Override
    protected void clearArguments() {
    }
}