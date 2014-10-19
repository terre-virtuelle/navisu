/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.impl.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import gov.nasa.worldwind.render.SurfacePolygons;
import java.util.ArrayList;
import java.util.List;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import org.capcaval.c3.component.annotation.UsedService;

/**
 *
 * @author Serge Morvan
 * @date 18 oct. 2014 NaVisu project
 */
public class AreaController implements EventHandler<Event> {

    @UsedService
    GuiAgentServices guiAgentServices;
    private static final AreaController instance = new AreaController();
    private final List<SurfacePolygons> shapes;

    private AreaController() {
        shapes = new ArrayList<>();
        guiAgentServices.getRoot().addEventFilter(EventType.ROOT, this);
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
