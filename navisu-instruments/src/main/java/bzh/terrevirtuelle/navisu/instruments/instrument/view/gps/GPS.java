/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.instrument.view.gps;

import org.navisu.instrument.misc.Pair;
import org.navisu.instrument.model.Display;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import org.navisu.instrument.controller.events.DayNightEvent;
import org.navisu.instrument.controller.events.DayNightEventListener;

/**
 *
 * @author Serge Morvan
 */
public class GPS
        extends Display
        implements DayNightEventListener {

    private static String ID = "gps";
    private static String BACKGROUND_IMAGE = "display.png";
    private GPSPane selectedPane;
    private AlarmPane alarmPane;
    private LocationPane locationPane;
    private PrecisionPane precisionPane;
    private CommPane commPane;
    private DataPane dataPane;
    private List<Text> satellites = new ArrayList<>();
    private List<Integer> satellitesUsed;
    private boolean exist = false;
    private ConcurrentMap<Integer, Pair<Integer, Integer>> satelliteValues;
    private int count = 0;
    private GPSHandler handler;
    private boolean initComm = false;

    public GPS(GPSHandler handler) {
        super(ID, BACKGROUND_IMAGE);
        this.handler = handler;
        satelliteValues = new ConcurrentHashMap<>();
        init();
        addDayNightEventListener(this);
    }

    private void init() {
        Platform.runLater(new Runnable() {
           @Override
            public void run() {
                precisionPane = new PrecisionPane(GPS.this);
                precisionPane.initPage();
                dataPane = new DataPane(GPS.this);
                dataPane.initPage();
                commPane = new CommPane(GPS.this, handler);
                commPane.initPage();
                locationPane = new LocationPane(GPS.this);
                locationPane.initPage();
                alarmPane = new AlarmPane(GPS.this);
                alarmPane.initPage();
                alarmPane.initMenu();
                createLocalScene();
                getChildren().add(commPane);
                selectedPane = commPane;
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
                    if (id.equals("commPane")) {
                        nodes.remove(commPane);
                        nodes.add(1, dataPane);
                        dataPane.initPage();
                        selectedPane = dataPane;
                        break;
                    }
                    if (id.equals("dataPane")) {
                        nodes.remove(dataPane);
                        nodes.add(1, alarmPane);
                        alarmPane.initPage();
                        alarmPane.initMenu();
                        selectedPane = alarmPane;
                        break;
                    }
                    if (id.equals("alarmPane")) {
                        nodes.remove(alarmPane);
                        nodes.add(1, locationPane);
                        locationPane.initPage();
                        selectedPane = locationPane;
                        break;
                    }
                    if (id.equals("locationPane")) {
                        nodes.remove(locationPane);
                        nodes.add(1, precisionPane);
                        precisionPane.initPage();
                        selectedPane = precisionPane;
                        break;
                    }
                    if (id.equals("precisionPane")) {
                        nodes.remove(precisionPane);
                        nodes.add(1, commPane);
                        commPane.initPage();
                        selectedPane = commPane;
                        break;
                    }
                }
            }
        });
    }

    public void setLocation(final int prnNumber, final int elevation, final int azimut) {
        /* Stratégie d'affichage */
        Platform.runLater(new Runnable() {
            String id = new Integer(prnNumber).toString();

            @Override
            public void run() {
                exist = false;
                double alpha = Math.toRadians(azimut) - Math.PI / 2;
                int x = (int) (LAYOUT_X + elevation * Math.cos(alpha));
                int y = (int) (LAYOUT_Y + elevation * Math.sin(alpha));

                for (Text t : satellites) {
                    if (t.getId() != null) {
                        if (t.getId().equals(id)) {
                            locationPane.getChildren().remove(t);
                            t.setLayoutX(x);
                            t.setLayoutY(y);
                            locationPane.getChildren().add(t);
                            exist = true;
                        }
                    }
                }
                if (exist == false) {
                    Text text = TextBuilder.create()
                            .id(id)
                            .text(id)
                            .layoutX(x)
                            .layoutY(y)
                            .build();
                    locationPane.getChildren().add(text);
                    satellites.add(text);
                }
            }
        });
    }

    public void setLocation(final float lat, final float lon) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                dataPane.setLocation(lat, lon);
                alarmPane.setLocation(lat, lon);
            }
        });
    }

    public void setSNR(final ConcurrentMap<Integer, Integer> values) {
        /* Stratégie d'affichage */
        /* satNB, sat SNR */
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Set<Integer> satNB = values.keySet();
                List<Integer> l = new ArrayList<>();
                for (Integer i : satNB) {
                    if (satellitesUsed != null && !satellitesUsed.contains(i)) {
                        l.add(new Integer(i));
                    }
                }
                for (int i : l) {
                    satNB.remove(i);
                }

                for (Integer s : satNB) {
                    if (satelliteValues.containsKey(s)) {
                        satelliteValues.get(s).setRight(values.get(s));
                    } else {
                        satelliteValues.put(s, new Pair<>(count, values.get(s)));
                        count++;
                    }
                }
                precisionPane.setValues(satelliteValues);
            }
        });
    }

    public void setHdopValue(double value) {
        // precisionPane.setHdopValue(value);
    }

    public void setDate(Date date) {
        dataPane.setDate(date);
    }

    public void setCog(float cog) {
        dataPane.setCog(cog);
    }

    public void setSog(float sog) {
        dataPane.setSog(sog);
    }

    public void setSatellitesUsed(List<Integer> satellitesUsed) {
        this.satellitesUsed = satellitesUsed;
    }

    public void initComm() {
        initComm = true;
    }

    public boolean getInitComm() {
        return initComm;
    }

    @Override
    public void update(DayNightEvent event) {
        day = event.isDay();
        if (selectedPane != null) {
            selectedPane.initPage();
        }
    }
}
