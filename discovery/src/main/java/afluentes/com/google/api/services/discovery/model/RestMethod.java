package afluentes.com.google.api.services.discovery.model;

import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class RestMethod {
    public final String description;
    public final String httpMethod;
    public final String id;
    public final List<String> parameterOrder;
    public final Map<String, JsonSchema> parameters;
    public final String path;
    public final Response response;

    @JsonCreator
    public RestMethod(@JsonProperty("description") final String description,
                      @JsonProperty("httpMethod") final String httpMethod,
                      @JsonProperty("id") final String id,                      
                      @JsonProperty("parameterOrder") final List<String> parameterOrder,
                      @JsonProperty("parameters") final Map<String, JsonSchema> parameters,
                      @JsonProperty("path") final String path,
                      @JsonProperty("response") final Response response) {
        this.description = description;
        this.httpMethod = httpMethod;
        this.id = id;
        this.parameterOrder = parameterOrder;
        this.parameters = parameters;
        this.path = path;
        this.response = response;
    }
    
    public static class Response {
        public final String $ref;

        public Response(@JsonProperty("$ref") final String $ref) {
            this.$ref = $ref;
        }
    }    
}