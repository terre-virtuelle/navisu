/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.media.sound.impl;

import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.media.sound.Sound;
import bzh.terrevirtuelle.navisu.media.sound.SoundServices;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

/**
 *
 * @author Serge Morvan
 * @date 9 oct. 2014 NaVisu project
 */
public class SoundImpl implements Sound, SoundServices,
        InstrumentDriver, ComponentState {

    @UsedService
    GuiAgentServices guiAgentServices;

    private final String NAME = "SOUND";
    private static final String EXTENSION_0 = ".wav";
    private static final String EXTENSION_1 = ".mp3";
    private javafx.scene.media.Media media;
    private MediaPlayer mediaPlayer;

    public void open(String... config) {
        if (canOpen(config[0])) {
            guiAgentServices.getJobsManager().newJob("", (ProgressHandle progressHandle) -> {

                media = new Media("file:///" + config[0]);
                mediaPlayer = new MediaPlayer(media);

                if (config.length > 0) {
                    mediaPlayer.setAutoPlay(Boolean.valueOf(config[1]));
                }
                if (config.length > 1) {
                    mediaPlayer.setCycleCount(Integer.parseInt(config[2]));
                }
            });
        }
    }

    @Override
    public boolean canOpen(String file) {
        boolean canOpen = false;

        if (file.toLowerCase().endsWith(EXTENSION_0) || file.toLowerCase().endsWith(EXTENSION_1)) {
            canOpen = true;
        }

        return canOpen;
    }

    @Override
    public void componentInitiated() {

    }

    @Override
    public void componentStarted() {

    }

    @Override
    public void componentStopped() {

    }

    @Override
    public void on(String... files) {
        open(files);
    }

    @Override
    public void off() {
        mediaPlayer.dispose();
    }

    @Override
    public InstrumentDriver getDriver() {
        return this;
    }
}
