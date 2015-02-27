/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.locators.gps.view;

import gov.nasa.worldwind.layers.RenderableLayer;
import java.util.logging.Logger;

/**
 *
 * @author Serge
 */
public class GpsLayer
        extends RenderableLayer {

    protected final static Logger LOGGUER = Logger.getLogger(GpsLayer.class.getName());
   
    public GpsLayer() {  
        setName("GPS Layer");
    }
}
