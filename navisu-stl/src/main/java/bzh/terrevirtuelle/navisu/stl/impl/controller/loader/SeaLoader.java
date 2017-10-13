/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.impl.controller.loader;

/**
 *
 * @author serge
 * @date Sep 18, 2017
 */
public class SeaLoader {

    String txt = " <!--" + "Sea" + "-->\n"
            + "<Transform DEF=\"Shape_Box_TRANSFORM\" \n"
            + "translation=\"100.000000 -0.0000 -100.000000\" "
            + "scale='1 1.1 1'>\n"
            + "<Group DEF=\"group_ME_Shape_Box\">\n"
            + "<Shape>\n"
            + "<Appearance>\n"
            + "<ImageTexture DEF='Sea' url='\"common/mer.jpg\"'/> \n"
            + "<Material\n"
            + "diffuseColor=\"0.0600 0.1200 0.2200\">\n"
            + "</Material> \n"
            + "</Appearance>\n"
            + "<IndexedFaceSet solid=\"false\"\n"
            + "                coordIndex=\"0 1 2 3 -1 4 0 3 7 -1 7 3 2 6 -1 6 2 1 5 -1 0 4 5 1 -1 7 6 5 4 -1\" >\n"
            + "<Coordinate DEF=\"coords_ME_Shape_Box\"\n"
            + "point=\"100.000000 0.010000 100.000000 \n"
            + "-100.000000 0.050000 100.000000 \n"
            + "-100.000000 0.050000 -100.000000 \n"
            + "100.000000 0.050000 -100.000000 \n"
            + "100.000000 0.050000 100.000000 \n"
            + "-100.000000 0.050000 100.000000 \n"
            + "-100.000000 0.050000 -100.000000 \n"
            + "100.000000 0.050000 -100.000000 \"/>"
            + "</IndexedFaceSet>\n"
            + "</Shape>\n"
            + "</Group>\n"
            + "</Transform>\n";

    public SeaLoader() {
    }

    public String compute() {
        return txt;
    }
}
