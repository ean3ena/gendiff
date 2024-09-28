package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {

    public static Map<String, Object> parse(String filePath) throws Exception {

        String fileContent = getFileContent(filePath);
        String fileExtension = getFileExtension(filePath);

        return switch (fileExtension) {
            case "json" -> parseJson(fileContent);
            case "yml", "yaml" -> parseYaml(fileContent);
            default -> throw new Exception("Файл '" + filePath
                                            + "' имеет неверное расширение");
        };
    }

    private static String getFileExtension(String filePath) {
        int lastIndexOfDot = filePath.lastIndexOf('.');
        return filePath.substring(lastIndexOfDot + 1).toLowerCase();
    }

    private static String getFileContent(String filePath) throws Exception {

        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new Exception("Файл '" + filePath + "' не существует");
        }

        return Files.readString(path);
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
