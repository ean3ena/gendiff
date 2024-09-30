package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;

public class Parser {

    public static Map<String, Object> parse(String fileContent, String fileExtension) throws Exception {

        return switch (fileExtension) {
            case "json" -> parseJson(fileContent);
            case "yml", "yaml" -> parseYaml(fileContent);
            default -> throw new Exception("Неверное расширение файла - " + fileExtension);
        };
    }

    private static Map<String, Object> parseJson(String fileContent) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(fileContent, new TypeReference<>() { });
    }

    private static Map<String, Object> parseYaml(String fileContent) throws Exception {

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(fileContent, new TypeReference<>() { });
    }
}
