/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.client;

import bzh.terrevirtuelle.navisu.client.nmea.NmeaClientServices;
import bzh.terrevirtuelle.navisu.client.nmea.impl.vertx.NmeaClientImpl;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.capcaval.c3.componentmanager.ComponentManager;

/**
 *
 * @author Serge
 */
public class ClientMain extends Application {

    private static final Logger LOGGER = Logger.getLogger(ClientMain.class.getName());

    @SuppressWarnings("unchecked")
    @Override
    public void start(Stage stage) throws Exception {

        final ComponentManager componentManager = ComponentManager.componentManager;

        // deploy components
        LOGGER.log(Level.INFO, "\n Start", componentManager.startApplication(
                NmeaClientImpl.class
        ));
        NmeaClientServices nmeaClientServices = componentManager.getComponentService(NmeaClientServices.class);
        nmeaClientServices.open("localhost", 8080);
        nmeaClientServices.request(100);

        Button button = new Button("Stop",
                new ImageView(new Image(getClass().getResourceAsStream("stop_40x40.png"))));
        button.setOnAction((ActionEvent event) -> {
            System.exit(0);
        });

        StackPane root = new StackPane();
        root.getChildren().add(button);

        Scene scene = new Scene(root, 300, 250);

        stage.setTitle("ClientMain test");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) throws Exception {
        Application.launch();
    }
}
