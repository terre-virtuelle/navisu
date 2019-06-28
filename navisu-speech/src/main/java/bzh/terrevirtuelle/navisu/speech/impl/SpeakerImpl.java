/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.speech.impl;

import bzh.terrevirtuelle.navisu.speech.Speaker;
import bzh.terrevirtuelle.navisu.speech.SpeakerServices;

import org.capcaval.c3.component.ComponentState;

/* NaVisu
 *
 * @date 3 mai 2015
 * @author Serge Morvan
 */
public class SpeakerImpl
        implements Speaker, SpeakerServices, ComponentState {



    @Override
    public void componentInitiated() {
       
    }

    @Override
    public void read(String rep, String filename, String language) {

    }

    @Override
    public void read(String text) {
       
    }

    @Override
    public void read(String text, String language) {

    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }
}
