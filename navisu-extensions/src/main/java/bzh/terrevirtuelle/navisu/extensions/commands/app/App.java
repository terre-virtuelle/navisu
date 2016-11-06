/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.extensions.commands.app;

import bzh.terrevirtuelle.navisu.domain.camera.model.Camera;
import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import bzh.terrevirtuelle.navisu.extensions.commands.ArCommand;
import bzh.terrevirtuelle.navisu.util.xml.ImportExportXML;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

/**
 *
 * @author serge
 * @date Jan 18, 2016
 *
 */
public class App {

    public App() {
        //Emission
        NavigationData camera = new Camera();
        ArCommand cmd = new ArCommand("cmd", camera);
        try {
            ImportExportXML.exports(cmd, "cmd.xml");
        } catch (JAXBException | FileNotFoundException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Reception
        cmd = new ArCommand();
        try {
            cmd = ImportExportXML.imports(cmd, "cmd.xml");
        } catch (JAXBException | FileNotFoundException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("cmd : " + cmd);
    }

    public static void main(String[] args) {
        new App();
    }

}
