/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.citygml.impl;

import bzh.terrevirtuelle.navisu.citygml.CityGML;
import bzh.terrevirtuelle.navisu.citygml.CityGMLServices;
import bzh.terrevirtuelle.navisu.domain.geometry.FaceGeo;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3DGeo;
import bzh.terrevirtuelle.navisu.domain.geometry.SolidGeo;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.capcaval.c3.component.ComponentState;

import org.citygml4j.CityGMLContext;
import org.citygml4j.builder.jaxb.CityGMLBuilder;
import org.citygml4j.builder.jaxb.CityGMLBuilderException;
import org.citygml4j.factory.DimensionMismatchException;
import org.citygml4j.factory.GMLGeometryFactory;
import org.citygml4j.model.citygml.CityGMLClass;
import org.citygml4j.model.citygml.building.AbstractBoundarySurface;
import org.citygml4j.model.citygml.building.BoundarySurfaceProperty;
import org.citygml4j.model.citygml.building.Building;
import org.citygml4j.model.citygml.building.GroundSurface;
import org.citygml4j.model.citygml.building.RoofSurface;
import org.citygml4j.model.citygml.building.WallSurface;
import org.citygml4j.model.citygml.core.CityModel;
import org.citygml4j.model.citygml.core.CityObjectMember;
import org.citygml4j.model.gml.feature.BoundingShape;
import org.citygml4j.model.gml.geometry.aggregates.MultiSurface;
import org.citygml4j.model.gml.geometry.aggregates.MultiSurfaceProperty;
import org.citygml4j.model.gml.geometry.complexes.CompositeSurface;
import org.citygml4j.model.gml.geometry.primitives.Envelope;
import org.citygml4j.model.gml.geometry.primitives.Polygon;
import org.citygml4j.model.gml.geometry.primitives.Solid;
import org.citygml4j.model.gml.geometry.primitives.SolidProperty;
import org.citygml4j.model.gml.geometry.primitives.SurfaceProperty;
import org.citygml4j.model.module.citygml.CityGMLVersion;
import org.citygml4j.util.bbox.BoundingBoxOptions;
import org.citygml4j.util.gmlid.DefaultGMLIdManager;
import org.citygml4j.util.gmlid.GMLIdManager;
import org.citygml4j.xml.io.CityGMLOutputFactory;
import org.citygml4j.xml.io.writer.CityGMLWriteException;
import org.citygml4j.xml.io.writer.CityGMLWriter;

/**
 * @date 13 mars 2015
 * @author Serge Morvan
 */
public class CityGMLImpl
        implements CityGML, CityGMLServices, ComponentState {

    @Override
    public Building exportSolid(SolidGeo solid) {
        Building result = null;
        return result;
    }

    @Override
    public List<Building> exportToGML(List<SolidGeo> solids) {
        List<Building> result = new ArrayList<>();
        try {
            CityGMLContext ctx = CityGMLContext.getInstance();
            CityGMLBuilder builder = ctx.createCityGMLBuilder();
            GMLGeometryFactory geom = new GMLGeometryFactory();
            GMLIdManager gmlIdManager = DefaultGMLIdManager.getInstance();

            for (SolidGeo g : solids) {
                Building building = new Building();

                List<SurfaceProperty> surfaceMember = new ArrayList<>();
                Polygon ground = null;
                List<Polygon> walls = new ArrayList<>();
                List<Polygon> roofs = new ArrayList<>();

                //Walls
                List<FaceGeo> solidFaces = g.getFaces();
                for (FaceGeo f : solidFaces) {
                    List<Point3DGeo> vertices = f.getVertices();
                    double[] coordTab = new double[vertices.size() * 3];
                    for (int i = 0; i < vertices.size(); i++) {
                        coordTab[i * 3] = vertices.get(i).getLongitude();
                        coordTab[i * 3 + 1] = vertices.get(i).getLatitude();
                        coordTab[i * 3 + 2] = vertices.get(i).getElevation();
                    }
                    Polygon wall = geom.createLinearPolygon(coordTab, 3);
                    wall.setId(gmlIdManager.generateUUID());
                    walls.add(wall);
                }
                //Roof
                List<FaceGeo> solidRoofFaces = g.getRoof();
                for (FaceGeo f : solidRoofFaces) {
                    List<Point3DGeo> vertices = f.getVertices();
                    double[] coordTab = new double[vertices.size() * 3];
                    for (int i = 0; i < vertices.size(); i++) {
                        coordTab[i * 3] = vertices.get(i).getLongitude();
                        coordTab[i * 3 + 1] = vertices.get(i).getLatitude();
                        coordTab[i * 3 + 2] = vertices.get(i).getElevation();
                    }
                    Polygon roof = geom.createLinearPolygon(coordTab, 3);
                    roof.setId(gmlIdManager.generateUUID());
                    roofs.add(roof);
                }

                if (ground != null) {
                    surfaceMember.add(new SurfaceProperty('#' + ground.getId()));
                }

                walls.forEach((w) -> {
                    surfaceMember.add(new SurfaceProperty('#' + w.getId()));
                });
                roofs.forEach((r) -> {
                    surfaceMember.add(new SurfaceProperty('#' + r.getId()));
                });

                CompositeSurface compositeSurface = new CompositeSurface();
                compositeSurface.setSurfaceMember(surfaceMember);

                Solid solid = new Solid();
                solid.setExterior(new SurfaceProperty(compositeSurface));
                building.setLod2Solid(new SolidProperty(solid));

                List<BoundarySurfaceProperty> boundedBy = new ArrayList<>();
                if (ground != null) {
                    boundedBy.add(createBoundarySurface(org.citygml4j.model.citygml.CityGMLClass.BUILDING_GROUND_SURFACE, ground));
                }
                walls.forEach((p) -> {
                    boundedBy.add(createBoundarySurface(CityGMLClass.BUILDING_WALL_SURFACE, p));
                });
                roofs.forEach((p) -> {
                    boundedBy.add(createBoundarySurface(CityGMLClass.BUILDING_ROOF_SURFACE, p));
                });
                building.setBoundedBySurface(boundedBy);
                result.add(building);
            }
        } catch (CityGMLBuilderException | DimensionMismatchException e) {
            System.out.println("Exception : " + e);
        }
        return result;
    }

    @Override
    public CityModel createCityModel(List<Building> buildings) {
        CityModel cityModel = new CityModel();
        for (Building building : buildings) {
            BoundingShape b = building.calcBoundedBy(BoundingBoxOptions.defaults());
            Envelope envelope = b.getEnvelope();
            envelope.setSrsName("urn:ogc:def:crs,crs:EPSG:4326");//:: OK for Qgis and FME; KO for cesium. 
            envelope.setSrsDimension(3);
            cityModel.setBoundedBy(b);
            cityModel.addCityObjectMember(new CityObjectMember(building));
        }
        return cityModel;
    }

    @Override
    public void write(CityModel cityModel, String name) {
        CityGMLContext ctx = CityGMLContext.getInstance();
        CityGMLBuilder builder;
        CityGMLWriter writer;
        try {
            builder = ctx.createCityGMLBuilder();
            CityGMLOutputFactory out = builder.createCityGMLOutputFactory(CityGMLVersion.DEFAULT);
            writer = out.createCityGMLWriter(new File(name), "UTF-8");
            writer.setPrefixes(CityGMLVersion.DEFAULT);
            writer.setSchemaLocations(CityGMLVersion.DEFAULT);
            writer.setIndentString("  ");
            writer.write(cityModel);
            writer.close();
        } catch (CityGMLWriteException | CityGMLBuilderException ex) {
            Logger.getLogger(CityGMLImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }


    private BoundarySurfaceProperty createBoundarySurface(CityGMLClass type, Polygon geometry) {
        AbstractBoundarySurface boundarySurface = null;

        switch (type) {
            case BUILDING_WALL_SURFACE:
                boundarySurface = new WallSurface();
                break;
            case BUILDING_ROOF_SURFACE:
                boundarySurface = new RoofSurface();
                break;
            case BUILDING_GROUND_SURFACE:
                boundarySurface = new GroundSurface();
                break;
            default:
                break;
        }

        if (boundarySurface != null) {
            boundarySurface.setLod2MultiSurface(new MultiSurfaceProperty(new MultiSurface(geometry)));
            return new BoundarySurfaceProperty(boundarySurface);
        }

        return null;
    }

    @Override
    public void componentInitiated() {
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

}
