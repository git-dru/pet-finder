package pet.finder.petfinder.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import pet.finder.petfinder.model.response.OrganizationResDTO;
import pet.finder.petfinder.util.JsonFileUtil;

/**
 * Service class to manage organization data operations.
 */
@Service
public class OrganizationService {
    private ObjectMapper objectMapper;

    public OrganizationService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Retrieves a list of all organizations from a JSON file.
     *
     * @return List of OrganizationResDTO representing all organizations.
     */
    public List<OrganizationResDTO> findAll() {
        JsonNode response = JsonFileUtil.readJsonFromFile("mockResponse/organizations.json");

        // Extract the "organizations" array node
        JsonNode organizations = response.get("organizations");

        // Convert the "organizations" array node to a list of OrganizationDTO
        return objectMapper.convertValue(organizations, new TypeReference<List<OrganizationResDTO>>() {
        });
    }

    /**
     * Retrieves a specific organization by ID from a JSON file.
     *
     * @param id The ID of the organization to retrieve.
     * @return OrganizationResDTO representing the organization with the specified
     *         ID.
     */
    public OrganizationResDTO get(final long id) {
        JsonNode response = JsonFileUtil.readJsonFromFile("mockResponse/organization.json");

        // Extract the "organizations" array node
        JsonNode organizations = response.get("organization");

        // Convert the "organizations" array node to a list of OrganizationDTO
        return objectMapper.convertValue(organizations, new TypeReference<OrganizationResDTO>() {
        });
    }
}
