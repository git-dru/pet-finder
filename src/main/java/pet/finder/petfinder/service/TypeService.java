package pet.finder.petfinder.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import pet.finder.petfinder.model.response.TypeResDTO;
import pet.finder.petfinder.util.JsonFileUtil;

@Service
public class TypeService {
    private ObjectMapper objectMapper;

    public TypeService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Retrieves a list of all types from a JSON file.
     *
     * @return List of TypeResDTO representing all types.
     */
    public List<TypeResDTO> findAll() {
        JsonNode response = JsonFileUtil.readJsonFromFile("mockResponse/types.json");

        // Extract the "types" array node
        JsonNode types = response.get("types");

        // Convert the "types" array node to a list of TypeDTO
        return objectMapper.convertValue(types, new TypeReference<List<TypeResDTO>>() {
        });
    }

    /**
     * Retrieves a specific type by name from a JSON file.
     *
     * @param type The name of the type to retrieve.
     * @return TypeResDTO representing the type with the specified name.
     */
    public TypeResDTO get(final String type) {
        JsonNode response = JsonFileUtil.readJsonFromFile("mockResponse/type.json");

        // Extract the "types" array node
        JsonNode types = response.get("type");

        // Convert the "types" array node to a list of TypeDTO
        return objectMapper.convertValue(types, new TypeReference<TypeResDTO>() {
        });
    }
}
