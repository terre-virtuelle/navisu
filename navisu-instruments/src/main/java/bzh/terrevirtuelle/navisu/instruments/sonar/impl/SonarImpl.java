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
import bzh.terrevirtuelle.navisu.instruments.sonar.impl.controller.SonarController;
import bzh.terrevirtuelle.navisu.widgets.sonar.sonar3D.TriangleMeshes;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
        controller = new SonarController(this, stage);

        // Bug in JavaFX ?
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
              /* 
                 stage = new Stage();
                 stage.setOpacity(.75);
                 stage.setHeight(200);
                 stage.setWidth(400);
                 stage.setX(600);
                 stage.setY(200);
                 stage.initStyle(StageStyle.UNDECORATED);
                 
                new TriangleMeshes(stage);
                */        
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
