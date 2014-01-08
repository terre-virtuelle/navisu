package bzh.terrevirtuelle.navisu.server.app;

import bzh.terrevirtuelle.navisu.server.DataServerServices;
import bzh.terrevirtuelle.navisu.server.impl.vertx.DataServerImpl;
import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
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
 * Created with IntelliJ IDEA. User: Serge Morvan Date: 21/12/13 Time: 18:18
 */
public class ServerMain extends Application {

    private static final Logger LOGGER = Logger.getLogger(ServerMain.class.getName());

    @Override
    public void start(Stage stage) throws Exception {
        // initialize logging
        LogManager.getLogManager().readConfiguration(new FileInputStream("conf/logging.properties"));

        final ComponentManager componentManager = ComponentManager.componentManager;

        // deploy components
        LOGGER.log(Level.INFO, "\n Start", componentManager.startApplication(
                DataServerImpl.class
        ));
        DataServerServices nmeaServerServices = componentManager.getComponentService(DataServerServices.class);
        // Test avec choix des parametres de comm
        nmeaServerServices.init("localhost", 8080);
        nmeaServerServices.openSerialPort("COM4", 4800, 8, 1, 0);
        nmeaServerServices.openSerialPort("COM5", 4800, 8, 1, 0);
        // Test avec les parametres de comm dans properties/nmea.properties
       //  nmeaServerServices.init();
       //  nmeaServerServices.openSerialPort();
        // nmeaServerServices.openFile();

        Button button = new Button("Stop",
                new ImageView(new Image(getClass().getResourceAsStream("stop_40x40.png"))));
        button.setOnAction((ActionEvent event) -> {
            System.exit(0);
        });

        StackPane root = new StackPane();
        root.getChildren().add(button);

        Scene scene = new Scene(root, 300, 250);

        stage.setTitle("ServerMain test");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        Application.launch();
    }
}
