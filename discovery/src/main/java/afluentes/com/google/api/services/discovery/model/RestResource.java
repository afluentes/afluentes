package afluentes.com.google.api.services.discovery.model;

import java.util.Map;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class RestResource {
    public final Map<String, RestMethod> methods;
    public final Map<String, RestResource> resources;

    @JsonCreator
    public RestResource(@JsonProperty("methods") final Map<String, RestMethod> methods,
                        @JsonProperty("resources") final Map<String, RestResource> resources) {
        this.methods = methods;
        this.resources = resources;
    }
}