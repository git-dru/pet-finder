package pet.finder.petfinder.rest;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pet.finder.petfinder.model.response.BreedResDTO;
import pet.finder.petfinder.service.BreedService;

/**
 * REST Controller for handling breed-related operations.
 */
@RestController
@RequestMapping(value = "/api/breeds", produces = MediaType.APPLICATION_JSON_VALUE)
public class BreedResource {
    private final BreedService breedService;

    /**
     * Constructor injection of BreedService.
     *
     * @param breedService The BreedService instance to use.
     */
    public BreedResource(BreedService breedService) {
        this.breedService = breedService;
    }

    /**
     * Endpoint to retrieve all breeds.
     *
     * @return ResponseEntity containing a list of BreedResDTOs.
     */
    @GetMapping
    public ResponseEntity<List<BreedResDTO>> index() {
        return ResponseEntity.ok(breedService.findAll());
    }
}
