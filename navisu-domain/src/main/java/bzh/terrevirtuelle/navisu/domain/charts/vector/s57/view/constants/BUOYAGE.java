package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.constants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BUOYAGE {

   
    public static final Map<String, String> ATT 
            = Collections.unmodifiableMap(new HashMap<String, String>() {
        {
            put("BCNCAR", "BeaconCardinal");
            put("BCNLAT", "BeaconLateral");
            put("BCNSPP", "BeaconSpecialPurpose");
            put("BCNISD", "BeaconIsolatedDanger");
            put("BCNSAW", "BeaconSafeWater");
            put("BOYCAR", "BuoyCardinal");
            put("BOYLAT", "BuoyLateral");
            put("BOYSPP", "BuoySpecialPurpose");
            put("BOYISD", "BuoyIsolatedDanger");
            put("BOYSAW", "BuoySafeWater");
            put("BOYINB", "BuoyInstallation");
            put("MORFAC", "MooringWarpingFacility");
            put("DAYMAR", "Daymark");
        }
    });

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        Set<Map.Entry<String, String>> entries = BUOYAGE.ATT.entrySet();
        buffer.append("[");
        entries.stream().forEach((e) -> {
            buffer.append("[").append(e.getKey()).append(", ").append(e.getValue()).append("]");
        });
        buffer.append("]");
        return buffer.toString();
    }

}
