/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.gps.track.polygon;

import java.util.LinkedList;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.instruments.common.view.panel.TrackPanel;

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
    
    void quadShapeOn();
    
    void freeHandOn();
    
    void createCpaZone();
    
    void createCpaZone(double yards);
    
    void activateCpaZone();
    
    void createPath();
    
    void activatePath();
    
    void savePath();
    
    void loadPath();
    
    void createRule();
    
    void activateRule();
    
    LinkedList<Ship> getSavedAisShips();
    
    TrackPanel getPanel();
    
    boolean getComponentReady();
    
    int getInSight();
}
