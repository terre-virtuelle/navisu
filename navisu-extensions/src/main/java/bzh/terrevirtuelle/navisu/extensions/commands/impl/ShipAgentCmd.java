/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.extensions.commands.impl;

import bzh.terrevirtuelle.navisu.agents.ship.ShipAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.domain.bathymetry.model.Depth;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationDataSet;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.extensions.commands.NavigationCmd;
import gov.nasa.worldwind.layers.RenderableLayer;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * NaVisu project
 *
 * @date Jan 22, 2019 10:09:20 AM
 * @author Serge Morvan
 */
@XmlRootElement(name = "ShipAgentCmd")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShipAgentCmd
        implements NavigationCmd {

    private final ShipAgentServices shipAgentServices;
    private final LayersManagerServices layersManagerServices;
    protected static final String GROUP_0 = "S57 charts";
    protected static final String S57_LAYER = "S57Stl";
    protected RenderableLayer s57Layer;

    private NavigationDataSet navigationDataSet;
    private static ShipAgentCmd INSTANCE;

    public static ShipAgentCmd getInstance(ShipAgentServices shipAgentServices, LayersManagerServices layersManagerServices) {
        if (INSTANCE == null) {
            INSTANCE = new ShipAgentCmd(shipAgentServices, layersManagerServices);
        }
        return INSTANCE;
    }

    private ShipAgentCmd(ShipAgentServices shipAgentServices, LayersManagerServices layersManagerServices) {
        this.shipAgentServices = shipAgentServices;
        this.layersManagerServices = layersManagerServices;
        s57Layer = layersManagerServices.getLayer(GROUP_0, S57_LAYER);
    }

    @Override
    public NavigationDataSet doIt(NavigationData arg) {
        Ship ship = (Ship) arg;
        navigationDataSet = new NavigationDataSet();
        shipAgentServices.setPosition(ship.getLatitude(),ship.getLongitude());
        shipAgentServices.setHeading(ship.getHeading());
        navigationDataSet.add(ship);
        return navigationDataSet;
    }

    @Override
    public NavigationDataSet doIt(NavigationData arg0, String arg1) {
        shipAgentServices.init((Ship)arg0, s57Layer, arg1);
        return new NavigationDataSet();
    }
}
