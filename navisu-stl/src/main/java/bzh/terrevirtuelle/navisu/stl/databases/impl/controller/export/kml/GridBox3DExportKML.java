/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.kml;

import bzh.terrevirtuelle.navisu.geometry.objects3D.GridBox3D;
import bzh.terrevirtuelle.navisu.kml.impl.controller.wwj.KMLDocumentBuilder;
import bzh.terrevirtuelle.navisu.visualization.view.impl.DisplayImpl;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.Polygon;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author serge
 */
public class GridBox3DExportKML {

    private GridBox3D gridBox;

    /**
     * Get the value of gridBox
     *
     * @return the value of gridBox
     */
    public GridBox3D getGridBox() {
        return gridBox;
    }

    /**
     * Set the value of gridBox
     *
     * @param gridBox new value of gridBox
     */
    public void setGridBox(GridBox3D gridBox) {
        this.gridBox = gridBox;
    }

    public GridBox3DExportKML(GridBox3D gridBox) {
        this.gridBox = gridBox;
    }

    public void exportWKML(String outputFilename, boolean solid) {
        List<Path> result = new ArrayList<>();
        List<Polygon> res = new ArrayList<>();
        gridBox.getPaths().forEach((p) -> {
            Iterable<? extends Position> positions = p.getPositions();
            List<Position> tmpPos = new ArrayList<>();
            for (Position pp : positions) {
                tmpPos.add(new Position(pp.getLatitude(), pp.getLongitude(), pp.getElevation()));
            }
            result.add(new Path(tmpPos));
            res.add(new Polygon(tmpPos));
        });
        Path[] pathTab = null;
        Polygon[] polyTab = null;
        if (solid == false) {
            pathTab = new Path[result.size()];
            for (int i = 0; i < result.size(); i++) {
                pathTab[i] = result.get(i);
            }
        } else {
            polyTab = new Polygon[result.size()];
            for (int i = 0; i < result.size(); i++) {
                polyTab[i] = res.get(i);
            }
        }
        try {
            Writer stringWriter = new StringWriter();
            KMLDocumentBuilder kmlBuilder = new KMLDocumentBuilder(stringWriter);
            if (solid == false) {
                kmlBuilder.writeObjects(pathTab);
            } else {
                kmlBuilder.writeObjects(polyTab);
            }
            kmlBuilder.close();
            String xmlString = stringWriter.toString();
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(new StreamSource(new StringReader(xmlString)), new StreamResult(new File(outputFilename)));
        } catch (IOException | IllegalArgumentException | XMLStreamException | TransformerException ex) {
            Logger.getLogger(DisplayImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }
}
