package bzh.terrevirtuelle.navisu.tools;


import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @author Serge Morvan
* @date 1/02/2018 12:49
 */
public interface ToolsComponentServices
        extends ComponentService {

    void on(String... files);

    boolean canOpen(String category);

    InstrumentDriver getDriver();

}
