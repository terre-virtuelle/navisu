package bzh.terrevirtuelle.navisu.app;

import bzh.terrevirtuelle.navisu.app.charts.ChartsManagerServices;
import bzh.terrevirtuelle.navisu.app.charts.impl.ChartsManagerImpl;
import bzh.terrevirtuelle.navisu.app.drivers.DriverManagerServices;
import bzh.terrevirtuelle.navisu.app.drivers.impl.DriverManagerImpl;
import bzh.terrevirtuelle.navisu.app.grib.GribServices;
import bzh.terrevirtuelle.navisu.app.grib.impl.GribImpl;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.impl.GuiAgentImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import bzh.terrevirtuelle.navisu.app.guiagent.utilities.I18nLangEnum;
import bzh.terrevirtuelle.navisu.app.guiagent.utilities.Translator;
import bzh.terrevirtuelle.navisu.app.pointcloud.PointCloudServices;
import bzh.terrevirtuelle.navisu.app.pointcloud.impl.PointCloudImpl;
import javafx.application.Application;
import javafx.stage.Stage;
import org.capcaval.c3.componentmanager.ComponentManager;

/**
 *
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 * @author Jordan Mens <jordan.mens at gmail.com>
 */
public class AppMain extends Application {

    private static final Logger LOGGER = Logger.getLogger(AppMain.class.getName());

    @Override
    public void start(Stage stage) throws Exception {

        Translator.setLang(I18nLangEnum.FRENCH);

        final ComponentManager componentManager = ComponentManager.componentManager;

        LOGGER.info(
                componentManager.startApplication(

                        GuiAgentImpl.class,
                        DriverManagerImpl.class,
                        ChartsManagerImpl.class,
                        GribImpl.class,
                        PointCloudImpl.class
                )
        );

        GuiAgentServices guiServices = componentManager.getComponentService(GuiAgentServices.class);
        guiServices.showGui(stage, 800, 500);

        ChartsManagerServices chartsServices = componentManager.getComponentService(ChartsManagerServices.class);
        GribServices gribServices = componentManager.getComponentService(GribServices.class);
        PointCloudServices pointCloudServices = componentManager.getComponentService(PointCloudServices.class);

        DriverManagerServices driverServices = componentManager.getComponentService(DriverManagerServices.class);
        driverServices.registerNewDriver(chartsServices.getDriver());
        driverServices.registerNewDriver(gribServices.getDriver());
        driverServices.registerNewDriver(pointCloudServices.getDriver());

        chartsServices.openChart("navisu-app/data/101.KAP");
    }

    public static void main(String[] args) throws Exception {
        Application.launch();

        /*String outputPath = "navisu-app/data/";
        createDirIfNotExists(outputPath);
        String command = "navisu-app/gdal_translate";

        Proc proc = Proc.builder.create().setCmd(command)
                .addArg("-of GTiff")
                .addArg("-expand rgb")
                .addArg(outputPath + "101.kap")
                .addArg(outputPath + "101.tif")
                .setOut(System.out)
                .setErr(System.err)
                .exec();

        System.out.println("exit code: " + proc.getReturnCode());*/
    }



    public static boolean createDirIfNotExists(String dirPath) {

        boolean result = true;
        Path path = Paths.get(dirPath);

        if(Files.notExists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                result = false;
            }
        }

        return result;
    }
}













