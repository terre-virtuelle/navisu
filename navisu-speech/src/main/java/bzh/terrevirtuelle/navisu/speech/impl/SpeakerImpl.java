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

    LecteurTexte lecteur;

    @Override
    public void componentInitiated() {
        lecteur = new LecteurTexte();
    }

    @Override
    public void read(String text) {
        Executors.newSingleThreadExecutor().execute(() -> {
            lecteur.setTexte(text);
            lecteur.playAll();
        });
        // new Reader(text).start();
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }
/*
    public class Reader extends Thread {

        private final String str;

        public Reader(String str) {
            this.str = str;
        }

        @Override
        public void run() {
            lecteur.setTexte(str);
            lecteur.playAll();
        }
    }
    */
}
