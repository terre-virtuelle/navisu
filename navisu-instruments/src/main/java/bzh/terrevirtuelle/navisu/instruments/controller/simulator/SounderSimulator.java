/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.controller.simulator;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import bzh.terrevirtuelle.navisu.instruments.view.sounder.Sounder;

/**
 *
 * @author Serge Morvan
 */
public class SounderSimulator
        extends SwingWorker<Integer, String> {

    private Sounder sounder;
    private float range;
    private float min;

    public SounderSimulator(Sounder sounder, float range, float min) {
        this.range = range;
        this.min = min;
        this.sounder = sounder;
    }

    /**
     * Get the value of range
     *
     * @return the value of range
     */
    public float getRange() {
        return range;
    }

    /**
     * Set the value of range
     *
     * @param range new value of range
     */
    public void setRange(float range) {
        this.range = range;
    }

    /**
     * Get the value of min
     *
     * @return the value of min
     */
    public float getMin() {
        return min;
    }

    /**
     * Set the value of min
     *
     * @param min new value of min
     */
    public void setMin(float min) {
        this.min = min;
    }

    @Override
    protected Integer doInBackground() {
        float var;
        float j = 0;
        for (int i = 0; i < 10000; i++) {
            // var = new Float((int) (Math.random() * range));
            var = range - (float) (Math.sin(j) * range);
            if (sounder != null) {
                sounder.setDepth(var / 500 + min);
                sounder.setBathymetry(var + min);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(SounderSimulator.class.getName()).log(Level.SEVERE, null, ex);
            }
            j += 0.1;
        }
        return 0;
    }
}
