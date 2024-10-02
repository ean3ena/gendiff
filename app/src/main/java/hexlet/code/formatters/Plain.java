package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Plain {

    public static String format(List<Map<String, Object>> diff) throws Exception {

        String templateAdded = "Property '%s' was added with value: %s\n";
        String templateDeleted = "Property '%s' was removed\n";
        String templateChanged = "Property '%s' was updated. From %s to %s\n";

        StringBuilder result = new StringBuilder();

        for (var elem : diff) {
            var status = (String) elem.get("status");
            var key = elem.get("key");

            switch (status) {
                case "added" -> result.append(String.format(templateAdded, key,
                        getTextByValue(elem.get("value"))));
                case "deleted" -> result.append(String.format(templateDeleted, key));
                case "changed" -> result.append(String.format(templateChanged, key,
                        getTextByValue(elem.get("value1")), getTextByValue(elem.get("value2"))));
                case "unchanged" -> { }
                default -> throw new Exception("Неверный статус - " + status);
            }
        }
        return result.substring(0, result.length() - 1);
    }

    private static String getTextByValue(Object obj) {

        if (obj == null) {
            return "null";
        }
        if (obj instanceof String) {
            return "'" + obj + "'";
        }
        if (obj instanceof Boolean || obj instanceof Number) {
            return obj.toString();
        }
        return "[complex value]";
    }
}
