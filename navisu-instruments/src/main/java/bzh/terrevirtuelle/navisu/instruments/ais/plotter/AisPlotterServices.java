/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.ais.plotter;

import org.capcaval.c3.component.ComponentService;

/**
 * @date 3 mars 2015
 * @author Serge Morvan
 */
public interface AisPlotterServices extends ComponentService {

    void on();

    default void off() {
    }

    boolean isOn();

}
