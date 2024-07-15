package pet.finder.petfinder.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * JacksonConfig is a Spring configuration class that customizes the Jackson
 * ObjectMapper
 * used for JSON serialization and deserialization.
 */
@Configuration
public class JacksonConfig {

    /**
     * Provides a bean to customize the Jackson ObjectMapper builder.
     *
     * @return Jackson2ObjectMapperBuilderCustomizer bean for customizing
     *         ObjectMapper features
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonCustomizer() {
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder
                .featuresToDisable(
                        DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                        DeserializationFeature.ACCEPT_FLOAT_AS_INT,
                        SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

}
