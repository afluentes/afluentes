package afluentes.middleware.test;

import java.util.Date;

import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

@Path("calculator")
@Component
public class CalculatorResourceImpl implements ICalculatorResource {
	@Override
	public double sum(final double x, final double y) {
		return x + y;
	}

	@Override
	public double difference(final double x, final double y) {
		System.out.println(new Date() + " - difference: " + x + ", " + y);
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			throw new RuntimeException();
		}		
		return x - y;
	}

	@Override
	public double product(final double x, final double y) {
		System.out.println(new Date() + " - product: " + x + ", " + y);
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return x * y;
	}

	@Override
	public double quotient(final double x, final double y) {		
		return x / y;
	}
}