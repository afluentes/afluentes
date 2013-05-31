package afluentes.core.impl;

import afluentes.core.api.IEvaluator2;

public interface ICalculatorStub {
    public IEvaluator2<Double, Double, Double> subtraction();
    public IEvaluator2<Double, Double, Double> multiplication();
}