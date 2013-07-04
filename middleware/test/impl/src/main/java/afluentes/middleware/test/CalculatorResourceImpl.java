package afluentes.middleware.test;

import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

@Path("calculator")
@Component
public class CalculatorResourceImpl implements ICalculatorResource {
	@Override
	public double sum(final double x, final double y) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public double difference(final double x, final double y) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public double product(final double x, final double y) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public double quotient(final double x, final double y) {
		// TODO
		throw new UnsupportedOperationException();
	}
}