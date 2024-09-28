package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AppTest {

    private final String fixturesDirectoryPath = "src/test/resources/fixtures/";

    @Test
    public void testGenDiffWrongFormat() throws Exception {
        var actual = Differ.generate(
                fixturesDirectoryPath + "file1.json",
                fixturesDirectoryPath + "file2.json",
                "wrongFormat"
        );
        assertNull(actual);
    }

    @Test
    public void testGenDiffWrongPath() {
        Exception exception = assertThrows(
                Exception.class, () -> Differ.generate(
                        "src/test/resources/file1.json",
                        "src/test/resources/fixtures/file2.json"
                )
        );
        assertNotNull(exception.getMessage());
    }

    @Test
    public void testGenDiffWrongFileExtension() {
        Exception exception = assertThrows(
                Exception.class, () -> Differ.generate(
                        "src/test/resources/fixtures/fileWrongExtension.csv",
                        "src/test/resources/fixtures/file2.json"
                )
        );
        assertNotNull(exception.getMessage());
    }

    @Test
    public void testJsonGenDiffFormatDefault() throws Exception {
        var actual1 = Differ.generate(
                fixturesDirectoryPath + "file1.json",
                fixturesDirectoryPath + "file2.json"
        );
        var expected1 = getExpectedFileContent("stylish12.txt");
        assertEquals(expected1, actual1);

        var actual2 = Differ.generate(
                fixturesDirectoryPath + "file3.json",
                fixturesDirectoryPath + "file4.json"
        );
        var expected2 = getExpectedFileContent("stylish34.txt");
        assertEquals(expected2, actual2);
    }

    @Test
    public void testJsonGenDiffFormatStylish() throws Exception {
        var actual1 = Differ.generate(
                fixturesDirectoryPath + "file1.json",
                fixturesDirectoryPath + "file2.json",
                "stylish"
        );
        var expected1 = getExpectedFileContent("stylish12.txt");
        assertEquals(expected1, actual1);

        var actual2 = Differ.generate(
                fixturesDirectoryPath + "file3.json",
                fixturesDirectoryPath + "file4.json",
                "stylish"
        );
        var expected2 = getExpectedFileContent("stylish34.txt");
        assertEquals(expected2, actual2);
    }

    @Test
    public void testYamlGenDiffFormatDefault() throws Exception {
        var actual1 = Differ.generate(
                fixturesDirectoryPath + "file1.yaml",
                fixturesDirectoryPath + "file2.yaml"
        );
        var expected1 = getExpectedFileContent("stylish12.txt");
        assertEquals(expected1, actual1);

        var actual2 = Differ.generate(
                fixturesDirectoryPath + "file3.yml",
                fixturesDirectoryPath + "file4.yml"
        );
        var expected2 = getExpectedFileContent("stylish34.txt");
        assertEquals(expected2, actual2);
    }

    @Test
    public void testYamlGenDiffFormatStylish() throws Exception {
        var actual1 = Differ.generate(
                fixturesDirectoryPath + "file1.yaml",
                fixturesDirectoryPath + "file2.yaml",
                "stylish"
        );
        var expected1 = getExpectedFileContent("stylish12.txt");
        assertEquals(expected1, actual1);

        var actual2 = Differ.generate(
                fixturesDirectoryPath + "file3.yml",
                fixturesDirectoryPath + "file4.yml",
                "stylish"
        );
        var expected2 = getExpectedFileContent("stylish34.txt");
        assertEquals(expected2, actual2);
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
