/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.netcdf.meteo.view;

import bzh.terrevirtuelle.navisu.netcdf.common.view.NetCDFViewer;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.domain.netcdf.Netcdf;
import bzh.terrevirtuelle.navisu.domain.netcdf.common.TimeSeriesVectorField;
import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.Contour;
import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.Delaunay_Triangulation;
import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.Point_dt;
import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.Triangle_dt;
import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.util.DT;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.ShapeAttributes;
import java.io.BufferedWriter;
import java.nio.file.Files;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import ucar.nc2.Variable;

/**
 *
 * @author serge
 * @date Nov 24, 2016
 */
public class PressureNetCDFViewer 
        extends NetCDFViewer {

    public PressureNetCDFViewer(GuiAgentServices guiAgentServices,
            RenderableLayer vectorLayer,
            RenderableLayer analyticLayer,
            RenderableLayer legendLayer,
            Netcdf netcdf,
            String dataInfo,
            List<Variable> variables,
            String name, String fileName,
            TimeSeriesVectorField timeSeriesVectorField) {
        super(guiAgentServices,
                vectorLayer, analyticLayer, legendLayer,
                netcdf,
                dataInfo, variables,
                name, fileName,
                timeSeriesVectorField);
    }

    @Override
    protected void createVectors() {

    }

    @Override
    protected void createIsolines() {
        //Pb sur la valeur associee a la position
        ShapeAttributes attrs = new BasicShapeAttributes();
        attrs.setOutlineOpacity(1.0);
        attrs.setOutlineWidth(1d);
        int a = latDimension * lonDimension;
        final Delaunay_Triangulation dt = new Delaunay_Triangulation();
        int l = 0;
        double average = 0.0;
        int z=0;
        for (int i = 0; i < values.length; i++) {
            if (!Double.isNaN(values[i])) {
                average += values[i];
                z++;
            }
        }
        average = average / z;
        System.out.println("average : " + average);
        
        String file = "data.txt";
      //  try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(file))) {
            for (int h = 0; h < latDimension; h += 10) {
                for (int w = 0; w < lonDimension; w += 10) {
                    if ((!Double.isNaN(values[h + l + w]))) {
                        dt.insertPoint(new Point_dt(latTab[h], lonTab[w], values[l + h + w]));
                        //writer.write(String.valueOf(values[l + h + w]) + " ");
                    } else {
                        dt.insertPoint(new Point_dt(latTab[h], lonTab[w], average));
                    }
                }
              //  writer.write("\n");
                l += lonDimension;
            }
      //  } catch (Exception e) {
       //     System.out.println("e " + e);
       // }

        Path triangle;
        int i = 0;
        double h = 0;
        System.out.println("dt.get_triangles() : " + dt.get_triangles().size());
        for (Triangle_dt t : dt.get_triangles()) {
            if (t.A != null && t.B != null && t.C != null) {
                //  System.out.println("t : " + t.A.x + " " + t.B.x + " " + t.C.x);
                //  System.out.println("t : " + t.A.y + " " + t.B.y + " " + t.C.y);
                //  System.out.println("t : " + t.A.z + " " + t.B.z + " " + t.C.z);
                h += t.B.z;
                Path path;//Polygon
                ArrayList<Position> pathPositions = new ArrayList<>();
                pathPositions.add(Position.fromDegrees(t.A.x, t.A.y, 300000+(-average + t.A.z) * 200));
                pathPositions.add(Position.fromDegrees(t.B.x, t.B.y, 300000+(-average + t.B.z) * 200));
                pathPositions.add(Position.fromDegrees(t.C.x, t.C.y, 300000+(-average + t.C.z) * 200));
                pathPositions.add(Position.fromDegrees(t.A.x, t.A.y, 300000+(-average + t.A.z) * 200));
                path = new Path(pathPositions);
                path.setAttributes(attrs);
                path.setValue(AVKey.DISPLAY_NAME, (int)t.B.z);
                vectorLayer.addRenderable(path);
                i++;
            }
        };
        System.out.println("h moyen : " + h / i);
        final ArrayList<Contour> contours = DT.contours(dt, 200);//600

        contours.stream().map((c) -> {
            Path path;
            ArrayList<Position> pathPositions = new ArrayList<>();
            c.points.forEach((p) -> {
                pathPositions.add(Position.fromDegrees(p.x, p.y, 1000));
            });
            path = new Path(pathPositions);
            path.setAttributes(attrs);
            //  path.setValue(AVKey.DISPLAY_NAME, (int)c.level/100);
            return path;
        }).forEachOrdered((path) -> {
            vectorLayer.addRenderable(path);
        });

    }
}
