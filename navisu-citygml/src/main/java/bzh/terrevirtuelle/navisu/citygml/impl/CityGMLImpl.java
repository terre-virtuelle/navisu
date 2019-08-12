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
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
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
import org.citygml4j.model.gml.geometry.aggregates.MultiSurface;
import org.citygml4j.model.gml.geometry.aggregates.MultiSurfaceProperty;
import org.citygml4j.model.gml.geometry.complexes.CompositeSurface;
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
    public Building importSolid(SolidGeo solid) {
        Building result = null;
        return result;
    }

    @Override
    public List<Building> importSolid(List<SolidGeo> solids) {
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

                //Ground
                Geometry solidGround = g.getGround();

                if (solidGround != null) {
                    Coordinate[] coordinates = solidGround.getCoordinates();
                    double[] coordTab = new double[coordinates.length * 3];
                    for (int i = 0; i < coordinates.length; i++) {
                        coordTab[i] = coordinates[i].x;
                        coordTab[i + 1] = coordinates[i].y;
                        coordTab[i + 2] = coordinates[i].z;
                    }
                    ground = geom.createLinearPolygon(coordTab, 3);
                    ground.setId(gmlIdManager.generateUUID());
                }
                //Walls

                Set<FaceGeo> solidFaces = g.getFaces();
                for (FaceGeo f : solidFaces) {
                    List<Point3DGeo> vertices = f.getVertices();
                    double[] coordTab = new double[vertices.size() * 3];
                    for (int i = 0; i < vertices.size(); i++) {
                        coordTab[i] = vertices.get(i).getLongitude();
                        coordTab[i + 1] = vertices.get(i).getLatitude();
                        coordTab[i + 2] = vertices.get(i).getElevation();
                    }
                    Polygon wall = geom.createLinearPolygon(coordTab, 3);
                    wall.setId(gmlIdManager.generateUUID());
                    walls.add(wall);
                }
                //Roof
                SolidGeo solidRoof = g.getRoof();
                if (solidRoof != null) {
                    Set<FaceGeo> solidRoofFaces = solidRoof.getFaces();
                    for (FaceGeo f : solidRoofFaces) {
                        List<Point3DGeo> vertices = f.getVertices();
                        double[] coordTab = new double[vertices.size() * 3];
                        for (int i = 0; i < vertices.size(); i++) {
                            coordTab[i] = vertices.get(i).getLongitude();
                            coordTab[i + 1] = vertices.get(i).getLatitude();
                            coordTab[i + 2] = vertices.get(i).getElevation();
                        }
                        Polygon roof = geom.createLinearPolygon(coordTab, 3);
                        roof.setId(gmlIdManager.generateUUID());
                        roofs.add(roof);
                    }
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

                boundedBy.add(createBoundarySurface(org.citygml4j.model.citygml.CityGMLClass.BUILDING_GROUND_SURFACE, ground));
                walls.forEach((p) -> {
                    boundedBy.add(createBoundarySurface(CityGMLClass.BUILDING_WALL_SURFACE, p));
                });
                roofs.forEach((p) -> {
                    boundedBy.add(createBoundarySurface(CityGMLClass.BUILDING_ROOF_SURFACE, p));
                });
                building.setBoundedBySurface(boundedBy);
                result.add(building);
            }
            /*
            CityModel cityModel = new CityModel();
		cityModel.setBoundedBy(building.calcBoundedBy(BoundingBoxOptions.defaults()));
		cityModel.addCityObjectMember(new CityObjectMember(building));

		System.out.println(df.format(new Date()) + "writing citygml4j object tree");
		CityGMLOutputFactory out = builder.createCityGMLOutputFactory(CityGMLVersion.DEFAULT);
		CityGMLWriter writer = out.createCityGMLWriter(new File("output/LOD2_Building_v200.gml"), "UTF-8");

		writer.setPrefixes(CityGMLVersion.DEFAULT);
		writer.setSchemaLocations(CityGMLVersion.DEFAULT);
		writer.setIndentString("  ");
		writer.write(cityModel);
		writer.close();	
		
		System.out.println(df.format(new Date()) + "CityGML file LOD2_Building_v200.gml written");
		System.out.println(df.format(new Date()) + "sample citygml4j application successfully finished");
             */
        } catch (CityGMLBuilderException | DimensionMismatchException e) {
            System.out.println("Exception : " + e);
        }
        return result;
    }

    @Override
    public void write(List<Building> buildings) {
        SimpleDateFormat df = new SimpleDateFormat("[HH:mm:ss] ");

        CityGMLContext ctx = CityGMLContext.getInstance();
        CityGMLBuilder builder;
        CityGMLWriter writer;
        try {
            builder = ctx.createCityGMLBuilder();
            CityGMLOutputFactory out = builder.createCityGMLOutputFactory(CityGMLVersion.DEFAULT);
            writer = out.createCityGMLWriter(new File("output/LOD2_Building_v200.gml"), "UTF-8");
            writer.setPrefixes(CityGMLVersion.DEFAULT);
            writer.setSchemaLocations(CityGMLVersion.DEFAULT);
            writer.setIndentString("  ");

            CityModel cityModel = new CityModel();
            for (Building building : buildings) {
                cityModel.setBoundedBy(building.calcBoundedBy(BoundingBoxOptions.defaults()));
                cityModel.addCityObjectMember(new CityObjectMember(building));
            }
            writer.write(cityModel);
            writer.close();
        } catch (CityGMLWriteException | CityGMLBuilderException ex) {
            Logger.getLogger(CityGMLImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        System.out.println(df.format(new Date()) + "CityGML file LOD2_Building_v200.gml written");
        System.out.println(df.format(new Date()) + "sample citygml4j application successfully finished");
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
