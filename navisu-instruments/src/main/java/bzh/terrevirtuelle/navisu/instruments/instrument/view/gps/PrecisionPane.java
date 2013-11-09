/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.instrument.view.gps;

import bzh.terrevirtuelle.navisu.instruments.instrument.misc.Pair;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.GridPaneBuilder;
import jfxtras.labs.scene.control.gauge.Battery;
import bzh.terrevirtuelle.navisu.instruments.instrument.model.Display;

/**
 *
 * @author Serge Morvan
 */
public class PrecisionPane
        extends GPSPane {

    private Battery[] batteries;
    private Label labels[];
    private final int NB_BATTERY = 12;
    private final int HALF_NB_BATTERY = NB_BATTERY / 2;
    private Label pdopValue;
    private Label vdopValue;
    private Map<Integer, Date> satelliteDates;
    private Pair<Integer, Integer> sat;

    public PrecisionPane(Display display) {
        super(display);
        setId("precisionPane");
        backgroundFileName = "gpsBackground.png";
        foregroundFileName = "verre.png";
        backgroundNightFileName = "night_gpsBackground.png";
        //  display.
        LAYOUT_X = 28;
        LAYOUT_Y = 28;

        batteries = new Battery[2 * NB_BATTERY];
        labels = new Label[NB_BATTERY];
        satelliteDates = new HashMap<>();
        createScene();
    }

    private void createScene() {
        root = new Group();
        getChildren().add(root);
        backgroundImage = new Image(rootDir + IMAGES + backgroundFileName);
        background = ImageViewBuilder.create()
                .id("posSat")
                .layoutX(LAYOUT_X)
                .layoutY(LAYOUT_Y - 1)
                .pickOnBounds(true)
                .image(backgroundImage)
                .build();
        getChildren().add(background);
        GridPane gridPane = GridPaneBuilder.create()
                .layoutX(46)
                .layoutY(46)
                .alignment(Pos.CENTER)
                .build();

        getChildren().add(gridPane);


        for (int i = 0; i < NB_BATTERY; i++) {
            batteries[i] = new Battery();
            batteries[i].setMouseTransparent(true);
            batteries[i].setPrefWidth(20);
            batteries[i].setPrefHeight(20);
            batteries[i].setScaleX(2.5);
            batteries[i].setScaleY(3);
        }

        for (int i = 0; i < HALF_NB_BATTERY; i++) {
            gridPane.add(batteries[i], i, 1);
            labels[i] = new Label();
            gridPane.add(labels[i], i, 1);
        }
        for (int i = HALF_NB_BATTERY; i < NB_BATTERY; i++) {
            gridPane.add(batteries[i], i - HALF_NB_BATTERY, 2);
            labels[i] = new Label();
            gridPane.add(labels[i], i - HALF_NB_BATTERY, 2);
        }

        /* - verre sur le cadre - */
        foregroundImage = new Image(rootDir + IMAGES + foregroundFileName);
        foreground = ImageViewBuilder.create()
                .id("verreSat")
                .layoutX(LAYOUT_X - 20)
                .layoutY(LAYOUT_Y - 20)
                .mouseTransparent(true)
                .pickOnBounds(true)
                .image(foregroundImage)
                .build();
        root.getChildren().add(foreground);
        /*
         Label pdop = new Label("PDOP ");
         gridPane.add(pdop, 0, 3);
         pdopValue = new Label();
         gridPane.add(pdopValue, 1, 3);
         Label hdop = new Label("HDOP ");
         gridPane.add(hdop, 2, 3);
         hdopValue = new Label();
         gridPane.add(hdopValue, 3, 3);
         Label vdop = new Label("VDOP ");
         gridPane.add(vdop, 4, 3);
         vdopValue = new Label();
         gridPane.add(vdopValue, 5, 3);
         * */

    }

    public void initMenu() {
       
    }

    public Battery[] getBatteries() {
        return batteries;
    }

    public void setValues(Map<Integer, Pair<Integer, Integer>> satellites) {
        try {
            Set<Integer> satelliteNB = satellites.keySet();
            for (Integer s : satelliteNB) {
              //  satelliteDates.put(s, Calendar.getInstance().getTime());
                sat = satellites.get(s);
                if (sat != null) {
                    if (sat.getLeft() != null && batteries[sat.getLeft()] != null && sat.getRight() != null) {
                        batteries[sat.getLeft()].setChargingLevel(sat.getRight() / 45.0);
                        labels[sat.getLeft()].setText("   " + s.toString());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("sat.getLeft() : " + sat.getLeft());
            System.out.println("batteries[sat.getLeft()] : " + batteries[sat.getLeft()]);
            System.out.println("sat.getRight() : " + sat.getRight());
            System.out.println("labels[sat.getLeft()] : " + labels[sat.getLeft()]);
        }
    }

    public Label[] getLabels() {
        return labels;
    }

    public void setPdopValue(double pdopValue) {
        this.pdopValue.setText(new Double(pdopValue).toString());
    }

    public void setHdopValue(double hdopValue) {
        // this.hdopValue.setText(new Double(hdopValue).toString());
    }

    public void setVdopValue(double vdopValue) {
        this.vdopValue.setText(new Double(vdopValue).toString());
    }
}
