/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.gdal;

import bzh.terrevirtuelle.navisu.domain.raster.RasterInfo;
import org.capcaval.c3.component.ComponentService;

/**
 * @date 22 april 2019
 * @author Serge Morvan
 */
public interface GdalServices
        extends ComponentService {

    RasterInfo resample(RasterInfo rasterInfo);

    RasterInfo resample(RasterInfo src, RasterInfo target);

    RasterInfo gdalInfo(RasterInfo rasterInfo);

}
