package bzh.terrevirtuelle.navisu.app.guiagent.options;

import org.capcaval.c3.component.ComponentService;

/**
 * User: Jordan
 * Date: 08/11/2013
 */
public interface OptionsManagerServices extends ComponentService {

    void add(OptionsController... controllers);

    void show();

    void hide();
}
