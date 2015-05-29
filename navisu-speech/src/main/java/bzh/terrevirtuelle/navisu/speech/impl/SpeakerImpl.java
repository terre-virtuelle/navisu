/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.speech.impl;

import bzh.terrevirtuelle.navisu.speech.Speaker;
import bzh.terrevirtuelle.navisu.speech.SpeakerServices;
import java.util.concurrent.Executors;
import org.capcaval.c3.component.ComponentState;
import t2s.son.LecteurTexte;

/**
 * NaVisu
 *
 * @date 3 mai 2015
 * @author Serge Morvan
 */
public class SpeakerImpl
        implements Speaker, SpeakerServices, ComponentState {

    LecteurTexte reader;
    String text;

    @Override
    public void componentInitiated() {
        reader = new LecteurTexte();
    }

    @Override
    public void read(String text) {
        this.text = text;
        if (this.text.contains("/")) {
            this.text = text.replace("/", "");
        }
        Executors.newSingleThreadExecutor().execute(() -> {
            reader.setTexte(this.text);
            reader.playAll();
        });
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }
}
