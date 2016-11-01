/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.netcdf.common.controller;

import bzh.terrevirtuelle.navisu.netcdf.impl.controller.NetCdfController;
import java.util.logging.Logger;

/**
 *
 * @author serge
 */
public class NetCdfInfoController extends NetCdfController{
private static final Logger LOGGER = Logger.getLogger(NetCdfInfoController.class.getName());

    public NetCdfInfoController(String fileName) {
        super(fileName);
        variables.stream().forEach((v) -> {
            System.out.println(v.getNameAndDimensions());
        });
        netcdf.getNetcdfDataset().getDetailInfo();
        System.out.println("****************");
        System.out.println("GlobalAttributes");
        System.out.println(netcdf.getNetcdfDataset().getGlobalAttributes());
        System.out.println("DetailInfo");
        System.out.println(netcdf.getNetcdfDataset().getDetailInfo());
        System.out.println("Groups");
        System.out.println(netcdf.getNetcdfDataset().getRootGroup().getGroups());
        System.out.println("FileTypeDescription");
        System.out.println(netcdf.getNetcdfDataset().getFileTypeDescription());
        System.out.println("******************");
        
        System.out.println("u : " + u.getSize());
        System.out.println("v : " + v.getSize());
        System.out.println("lat : " + latitudes.getSize());
        System.out.println("lon : " + longitudes.getSize());
        if (time != null) {
            System.out.println("time : " + time.getSize());
        }
        if (height != null) {
            System.out.println("height : " + height.getSize());
            long size = latitudes.getSize()*longitudes.getSize()*time.getSize()*height.getSize();
            System.out.println("size : " + size);
        }
    }
    
}
