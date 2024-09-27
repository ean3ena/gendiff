package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {

    public static Map<String, Object> parse(String filepath) throws Exception {

        String fileContent = getFileContent(filepath);
        return parseJson(fileContent);
    }

    public static String getFileContent(String filepath) throws Exception {

        Path path = Paths.get(filepath).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new Exception("Файл '" + path + "' не существует");
        }

        return Files.readString(path);
    }

    public static Map<String, Object> parseJson(String fileContent) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(fileContent, new TypeReference<>() { });
    }
}
