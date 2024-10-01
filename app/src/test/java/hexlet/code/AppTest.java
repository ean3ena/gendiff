package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AppTest {

    private final String fixturesDirectoryPath = "src/test/resources/fixtures/";
    private static String expectedStylish;
    private static String expectedPlain;
    private static String expectedJson;

    @BeforeAll
    public static void init() throws Exception {
        expectedStylish = getExpectedFileContent("testStylish.txt");
        expectedPlain = getExpectedFileContent("testPlain.txt");
        expectedJson = getExpectedFileContent("testJson.json");
    }

    @Test
    public void testGenDiffWrongFormat() {
        Exception exception = assertThrows(
                Exception.class, () -> Differ.generate(
                    fixturesDirectoryPath + "file1.json",
                    fixturesDirectoryPath + "file2.json",
                    "wrongFormat")
        );
        assertNotNull(exception.getMessage());
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
        assertEquals(expectedStylish, actual);
    }

    @Test
    public void testJsonGenDiffFormatStylish() throws Exception {
        var actual = Differ.generate(
                fixturesDirectoryPath + "file1.json",
                fixturesDirectoryPath + "file2.json",
                "stylish"
        );
        assertEquals(expectedStylish, actual);
    }

    @Test
    public void testYamlGenDiffFormatDefault() throws Exception {
        var actual = Differ.generate(
                fixturesDirectoryPath + "file1.yml",
                fixturesDirectoryPath + "file2.yml"
        );
        assertEquals(expectedStylish, actual);
    }

    @Test
    public void testYamlGenDiffFormatStylish() throws Exception {
        var actual = Differ.generate(
                fixturesDirectoryPath + "file1.yml",
                fixturesDirectoryPath + "file2.yml",
                "stylish"
        );
        assertEquals(expectedStylish, actual);
    }

    @Test
    public void testJsonGenDiffFormatPlain() throws Exception {
        var actual = Differ.generate(
                fixturesDirectoryPath + "file1.json",
                fixturesDirectoryPath + "file2.json",
                "plain"
        );
        assertEquals(expectedPlain, actual);
    }

    @Test
    public void testYamlGenDiffFormatPlain() throws Exception {
        var actual = Differ.generate(
                fixturesDirectoryPath + "file1.yml",
                fixturesDirectoryPath + "file2.yml",
                "plain"
        );
        assertEquals(expectedPlain, actual);
    }

    @Test
    public void testJsonGenDiffFormatJson() throws Exception {
        var actualJson = Differ.generate(
                fixturesDirectoryPath + "file1.json",
                fixturesDirectoryPath + "file2.json",
                "json"
        );
        JSONAssert.assertEquals(expectedJson, actualJson, true);
    }

    @Test
    public void testYamlGenDiffFormatJson() throws Exception {
        var actualJson = Differ.generate(
                fixturesDirectoryPath + "file1.yml",
                fixturesDirectoryPath + "file2.yml",
                "json"
        );
        JSONAssert.assertEquals(expectedJson, actualJson, true);
    }

    private static String getExpectedFileContent(String filename) throws Exception {
        Path fixturePath = getFixturePath(filename);
        return Files.readString(fixturePath);
    }

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }
}
