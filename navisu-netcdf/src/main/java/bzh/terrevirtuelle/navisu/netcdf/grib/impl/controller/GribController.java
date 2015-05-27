 package bzh.terrevirtuelle.navisu.netcdf.grib.impl.controller;



import bzh.terrevirtuelle.navisu.netcdf.grib.GribConstants;
import bzh.terrevirtuelle.navisu.netcdf.grib.impl.model.GribModel;
import bzh.terrevirtuelle.navisu.netcdf.grib.impl.view.GribLayer;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ucar.nc2.dt.grid.GeoGrid;
import ucar.nc2.dt.grid.GridDataset;

/**
 * User: jordan
 * Date: 24/11/2013
 */
public class GribController {

    protected File gribFile;

    protected GridDataset gridDataset;

    protected GribModel model;

    protected GribLayer layer;

    public GribController(String path) {
        this.gribFile = new File(path);

        if(this.gribFile == null) {
            throw new IllegalArgumentException("Grib path is not valid"); //Todo Exception
        }

        
        try {
            this.gridDataset = GridDataset.open(path);
        } catch (IOException ex) {
            Logger.getLogger(GribController.class.getName()).log(Level.SEVERE, null, ex);
        }
       

        //check grid dimension
        //TODO Check if all grib have the same titleText of grid !
        GeoGrid pressureGrid = this.gridDataset.findGridByName(GribConstants.PRESSURE_GRID_NAME);
        if(pressureGrid == null) {
            throw new IllegalArgumentException("Invalid Grib file"); //Todo Exception
        }

        GeoGrid uGrid = this.gridDataset.findGridByName(GribConstants.U_GRID_NAME);
        if(uGrid == null) {
            throw new IllegalArgumentException("Invalid Grib File"); //Todo Exception
        }

        GeoGrid vGrid = this.gridDataset.findGridByName(GribConstants.V_GRID_NAME);
        if(vGrid == null) {
            throw new IllegalArgumentException("Invalid Grib File"); //Todo Exception
        }

        //instantiate model
        this.model = new GribModel(gridDataset, pressureGrid, uGrid, vGrid);
        this.layer = new GribLayer(this.model);
    }

    public GribModel getModel() {
        return this.model;
    }

    public GribLayer getLayer() {
        return this.layer;
    }
}
