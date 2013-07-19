package afluentes.middleware.test;

import java.util.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import afluentes.middleware.ws.rs.*;


@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ITestResource {
	
	@Path("calculator")
	ICalculatorResource getCalculator(
	);	

}
