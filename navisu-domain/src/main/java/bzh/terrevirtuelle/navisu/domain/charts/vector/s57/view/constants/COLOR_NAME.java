package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.constants;

import static bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.constants.COLOR.ATT;
import java.awt.Color;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class COLOR_NAME {

    private static final Map<String, String> ATT = Collections.unmodifiableMap(new HashMap<String, String>() {
        {
            put("1", "white");
            put("2", "black");
            put("3", "red");
            put("4", "green");
            put("5", "blue");
            put("6", "yellow");
            put("7", "gray");
            put("8", "brown");
            put("9", "amber");
            put("10", "violet");
            put("11", "orange");
            put("12", "magenta");
            put("13", "pink");
        }
    });

    public static String getColor(String type) {
        return ATT.get(type);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        Set<Map.Entry<String, String>> entries = COLOR_NAME.ATT.entrySet();
        buffer.append("[");
        entries.stream().forEach((e) -> {
            buffer.append("[").append(e.getKey()).append(", ").append(e.getValue()).append("]");
        });
        buffer.append("]");
        return buffer.toString();
    }

}
