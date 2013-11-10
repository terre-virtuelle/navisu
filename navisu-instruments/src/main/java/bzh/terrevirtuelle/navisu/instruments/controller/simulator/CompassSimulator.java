/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.controller.simulator;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import bzh.terrevirtuelle.navisu.instruments.view.compass.Compass;

/**
 *
 * @author Serge Morvan
 */
public class CompassSimulator
        extends SwingWorker<Integer, String> {

    private Compass compass;

    public CompassSimulator(Compass compass) {
        this.compass = compass;
    }

    @Override
    protected Integer doInBackground() {
        for (int j = 0; j < 100; j++) {
            for (int i = 0; i < 360; i++) {
                compass.setHeading(i);
                compass.setVariation(4);
                compass.setDeviation(10);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(CompassSimulator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return 0;
    }

    /**
     * Get the value of compass
     *
     * @return the value of compass
     */
    public Compass getCompass() {
        return compass;
    }

    /**
     * Set the value of compass
     *
     * @param compass new value of compass
     */
    public void setCompass(Compass compass) {
        this.compass = compass;
    }
}
