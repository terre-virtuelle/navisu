package bzh.terrevirtuelle.navisu.netcdf.grib.impl.controller;

import bzh.terrevirtuelle.navisu.netcdf.grib.GribConstants;
import bzh.terrevirtuelle.navisu.netcdf.grib.impl.model.GribModel;
import bzh.terrevirtuelle.navisu.netcdf.grib.impl.view.GribLayer;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ucar.nc2.NetcdfFile;
import ucar.nc2.dataset.NetcdfDataset;
import ucar.nc2.dt.GridDatatype;
import ucar.nc2.dt.grid.GeoGrid;
import ucar.nc2.dt.grid.GridDataset;

/**
 * User: jordan Date: 24/11/2013
 */
public class GribController {

    protected File gribFile;
    protected NetcdfDataset netcdfDataset;
    NetcdfFile ncfile = null;
    protected GridDataset gridDataset;

    protected GribModel model;

    protected GribLayer layer;

    public GribController(String path) {

        /*
         this.gribFile = new File(path);
            
         if (this.gribFile == null) {
         throw new IllegalArgumentException("Grib path is not valid"); //Todo Exception
         }
            
         try {
         this.gridDataset = GridDataset.open(path);
         } catch (IOException ex) {
         Logger.getLogger(GribController.class.getName()).log(Level.SEVERE, null, ex);
         }
         */
        try {
            netcdfDataset = NetcdfDataset.openDataset(path);//OK
            //ncfile = NetcdfDataset.openFile(path, null);//ok
            //ncfile = NetcdfDataset.open(path);//ok
            gridDataset = new GridDataset(netcdfDataset);//ok
            // System.out.println(gridDataset.getGrids());
            for (GridDatatype g : gridDataset.getGrids()) {
                System.out.println("g : " + g.getShortName());
            }
            //  System.out.println("netcdfDataset " + netcdfDataset);
            // System.out.println("ncfile " + ncfile);
            //  System.out.println("gridDataset " + gridDataset);
        } catch (IOException ex) {
            Logger.getLogger(GribController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // dumpMetaDataGribDataSet(gridDataset);
        //check grid dimension
        //TODO Check if all grib have the same titleText of grid !
        GeoGrid pressureGrid = null;
/*
        pressureGrid = this.gridDataset.findGridByShortName(GribConstants.PRESSURE_GRID_NAME);
        if (pressureGrid == null) {
            throw new IllegalArgumentException("Invalid Grib file " + GribConstants.PRESSURE_GRID_NAME); //Todo Exception
        }
        */
         GeoGrid uGrid = this.gridDataset.findGridByShortName(GribConstants.U_GRID_NAME);

         if (uGrid == null) {
         throw new IllegalArgumentException("Invalid Grib File " + GribConstants.U_GRID_NAME); //Todo Exception
         }
        
         GeoGrid vGrid = this.gridDataset.findGridByShortName(GribConstants.V_GRID_NAME);
         if (vGrid == null) {
         throw new IllegalArgumentException("Invalid Grib File "+GribConstants.V_GRID_NAME); //Todo Exception
         }
        System.out.println("GribController");
         //instantiate model
         this.model = new GribModel(gridDataset, pressureGrid, uGrid, vGrid);
         System.out.println("GribController");
         this.layer = new GribLayer(this.model);
         System.out.println("GribController");
         
    }

    public GribModel getModel() {
        return this.model;
    }

    public GribLayer getLayer() {
        return this.layer;
    }

    public void dumpMetaDataGribDataSet(GridDataset gridDataset) {
        System.out.println("Title : " + gridDataset.getTitle());
        System.out.println("Description : " + gridDataset.getDescription());
        System.out.println("DetailInfo : " + gridDataset.getDetailInfo());
        System.out.println("ImplementationName : " + gridDataset.getImplementationName());
        System.out.println("CalendarDateStart : " + gridDataset.getCalendarDateStart().toDate());
        System.out.println("CalendarDateEnd : " + gridDataset.getCalendarDateEnd().toDate());
        System.out.println("GlobalAttributes :" + gridDataset.getGlobalAttributes());
        System.out.println("Grids :" + gridDataset.getGrids());
        System.out.println("Gridsets :" + gridDataset.getGridsets());
        System.out.println("CalendarDateRange : " + gridDataset.getCalendarDateRange().getDuration());
        System.out.println("Variables : " + gridDataset.getDataVariables());

    }
}
