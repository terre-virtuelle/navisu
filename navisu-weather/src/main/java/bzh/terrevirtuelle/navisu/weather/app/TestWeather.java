/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.weather.app;


import bzh.terrevirtuelle.navisu.weather.impl.darksky.controller.DarkSkyComponentController;
import bzh.terrevirtuelle.navisu.weather.impl.darksky.controller.DataPoint;
import java.util.List;

/**
 *
 * @author serge
 * @date Dec 24, 2016
 */
public class TestWeather {

    String DARK_SKY_API_KEY = "3a9c0c39c2e2463c8a36c7ef6ed58dd5";
   

    public TestWeather() {
        DarkSkyComponentController darkSky = new DarkSkyComponentController(DARK_SKY_API_KEY, 
                DarkSkyComponentController.Unit.CA, DarkSkyComponentController.Language.ENGLISH, 48.3649, -4.4871);
      
        darkSky.update();
        List<DataPoint> dataPoints = darkSky.getForecast();
        System.out.println(darkSky.getCity());
        dataPoints.forEach((d) -> {
            System.out.println(d.toJsonString());
            System.out.println(d.getWindSpeed()+"  "+ d.getWindBearing());
        });
    }

    public static void main(String[] args) {
        new TestWeather();
    }
}
