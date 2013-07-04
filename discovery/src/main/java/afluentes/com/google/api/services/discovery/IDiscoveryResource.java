package afluentes.com.google.api.services.discovery;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import afluentes.com.google.api.services.discovery.model.DirectoryList;
import afluentes.com.google.api.services.discovery.model.RestDescription;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface IDiscoveryResource {
    @Path("apis")
    IApisResource apis();

    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    interface IApisResource {
        @GET
        DirectoryList list(@QueryParam("name") final String name,
                           @QueryParam("preferred") final boolean preferred,
                           @QueryParam("fields") final String fields);

        @GET
        @Path("{api}/{version}/rest")
        RestDescription getRest(@PathParam("api") final String api,
                                @PathParam("version") final String version,
                                @QueryParam("fields") final String fields);
    }
}