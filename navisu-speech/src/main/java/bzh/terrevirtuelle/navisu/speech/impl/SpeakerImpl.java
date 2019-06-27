/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.speech.impl;

import bzh.terrevirtuelle.navisu.speech.Speaker;
import bzh.terrevirtuelle.navisu.speech.SpeakerServices;

import org.capcaval.c3.component.ComponentState;
import t2s.son.LecteurTexte;

/* NaVisu
 *
 * @date 3 mai 2015
 * @author Serge Morvan
 */
public class SpeakerImpl
        implements Speaker, SpeakerServices, ComponentState {

    LecteurTexte reader;

    @Override
    public void componentInitiated() {
        reader = new LecteurTexte();
    }

    @Override
    public void read(String rep, String filename, String language) {

    }

    @Override
    public void read(String text) {
        reader.setTexte(text);
        reader.playAll();
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
