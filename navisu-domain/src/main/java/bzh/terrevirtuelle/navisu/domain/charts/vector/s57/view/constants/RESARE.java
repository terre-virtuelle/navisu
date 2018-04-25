package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.constants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RESARE {

    public static final Map<String, String> ATT = Collections.unmodifiableMap(new HashMap<String, String>() {
        {
            put("1", "offshore safety zone");
            put("4", "nature reserve ");
            put("5", "bird sanctuary ");
            put("6", "game reserve");
            put("7", "seal sanctuary");
            put("8", "degaussing range ");
            put("9", "military area");
            put("10", "historic wreck area");
            put("12", "navigational aid safety zone");
            put("14", "minefield");
            put("18", "swimming area");
            put("19", "waiting area");
            put("20", "research area");
            put("21", "dredging area");
            put("22", "fish sanctuary");
            put("23", "ecological reserve");
            put("24", "no wake area");
            put("25", "swinging area	");
            put("26", "water skiing area");
            put("27", "Environmentally Sensitive Sea Area (ESSA)");
            put("28", "Particularly Sensitive Sea Area (PSSA)"); 
        }
    });

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        Set<Map.Entry<String, String>> entries = RESARE.ATT.entrySet();
        buffer.append("[");
        entries.stream().forEach((e) -> {
            buffer.append("[").append(e.getKey()).append(", ").append(e.getValue()).append("]");
        });
        buffer.append("]");
        return buffer.toString();
    }

}
