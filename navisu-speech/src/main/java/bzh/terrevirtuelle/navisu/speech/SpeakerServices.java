/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.speech;

import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @date 3 mai 2015
 * @author Serge Morvan
 */
public interface SpeakerServices
        extends ComponentService {

    void read(String text);
}
