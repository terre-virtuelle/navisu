/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.view.sounder;

import java.util.List;
import java.util.Map;
import bzh.terrevirtuelle.navisu.instruments.model.Display;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import bzh.terrevirtuelle.navisu.instruments.controller.events.DayNightEvent;
import bzh.terrevirtuelle.navisu.instruments.controller.events.DayNightEventListener;
import bzh.terrevirtuelle.navisu.instruments.controller.events.SounderEventListener;

/**
 *
 * @author Serge Morvan
 */
public class Sounder
        extends Display
        implements DayNightEventListener {

    private static String ID = "sounder";
    private static String BACKGROUND_IMAGE = "display.png";
    private SounderPane realSounderPane;
    private SounderPane virtualSounderPane;
    private SounderPane selectedPane;

    public Sounder() {
        super(ID, BACKGROUND_IMAGE);
        realSounderPane = new RealSounderPane(Sounder.this);
        virtualSounderPane = new VirtualSounderPane(Sounder.this);
        init();
        addDayNightEventListener(this);
    }

    private void init() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                getChildren().add(realSounderPane);
                realSounderPane.initMenu(); 
                realSounderPane.initPage();
                selectedPane = realSounderPane;
                createLocalScene();
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
                    if (id.equals("realSounder")) {
                        nodes.remove(realSounderPane);
                        nodes.add(1, virtualSounderPane);
                        virtualSounderPane.initMenu();
                        virtualSounderPane.initPage();
                        selectedPane = virtualSounderPane;
                        break;
                    }
                    if (id.equals("virtualSounder")) {
                        nodes.remove(virtualSounderPane);
                        nodes.add(1, realSounderPane);
                        realSounderPane.initMenu();
                        realSounderPane.initPage();
                        selectedPane = realSounderPane;
                        break;
                    }
                }
            }
        });
    }

    public void setDepth(final float data) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                realSounderPane.setDepth(data);
            }
        });
    }

    public void setBathymetry(final float data) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                virtualSounderPane.setDepth(data);
            }
        });
    }

    public void setRealOffset(float offset) {
        realSounderPane.setOffset(offset);
    }

    public void setVirtualOffset(float offset) {
        virtualSounderPane.setOffset(offset);
    }

    public float getVirtualOffset() {
        return virtualSounderPane.getOffset();
    }

    public float getDepth() {
        return realSounderPane.getDepth();
    }

    public float getRealOffset() {
        return realSounderPane.getOffset();
    }

    public void setRealRange(float range) {
        realSounderPane.setRange(range);
    }

    public void setRealDepthAlarm(float alarm) {
        realSounderPane.setDepthAlarm(alarm);
    }

    public void setVirtualAlarm(float alarm) {
        virtualSounderPane.setDepthAlarm(alarm);
    }

    public void setVirtualRange(float range) {
        virtualSounderPane.setRange(range);
    }

    public Map<String, String> getRealAttributes() {
        return realSounderPane.getAttributes();
    }

    public Map<String, String> getVirtualAttributes() {
        return virtualSounderPane.getAttributes();
    }

    public float getBathymetry() {
        return virtualSounderPane.getDepth();
    }

    public void addEventListener(SounderEventListener listener) {
        realSounderPane.addEventListener(listener);
        virtualSounderPane.addEventListener(listener);
    }

    public void removeEventListener(SounderEventListener listener) {
        realSounderPane.removeEventListener(listener);
        virtualSounderPane.removeEventListener(listener);
    }

    public void setTypeOfBoat(String boat) {
        realSounderPane.getAttributes().put("boatType", boat);
        virtualSounderPane.getAttributes().put("boatType", boat);
    }

    @Override
    public void update(DayNightEvent event) {
        day = event.isDay();
        if (selectedPane != null) {
            selectedPane.initPage();
        }
    }
}
