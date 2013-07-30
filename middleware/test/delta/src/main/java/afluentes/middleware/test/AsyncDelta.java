package afluentes.middleware.test;

import java.util.concurrent.TimeUnit;

import javax.ws.rs.client.Client;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import afluentes.core.impl.Constant;

public class AsyncDelta {		
	public static void main(String[] args) {		
		double a = 1;
		double b = -3;
		double c = 2;

		ResteasyClientBuilder builder = new ResteasyClientBuilder();
		builder.connectionPoolSize(10);
		Client client = builder.build();
		ICalculatorAsyncResource calculator = new CalculatorAsyncResourceImpl(client, "http://localhost/api/calculator");
		long t = System.currentTimeMillis();		
		double delta = calculator.difference(
			calculator.product(b, b), 
			calculator.product(new Constant<>(4.0), calculator.product(a, c))
		).y(1, TimeUnit.SECONDS);
		t = System.currentTimeMillis() - t;
		System.out.println("Elapse time: " + t);
		System.out.println("Delta: " + delta);
	}
}