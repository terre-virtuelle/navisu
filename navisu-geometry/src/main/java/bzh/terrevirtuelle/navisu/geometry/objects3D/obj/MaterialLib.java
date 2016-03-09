package bzh.terrevirtuelle.navisu.geometry.objects3D.obj;

import java.io.*;
import java.util.*;

/**
 *
 * @author serge
 * @date Mar 8, 2016
 *
 */
public class MaterialLib {

    private final Set<String> materialNames;

    public MaterialLib(File mtlFile)
            throws IOException {
        this.materialNames = grepMaterialNames(mtlFile);
    }

    public static Set<String> grepMaterialNames(File mtlFile)
            throws IOException {
        Set<String> materialNames = new TreeSet<>();
        FileReader r = new FileReader(mtlFile);
        BufferedReader br = new BufferedReader(r);

        while (true) {
            String line = br.readLine();
            if (null == line) {
                break;
            }

            line = line.trim();
            if (line.startsWith("newmtl ")) {
                String mn = line.substring(7).trim();
                materialNames.add(mn);
            }
        }
        return materialNames;
    }

    public boolean hasMaterial(String rval) {
        return materialNames.contains(rval);
    }
}
