package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    private final String fixturesDirectoryPath = "src/test/resources/fixtures/";

    @Test
    public void testJsonGenDiffFormatDefault() throws Exception {
        var actual = Differ.generate(
                fixturesDirectoryPath + "file1.json",
                fixturesDirectoryPath + "file2.json"
        );
        var expected = getExpectedFileContent("stylish12.txt");
        assertEquals(expected, actual);
    }

    @Test
    public void testJsonGenDiffFormatStylish() throws Exception {
        var actual = Differ.generate(
                fixturesDirectoryPath + "file1.json",
                fixturesDirectoryPath + "file2.json",
                "stylish"
        );
        var expected = getExpectedFileContent("stylish12.txt");
        assertEquals(expected, actual);
    }

    private String getExpectedFileContent(String filename) throws Exception {
        Path fixturePath = getFixturePath(filename);
        return Files.readString(fixturePath);
    }

    private Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }
}
