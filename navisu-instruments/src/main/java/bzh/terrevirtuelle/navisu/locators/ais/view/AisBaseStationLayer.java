/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.locators.ais.view;

import gov.nasa.worldwind.layers.IconLayer;
import java.util.logging.Logger;

/**
 *
 * @author Serge
 */
public class AisBaseStationLayer
        extends IconLayer {

    protected final static Logger LOGGUER = Logger.getLogger(AisBaseStationLayer.class.getName());

    public AisBaseStationLayer() {
        setName("AisBaseStation Layer");
    }

}
