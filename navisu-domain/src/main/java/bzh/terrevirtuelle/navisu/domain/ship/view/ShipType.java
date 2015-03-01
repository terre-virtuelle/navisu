package bzh.terrevirtuelle.navisu.domain.ship.view;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ShipType {

    public static final Map<Integer, String> TYPE = Collections.unmodifiableMap(new HashMap<Integer, String>() {
        {
            put(0, "Not defined");
            put(20, " Wing in ground");
            put(21, " Wing in ground");
            put(22, " Wing in ground");
            put(23, " Wing in ground");
            put(24, " Wing in ground");
            put(25, " Wing in ground");
            put(26, " Wing in ground");
            put(27, " Wing in ground");
            put(28, " Wing in ground");
            put(29, " Wing in ground");
            put(30, " Fishing");
            put(31, " Towing");
            put(32, " Towing");
            put(33, " Dredging");
            put(34, " Diving");
            put(35, " Military");
            put(36, " Sailing");
            put(37, " Pleasure craft");
            put(38, " Reserved");
            put(39, " Reserved");
            put(40, " High speed craft");
            put(41, " High speed craft");
            put(42, " High speed craft");
            put(43, " High speed craft");
            put(44, " High speed craft");
            put(45, " High speed craft");
            put(46, " High speed craft");
            put(47, " High speed craft");
            put(48, " High speed craft");
            put(49, " High speed craft");
            put(50, " Pilot vessel");
            put(51, " Search and rescue");
            put(52, " Tug");
            put(53, " Port tender");
            put(54, " Anti-polution equipment");
            put(55, " Law enforcement");
            put(56, " Spare");
            put(57, " Spare");
            put(58, " Medical transpor");
            put(59, " Ship according to RR Resolution No. 18");
            put(60, " Passenger");
            put(61, " Passenger");
            put(62, " Passenger");
            put(63, " Passenger");
            put(64, " Passenger");
            put(65, " Passenger");
            put(66, " Passenger");
            put(67, " Passenger");
            put(68, " Passenger");
            put(69, " Passenger");
            put(70, " Cargo");
            put(71, " Cargo");
            put(72, " Cargo");
            put(73, " Cargo");
            put(74, " Cargo");
            put(75, " Cargo");
            put(76, " Cargo");
            put(77, " Cargo");
            put(78, " Cargo");
            put(79, " Cargo");
            put(80, " Tanker");
            put(81, " Tanker");
            put(82, " Tanker");
            put(83, " Tanker");
            put(84, " Tanker");
            put(85, " Tanker");
            put(86, " Tanker");
            put(87, " Tanker");
            put(88, " Tanker");
            put(89, " Tanker");
            put(90, " Undefined");
            put(91, " Undefined");
            put(92, " Undefined");
            put(93, " Undefined");
            put(94, " Undefined");
            put(95, " Undefined");
            put(96, " Undefined");
            put(97, " Undefined");
            put(98, " Undefined");
            put(99, " v");
        }
    });

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        Set<Map.Entry<Integer, String>> entries = ShipType.TYPE.entrySet();
        buffer.append("[");
        entries.stream().forEach((e) -> {
            buffer.append("[").append(e.getKey()).append(", ").append(e.getValue()).append("]");
        });
        buffer.append("]");
        return buffer.toString();
    }

}
