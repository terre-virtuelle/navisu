package bzh.terrevirtuelle.navisu.locators.view;

import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.render.SurfaceCircle;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ShipTypeShape {

    public static final Map<Integer, Class<? extends Renderable>> VIEW
            = Collections.unmodifiableMap(new HashMap<Integer, Class<? extends Renderable>>() {
                {
                    put(0, SurfaceCircle.class);
                    put(1, Polygon.class);
                }
            });

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        Set<Map.Entry<Integer, Class<? extends Renderable>>> entries = ShipTypeShape.VIEW.entrySet();
        buffer.append("[");
        entries.stream().forEach((e) -> {
            buffer.append("[").append(e.getKey()).append(", ").append(e.getValue()).append("]");
        });
        buffer.append("]");
        return buffer.toString();
    }

    public static void main(String[] args) {
        System.out.println(new ShipTypeShape());
    }
}
