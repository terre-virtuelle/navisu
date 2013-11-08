/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.instrument.view.compass;

import org.navisu.instrument.model.Display;
import java.util.List;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import org.navisu.instrument.controller.events.DayNightEvent;
import org.navisu.instrument.controller.events.DayNightEventListener;

/**
 *
 * @author Serge Morvan
 */
public class Compass
        extends Display
        implements DayNightEventListener {

    private static String ID = "gps";
    private static String BACKGROUND_IMAGE = "display.png";
    private CompassPane selectedPane;
    private CompassDataPane dataPane;

    public Compass() {
        super(ID, BACKGROUND_IMAGE);
        init();
        addDayNightEventListener(this);
    }

    private void init() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                dataPane = new CompassDataPane(Compass.this);
                dataPane.initPage();
                createLocalScene();
                getChildren().add(dataPane);
                selectedPane = dataPane;
            }
        });
    }

    private void createLocalScene() {
        pageButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                List<Node> nodes = getChildren();
                for (Node n : nodes) {
                    String id = n.getId();
                }
            }
        });
    }

    public void setHeading(final float data) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                dataPane.setHeading(data);
            }
        });
    }

    public void setVariation(final float data) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                dataPane.setVariation(data);
            }
        });
    }

    public void setDeviation(final float data) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                dataPane.setDeviation(data);
            }
        });
    }

    @Override
    public void update(DayNightEvent event) {
        day = event.isDay();
        if (selectedPane != null) {
            selectedPane.initPage();
        }
    }
}
