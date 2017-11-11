/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.architecture.app.controlcommand;

import bzh.terrevirtuelle.navisu.architecture.impl.model.ComponentModelView;
import java.util.List;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author serge
 * @date Nov 11, 2017
 */
public class ControlFrame {

    JFrame frame;

    public ControlFrame(Map<String, List<ComponentModelView>> componentMap) {
        frame = new JFrame("Liste des modules");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new ControlPanel(componentMap);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
    }

    public void setVisible() {
        frame.pack();
        frame.setVisible(true);
    }

}
