package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.constants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CATSPM {

    public static final Map<String, String> ATT = Collections.unmodifiableMap(new HashMap<String, String>() {
        {
            put("1", "firing danger area mark");
            put("2", "target mark");
            put("3", "marker ship mark");
            put("4", "degaussing range mark");
            put("5", "barge mark");
            put("6", "cable mark");
            put("7", "spoil ground mark");
            put("8", "outfall mark");
            put("9", "ODAS (Ocean-Data-Acquisition-System)");
            put("10", "recording mark");
            put("11", "seaplane anchorage mark");
            put("12", "recreation zone mark");
            put("13", "private mark");
            put("14", "mooring mark");
            put("15", "LANBY (Large Automatic Navigational Buoy)");
            put("16", "leading mark");
            put("17", "measured distance mark");
            put("18", "notice mark");
            put("19", "TSS mark (Traffic Separation Scheme)");
            put("20", "anchoring prohibited mark");
            put("21", "berthing prohibited mark");
            put("22", "overtaking prohibited mark");
            put("23", "two-way traffic prohibited mark");
            put("24", "'reduced wake' mark");
            put("25", "speed limit mark");
            put("26", "stop mark");
            put("27", "general warning mark");
            put("28", "'sound ship's siren' mark");
            put("29", "restricted vertical clearance mark");
            put("30", "maximum vessel's draught mark");
            put("31", "restricted horizontal clearance mark");
            put("32", "strong current warning mark");
            put("33", "berthing permitted mark");
            put("34", "overhead power cable mark");
            put("35", "'channel edge gradient' mark");
            put("36", "telephone mark");
            put("37", "ferry crossing mark");
            put("38", "marine traffic lights");
            put("39", "pipeline mark");
            put("40", "anchorage mark");
            put("41", "clearing mark");
            put("42", "control mark");
            put("43", "diving mark");
            put("44", "refuge beacon");
            put("45", "foul ground mark");
            put("46", "yachting mark");
            put("47", "heliport mark");
            put("48", "GPS mark");
            put("49", "seaplane landing mark");
            put("50", "entry prohibited mark");
            put("51", "work in progress mark");
            put("52", "mark with unknown purpose");
            put("53", "wellhead mark");
            put("54", "channel separation mark");
            put("55", "marine farm mark");
            put("56", "artificial reef mark");

        }
    });

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        Set<Map.Entry<String, String>> entries = CATSPM.ATT.entrySet();
        buffer.append("[");
        entries.stream().forEach((e) -> {
            buffer.append("[").append(e.getKey()).append(", ").append(e.getValue()).append("]");
        });
        buffer.append("]");
        return buffer.toString();
    }

}
