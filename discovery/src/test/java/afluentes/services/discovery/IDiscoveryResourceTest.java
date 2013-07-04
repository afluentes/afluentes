package afluentes.services.discovery;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.jboss.resteasy.client.ProxyFactory;
import org.junit.Test;

import afluentes.com.google.api.services.discovery.DiscoveryResource;
import afluentes.com.google.api.services.discovery.IDiscoveryResource;
import afluentes.com.google.api.services.discovery.model.DirectoryList;
import afluentes.com.google.api.services.discovery.model.RestDescription;
import afluentes.core.api.IEvaluation;
import afluentes.core.impl.Constant;

public class IDiscoveryResourceTest {
    @Test
    public void testApis() {
        IDiscoveryResource discovery = ProxyFactory.create(IDiscoveryResource.class, "https://www.googleapis.com/discovery/v1");
        DirectoryList list = discovery.apis().list(null, true, null);
    }

    @Test
    public void testGetRest() {
        IDiscoveryResource discovery = ProxyFactory.create(IDiscoveryResource.class, "https://www.googleapis.com/discovery/v1");
        RestDescription description = discovery.apis().getRest("discovery", "v1", null);
    }

    @Test
    public void testStub() {
        Client client = ClientBuilder.newClient();
        DiscoveryResource resource = new DiscoveryResource(client, "https://www.googleapis.com/discovery/v1");
        IEvaluation<DirectoryList> evaluation = resource.apis().list.y(new Constant(null), new Constant(null), new Constant(null));
        System.out.println(evaluation.y());
        System.out.println(evaluation.y());
    }
}