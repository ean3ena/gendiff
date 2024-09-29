package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {

    public static String format(List<Map<String, Object>> diff) {

        StringBuilder result = new StringBuilder();

        result.append("{\n");

        for (var elem : diff) {
            var status = (String) elem.get("status");
            var key = elem.get("key");
            var oldValue = elem.get("oldValue");
            var newValue = elem.get("newValue");

            switch (status) {
                case "added" -> result.append("  + ").append(key).append(": ").append(newValue).append("\n");
                case "deleted" -> result.append("  - ").append(key).append(": ").append(oldValue).append("\n");
                case "unchanged" -> result.append("    ").append(key).append(": ").append(oldValue).append("\n");
                case "changed" -> {
                    result.append("  - ").append(key).append(": ").append(oldValue).append("\n");
                    result.append("  + ").append(key).append(": ").append(newValue).append("\n");
                }
                default -> { }
            }
        }
        result.append("}");

        return result.toString();
    }
}
