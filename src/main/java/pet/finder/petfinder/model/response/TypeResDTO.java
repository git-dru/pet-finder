package pet.finder.petfinder.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a response data transfer object (DTO) for pet type details.
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TypeResDTO {
    private String name;
    private List<String> coats;
    private List<String> colors;
    private List<String> genders;
    private Links links;

    /**
     * Nested class representing links related to pet types.
     */
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Links {
        private Link self;
        private Link breeds;

        /**
         * Nested class representing a link.
         */
        @Getter
        @Setter
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Link {
            private String href;
        }
    }
}
