package pet.finder.petfinder.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a response data transfer object (DTO) for breed details.
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BreedResDTO {
    private String name;
    private Links links;

    /**
     * Nested class representing links related to the breed.
     */
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Links {
        private Link type;

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
