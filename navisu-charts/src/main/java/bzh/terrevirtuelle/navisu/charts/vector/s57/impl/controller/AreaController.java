/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.impl.controller;

import gov.nasa.worldwind.render.SurfacePolygons;
import java.util.ArrayList;
import java.util.List;
import javafx.event.Event;
import javafx.event.EventHandler;

/**
 *
 * @author Serge Morvan
 * @date 18 oct. 2014 NaVisu project
 */
public class AreaController implements EventHandler<Event> {

   
    private static final AreaController instance = new AreaController();
    private final List<SurfacePolygons> shapes;

    private AreaController() {
        shapes = new ArrayList<>();
    }

    public static AreaController getInstance() {
        return instance;
    }

    public void add(SurfacePolygons shape) {
        shapes.add(shape);
    }

    @Override
    public void handle(Event event) {
        System.out.println("event : " + event );
    }
    
}
