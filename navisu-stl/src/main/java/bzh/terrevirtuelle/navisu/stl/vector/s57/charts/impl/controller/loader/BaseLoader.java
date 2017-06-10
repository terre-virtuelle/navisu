/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.vector.s57.charts.impl.controller.loader;

/**
 *
 * @author serge
 * @date Mar 18, 2017
 */
public class BaseLoader {

    /*
    String txt = "<Transform DEF=\"Shape_Box_TRANSFORM\" \n"
            + "translation=\"100.000000 -0.0000 -100.000000\" >\n"
            + "<Group DEF=\"group_ME_Shape_Box\">\n"
            + "<Shape>\n"
            + "<Appearance>\n"
            + "<ImageTexture DEF='Sea' url='\"mer.jpg\"'/> \n"
            + "<Material\n"
            + "diffuseColor=\"0.0600 0.1200 0.2200\">\n"
            + "</Material> \n"
            + "</Appearance>\n"
            + "<IndexedFaceSet solid=\"false\"\n"
            + "                coordIndex=\"0 1 2 3 -1 4 0 3 7 -1 7 3 2 6 -1 6 2 1 5 -1 0 4 5 1 -1 7 6 5 4 -1\" >\n"
            + "<Coordinate DEF=\"coords_ME_Shape_Box\"\n"
            + "point=\"100.000000 0.050000 100.000000 \n"
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
     */
    String txt = " <!--" + "Base for charts" + "-->\n"
            + "<Transform DEF=\"Shape_Box_TRANSFORM\" \n"
            + "translation=\"100.000000 -2.15 -100.000000\"  \n"
            + "scale=\"200.00000 200.00000 200.00000\" \n"
            + "rotation=\"0.000000 1.000000 0.000000 3.14116\"> \n"
            + "<Transform DEF=\"Box1_Box_001_TRANSFORM\"\n"
            + "		           translation=\"0.000000 0.012500 0.000000\"\n"
            + "		           scale=\"0.100000 0.500000 0.100000\"\n"
            + "		           rotation=\"0.000000 0.000000 -0.000000 0.000000\"\n"
            + "		           >\n"
            + "			<Transform DEF=\"Box1_Box_001_ifs_TRANSFORM\"\n"
            + "			           translation=\"0.000000 0.000000 0.000000\"\n"
            + "			           scale=\"1.000000 1.000000 1.000000\"\n"
            + "			           rotation=\"0.000000 -0.000000 0.000000 0.000000\"\n"
            + "			           >\n"
            + "				<Group DEF=\"group_ME_Box1_Box\">\n"
            + "					<Shape>\n"
            + "						<Appearance>\n"
            + "							<Material DEF=\"MA_Box1\"\n"
            + "							          diffuseColor=\"1.000 0.000 0.000\"\n"
            + "							          specularColor=\"0.000 0.000 0.000\"\n"
            + "							          emissiveColor=\"0.000 0.000 0.000\"\n"
            + "							          ambientIntensity=\"0.067\"\n"
            + "							          shininess=\"0.201\"\n"
            + "							          transparency=\"0.0\"\n"
            + "							          />\n"
            + "						</Appearance>\n"
            + "						<IndexedFaceSet solid=\"true\"\n"
            + "						                coordIndex=\"0 1 2 3 -1 4 0 3 7 -1 7 3 2 6 -1 6 2 1 5 -1 0 4 5 1 -1 7 6 5 4 -1 \"\n"
            + "						                >\n"
            + "							<Coordinate DEF=\"coords_ME_Box1_Box\"\n"
            + "							            point=\"5.000000 0.005000 5.000000 -5.000000 0.005000 5.000000 -5.000000 0.005000 -5.000000 5.000000 0.005000 -5.000000 5.000000 -0.005000 5.000000 -5.000000 -0.005000 5.000000 -5.000000 -0.005000 -5.000000 5.000000 -0.005000 -5.000000 \"\n"
            + "							            />\n"
            + "						</IndexedFaceSet>\n"
            + "					</Shape>\n"
            + "				</Group>\n"
            + "			</Transform>\n"
            + "		</Transform>\n"
            + "		<Transform DEF=\"Box1_Box_TRANSFORM\"\n"
            + "		           translation=\"0.000000 -0.002500 0.000000\"\n"
            + "		           scale=\"0.100000 0.500000 0.100000\"\n"
            + "		           rotation=\"0.000000 0.000000 -0.000000 0.000000\"\n"
            + "		           >\n"
            + "			<Transform DEF=\"Box1_Box_ifs_TRANSFORM\"\n"
            + "			           translation=\"-0.000000 0.000000 0.000000\"\n"
            + "			           scale=\"1.000000 1.000000 1.000000\"\n"
            + "			           rotation=\"0.000000 -0.000000 0.000000 0.000000\"\n"
            + "			           >\n"
            + "				<Group USE=\"group_ME_Box1_Box\" />\n"
            + "			</Transform>\n"
            + "		</Transform>\n"
            + "		<Transform DEF=\"Extrusion2_Extrusion_TRANSFORM\"\n"
            + "		           translation=\"0.000000 0.000000 -0.500000\"\n"
            + "		           scale=\"0.099972 0.040000 0.100000\"\n"
            + "		           rotation=\"0.000000 0.000000 -0.000000 0.000000\"\n"
            + "		           >\n"
            + "			<Transform DEF=\"Extrusion2_Extrusion_ifs_TRANSFORM\"\n"
            + "			           translation=\"0.000000 0.000000 0.000000\"\n"
            + "			           scale=\"1.000000 1.000000 1.000000\"\n"
            + "			           rotation=\"-0.000000 -0.000000 0.000000 0.000000\"\n"
            + "			           >\n"
            + "				<Group DEF=\"group_ME_Extrusion2_Extrusion\">\n"
            + "					<Shape>\n"
            + "						<Appearance>\n"
            + "							<Material USE=\"MA_Box1\" />\n"
            + "						</Appearance>\n"
            + "						<IndexedFaceSet solid=\"true\"\n"
            + "						                coordIndex=\"0 19 18 -1 17 16 15 -1 0 18 17 -1 2 1 0 -1 3 2 0 -1 0 17 15 -1 15 14 13 -1 12 11 10 -1 15 13 12 -1 3 0 15 -1 6 5 4 -1 4 3 15 -1 15 12 10 -1 15 10 9 -1 6 4 15 -1 8 7 6 -1 15 9 8 -1 15 8 6 -1 0 1 21 20 -1 1 2 22 21 -1 2 3 23 22 -1 3 4 24 23 -1 4 5 25 24 -1 5 6 26 25 -1 6 7 27 26 -1 7 8 28 27 -1 8 9 29 28 -1 9 10 30 29 -1 10 11 31 30 -1 11 12 32 31 -1 12 13 33 32 -1 13 14 34 33 -1 14 15 35 34 -1 15 16 36 35 -1 16 17 37 36 -1 17 18 38 37 -1 18 19 39 38 -1 19 0 20 39 -1 20 21 22 -1 20 22 23 -1 38 39 20 -1 35 36 37 -1 37 38 20 -1 20 23 24 -1 24 25 26 -1 26 27 28 -1 29 30 31 -1 24 26 28 -1 29 31 32 -1 24 28 29 -1 37 20 24 -1 33 34 35 -1 35 37 24 -1 24 29 32 -1 32 33 35 -1 24 32 35 -1 \"\n"
            + "						                >\n"
            + "							<Coordinate DEF=\"coords_ME_Extrusion2_Extrusion\"\n"
            + "							            point=\"-0.500000 0.000000 0.000000 -5.000000 0.000000 0.000000 -5.000000 0.000000 4.500000 -4.500000 0.000000 4.500000 -4.500000 0.000000 5.500000 -5.000000 0.000000 5.500000 -5.000000 0.000000 10.000000 -0.500000 0.000000 10.000000 -0.500000 0.000000 9.500000 0.500000 0.000000 9.500000 0.500000 0.000000 10.000000 5.000000 0.000000 10.000000 5.000000 0.000000 5.500000 5.500000 0.000000 5.500000 5.500000 0.000000 4.500000 5.000000 0.000000 4.500000 5.000000 0.000000 0.000000 0.500000 0.000000 0.000000 0.500000 0.000000 -0.500000 -0.500000 0.000000 -0.500000 -0.500000 0.250000 0.000000 -5.000000 0.250000 0.000000 -5.000000 0.250000 4.500000 -4.500000 0.250000 4.500000 -4.500000 0.250000 5.500000 -5.000000 0.250000 5.500000 -5.000000 0.250000 10.000000 -0.500000 0.250000 10.000000 -0.500000 0.250000 9.500000 0.500000 0.250000 9.500000 0.500000 0.250000 10.000000 5.000000 0.250000 10.000000 5.000000 0.250000 5.500000 5.500000 0.250000 5.500000 5.500000 0.250000 4.500000 5.000000 0.250000 4.500000 5.000000 0.250000 0.000000 0.500000 0.250000 0.000000 0.500000 0.250000 -0.500000 -0.500000 0.250000 -0.500000 \"\n"
            + "							            />\n"
            + "						</IndexedFaceSet>\n"
            + "					</Shape>\n"
            + "				</Group>\n"
            + "			</Transform>\n"
            + "		</Transform>\n"
            + "	</Transform>";

    public BaseLoader() {
    }

    public String compute() {
        return txt;
    }
}
