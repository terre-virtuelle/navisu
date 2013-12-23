package bzh.terrevirtuelle.navisu.api.progress;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * NaVisu
 *
 * @author tibus
 * @date 10/12/2013 21:23
 */
public class UsageMain extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Pane root = new Pane();
        Scene scene = new Scene(root, Color.TRANSPARENT);
        stage.setScene(scene);

        final JobsManager jobsManager = JobsManager.create();

        root.getChildren().add(jobsManager.getDisplay().getDisplayable());

        jobsManager.setJobViewSize(400, 50);

        jobsManager.newJob("Charts Loading", (pHandle) -> {

            try {
                pHandle.progress("Start progressing");
                Thread.sleep(1000);

                pHandle.progress("Comment allez vous ?");
                Thread.sleep(2000);

                pHandle.progress("Ca va bien, et vous ?");
                Thread.sleep(2000);

                pHandle.progress("Bien merci");

            } catch (InterruptedException e) {}
        });

        jobsManager.newJob("Long long job", pHandle -> {

            DateFormat df = new SimpleDateFormat("HH:mm:ss");

            while(true) {

                pHandle.progress("Current time : " + df.format(new Date(System.currentTimeMillis())));

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //TODO see why it does not work on Windows ? JDK version ? Gradle launch ? 
        /* 
        Job.manager.newJob("Grib Loading", 4, (pHandle) -> {

            try {
                pHandle.progress("Start progressing", 1);
                Thread.sleep(1000);

                pHandle.progress("Comment allez vous ?", 2);
                Thread.sleep(2000);

                pHandle.progress("Ca va bien, et vous ?", 3);
                Thread.sleep(2000);

                pHandle.progress("Bien merci", 4);
                Thread.sleep(2000);

                pHandle.switchToIndeterminate();
                pHandle.progress("Au revoir");
                Thread.sleep(2000);

                pHandle.progress("Si vous le voulez bien...");
                Thread.sleep(1000);

            } catch (InterruptedException e) {}
        });
        */
        
        stage.setTitle("Usage of progress API");
        stage.setWidth(500);
        stage.setHeight(500);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(UsageMain.class);
    }
}
