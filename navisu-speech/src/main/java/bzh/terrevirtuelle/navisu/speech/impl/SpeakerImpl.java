/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.speech.impl;

import bzh.terrevirtuelle.navisu.speech.Speaker;
import bzh.terrevirtuelle.navisu.speech.SpeakerServices;
import com.gtranslate.Audio;
import java.io.InputStream;
import java.util.stream.Stream;
import org.capcaval.c3.component.ComponentState;

/* NaVisu
 *
 * @date 3 mai 2015
 * @author Serge Morvan
 */
public class SpeakerImpl
        implements Speaker, SpeakerServices, ComponentState {

    Audio audio;
    InputStream sound = null;
    String text;
    String lang = null;
    Stream<String> lines = null;

    @Override
    public void componentInitiated() {
        audio = Audio.getInstance();
    }

    @Override
    public void read(String rep, String filename, String language) {
/*
        if (language == null) {
            lang = Locale.getDefault().toString();
        } else {
            lang = language;
        }
        try {
            lines = Files.lines(Paths.get(rep, filename));
            text = lines.map(Object::toString).collect(Collectors.joining(""));
        } catch (IOException ex) {
            Logger.getLogger(SpeakerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                sound = audio.getAudio(text, lang);
                audio.play(sound);
            } catch (IOException | JavaLayerException ex) {
                // Logger.getLogger(SpeakerImpl.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("ex " + ex);
            }
        });
        */
    }

    @Override
    public void read(String text) {
/*
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                sound = audio.getAudio(text, Locale.getDefault().toString());
                audio.play(sound);
            } catch (IOException | JavaLayerException ex) {
                Logger.getLogger(SpeakerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        */
    }

    @Override
    public void read(String text, String language) {
/*
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                sound = audio.getAudio(text, "Language." + language);
                audio.play(sound);
            } catch (IOException | JavaLayerException ex) {
                Logger.getLogger(SpeakerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        */
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }
}
