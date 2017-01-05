/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.weather;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.weather.impl.darksky.controller.DataPoint;
import java.util.List;
import org.capcaval.c3.component.ComponentService;

/**
 *
 * @author serge
 */
public interface WeatherComponentServices
        extends ComponentService {

    void on(String... category);

    InstrumentDriver getDriver();

    boolean update();

    List<DataPoint> getForecast();
}
