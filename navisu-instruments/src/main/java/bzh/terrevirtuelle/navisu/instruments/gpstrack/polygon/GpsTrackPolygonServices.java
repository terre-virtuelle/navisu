/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.gpstrack.polygon;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;

import org.capcaval.c3.component.ComponentService;

/**
 * @date 3 mars 2015
 * @author Serge Morvan
 */
public interface GpsTrackPolygonServices extends ComponentService {

    void on(String ... files);

    default void off() {
    }

    boolean isOn();

    boolean canOpen(String category);

    InstrumentDriver getDriver();
    
    void drawerOn();
    
    void savePolygon();
    
    void saveAllPolygons();
    
    void loadPolygons();
    
    void polyShapeOn();
    
    void ellipseShapeOn();
    
    void circleShapeOn();
    
    void freeHandOn();
    
    void createCpaZone();
    
    void createCpaZone500();
    
    void createCpaZone1000();
    
    void activateCpaZone();
}
