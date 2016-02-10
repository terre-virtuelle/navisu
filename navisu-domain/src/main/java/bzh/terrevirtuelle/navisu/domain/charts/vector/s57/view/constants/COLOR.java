package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.constants;

import java.awt.Color;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class COLOR {

    public static final Map<String, Color> ATT = Collections.unmodifiableMap(new HashMap<String, Color>() {
        {
            put("1", Color.WHITE);
            put("2", Color.BLACK);
            put("3", Color.RED);
            put("4", Color.GREEN);
            put("5", Color.BLUE);
            put("6", Color.YELLOW);
            put("7", Color.GRAY);
            put("8", new Color(165, 42, 42));
            put("9", new Color(255, 191, 0));
            put("10", new Color(118, 31, 51));
            put("11", Color.ORANGE);
            put("12", new Color(186, 126, 93));
            put("13", Color.PINK);
        }
    });

    public static Color getColor(String type) {
        return ATT.get(type);
    }

    @Override

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        Set<Map.Entry<String, Color>> entries = COLOR.ATT.entrySet();
        buffer.append("[");
        entries.stream().forEach((e) -> {
            buffer.append("[").append(e.getKey()).append(", ").append(e.getValue()).append("]");
        });
        buffer.append("]");
        return buffer.toString();
    }

}
