package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String generate(String filepath1, String filepath2, String format) throws Exception {

        String contentFile1 = getFileData(filepath1);
        String contentFile2 = getFileData(filepath2);

        var mapContentFile1 = contentFileToMap(contentFile1);
        var mapContentFile2 = contentFileToMap(contentFile2);

        return mapContentFile1.toString() + "\n" + mapContentFile2.toString();
    }

    public static String getFileData(String filepath) throws Exception {

        Path path = Paths.get(filepath).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new Exception("Файл '" + path + "' не существует");
        }

        return Files.readString(path);
    }

    public static Map<String, Object> contentFileToMap(String contentFile) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(contentFile, new TypeReference<Map<String, Object>>(){});
    }
}
