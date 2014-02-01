package bzh.terrevirtuelle.navisu.world.ais.locator.view;

import gov.nasa.worldwind.render.Material;
import java.awt.Color;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CategoryView {

    public static final Map<Integer, Material> VIEW = Collections.unmodifiableMap(new HashMap<Integer, Material>() {
        {
            put(20, Material.ORANGE);
            put(21, Material.WHITE);
            put(22, Material.WHITE);
            put(23, Material.WHITE);
            put(24, Material.WHITE);
            put(25, Material.WHITE);
            put(26, Material.WHITE);
            put(27, Material.WHITE);
            put(28, Material.WHITE);
            put(29, Material.WHITE);
            put(30, new Material(new Color(0xFFA07A)));
            put(31, Material.WHITE);
            put(32, Material.WHITE);
            put(33, Material.WHITE);
            put(34, Material.WHITE);
            put(35, Material.GRAY);
            put(36, new Material(new Color(0xFF00FF)));
            put(37, new Material(new Color(0xFF00FF)));
            put(38, Material.WHITE);
            put(39, Material.WHITE);
            put(40, new Material(new Color(0xFF6347)));
            put(41, new Material(new Color(0xFF6347)));
            put(42, new Material(new Color(0xFF6347)));
            put(43, new Material(new Color(0xFF6347)));
            put(44, new Material(new Color(0xFF6347)));
            put(45, new Material(new Color(0xFF6347)));
            put(46, new Material(new Color(0xFF6347)));
            put(47, new Material(new Color(0xFF6347)));
            put(48, new Material(new Color(0xFF6347)));
            put(49, new Material(new Color(0xFF6347)));
            put(50, new Material(new Color(0x40E0D0)));
            put(51, new Material(new Color(0x40E0D0)));
            put(52, new Material(new Color(0x40E0D0)));
            put(53, new Material(new Color(0x40E0D0)));
            put(54, new Material(new Color(0x40E0D0)));
            put(55, Material.WHITE);
            put(56, Material.WHITE);
            put(57, Material.WHITE);
            put(58, Material.WHITE);
            put(59, Material.WHITE);
            put(60, Material.BLUE);
            put(61, Material.BLUE);
            put(62, Material.BLUE);
            put(63, Material.BLUE);
            put(64, Material.BLUE);
            put(65, Material.BLUE);
            put(66, Material.BLUE);
            put(67, Material.BLUE);
            put(68, Material.BLUE);
            put(69, Material.BLUE);
            put(70, Material.GREEN);
            put(71, Material.GREEN);
            put(72, Material.GREEN);
            put(73, Material.GREEN);
            put(74, Material.GREEN);
            put(75, Material.GREEN);
            put(76, Material.GREEN);
            put(77, Material.GREEN);
            put(78, Material.GREEN);
            put(79, Material.GREEN);
            put(80, Material.RED);
            put(81, Material.RED);
            put(82, Material.RED);
            put(83, Material.RED);
            put(84, Material.RED);
            put(85, Material.RED);
            put(86, Material.RED);
            put(87, Material.RED);
            put(88, Material.RED);
            put(89, Material.RED);
            put(90, Material.WHITE);
            put(91, Material.WHITE);
            put(92, Material.WHITE);
            put(93, Material.WHITE);
            put(94, Material.WHITE);
            put(95, Material.WHITE);
            put(96, Material.WHITE);
            put(97, Material.WHITE);
            put(98, Material.WHITE);
            put(99, Material.WHITE);
        }
    });

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        Set<Map.Entry<Integer, Material>> entries = CategoryView.VIEW.entrySet();
        buffer.append("[");
        entries.stream().forEach((e) -> {
            buffer.append("[").append(e.getKey()).append(", ").append(e.getValue()).append("]");
        });
        buffer.append("]");
        return buffer.toString();
    }

}
