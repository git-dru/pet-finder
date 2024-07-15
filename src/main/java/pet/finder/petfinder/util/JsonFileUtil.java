package pet.finder.petfinder.util;

import java.io.InputStream;

import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class to read JSON data from files on the classpath.
 */
public class JsonFileUtil {
    private static final Logger logger = LoggerFactory.getLogger(JsonFileUtil.class);

    /**
     * Reads JSON data from a file located on the classpath.
     *
     * @param filePath the path to the JSON file on the classpath
     * @return a JsonNode containing the parsed JSON data, or an empty object node
     *         if there was an error
     */
    public static JsonNode readJsonFromFile(String filePath) {
        try {
            InputStream inputStream = new ClassPathResource(filePath).getInputStream();
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readTree(inputStream);
        } catch (Exception e) {
            logger.error("Error reading JSON from file: {}", filePath, e);
            // Return an empty object node
            return JsonNodeFactory.instance.objectNode();
        }
    }
}
