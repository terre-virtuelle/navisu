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

        /*
        facet normal 1.695421e-07 1.000000e+00 0.000000e+00
              outer loop
                vertex 0.000000e+00 -4.461072e+00 5.000000e+00
                vertex -3.000000e+00 -6.193123e+00 5.000000e+00
                vertex 3.000000e+00 -6.193123e+00 5.000000e+00
              endloop
            endfacet
         */
        String[] facetTab = root.split("\n");
        // System.out.println("facetTab : " + facetTab[0]);
        for (String s : facetTab) {
            if (s.contains("facet")) {
                result += s + "\n";
            }
            if (s.contains("outer")) {
                result += s + "\n";
            }
            if (s.contains("vertex")) {
                String[] vTab = s.trim().split("\\s+");
                result += "vertex "
                        + Double.toString(Double.valueOf(vTab[1]) + lon) + " "
                        + Double.toString(Double.valueOf(vTab[2]) + lat) + " "
                        + vTab[3] + "\n";
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
