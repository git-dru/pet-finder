package pet.finder.petfinder.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Represents a response data transfer object (DTO) for animal details.
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnimalResDTO {
    private int id;
    private String organizationId;
    private String url;
    private String type;
    private String species;
    private Breeds breeds;
    private Colors colors;
    private String age;
    private String gender;
    private String size;
    private String coat;
    private String name;
    private String description;
    private List<Photo> photos;
    private List<Video> videos;
    private String status;
    private Attributes attributes;
    private Environment environment;
    private List<String> tags;
    private Contact contact;
    private OffsetDateTime publishedAt;
    private Double distance;
    private Links links;

    /**
     * Nested class representing animal breeds.
     */
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Breeds {
        private String primary;
        private String secondary;
        private boolean mixed;
        private boolean unknown;
    }

    /**
     * Nested class representing animal colors.
     */
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Colors {
        private String primary;
        private String secondary;
        private String tertiary;
    }

    /**
     * Nested class representing animal photos.
     */
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Photo {
        private String small;
        private String medium;
        private String large;
        private String full;
    }

    /**
     * Nested class representing animal videos.
     */
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Video {
        private String embed;
    }

    /**
     * Nested class representing animal attributes.
     */
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Attributes {
        private boolean spayedNeutered;
        private boolean houseTrained;
        private boolean declawed;
        private boolean specialNeeds;
        private boolean shotsCurrent;
    }

    /**
     * Nested class representing animal environment preferences.
     */
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Environment {
        private boolean children;
        private boolean dogs;
        private boolean cats;
    }

    /**
     * Nested class representing animal contact information.
     */
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Contact {
        private String email;
        private String phone;
        private Address address;

        /**
         * Nested class representing contact address details.
         */
        @Getter
        @Setter
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Address {
            private String address1;
            private String address2;
            private String city;
            private String state;
            private String postcode;
            private String country;
        }
    }

    /**
     * Nested class representing links related to the animal.
     */
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Links {
        private Link self;
        private Link type;
        private Link organization;

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
