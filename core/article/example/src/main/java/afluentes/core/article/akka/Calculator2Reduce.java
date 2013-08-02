package afluentes.core.article.akka;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import scala.concurrent.Await;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import akka.dispatch.ExecutionContexts;
import akka.dispatch.Futures;
import akka.japi.Function2;

public class Calculator2Reduce {
	private ExecutionContext context;

	public Calculator2Reduce(ExecutionContext context) {
		this.context = context;
	}

	public Future<Double> delta(double a, double b, double c) throws Exception {
		return delta(Futures.successful(a), Futures.successful(b), Futures.successful(c)); 
	}

	private Future<Double> delta(Future<Double> a, Future<Double> b, Future<Double> c) {		
		return sub(mul(b, b), mul(Futures.successful(4.0), mul(a, c)));
	}

	private Future<Double> sub(Future<Double> x, Future<Double> y) {
		List<Future<Double>> futures = new ArrayList<>();
		futures.add(x);
		futures.add(y); 
		return Futures.reduce(
			futures, 
			new Function2<Double, Double, Double>() {
				@Override
				public Double apply(Double a, Double b) throws Exception {
					return a + b;
				}				
			}, context
		);
	}	
	
	private Future<Double> mul(Future<Double> x, final Future<Double> y) {
		List<Future<Double>> futures = new ArrayList<>();
		futures.add(x);
		futures.add(y); 
		return Futures.reduce(
			futures, 
			new Function2<Double, Double, Double>() {
				@Override
				public Double apply(Double a, Double b) throws Exception {
					return a * b;
				}				
			}, context
		);
	}
	
	public static void main(String[] args) throws Exception {
		long t = System.currentTimeMillis();
		ExecutionContext context = ExecutionContexts.global();
		Calculator2Reduce calculator = new Calculator2Reduce(context);
		Future<Double> delta = calculator.delta(1, 3, 2);
		double deltaResult = Await.result(delta, Duration.create(1, TimeUnit.MINUTES));
		t = System.currentTimeMillis() - t;
		System.out.println("Elapsed time: " + t);
		System.out.println("Delta: " + deltaResult);
	}
}