/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.widgets.view.clock;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.HBox;
import jfxtras.labs.scene.control.gauge.Clock;
import jfxtras.labs.scene.control.gauge.ClockBuilder;
import bzh.terrevirtuelle.navisu.widgets.model.Widget;

/**
 *
 * @author Serge Morvan
 */
public class ClockGauges 
extends Widget {

    List<Clock> clocks;
    Clock clockPst;
    Clock clockEst;
    Clock clockCet;
    Clock clockPs;

    public ClockGauges() {
        clocks = new ArrayList<>();
        createScene();
    }

    @Override
    protected final void createScene() {
        // Create some controls
        clockPst = ClockBuilder.create().title("San Francisco").timeZone("US/Pacific").clockStyle(Clock.ClockStyle.IOS6).autoDimEnabled(true).running(true).build();
        clocks.add(clockPst);
        
        clockEst = ClockBuilder.create().title("New York").timeZone("EST").clockStyle(Clock.ClockStyle.IOS6).autoDimEnabled(true).running(true).build();
        clocks.add(clockEst);
        clockCet = ClockBuilder.create().title("Berlin").timeZone("CET").autoDimEnabled(true).running(true).build();
        clocks.add(clockCet);
        clockPs = ClockBuilder.create().title("Paris").timeZone("GMT+1").autoDimEnabled(true).running(true).build();
        clocks.add(clockPs);
        // Layout
        HBox row1 = new HBox();
        row1.getChildren().addAll(clockPst, clockEst, clockCet, clockPs);
        getChildren().add(row1);

    }

    public void setScale(double scale) {
        for(Clock c : clocks){
            c.setScaleX(scale);
            c.setScaleY(scale);
        }
    }
}
