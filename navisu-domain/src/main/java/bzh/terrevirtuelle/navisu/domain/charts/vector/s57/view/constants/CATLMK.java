package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.constants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CATLMK {

    public static final Map<String, String> ATT = Collections.unmodifiableMap(new HashMap<String, String>() {
        {
            put("1", "cairn");
            put("2", "cemetery");
            put("3", "chimney");
            put("4", "dish aerial");
            put("5", "flagstaff (flagpole)");
            put("6", "flare stack");
            put("7", "mast");
            put("8", "windsock");
            put("9", "monument");
            put("10", "column (pillar)");
            put("11", "memorial plaque");
            put("12", "obelisk");
            put("13", "statue");
            put("14", "cross");
            put("15", "dome");
            put("16", "radar scanner");
            put("17", "tower");
            put("18", "windmill");
            put("19", "windmotor");
            put("20", "spire/minaret");
            put("21", "large rock or boulder on land");
            put("22", "rock pinnacle");
        }
    });

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        Set<Map.Entry<String, String>> entries = CATLMK.ATT.entrySet();
        buffer.append("[");
        entries.stream().forEach((e) -> {
            buffer.append("[").append(e.getKey()).append(", ").append(e.getValue()).append("]");
        });
        buffer.append("]");
        return buffer.toString();
    }

}
