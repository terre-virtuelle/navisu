/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.sonar.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.instruments.sonar.Sonar;
import bzh.terrevirtuelle.navisu.instruments.sonar.SonarServices;
import bzh.terrevirtuelle.navisu.instruments.sonar.impl.controller.Chart3dDemo;
import bzh.terrevirtuelle.navisu.instruments.sonar.impl.controller.SonarController;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

/**
 * NaVisu
 *
 * @date 4 avr. 2015
 * @author Serge Morvan
 */
public class SonarImpl
        implements Sonar, SonarServices, InstrumentDriver, ComponentState {

    @UsedService
    GuiAgentServices guiAgentServices;
    private SonarController controller;
    private final String NAME = "Sonar";
    Stage stage;

    @Override
    public void componentInitiated() {

    }

    @Override
    public void on(String ... files) {
        stage = guiAgentServices.getStage();
        stage.setOpacity(.75);
     //   controller = new SonarController(this, stage);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                 stage = new Stage();
                 
               // stage.setOpacity(0.1);
                 stage.setHeight(450);
                 stage.setWidth(650);
                 stage.setX(600);
                 stage.setY(200);
               //  stage.initStyle(StageStyle.UNDECORATED);
               new  Chart3dDemo().start(stage);         
            }
        });
    }

    @Override
    public boolean canOpen(String category) {
        return category.equals(NAME);
    }

    @Override
    public InstrumentDriver getDriver() {
        return this;
    }

    @Override
    public void off() {
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

}
