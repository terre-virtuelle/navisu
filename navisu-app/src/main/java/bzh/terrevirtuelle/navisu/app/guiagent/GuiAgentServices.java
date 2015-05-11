/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.app.guiagent;

import bzh.terrevirtuelle.navisu.api.progress.JobsManager;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.capcaval.c3.component.ComponentService;

/**
 *
 * @author tibus
 */
public interface GuiAgentServices extends ComponentService {

    boolean isFullScreen();

    void setFullScreen(boolean fullScreen);

    void showGui(Stage stage, int width, int height);

    StackPane getRoot();

    Scene getScene();

    JobsManager getJobsManager();

    Stage getStage();

    
    
}
