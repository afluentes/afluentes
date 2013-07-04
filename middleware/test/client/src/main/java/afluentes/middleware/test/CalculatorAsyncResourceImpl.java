package afluentes.middleware.test;

import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;

import afluentes.core.api.IAsynchronousFunction2;
import afluentes.core.api.ICallback;
import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator2;
import afluentes.core.impl.AsynchronousEvaluator2;
import afluentes.core.impl.Constant;
import afluentes.middleware.ws.rs.client.ICallbackAdapter;

@SuppressWarnings("unused")
public class CalculatorAsyncResourceImpl implements ICalculatorAsyncResource {
	private final Client client;
	private final String uri;

	private final IEvaluator2<Double, Double, Double> sum;
	private final IEvaluator2<Double, Double, Double> difference;
	private final IEvaluator2<Double, Double, Double> product;
	private final IEvaluator2<Double, Double, Double> quotient;

	public CalculatorAsyncResourceImpl(final Client client, final String uri) {
		this.client = client;
		this.uri = uri;

		this.sum = new AsynchronousEvaluator2<>(
				new IAsynchronousFunction2<Double, Double, Double>() {
					@Override
					public void y(Double x, Double y, ICallback<Double> callback) {
						WebTarget target = client.target(uri);
						target = target.path("sum");

						target = target.queryParam("x", x);
						target = target.queryParam("y", y);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("GET", new ICallbackAdapter<Double>(
								callback) {
						});
					}
				});

		this.difference = new AsynchronousEvaluator2<>(
				new IAsynchronousFunction2<Double, Double, Double>() {
					@Override
					public void y(Double x, Double y, ICallback<Double> callback) {
						WebTarget target = client.target(uri);
						target = target.path("difference");

						target = target.queryParam("x", x);
						target = target.queryParam("y", y);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("GET", new ICallbackAdapter<Double>(
								callback) {
						});
					}
				});

		this.product = new AsynchronousEvaluator2<>(
				new IAsynchronousFunction2<Double, Double, Double>() {
					@Override
					public void y(Double x, Double y, ICallback<Double> callback) {
						WebTarget target = client.target(uri);
						target = target.path("product");

						target = target.queryParam("x", x);
						target = target.queryParam("y", y);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("GET", new ICallbackAdapter<Double>(
								callback) {
						});
					}
				});

		this.quotient = new AsynchronousEvaluator2<>(
				new IAsynchronousFunction2<Double, Double, Double>() {
					@Override
					public void y(Double x, Double y, ICallback<Double> callback) {
						WebTarget target = client.target(uri);
						target = target.path("quotient");

						target = target.queryParam("x", x);
						target = target.queryParam("y", y);

						Builder request = target.request();
						AsyncInvoker invoker = request.async();
						invoker.method("GET", new ICallbackAdapter<Double>(
								callback) {
						});
					}
				});

	}

	@Override
	public IEvaluation<Double> sum(final Double x, final Double y) {
		return sum(new Constant<>(x), new Constant<>(y));
	}

	@Override
	public IEvaluation<Double> sum(final IEvaluation<Double> x,
			final IEvaluation<Double> y) {
		return sum.y(x, y);
	}

	@Override
	public IEvaluation<Double> difference(final Double x, final Double y) {
		return difference(new Constant<>(x), new Constant<>(y));
	}

	@Override
	public IEvaluation<Double> difference(final IEvaluation<Double> x,
			final IEvaluation<Double> y) {
		return difference.y(x, y);
	}

	@Override
	public IEvaluation<Double> product(final Double x, final Double y) {
		return product(new Constant<>(x), new Constant<>(y));
	}

	@Override
	public IEvaluation<Double> product(final IEvaluation<Double> x,
			final IEvaluation<Double> y) {
		return product.y(x, y);
	}

	@Override
	public IEvaluation<Double> quotient(final Double x, final Double y) {
		return quotient(new Constant<>(x), new Constant<>(y));
	}

	@Override
	public IEvaluation<Double> quotient(final IEvaluation<Double> x,
			final IEvaluation<Double> y) {
		return quotient.y(x, y);
	}

}
