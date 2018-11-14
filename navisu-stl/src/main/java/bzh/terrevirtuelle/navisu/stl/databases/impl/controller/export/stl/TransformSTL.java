/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.stl;

/**
 *
 * @author serge
 */
public class TransformSTL {

    public String transform(String root, double lat, double lon, double elv) {
        String result = "";

        String[] facetTab = root.split("\n");
        for (String s : facetTab) {
            if (s.contains("facet")||s.contains("outer")||s.contains("solid")||s.contains("endloop")||s.contains("endfacet")) {
                result += s + "\n";
            }
            if (s.contains("vertex")) {
                String[] vTab = s.trim().split("\\s+");
                result += "vertex "
                        + Double.toString(Double.valueOf(vTab[1]) + lon) + " "
                        + Double.toString(Double.valueOf(vTab[2]) + lat) + " "
                        + Double.toString(Double.valueOf(vTab[3]) + elv) + "\n";
            }
            
        }
        return result;
    }

    public String transformAndScale(String root, double scale, double lat, double lon, double elv) {
        String result = "";
        double x;
        double y;
        double z;
        String[] facetTab = root.split("\n");
        for (String s : facetTab) {
            if (s.contains("facet")) {
                result += s + "\n";
            }
            if (s.contains("outer")) {
                result += s + "\n";
            }
            if (s.contains("vertex")) {
                String[] vTab = s.trim().split("\\s+");
                x = Double.valueOf(vTab[1]);
                x /= scale;
                y = Double.valueOf(vTab[2]);
                y /= scale;
                z = Double.valueOf(vTab[3]);
                z /= scale;
                result += "vertex "
                        + Double.toString(x + lon ) + " "
                        + Double.toString(y + lat ) + " "
                        + Double.toString(z + elv) + "\n";
            }
            if (s.contains("endloop")) {
                result += s + "\n";
            }
            if (s.contains("endfacet")) {
                result += s + "\n";
            }
        }
        return result;
    }

}
