/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.main;

import bzh.terrevirtuelle.navisu.weather.impl.darksky.controller.DarkSkyComponentController;
import bzh.terrevirtuelle.navisu.weather.impl.darksky.controller.DarkSkyController;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author serge
 * @date Feb 12, 2017
 */
public class Configuration {

    public static void init() {
        String navisuHome = System.getProperty("user.home") + "/.navisu";
        Path navisuHomePath = Paths.get(navisuHome);
        if (!Files.exists(navisuHomePath, LinkOption.NOFOLLOW_LINKS)) {
            try {
                Files.createDirectory(navisuHomePath);
            } catch (IOException ex) {
                Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (!Files.exists(Paths.get(navisuHome + "/databases"), LinkOption.NOFOLLOW_LINKS)) {
            try {
                Files.createDirectory(Paths.get(navisuHome + "/v/"));
            } catch (IOException ex) {
                Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (!Files.exists(Paths.get(navisuHome + "/config"), LinkOption.NOFOLLOW_LINKS)) {
            try {
                Files.createDirectory(Paths.get(navisuHome + "/config/"));
            } catch (IOException ex) {
                Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (!Files.exists(Paths.get(navisuHome + "/caches"), LinkOption.NOFOLLOW_LINKS)) {
            try {
                Files.createDirectory(Paths.get(navisuHome + "/caches/"));
            } catch (IOException ex) {
                Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        String navisuWeatherCache = navisuHome + "/caches/weather.properties";
        if (!Files.exists(Paths.get(navisuWeatherCache), LinkOption.NOFOLLOW_LINKS)) {
            try {
                Files.createFile(Paths.get(navisuWeatherCache));
            } catch (IOException ex) {
                Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
            }
            writeDefaultWeatherCache(navisuWeatherCache);
        } else {
            Properties properties = new Properties();
            try {
                properties.load(new FileInputStream(navisuWeatherCache));
                String town = properties.getProperty("town");
                String language = properties.getProperty("language");
                String unit = properties.getProperty("unit");
                String country = properties.getProperty("country");
                String countryCode = properties.getProperty("countryCode");

                if (town == null || language == null || unit == null || country == null || countryCode == null) {
                    writeDefaultWeatherCache(navisuWeatherCache);
                }
            } catch (IOException ex) {
                Logger.getLogger(DarkSkyComponentController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        }

        String userProperties = navisuHome + "/config/config.properties";
        if (!Files.exists(Paths.get(userProperties), LinkOption.NOFOLLOW_LINKS)) {
            try {
                Files.createFile(Paths.get(userProperties));
            } catch (IOException ex) {
                Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
            }
            writeDefaultUserProperties(userProperties);
        } else {
            Properties properties = new Properties();
            try {
                properties.load(new FileInputStream(userProperties));
                String s57ChartsDir = properties.getProperty("s57ChartsDir");
                String darkSkyApiKey = properties.getProperty("darkSkyApiKey");
                String allCountriesPath = properties.getProperty("allCountriesPath");
                String luceneAllCountriesIndexPath = properties.getProperty("luceneAllCountriesIndexPath");

                if (s57ChartsDir == null || darkSkyApiKey == null
                        || allCountriesPath == null || luceneAllCountriesIndexPath == null) {
                    writeDefaultUserProperties(navisuWeatherCache);
                }
            } catch (IOException ex) {
                Logger.getLogger(DarkSkyComponentController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        }

    }

    private static void writeDefaultWeatherCache(String navisuWeatherCache) {
        try {
            List<String> keys = new ArrayList<>(Arrays.asList("town=",
                    "language=", "unit=", "country=", "countryCode="));
            Files.write(Paths.get(navisuWeatherCache), keys, StandardOpenOption.WRITE);
        } catch (IOException ex) {
            Logger.getLogger(DarkSkyController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }

    private static void writeDefaultUserProperties(String userProperties) {
        try {
            List<String> keys = new ArrayList<>(Arrays.asList("s57ChartsDir=",
                    "darkSkyApiKey=", "allCountriesPath=", "luceneAllCountriesIndexPath="));
            Files.write(Paths.get(userProperties), keys, StandardOpenOption.WRITE);
        } catch (IOException ex) {
            Logger.getLogger(DarkSkyController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }
    /**
     * Adds the specified path to the java library path
     *
     * @param pathToAdd the path to add
     * @throws Exception
     */
    public static void addLibraryPath(String pathToAdd) throws Exception {
        final Field usrPathsField = ClassLoader.class.getDeclaredField("usr_paths");
        usrPathsField.setAccessible(true);
        String[] paths = (String[]) usrPathsField.get(null);
        //check if the path to add is already present
        for (String path : paths) {
            if (path.equals(pathToAdd)) {
                return;
            }
        }
        //add the new path
        final String[] newPaths = Arrays.copyOf(paths, paths.length + 1);
        newPaths[newPaths.length - 1] = pathToAdd;
        usrPathsField.set(null, newPaths);
    }
}
