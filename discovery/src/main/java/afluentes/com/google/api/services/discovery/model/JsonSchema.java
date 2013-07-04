package afluentes.com.google.api.services.discovery.model;

import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class JsonSchema {
    public final String $ref;
    public final JsonSchema additionalProperties;
    public final String default_;
    public final String description;
    public final List<String> enum_;    
    public final List<String> enumDescriptions;
    public final String format;
    public final String id;
    public final JsonSchema items;
    public final String location;
    public final Map<String, JsonSchema> properties;
    public final Boolean readOnly;
    public final boolean required;
    public final String type;

    @JsonCreator
    public JsonSchema(@JsonProperty("$ref") final String $ref,
                      @JsonProperty("additionalProperties") final JsonSchema additionalProperties,
                      @JsonProperty("default") final String default_,
                      @JsonProperty("description") final String description,
                      @JsonProperty("enum") final List<String> enum_,
                      @JsonProperty("enumDescriptions") final List<String> enumDescriptions,
                      @JsonProperty("format") final String format,
                      @JsonProperty("id") final String id,
                      @JsonProperty("items") final JsonSchema items,
                      @JsonProperty("location") final String location,
                      @JsonProperty("properties") final Map<String, JsonSchema> properties,
                      @JsonProperty("readOnly") final Boolean readOnly,
                      @JsonProperty("required") final boolean required,
                      @JsonProperty("type") final String type) {
        this.$ref = $ref;
        this.additionalProperties = additionalProperties;
        this.default_ = default_;        
        this.description = description;
        this.enum_ = enum_;        
        this.enumDescriptions = enumDescriptions;
        this.format = format;
        this.id = id;
        this.items = items;
        this.location = location;
        this.properties = properties;
        this.readOnly = readOnly;
        this.required = required;
        this.type = type;    	
    }
}