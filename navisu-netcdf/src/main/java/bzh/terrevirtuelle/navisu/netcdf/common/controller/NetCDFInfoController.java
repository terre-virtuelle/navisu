/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.netcdf.common.controller;

import bzh.terrevirtuelle.navisu.domain.util.Pair;
import bzh.terrevirtuelle.navisu.netcdf.impl.controller.NetCDFController;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import ucar.nc2.dataset.CoordinateAxis;

/**
 *
 * @author serge
 */
public class NetCDFInfoController
        extends NetCDFController {

    private static final Logger LOGGER = Logger.getLogger(NetCDFInfoController.class.getName());

    public NetCDFInfoController(String fileName) {
        super(fileName);
        System.out.println("****************");
        System.out.println("Variables");
        System.out.println("****************");
        variables.stream().forEach((v) -> {
            System.out.println(v.getNameAndDimensions());
        });
        netcdf.getNetcdfDataset().getDetailInfo();
        System.out.println("****************");
        System.out.println("GlobalAttributes");
        System.out.println("****************");
        System.out.println(netcdf.getNetcdfDataset().getGlobalAttributes());
        List<CoordinateAxis> attr = netcdf.getNetcdfDataset().getCoordinateAxes();
        String[] d = null;
        for (CoordinateAxis a : attr) {
            if (a.getFullName().equals("time")) {
                d = a.getUnitsString().split(" ");
            }
        }
        Date date = null;
        if (d != null) {
            date = Date.from(Instant.parse(d[d.length - 1]));
        }
        System.out.println("date : " + date);
        System.out.println("****************");
        System.out.println("DetailInfo");
        System.out.println("****************");
        System.out.println(netcdf.getNetcdfDataset().getDetailInfo());

        System.out.println("****************");
        System.out.println("Groups");
        System.out.println("****************");
        System.out.println(netcdf.getNetcdfDataset().getRootGroup().getGroups());
        System.out.println("****************");
        System.out.println("FileTypeDescription");
        System.out.println("****************");
        System.out.println(netcdf.getNetcdfDataset().getFileTypeDescription());
        System.out.println("****************");
        System.out.println("Autres");
        System.out.println("****************");
        // netcdf.getNetcdfDataset().
        System.out.println("u : " + u.getSize());
        System.out.println("v : " + v.getSize());
        System.out.println("lat : " + latitudes.getSize());
        System.out.println("lon : " + longitudes.getSize());
        Pair<Double, Double> minMax = Pair.minMax(latitudes);
        System.out.println("Lat min : " + minMax.getX());
        System.out.println("Lat max : " + minMax.getY());
        minMax = Pair.minMax(longitudes);
        System.out.println("Lon min : " + minMax.getX());
        System.out.println("Lon max : " + minMax.getY());
        if (time != null) {
            System.out.println("time : " + time.getSize());
        }
        if (height != null) {
            System.out.println("height : " + height.getSize());
            long size = latitudes.getSize() * longitudes.getSize() * time.getSize() * height.getSize();
            System.out.println("size : " + size);
        }
        System.out.println("****************");
        System.out.println("Times");
        System.out.println("****************");
        System.out.println("Seq " + netcdf.read("time"));
        System.out.println("reftime " + netcdf.read("reftime"));

        System.out.println("****************");
        System.out.println("Filename");
        System.out.println("****************");
        String[] nameTab = fileName.split("\\/");
        String name = nameTab[nameTab.length - 1];
        String[] name1Tab = nameTab[nameTab.length - 1].split("\\.");
        System.out.println("Name " + name1Tab[0]);
        System.out.println("****************");

    }

}
