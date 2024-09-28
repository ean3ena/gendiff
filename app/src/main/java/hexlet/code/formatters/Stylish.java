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
            var value1 = elem.get("value1");
            var value2 = elem.get("value2");

            switch (status) {
                case "added" -> result.append("  + ").append(key).append(": ").append(value1).append("\n");
                case "deleted" -> result.append("  - ").append(key).append(": ").append(value1).append("\n");
                case "unchanged" -> result.append("    ").append(key).append(": ").append(value1).append("\n");
                case "changed" -> {
                    result.append("  - ").append(key).append(": ").append(value1).append("\n");
                    result.append("  + ").append(key).append(": ").append(value2).append("\n");
                }
                default -> { }
            }
        }
        result.append("}");

        return result.toString();
    }
}
