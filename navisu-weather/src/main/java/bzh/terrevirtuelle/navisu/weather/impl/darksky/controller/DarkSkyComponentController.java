/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.weather.impl.darksky.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.domain.country.Abbreviations;
import bzh.terrevirtuelle.navisu.gazetteer.GazetteerComponentServices;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author serge
 * @date Jan 31, 2017
 */
public class DarkSkyComponentController {

    private String apiKey = null;
    private String darkSkyUrl = null;
    private final String DARK_SKY_URL = "https://api.darksky.net/forecast/";
    protected Properties properties;
    protected String CONFIG_FILE_NAME = System.getProperty("user.home") +"/.navisu/config/config.properties";
    protected String CACHE_FILE_NAME = System.getProperty("user.home") + "/.navisu/caches/weather.properties";
    protected String town;
    protected String language;
    protected String unit;
    protected String country;
    protected DarkSkyController darkSkyController;
    protected GazetteerComponentServices gazetteerComponentServices;
    protected GuiAgentServices guiAgentServices;

    public DarkSkyComponentController(
            GazetteerComponentServices gazetteerComponentServices,
            GuiAgentServices guiAgentServices) {
        this.guiAgentServices = guiAgentServices;
        List<String> languagelist = new ArrayList<>();
        Abbreviations.LANG.keySet().forEach((l) -> {
            languagelist.add(l);
        });
        List<String> unitList = new ArrayList<>();
        Abbreviations.UNIT.keySet().forEach((u) -> {
            unitList.add(u);
        });
        List<String> countryList = new ArrayList<>();
        Abbreviations.CODE.keySet().forEach((c) -> {
            countryList.add(c);
        });
        countryList.sort(String::compareToIgnoreCase);

        properties = new Properties();
        try {
            properties.load(new FileInputStream(CONFIG_FILE_NAME));
        } catch (IOException ex) {
            Logger.getLogger(DarkSkyComponentController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        apiKey = properties.getProperty("darkSkyApiKey").trim();

        properties = new Properties();
        try {
            properties.load(new FileInputStream(CACHE_FILE_NAME));
            town = properties.getProperty("town");
            language = properties.getProperty("language");
            unit = properties.getProperty("unit");
            country = properties.getProperty("country");
            if (town == null && language == null && unit == null && country == null) {
                System.out.println("properties null");
            }
        } catch (IOException ex) {
            Logger.getLogger(DarkSkyComponentController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        darkSkyController = new DarkSkyController(this,
                gazetteerComponentServices, guiAgentServices,
                languagelist, unitList, countryList);
    }

    public DarkSkyController getDarkSkyController() {
        return darkSkyController;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getDarkSkyUrl() {
        return DARK_SKY_URL;
    }

    public Properties getProperties() {
        return properties;
    }

    public String getTown() {
        return town;
    }

    public String getLanguage() {
        return language;
    }

    public String getUnit() {
        return unit;
    }

    public String getCountry() {
        return country;
    }

    public String getCountryCode() {
        return country;
    }

    public String getCACHE_FILE_NAME() {
        return CACHE_FILE_NAME;
    }

}
