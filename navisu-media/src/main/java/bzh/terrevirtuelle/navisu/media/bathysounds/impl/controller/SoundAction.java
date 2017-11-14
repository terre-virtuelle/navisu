/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.media.bathysounds.impl.controller;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriverManagerServices;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import java.util.Map;

/**
 *
 * @author serge
 * @date Oct 16, 2017
 */
public class SoundAction
        implements LocatableAction {

    protected Map<Point3D, String> soundMap;
    protected InstrumentDriverManagerServices instrumentDriverManagerServices;

    public SoundAction(InstrumentDriverManagerServices instrumentDriverManagerServices, 
            Map<Point3D, String> soundMap) {
        this.soundMap = soundMap;
        this.instrumentDriverManagerServices = instrumentDriverManagerServices;
    }

    public void doIt(Point3D point) {
        String sound = soundMap.get(point);
        instrumentDriverManagerServices.open(sound, "true", "1");
    }

}
