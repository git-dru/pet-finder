package pet.finder.petfinder.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Represents a response data transfer object (DTO) for organization details.
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrganizationResDTO {
    private String id;
    private String name;
    private String email;
    private String phone;
    private Address address;
    private Hours hours;
    private String url;
    private String website;
    private String missionStatement;
    private Adoption adoption;
    private SocialMedia socialMedia;
    private List<Photo> photos;
    private Double distance;
    private Links links;

    /**
     * Nested class representing address details of an organization.
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

    /**
     * Nested class representing operating hours of an organization.
     */
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Hours {
        private String monday;
        private String tuesday;
        private String wednesday;
        private String thursday;
        private String friday;
        private String saturday;
        private String sunday;
    }

    /**
     * Nested class representing adoption policies and URLs of an organization.
     */
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Adoption {
        private String policy;
        private String url;
    }

    /**
     * Nested class representing social media URLs of an organization.
     */
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SocialMedia {
        private String facebook;
        private String twitter;
        private String youtube;
        private String instagram;
        private String pinterest;
    }

    /**
     * Nested class representing photo URLs of an organization.
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
     * Nested class representing links related to an organization.
     */
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Links {
        private Link self;
        private Link animals;

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
