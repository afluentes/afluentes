package afluentes.middleware.test;

import afluentes.core.api.*;

public interface ICalculatorAsyncResource {
	IEvaluation<Double> sum(Double x, Double y);
	IEvaluation<Double> sum(IEvaluation<Double> x, IEvaluation<Double> y);
	IEvaluation<Double> difference(Double x, Double y);
	IEvaluation<Double> difference(IEvaluation<Double> x, IEvaluation<Double> y);
	IEvaluation<Double> product(Double x, Double y);
	IEvaluation<Double> product(IEvaluation<Double> x, IEvaluation<Double> y);
	IEvaluation<Double> quotient(Double x, Double y);
	IEvaluation<Double> quotient(IEvaluation<Double> x, IEvaluation<Double> y);
}