package afluentes.middleware.test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class CalculatorResourceImpl implements ICalculatorResource {
   private final Client client;
   private final String uri;

   public CalculatorResourceImpl(final Client client, 
                                 final String uri) {
      this.client = client;
      this.uri = uri;
   }

   public double sum(final double x, final double y) {
      WebTarget target = client.target(uri);
      target = target.path("sum");

      target = target.queryParam("x", x);
      target = target.queryParam("y", y);
      Builder request = target.request();

      Invocation invocation = request.build("GET");
      Response response = invocation.invoke();
      if (response.getStatus() != Status.OK.getStatusCode()) {
         throw new RuntimeException();
      }
      double responseEntity = response.readEntity(double.class);
      return responseEntity;
   }

   public double difference(final double x, final double y) {
      WebTarget target = client.target(uri);
      target = target.path("difference");

      target = target.queryParam("x", x);
      target = target.queryParam("y", y);
      Builder request = target.request();

      Invocation invocation = request.build("GET");
      Response response = invocation.invoke();
      if (response.getStatus() != Status.OK.getStatusCode()) {
         throw new RuntimeException();
      }
      double responseEntity = response.readEntity(double.class);
      return responseEntity;
   }

   public double product(final double x, final double y) {
      WebTarget target = client.target(uri);
      target = target.path("product");

      target = target.queryParam("x", x);
      target = target.queryParam("y", y);
      Builder request = target.request();

      Invocation invocation = request.build("GET");
      Response response = invocation.invoke();
      if (response.getStatus() != Status.OK.getStatusCode()) {
         throw new RuntimeException();
      }
      double responseEntity = response.readEntity(double.class);
      return responseEntity;
   }

   public double quotient(final double x, final double y) {
      WebTarget target = client.target(uri);
      target = target.path("quotient");

      target = target.queryParam("x", x);
      target = target.queryParam("y", y);
      Builder request = target.request();

      Invocation invocation = request.build("GET");
      Response response = invocation.invoke();
      if (response.getStatus() != Status.OK.getStatusCode()) {
         throw new RuntimeException();
      }
      double responseEntity = response.readEntity(double.class);
      return responseEntity;
   }
}	

