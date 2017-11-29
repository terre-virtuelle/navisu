package bzh.terrevirtuelle.navisu.architecture;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import static javafx.application.Application.launch;
import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @author Serge Morvan
 * @date 25/2/2017
 */
public interface ArchitectureComponentServices
        extends ComponentService {


    InstrumentDriver getDriver();

}
