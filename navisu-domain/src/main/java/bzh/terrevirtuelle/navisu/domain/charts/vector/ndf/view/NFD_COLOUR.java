package bzh.terrevirtuelle.navisu.domain.charts.vector.ndf.view;

import java.awt.Color;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class NFD_COLOUR {

    public static final Map<String, Color> ATT = Collections.unmodifiableMap(new HashMap<String, Color>() {
        {
            put("NFVSF", new Color(158, 187, 215));
            put("NFVS", new Color(158, 215, 194));
            put("NFVG", new Color(102, 255, 204));
            put("NFV", new Color(190, 210, 255));
            put("NFSV", new Color(209, 255, 115));
            put("NFSFC", new Color(254, 200, 10));
            put("NFSFV", new Color(165, 254, 164));
            put("NFSF", new Color(254, 248, 164));
            put("NFS", new Color(230, 230, 50));
            put("NFRoche", new Color(200, 0, 0));
            put("NFGV", new Color(255, 179, 102));
            put("NFGS", new Color(255, 170, 0));
            put("NFG", new Color(230, 152, 0));
            put("NFCV", new Color(168, 112, 0));
            put("NFCS", new Color(168, 100, 50));
            put("NFCG", new Color(137, 90, 68));
            put("NFC", new Color(150, 80, 60));
            put("NFASi", new Color(158, 170, 215));
            put("NFSi", new Color(158, 170, 215));
            put("NFSSi", new Color(158, 170, 215));
            put("NFSFSi", new Color(158, 170, 215));
            put("NFA", new Color(158, 170, 215));
            put("NFSG", new Color(255, 170, 0));
            put("NFGC", new Color(230, 152, 0));
        }
    });

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        Set<Map.Entry<String, Color>> entries = NFD_COLOUR.ATT.entrySet();
        buffer.append("[");
        entries.stream().forEach((e) -> {
            buffer.append("[").append(e.getKey()).append(", ").append(e.getValue()).append("]");
        });
        buffer.append("]");
        return buffer.toString();
    }

}
