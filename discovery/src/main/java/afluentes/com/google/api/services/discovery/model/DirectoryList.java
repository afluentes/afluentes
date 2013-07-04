package afluentes.com.google.api.services.discovery.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class DirectoryList {
    public final String discoveryVersion;
    public final List<Items> items;
    public final String kind;

    @JsonCreator
    public DirectoryList(@JsonProperty("discoveryVersion") String discoveryVersion,
                         @JsonProperty("items") List<Items> items,
                         @JsonProperty("kind") String kind) {
        this.discoveryVersion = discoveryVersion;
        this.items = items;
        this.kind = kind;
    }

    public static class Items {
        public final String description;
        public final String discoveryLink;
        public final String discoveryRestUrl;
        public final String documentationLink;
        public final Icons icons;
        public final String id;
        public final String kind;
        public final List<String> labels;
        public final String name;
        public final boolean preferred;
        public final String title;
        public final String version;

        public Items(@JsonProperty("description") final String description,
                     @JsonProperty("discoveryLink") final String discoveryLink,
                     @JsonProperty("discoveryRestUrl") final String discoveryRestUrl,
                     @JsonProperty("documentationLink") final String documentationLink,
                     @JsonProperty("icons") final Icons icons,
                     @JsonProperty("id") final String id,
                     @JsonProperty("kind") final String kind,
                     @JsonProperty("labels") final List<String> labels,
                     @JsonProperty("name") final String name,
                     @JsonProperty("preferred") final boolean preferred,
                     @JsonProperty("title") final String title,
                     @JsonProperty("version") final String version) {
            this.description = description;
            this.discoveryLink = discoveryLink;
            this.discoveryRestUrl = discoveryRestUrl;
            this.documentationLink = documentationLink;
            this.icons = icons;
            this.id = id;
            this.kind = kind;
            this.labels = labels;
            this.name = name;
            this.preferred = preferred;
            this.title = title;
            this.version = version;
        }
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