package afluentes.middleware.test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import afluentes.core.api.IEvaluation;

public class CalculatorTest {
	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		String uri = "http://localhost/api/calculator";
		ICalculatorResource calculator = new CalculatorResourceImpl(client, uri);

		double z;

		z = calculator.sum(1, 2);
		System.out.println(z);

		z = calculator.difference(1, 2);
		System.out.println(z);

		z = calculator.product(1, 2);
		System.out.println(z);

		z = calculator.quotient(1, 2);
		System.out.println(z);

		ICalculatorAsyncResource asyncCalculator = new CalculatorAsyncResourceImpl(
				client, uri);
		IEvaluation<Double> w = asyncCalculator.sum(1.0, 2.0);
		System.out.println(w.y());
	}
}