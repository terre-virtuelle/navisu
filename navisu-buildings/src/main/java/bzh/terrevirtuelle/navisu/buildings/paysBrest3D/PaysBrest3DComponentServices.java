/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.buildings.paysBrest3D;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import org.capcaval.c3.component.ComponentService;

/**
 * @date Jan 3, 2019 10:09:49 AM
 * @author Serge Morvan
 */
public interface PaysBrest3DComponentServices 
        extends ComponentService {

    InstrumentDriver getDriver();
}
