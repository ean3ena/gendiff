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
                        "src/test/resources/fixtures/testFileWrongExtension.csv",
                        "src/test/resources/fixtures/file2.json"
                )
        );
        assertNotNull(exception.getMessage());
    }

    @Test
    public void testJsonGenDiffFormatDefault() throws Exception {
        var actual = Differ.generate(
                fixturesDirectoryPath + "file1.json",
                fixturesDirectoryPath + "file2.json"
        );
        var expected = getExpectedFileContent("testStylish.txt");
        assertEquals(expected, actual);
    }

    @Test
    public void testJsonGenDiffFormatStylish() throws Exception {
        var actual = Differ.generate(
                fixturesDirectoryPath + "file1.json",
                fixturesDirectoryPath + "file2.json",
                "stylish"
        );
        var expected = getExpectedFileContent("testStylish.txt");
        assertEquals(expected, actual);
    }

    @Test
    public void testYamlGenDiffFormatDefault() throws Exception {
        var actual = Differ.generate(
                fixturesDirectoryPath + "file1.yml",
                fixturesDirectoryPath + "file2.yml"
        );
        var expected = getExpectedFileContent("testStylish.txt");
        assertEquals(expected, actual);
    }

    @Test
    public void testYamlGenDiffFormatStylish() throws Exception {
        var actual = Differ.generate(
                fixturesDirectoryPath + "file1.yml",
                fixturesDirectoryPath + "file2.yml",
                "stylish"
        );
        var expected = getExpectedFileContent("testStylish.txt");
        assertEquals(expected, actual);
    }

    @Test
    public void testJsonGenDiffFormatPlain() throws Exception {
        var actual = Differ.generate(
                fixturesDirectoryPath + "file1.json",
                fixturesDirectoryPath + "file2.json",
                "plain"
        );
        var expected = getExpectedFileContent("testPlain.txt");
        assertEquals(expected, actual);
    }

    @Test
    public void testYamlGenDiffFormatPlain() throws Exception {
        var actual = Differ.generate(
                fixturesDirectoryPath + "file1.yml",
                fixturesDirectoryPath + "file2.yml",
                "plain"
        );
        var expected = getExpectedFileContent("testPlain.txt");
        assertEquals(expected, actual);
    }

    @Test
    public void testJsonGenDiffFormatJson() throws Exception {
        var actual = Differ.generate(
                fixturesDirectoryPath + "file1.json",
                fixturesDirectoryPath + "file2.json",
                "json"
        );
        var expected = getExpectedFileContent("testJson.json");
        assertEquals(expected, actual);
    }

    @Test
    public void testYamlGenDiffFormatJson() throws Exception {
        var actual = Differ.generate(
                fixturesDirectoryPath + "file1.yml",
                fixturesDirectoryPath + "file2.yml",
                "json"
        );
        var expected = getExpectedFileContent("testJson.json");
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
