/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.controller.cmd.catalog;

import bzh.terrevirtuelle.navisu.domain.camera.model.Camera;
import bzh.terrevirtuelle.navisu.domain.navigation.NavigationData;
import bzh.terrevirtuelle.navisu.instruments.camera.CameraComponentServices;
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

    CameraComponentServices cameraComponentServices;
    
    public static CameraCmd INSTANCE;

    public static CameraCmd getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CameraCmd();
        }
        return INSTANCE;
    }

    private CameraCmd() {
    }

    public void setCameraComponentServices(CameraComponentServices cameraComponentServices) {
        this.cameraComponentServices = cameraComponentServices;
    }

    @Override
    public NavigationData doIt(NavigationData arg) {
        cameraComponentServices.updateTarget((Camera)arg);
        return arg;
    }

}
