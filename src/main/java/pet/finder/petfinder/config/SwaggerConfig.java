package pet.finder.petfinder.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    /**
     * Defines an OpenAPI bean for Swagger documentation.
     *
     * @return OpenAPI instance configured with schemas for error responses
     */
    @Bean
    public OpenAPI openApiSpec() {
        return new OpenAPI()
                .components(new Components()
                        .addSchemas("ApiErrorResponse", new ObjectSchema()
                                .addProperty("status", new Schema<Integer>().type("integer"))
                                .addProperty("code", new Schema<String>().type("string"))
                                .addProperty("message", new Schema<String>().type("string"))
                                .addProperty("fieldErrors", new ArraySchema().items(
                                        new Schema<ArraySchema>().$ref("ApiFieldError"))))
                        .addSchemas("ApiFieldError", new ObjectSchema()
                                .addProperty("code", new Schema<String>().type("string"))
                                .addProperty("message", new Schema<String>().type("string"))
                                .addProperty("property", new Schema<String>().type("string"))
                                .addProperty("rejectedValue", new ObjectSchema())
                                .addProperty("path", new Schema<String>().type("string")))
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
    }

    /**
     * Customizes operations in Swagger to add error response types.
     *
     * @return OperationCustomizer that adds error response schema to each operation
     */
    @Bean
    public OperationCustomizer operationCustomizer() {
        // add error type to each operation
        return (operation, handlerMethod) -> {
            operation.getResponses().addApiResponse("4xx/5xx", new ApiResponse()
                    .description("Error")
                    .content(new io.swagger.v3.oas.models.media.Content().addMediaType("*/*", new MediaType().schema(
                            new Schema<MediaType>().$ref("ApiErrorResponse")))));

            return operation;
        };
    }
}
