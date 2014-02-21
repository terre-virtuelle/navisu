package bzh.terrevirtuelle.navisu.locators.view;

import gov.nasa.worldwind.render.Material;
import java.awt.Color;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ShipStatusColor {

    public static final Map<Integer, Material> VIEW = Collections.unmodifiableMap(new HashMap<Integer, Material>() {
        {
            put(0, Material.YELLOW);
            
        }
    });

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        Set<Map.Entry<Integer, Material>> entries = ShipStatusColor.VIEW.entrySet();
        buffer.append("[");
        entries.stream().forEach((e) -> {
            buffer.append("[").append(e.getKey()).append(", ").append(e.getValue()).append("]");
        });
        buffer.append("]");
        return buffer.toString();
    }

}
