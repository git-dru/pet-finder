package pet.finder.petfinder.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import pet.finder.petfinder.model.response.BreedResDTO;
import pet.finder.petfinder.util.JsonFileUtil;

/**
 * Service class to manage breed data operations.
 */
@Service
public class BreedService {
    private ObjectMapper objectMapper;

    public BreedService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Retrieves a list of all breeds from a JSON file.
     *
     * @return List of BreedResDTO representing all breeds.
     */
    public List<BreedResDTO> findAll() {
        JsonNode response = JsonFileUtil.readJsonFromFile("mockResponse/breeds.json");

        // Extract the "breeds" array node
        JsonNode breeds = response.get("breeds");

        // Convert the "breeds" array node to a list of BreedDTO
        return objectMapper.convertValue(breeds, new TypeReference<List<BreedResDTO>>() {
        });
    }
}
