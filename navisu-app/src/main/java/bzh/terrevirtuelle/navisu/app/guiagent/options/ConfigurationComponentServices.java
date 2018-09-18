package bzh.terrevirtuelle.navisu.app.guiagent.options;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import java.util.Properties;
import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @date 21 mai 2016
 * @author Serge Morvan
 */
public interface ConfigurationComponentServices 
        extends ComponentService, CommonOptions {

    void on(String... files);

    boolean canOpen(String category);

    InstrumentDriver getDriver();
    
    Properties readCacheProperties();
    //void writeCacheProperties()
}
