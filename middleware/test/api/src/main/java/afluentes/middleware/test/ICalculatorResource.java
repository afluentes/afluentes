package afluentes.middleware.test;

import java.util.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import afluentes.middleware.ws.rs.*;


@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ICalculatorResource {
	@GET
	@Path("sum")
	double sum(
			@QueryParam("x") double x,
			@QueryParam("y") double y
	);	
	@GET
	@Path("difference")
	double difference(
			@QueryParam("x") double x,
			@QueryParam("y") double y
	);	
	@GET
	@Path("product")
	double product(
			@QueryParam("x") double x,
			@QueryParam("y") double y
	);	
	@GET
	@Path("quotient")
	double quotient(
			@QueryParam("x") double x,
			@QueryParam("y") double y
	);	

}
