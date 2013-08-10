package afluentes.core.article.example.akka;

import java.util.concurrent.TimeUnit;

import scala.concurrent.Await;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import akka.dispatch.ExecutionContexts;
import akka.dispatch.Futures;
import akka.dispatch.Mapper;

public class Calculator2 {
	private ExecutionContext context;
	
	public Calculator2(ExecutionContext context) {
		this.context = context;
	}

	public Future<Double> delta(double a, double b, double c) throws Exception {
		return delta(Futures.successful(a), Futures.successful(b), Futures.successful(c)); 
	}

	private Future<Double> delta(Future<Double> a, Future<Double> b, Future<Double> c) {		
		return sub(mul(b, b), mul(Futures.successful(4.0), mul(a, c)));
	}	

	private Future<Double> sub(Future<Double> x, final Future<Double> y) {
		return x.flatMap(
			new Mapper<Double, Future<Double>>() {
				@Override
				public Future<Double> apply(final Double xResult) {
					return y.map(
						new Mapper<Double, Double>() {
							@Override
							public Double apply(final Double yResult) {
								return xResult - yResult;
							}
						}, context
					);
				}
			}, context
		);
	}	
	
	private Future<Double> mul(Future<Double> x, final Future<Double> y) {
		return x.flatMap(
			new Mapper<Double, Future<Double>>() {
				@Override
				public Future<Double> apply(final Double xResult) {
					return y.map(
						new Mapper<Double, Double>() {
							@Override
							public Double apply(final Double yResult) {
								return xResult * yResult;
							}
						}, context
					);
				}
			}, context
		);		
	}
	
	public static void main(String[] args) throws Exception {
		long t = System.currentTimeMillis();
		ExecutionContext context = ExecutionContexts.global();
		Calculator2 calculator = new Calculator2(context);
		Future<Double> delta = calculator.delta(1, 3, 2);
		double deltaResult = Await.result(delta, Duration.create(1, TimeUnit.MINUTES));
		t = System.currentTimeMillis() - t;
		System.out.println("Elapsed time: " + t);
		System.out.println("Delta: " + deltaResult);
	}
}