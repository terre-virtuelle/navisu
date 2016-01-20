/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.controller.cmd.catalog;

import bzh.terrevirtuelle.navisu.domain.navigation.NavigationData;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author serge
 * @date Jan 18, 2016
 *
 */
@XmlRootElement(name = "CameraCmd")
@XmlAccessorType(XmlAccessType.FIELD)
public class CameraCmd
        implements NavigationCmd {

    public CameraCmd() {
    }

    @Override
    public NavigationData doIt(NavigationData arg) {
        System.out.println("arg " + arg);
        return arg;
    }

}
