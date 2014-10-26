/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.widgets.headUpDisplay.hud_3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Serge
 */
public class HUD_3_2_1 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        HUD_3_2_1_Controller controller = new HUD_3_2_1_Controller();
        Scene scene = new Scene(controller);
        stage.setScene(scene);
        stage.show();
        controller.schedule();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
