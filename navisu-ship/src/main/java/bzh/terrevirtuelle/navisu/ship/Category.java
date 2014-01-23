package bzh.terrevirtuelle.navisu.ship;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Category {

    public static final Map<Integer, String> TYPE = Collections.unmodifiableMap(new HashMap<Integer, String>() {
        {
            put(20, " Wing in ground (WIG), all ships of this type");
            put(21, " Wing in ground (WIG), Hazardous category A");
            put(22, " Wing in ground (WIG), Hazardous category B");
            put(23, " Wing in ground (WIG), Hazardous category C");
            put(24, " Wing in ground (WIG), Hazardous category D");
            put(25, " Wing in ground (WIG), Reserved for future use");
            put(26, " Wing in ground (WIG), Reserved for future use");
            put(27, " Wing in ground (WIG), Reserved for future use");
            put(28, " Wing in ground (WIG), Reserved for future use");
            put(29, " Wing in ground (WIG), No additional information");
            put(30, " fishing");
            put(31, " towing");
            put(32, " towing length exceeds 200m or breadth exceeds 25m");
            put(33, " dredging or underwater ops");
            put(34, " diving ops");
            put(35, " military ops");
            put(36, " sailing");
            put(37, " pleasure craft");
            put(38, " reserved");
            put(39, " reserved");
            put(40, " High speed craft (HSC), all ships of this type");
            put(41, " High speed craft (HSC), Hazardous category A");
            put(42, " High speed craft (HSC), Hazardous category B");
            put(43, " High speed craft (HSC), Hazardous category C");
            put(44, " High speed craft (HSC), Hazardous category D");
            put(45, " High speed craft (HSC), Reserved for future use");
            put(46, " High speed craft (HSC), Reserved for future use");
            put(47, " High speed craft (HSC), Reserved for future use");
            put(48, " High speed craft (HSC), Reserved for future use");
            put(49, " High speed craft (HSC), No additional information");
            put(50, " pilot vessel");
            put(51, " search and rescue vessel");
            put(52, " tug");
            put(53, " port tender");
            put(54, " anti-polution equipment");
            put(55, " law enforcement");
            put(56, " spare - local vessel");
            put(57, " spare - local vesse");
            put(58, " medical transpor");
            put(59, " ship according to RR Resolution No. 18");
            put(60, " passenger, all ships of this type");
            put(61, " passenger, Hazardous category A");
            put(62, " passenger, Hazardous category B");
            put(63, " passenger, Hazardous category C");
            put(64, " passenger, Hazardous category D");
            put(65, " passenger, Reserved for future use");
            put(66, " passenger, Reserved for future use");
            put(67, " passenger, Reserved for future use");
            put(68, " passenger, Reserved for future use");
            put(69, " passenger, No additional information");
            put(70, " cargo, all ships of this type");
            put(71, " cargo, Hazardous category A");
            put(72, " cargo, Hazardous category B");
            put(73, " cargo, Hazardous category C");
            put(74, " cargo, Hazardous category D");
            put(75, " cargo, Reserved for future use");
            put(76, " cargo, Reserved for future use");
            put(77, " cargo, Reserved for future use");
            put(78, " cargo, Reserved for future use");
            put(79, " cargo, No additional information");
            put(80, " tanker, all ships of this type");
            put(81, " tanker, Hazardous category A");
            put(82, " tanker, Hazardous category B");
            put(83, " tanker, Hazardous category C");
            put(84, " tanker, Hazardous category D");
            put(85, " tanker, Reserved for future use");
            put(86, " tanker, Reserved for future use");
            put(87, " tanker, Reserved for future use");
            put(88, " tanker, Reserved for future use");
            put(89, " tanker, No additional information");
            put(90, " other type, all ships of this type");
            put(91, " other type, Hazardous  A");
            put(92, " other type, Hazardous  B");
            put(93, " other type, Hazardous  C");
            put(94, " other type, Hazardous  D");
            put(95, " other type, Reserved for future use");
            put(96, " other type, Reserved for future use");
            put(97, " other type, Reserved for future use");
            put(98, " other type, Reserved for future use");
            put(99, " other type, No additional information");
        }
    });

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        Set<Map.Entry<Integer, String>> entries = Category.TYPE.entrySet();
        buffer.append("[");
        for (Map.Entry<Integer, String> e : entries) {
            buffer.append("[").append(e.getKey()).append(", ").append(e.getValue()).append("]");
        }
        buffer.append("]");
        return buffer.toString();
    }

    
}
