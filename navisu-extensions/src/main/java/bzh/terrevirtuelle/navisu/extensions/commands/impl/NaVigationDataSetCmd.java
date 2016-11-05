
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.extensions.commands.impl;

import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationDataSet;
import bzh.terrevirtuelle.navisu.extensions.commands.NavigationCmd;
import bzh.terrevirtuelle.navisu.util.xml.ImportExportXML;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

/**
 *
 * @author serge
 * @date Mar 11, 2016
 *
 */
public class NaVigationDataSetCmd
        implements NavigationCmd {

    private static NaVigationDataSetCmd INSTANCE;
    private final String REP = "privateData/nds/";
    private NavigationDataSet navigationDataSet;

    public static NaVigationDataSetCmd getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NaVigationDataSetCmd();
        }
        return INSTANCE;
    }

    private NaVigationDataSetCmd() {
    }

    @Override
    public NavigationDataSet doIt(String arg) {
        String filename = REP + arg;
        navigationDataSet = new NavigationDataSet();
        try {
            navigationDataSet = ImportExportXML.imports(navigationDataSet, filename);
        } catch (FileNotFoundException | JAXBException ex) {
            Logger.getLogger(NaVigationDataSetCmd.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return navigationDataSet;
    }

}
