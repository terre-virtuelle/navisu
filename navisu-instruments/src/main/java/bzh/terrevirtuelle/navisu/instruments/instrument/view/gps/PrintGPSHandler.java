/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.instrument.view.gps;

/**
 *
 * @author Serge Morvan
 */
public class PrintGPSHandler
        extends GPSHandler {

    public PrintGPSHandler() {
    }

    @Override
    public void doIt(String data) {
        System.out.print(data);
    }
}
