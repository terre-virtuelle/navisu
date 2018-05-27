package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.constants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BUOYAGE_INV {

    public static final Map<String, String> ATT
            = Collections.unmodifiableMap(new HashMap<String, String>() {
                {
                    put("BeaconCardinal", "BCNCAR");
                    put("BeaconLateral", "BCNLAT");
                    put("BeaconSpecialPurpose", "BCNSPP");
                    put("BeaconIsolatedDanger", "BCNISD");
                    put("BeaconSafeWater", "BCNSAW");
                    put("BuoyCardinal", "BOYCAR");
                    put("BuoyLateral", "BOYLAT");
                    put("BuoySpecialPurpose", "BOYSPP");
                    put("BuoyIsolatedDanger", "BOYISD");
                    put("BuoySafeWater", "BOYSAW");
                    put("BuoyInstallation", "BOYINB");
                    put("MooringWarpingFacility", "MORFAC");
                    put("Daymark", "DAYMAR");
                }
            });

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        Set<Map.Entry<String, String>> entries = BUOYAGE_INV.ATT.entrySet();
        buffer.append("[");
        entries.stream().forEach((e) -> {
            buffer.append("[").append(e.getKey()).append(", ").append(e.getValue()).append("]");
        });
        buffer.append("]");
        return buffer.toString();
    }

}
