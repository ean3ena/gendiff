package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {

    public static String format(List<Map<String, Object>> diff) throws Exception {

        StringBuilder result = new StringBuilder();

        result.append("{\n");

        for (var elem : diff) {

            var status = (String) elem.get("status");
            var key = elem.get("key");

            switch (status) {
                case "added" -> result.append("  + ").append(key).append(": ")
                        .append(elem.get("value")).append("\n");
                case "deleted" -> result.append("  - ").append(key).append(": ")
                        .append(elem.get("value")).append("\n");
                case "unchanged" -> result.append("    ").append(key).append(": ")
                        .append(elem.get("value")).append("\n");
                case "changed" -> {
                    result.append("  - ").append(key).append(": ")
                            .append(elem.get("value1")).append("\n");
                    result.append("  + ").append(key).append(": ")
                            .append(elem.get("value2")).append("\n");
                }
                default -> throw new Exception("Неверный статус - " + status);
            }
        }
        result.append("}");

        return result.toString();
    }
}
