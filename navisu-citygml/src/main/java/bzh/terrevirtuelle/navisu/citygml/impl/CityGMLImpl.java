/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.citygml.impl;

import bzh.terrevirtuelle.navisu.citygml.CityGML;
import bzh.terrevirtuelle.navisu.citygml.CityGMLServices;
import bzh.terrevirtuelle.navisu.domain.geometry.SolidGeo;
import java.util.ArrayList;
import java.util.List;
import org.capcaval.c3.component.ComponentState;
import org.citygml4j.model.citygml.building.Building;

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
/*
    Building building = new Building();

		Polygon ground = geom.createLinearPolygon(new double[] {0,0,0, 0,12,0, 6,12,0, 6,0,0}, 3);
		Polygon wall_1 = geom.createLinearPolygon(new double[] {6,0,0, 6,12,0, 6,12,6, 6,0,6}, 3);
		Polygon roof_1 = geom.createLinearPolygon(new double[] {6,0,6, 6,12,6, 3,12,9, 3,0,9}, 3);
		ground.setId(gmlIdManager.generateUUID());
		wall_1.setId(gmlIdManager.generateUUID());
		roof_1.setId(gmlIdManager.generateUUID());
		// lod2 solid
		List<SurfaceProperty> surfaceMember = new ArrayList<>();
		surfaceMember.add(new SurfaceProperty('#' + ground.getId()));
		surfaceMember.add(new SurfaceProperty('#' + wall_1.getId()));
		surfaceMember.add(new SurfaceProperty('#' + roof_1.getId()));
		CompositeSurface compositeSurface = new CompositeSurface();
		compositeSurface.setSurfaceMember(surfaceMember);		
		Solid solid = new Solid();
		solid.setExterior(new SurfaceProperty(compositeSurface));
		building.setLod2Solid(new SolidProperty(solid));
		// thematic boundary surfaces
		List<BoundarySurfaceProperty> boundedBy = new ArrayList<>();
		boundedBy.add(createBoundarySurface(CityGMLClass.BUILDING_GROUND_SURFACE, ground));
		boundedBy.add(createBoundarySurface(CityGMLClass.BUILDING_WALL_SURFACE, wall_1));
		boundedBy.add(createBoundarySurface(CityGMLClass.BUILDING_ROOF_SURFACE, roof_1));		
		building.setBoundedBySurface(boundedBy);
    */
    @Override
    public List<Building> importSolid(List<SolidGeo> solids) {
        List<Building> result = new ArrayList<>();
        for (SolidGeo g : solids) {

        }
        return result;
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
