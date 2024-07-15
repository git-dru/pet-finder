package pet.finder.petfinder.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import pet.finder.petfinder.model.response.AnimalResDTO;
import pet.finder.petfinder.util.JsonFileUtil;

/**
 * Service class to manage animal data operations.
 */
@Service
public class AnimalService {
    private ObjectMapper objectMapper;

    public AnimalService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Retrieves a list of all animals from a JSON file.
     *
     * @return List of AnimalResDTO representing all animals.
     */
    public List<AnimalResDTO> findAll() {
        JsonNode response = JsonFileUtil.readJsonFromFile("mockResponse/animals.json");

        // Extract the "animals" array node
        JsonNode animals = response.get("animals");

        // Convert the "animals" array node to a list of AnimalDTO
        return objectMapper.convertValue(animals, new TypeReference<List<AnimalResDTO>>() {
        });
    }

    /**
     * Retrieves an animal by its ID from a JSON file.
     *
     * @param id The ID of the animal to retrieve.
     * @return AnimalResDTO representing the animal with the specified ID.
     */
    public AnimalResDTO get(final long id) {
        JsonNode response = JsonFileUtil.readJsonFromFile("mockResponse/animal.json");

        // Extract the "animals" array node
        JsonNode animals = response.get("animal");

        // Convert the "animals" array node to a list of AnimalDTO
        return objectMapper.convertValue(animals, new TypeReference<AnimalResDTO>() {
        });
    }
}
