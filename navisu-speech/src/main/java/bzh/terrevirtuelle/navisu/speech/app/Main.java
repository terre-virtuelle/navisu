/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bzh.terrevirtuelle.navisu.speech.app;

import t2s.son.LecteurTexte;

/**
 *
 * @author serge
 * @date Jun 27, 2019
 */
public class Main {
    public static void main(String[] args) {
        LecteurTexte lecteur = new LecteurTexte();
        lecteur.setTexte("je suis un synthétiseur vocal, qui êtes-vous?");
        lecteur.playAll();
    }
}

