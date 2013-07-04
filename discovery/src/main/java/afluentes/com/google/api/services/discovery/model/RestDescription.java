package afluentes.com.google.api.services.discovery.model;

import java.util.Map;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class RestDescription {
    public final String basePath;
    public final String baseUrl;
    public final String batchPath;
    public final String description;
    public final String discoveryVersion;
    public final String documentationLink;
    public final String etag;
    public final Icons icons;
    public final String id;
    public final String kind;
    public final String name;
    public final String ownerDomain;
    public final String ownerName;
    public final Map<String, JsonSchema> parameters;
    public final String protocol;
    public final Map<String, RestResource> resources;
    public final String revision;
    public final String rootUrl;
    public final Map<String, JsonSchema> schemas;
    public final String servicePath;
    public final String title;

    public final String version;

    @JsonCreator
    public RestDescription(@JsonProperty("basePath") final String basePath,
    					   @JsonProperty("baseUrl") final String baseUrl,
    					   @JsonProperty("batchPath") final String batchPath,
    					   @JsonProperty("description") final String description,
    					   @JsonProperty("discoveryVersion") final String discoveryVersion,
    					   @JsonProperty("documentationLink") final String documentationLink,
    					   @JsonProperty("etag") final String etag,
    					   @JsonProperty("icons") final Icons icons,
    					   @JsonProperty("id") final String id,
    					   @JsonProperty("kind") final String kind,
    					   @JsonProperty("name") final String name,
    					   @JsonProperty("ownerDomain") final String ownerDomain,
    					   @JsonProperty("ownerName") final String ownerName,
    					   @JsonProperty("parameters") final Map<String, JsonSchema> parameters,
    					   @JsonProperty("protocol") final String protocol,
    					   @JsonProperty("resources") final Map<String, RestResource> resources,
    					   @JsonProperty("revision") final String revision,
    					   @JsonProperty("rootUrl") final String rootUrl,
    					   @JsonProperty("schemas") final Map<String, JsonSchema> schemas,
    					   @JsonProperty("servicePath") final String servicePath,
    					   @JsonProperty("title") final String title,
    					   @JsonProperty("version") final String version) {
        this.basePath = basePath;
        this.baseUrl = baseUrl;
        this.batchPath = batchPath;
        this.description = description;
        this.discoveryVersion = discoveryVersion;
        this.documentationLink = documentationLink;
        this.etag = etag;
        this.icons = icons;
        this.id = id;
        this.kind = kind;
        this.name = name;
        this.ownerDomain = ownerDomain;
        this.ownerName = ownerName;
        this.parameters = parameters;
        this.protocol = protocol;
        this.resources = resources;
        this.revision = revision;
        this.rootUrl = rootUrl;
        this.schemas = schemas;
        this.servicePath = servicePath;
        this.title = title;
        this.version = version;    	
    }
    
    public static class Icons {
        public final String x16;
        public final String x32;

        public Icons(@JsonProperty("x16") final String x16,
                     @JsonProperty("x32") final String x32) {
            this.x16 = x16;
            this.x32 = x32;
        }
    }    
}