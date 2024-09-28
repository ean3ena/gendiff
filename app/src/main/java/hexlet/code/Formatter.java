package hexlet.code;

import java.util.List;
import java.util.Map;

public class Formatter {

    public static String format(List<Map<String, Object>> diff, String format) {

        return switch (format) {
            case "stylish" -> formatStylish(diff);
            default -> null;
        };
    }

    public static String formatStylish(List<Map<String, Object>> diff) {

        StringBuilder result = new StringBuilder("{\n");

        for (var elem : diff) {
            result.append("  ");
            result.append(elem.get("status"));
            result.append(" ");
            result.append(elem.get("key"));
            result.append(": ");
            result.append(elem.get("value"));
            result.append("\n");
        }
        result.append("}");

        return result.toString();
    }
}
