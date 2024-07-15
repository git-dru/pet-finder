package pet.finder.petfinder.rest;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pet.finder.petfinder.model.response.OrganizationResDTO;
import pet.finder.petfinder.service.OrganizationService;

/**
 * REST Controller for handling organization-related operations.
 */
@RestController
@RequestMapping(value = "/api/organizations", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrganizationResource {
    private final OrganizationService organizationService;

    /**
     * Constructor injection of OrganizationService.
     *
     * @param organizationService The OrganizationService instance to use.
     */
    public OrganizationResource(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    /**
     * Endpoint to retrieve all organizations.
     *
     * @return ResponseEntity containing a list of OrganizationResDTOs.
     */
    @GetMapping
    public ResponseEntity<List<OrganizationResDTO>> index() {
        return ResponseEntity.ok(organizationService.findAll());
    }

    /**
     * Endpoint to retrieve a specific organization by ID.
     *
     * @param id The ID of the organization to retrieve.
     * @return ResponseEntity containing the OrganizationResDTO corresponding to the
     *         given ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrganizationResDTO> index(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(organizationService.get(id));
    }
}
