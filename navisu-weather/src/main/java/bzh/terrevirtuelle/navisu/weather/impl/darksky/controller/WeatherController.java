/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.weather.impl.darksky.controller;

import java.util.List;

/**
 *
 * @author serge
 * @date Jan 3, 2017
 */
public interface WeatherController {

    public boolean update();

    public List<DataPoint> getForecast();
}
