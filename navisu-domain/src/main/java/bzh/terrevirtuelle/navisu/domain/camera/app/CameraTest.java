/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.camera.app;

import bzh.terrevirtuelle.navisu.domain.camera.model.Camera;
import bzh.terrevirtuelle.navisu.domain.camera.model.CameraBuilder;
import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationDataSet;
import bzh.terrevirtuelle.navisu.util.xml.ImportExportXML;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

/**
 *
 * @author serge
 * @date Jan 14, 2016
 *
 */
public class CameraTest {

    NavigationData camera;

    public CameraTest() {
        camera = new Camera(44.25, -4.28, 10);
        System.out.println(camera);
        NavigationDataSet navigationDataSet = new NavigationDataSet();
        navigationDataSet.add(camera);
        
        try {
            navigationDataSet = ImportExportXML.exports(navigationDataSet, "data/cameraTest.xml");
        } catch (JAXBException | IOException ex) {
            Logger.getLogger(CameraTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
        System.out.println(navigationDataSet);
        
        try {
            navigationDataSet = new NavigationDataSet();
            navigationDataSet = ImportExportXML.imports(navigationDataSet, "data/cameraTest.xml");
        } catch (FileNotFoundException | JAXBException ex) {
            Logger.getLogger(CameraTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(navigationDataSet);
        System.out.println(camera.getGeometry());
        
        camera = CameraBuilder.create()
                .latitude(52.0)
                .longitude(3.6)
                .heading(125)
                .fieldOfView(56.0)
                .nearClipDistance(5000)
                .build();
        System.out.println(camera);
    }

    public static void main(String[] args) {
        new CameraTest();
    }
}
