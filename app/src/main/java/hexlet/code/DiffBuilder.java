package hexlet.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

public class DiffBuilder {

    public static List<Map<String, Object>> getDiff(Map<String, Object> mapFile1, Map<String, Object> mapFile2) {

        List<Map<String, Object>> result = new ArrayList<>();

        var allSortedKeys = new TreeSet<>(mapFile1.keySet());
        allSortedKeys.addAll(mapFile2.keySet());

        for (var key : allSortedKeys) {

            var elem = new HashMap<String, Object>();

            if (!mapFile1.containsKey(key)) {
                elem.put("status", "added");
                elem.put("key", key);
                elem.put("value", mapFile2.get(key));
            } else if (!mapFile2.containsKey(key)) {
                elem.put("status", "deleted");
                elem.put("key", key);
                elem.put("value", mapFile1.get(key));
            } else {

                var value1 = mapFile1.get(key);
                var value2 = mapFile2.get(key);

                if (Objects.equals(value1, value2)) {
                    elem.put("status", "unchanged");
                    elem.put("key", key);
                    elem.put("value", value1);
                } else {
                    elem.put("status", "changed");
                    elem.put("key", key);
                    elem.put("value1", value1);
                    elem.put("value2", value2);
                }
            }
            result.add(elem);
        }

        return result;
    }
}
