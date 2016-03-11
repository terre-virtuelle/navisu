/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.controller.commands;

import bzh.terrevirtuelle.navisu.domain.camera.model.Camera;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.S57Chart;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BeaconCardinal;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BeaconIsolatedDanger;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BeaconLateral;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BeaconSafeWater;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BeaconSpecialPurpose;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BuoyCardinal;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BuoyInstallation;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BuoyIsolatedDanger;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BuoyLateral;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BuoySafeWater;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BuoySpecialPurpose;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Landmark;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.MooringWarpingFacility;
import bzh.terrevirtuelle.navisu.domain.gpx.model.Gpx;
import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import bzh.terrevirtuelle.navisu.domain.navigation.navigationalWarnings.model.NavigationalWarnings;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.SailingDirections;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.SailingDirectionsOld;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author serge
 */
@XmlType(name = "arcommand", propOrder = {
    "cmd",
    "navigationData",
    "arg"
})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class ArCommand {

    private String cmd;
    @XmlElements({
        @XmlElement(name = "bcncar", type = BeaconCardinal.class),
        @XmlElement(name = "bcnisd", type = BeaconIsolatedDanger.class),
        @XmlElement(name = "bcnlat", type = BeaconLateral.class),
        @XmlElement(name = "bcnsaw", type = BeaconSafeWater.class),
        @XmlElement(name = "bcnspp", type = BeaconSpecialPurpose.class),
        @XmlElement(name = "buoycar", type = BuoyCardinal.class),
        @XmlElement(name = "buoyinb", type = BuoyInstallation.class),
        @XmlElement(name = "buoyisd", type = BuoyIsolatedDanger.class),
        @XmlElement(name = "buoylat", type = BuoyLateral.class),
        @XmlElement(name = "buoysaw", type = BuoySafeWater.class),
        @XmlElement(name = "buoyssp", type = BuoySpecialPurpose.class),
        @XmlElement(name = "morfac", type = MooringWarpingFacility.class),
        @XmlElement(name = "lndmrk", type = Landmark.class),
        @XmlElement(name = "ship", type = Ship.class),
        @XmlElement(name = "sailingDirections", type = SailingDirections.class),
        @XmlElement(name = "s57Chart", type = S57Chart.class),
        @XmlElement(name = "gpx", type = Gpx.class),
        @XmlElement(name = "camera", type = Camera.class)
    })
    private NavigationData navigationData = null;
    private String arg;

    public ArCommand() {
    }

    public ArCommand(String cmd, NavigationData navigationData) {
        this.cmd = cmd;
        this.navigationData = navigationData;

    }

    public ArCommand(String cmd, String arg) {
        this.cmd = cmd;
        this.arg = arg;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public NavigationData getNavigationData() {
        return navigationData;
    }

    public void setNavigationData(NavigationData navigationData) {
        this.navigationData = navigationData;
    }

    public String getArg() {
        return arg;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

    @Override
    public String toString() {
        return "ArCommand{" + "cmd=" + cmd + ", navigationData=" + navigationData + ", arg=" + arg + '}';
    }


}
