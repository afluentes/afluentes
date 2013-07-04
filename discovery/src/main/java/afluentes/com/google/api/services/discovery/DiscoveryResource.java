package afluentes.com.google.api.services.discovery;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import afluentes.com.google.api.services.discovery.model.DirectoryList;
import afluentes.core.api.IEvaluator3;
import afluentes.core.api.ISynchronousFunction3;
import afluentes.core.impl.SynchronousEvaluator3;

public class DiscoveryResource {
    private final Client client;
    private final String uri;

    public DiscoveryResource(final Client client,
                             final String uri) {
        this.client = client;
        this.uri = uri;
    }

    public ApisResource apis() {
        return new ApisResource(client, uri + "/apis");
    }

    public static class ApisResource {
        public final IEvaluator3<String, Boolean, String, DirectoryList> list;

        public ApisResource(final Client client,
                            final String uri) {
/*        	
            this.list = new SynchronousEvaluator3<>((name, preferred, fields) -> {
                WebTarget target = client.target(uri);
                if (name != null) {
                    target = target.queryParam("name", name);
                }
                if (preferred != null) {
                    target = target.queryParam("preferred", preferred);
                }
                if (fields != null) {
                    target = target.queryParam("fields", fields);
                }
                Response response = target.request().get();
                return response.readEntity(DirectoryList.class);
            });
*/

            this.list = new SynchronousEvaluator3<>(new ISynchronousFunction3<String, Boolean, String, DirectoryList>() {
				@Override
				public DirectoryList y(String name, Boolean preferred, String fields) {
	                WebTarget target = client.target(uri);
	                if (name != null) {
	                    target = target.queryParam("name", name);
	                }
	                if (preferred != null) {
	                    target = target.queryParam("preferred", preferred);
	                }
	                if (fields != null) {
	                    target = target.queryParam("fields", fields);
	                }
	                Response response = target.request().get();
	                return response.readEntity(DirectoryList.class);
				}            	
            });
        }
    }
}