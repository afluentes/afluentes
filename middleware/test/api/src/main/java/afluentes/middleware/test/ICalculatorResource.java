package afluentes.middleware.test;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ICalculatorResource {
	@GET
	@Path("sum")
	double sum(@QueryParam("x") double x, @QueryParam("y") double y);

	@GET
	@Path("difference")
	double difference(@QueryParam("x") double x, @QueryParam("y") double y);

	@GET
	@Path("product")
	double product(@QueryParam("x") double x, @QueryParam("y") double y);

	@GET
	@Path("quotient")
	double quotient(@QueryParam("x") double x, @QueryParam("y") double y);

}
