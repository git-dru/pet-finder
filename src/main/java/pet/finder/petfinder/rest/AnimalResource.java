package pet.finder.petfinder.rest;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pet.finder.petfinder.model.response.AnimalResDTO;
import pet.finder.petfinder.service.AnimalService;

/**
 * REST Controller for handling animal-related operations.
 */
@RestController
@RequestMapping(value = "/api/animals", produces = MediaType.APPLICATION_JSON_VALUE)
public class AnimalResource {
    private final AnimalService animalService;

    /**
     * Constructor injection of AnimalService.
     *
     * @param animalService The AnimalService instance to use.
     */
    public AnimalResource(AnimalService animalService) {
        this.animalService = animalService;
    }

    /**
     * Endpoint to retrieve all animals.
     *
     * @return ResponseEntity containing a list of AnimalResDTOs.
     */
    @GetMapping
    public ResponseEntity<List<AnimalResDTO>> index() {
        return ResponseEntity.ok(animalService.findAll());
    }

    /**
     * Endpoint to retrieve a specific animal by ID.
     *
     * @param id The ID of the animal to retrieve.
     * @return ResponseEntity containing the AnimalResDTO of the specified ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AnimalResDTO> index(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(animalService.get(id));
    }
}
