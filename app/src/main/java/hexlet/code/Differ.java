package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Differ {

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String generate(String filepath1, String filepath2, String format) throws Exception {

        String fileContent1 = getFileContent(filepath1);
        String fileExtension1 = getFileExtension(filepath1);
        Map<String, Object> mapFileContent1 = Parser.parse(fileContent1, fileExtension1);

        String fileContent2 = getFileContent(filepath2);
        String fileExtension2 = getFileExtension(filepath2);
        Map<String, Object> mapFileContent2 = Parser.parse(fileContent2, fileExtension2);

        List<Map<String, Object>> diff = DiffBuilder.getDiff(mapFileContent1, mapFileContent2);

        return Formatter.format(diff, format);
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
}
