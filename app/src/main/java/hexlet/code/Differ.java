package hexlet.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

public class Differ {

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String generate(String filepath1, String filepath2, String format) throws Exception {

        Map<String, Object> mapFileContent1 = Parser.parse(filepath1);
        Map<String, Object> mapFileContent2 = Parser.parse(filepath2);

        List<Map<String, Object>> diff = getDiff(mapFileContent1, mapFileContent2);

        return Formatter.format(diff, format);
    }

    public static List<Map<String, Object>> getDiff(Map<String, Object> mapFile1, Map<String, Object> mapFile2) {

        List<Map<String, Object>> result = new ArrayList<>();

        var allSortedKeys = new TreeSet<>(mapFile1.keySet());
        allSortedKeys.addAll(mapFile2.keySet());

        for (var key : allSortedKeys) {
            if (!mapFile1.containsKey(key)) {
                addElementInResult(result, "added",  key, mapFile2.get(key));
            } else if (!mapFile2.containsKey(key)) {
                addElementInResult(result, "deleted",  key, mapFile1.get(key));
            } else {

                var value1 = mapFile1.get(key);
                var value2 = mapFile2.get(key);

                if (Objects.equals(value1, value2)) {
                    addElementInResult(result, "unchanged", key, value1, value2);
                } else {
                    addElementInResult(result, "changed", key, value1, value2);
                }
            }
        }

        return result;
    }

    public static void addElementInResult(List<Map<String, Object>> resultList,
                                                  String status, String key,
                                                  Object value1, Object value2) {

        var mapElement = new HashMap<String, Object>();

        mapElement.put("status", status);
        mapElement.put("key", key);
        mapElement.put("value1", value1);
        mapElement.put("value2", value2);

        resultList.add(mapElement);
    }

    public static void addElementInResult(List<Map<String, Object>> resultList,
                                          String status, String key, Object value) {
        addElementInResult(resultList, status, key, value, null);
    }
}
