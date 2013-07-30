package afluentes.middleware.test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class Delta {		
	public static void main(String[] args) {		
		double a = 1;
		double b = -3;
		double c = 2;
				
		Client client = ClientBuilder.newClient();
		ICalculatorResource calculator = new CalculatorResourceImpl(client, "http://localhost/api/calculator");
		long t = System.currentTimeMillis();
		double delta = calculator.difference(
			calculator.product(b, b), 
			calculator.product(4, calculator.product(a, c))
		);
		t = System.currentTimeMillis() - t;
		System.out.println("Elapse time: " + t);
		System.out.println("Delta: " + delta);
	}
}