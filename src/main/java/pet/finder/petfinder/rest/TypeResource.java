package pet.finder.petfinder.rest;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pet.finder.petfinder.model.response.TypeResDTO;
import pet.finder.petfinder.service.TypeService;

/**
 * REST Controller for handling pet types-related operations.
 */
@RestController
@RequestMapping(value = "/api/types", produces = MediaType.APPLICATION_JSON_VALUE)
public class TypeResource {
    private final TypeService typeService;

    /**
     * Constructor injection of TypeService.
     *
     * @param typeService The TypeService instance to use.
     */
    public TypeResource(TypeService typeService) {
        this.typeService = typeService;
    }

    /**
     * Endpoint to retrieve all pet types.
     *
     * @return ResponseEntity containing a list of TypeResDTOs.
     */
    @GetMapping
    public ResponseEntity<List<TypeResDTO>> index() {
        return ResponseEntity.ok(typeService.findAll());
    }

    /**
     * Endpoint to retrieve pet type by name.
     *
     * @param type The name of the pet type to retrieve.
     * @return ResponseEntity containing the TypeResDTO corresponding to the given
     *         type name.
     */
    @GetMapping("/{type}")
    public ResponseEntity<TypeResDTO> index(@PathVariable(name = "type") final String type) {
        return ResponseEntity.ok(typeService.get(type));
    }
}
