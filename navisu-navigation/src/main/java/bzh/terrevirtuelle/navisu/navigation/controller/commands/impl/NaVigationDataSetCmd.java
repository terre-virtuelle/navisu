/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.controller.commands.impl;

import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationDataSet;
import bzh.terrevirtuelle.navisu.navigation.controller.commands.NavigationCmd;

/**
 *
 * @author serge
 * @date Mar 11, 2016
 *
 */
public class NaVigationDataSetCmd
        implements NavigationCmd {

    private static NaVigationDataSetCmd INSTANCE;

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
        System.out.println("NaVigationDataCmd " + arg);
        return null;
    }

}
