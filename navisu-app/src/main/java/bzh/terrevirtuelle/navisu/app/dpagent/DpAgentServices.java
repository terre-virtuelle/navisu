package bzh.terrevirtuelle.navisu.app.dpagent;

import bzh.terrevirtuelle.navisu.core.model.tobject.TObject;
import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @author tibus
 * @date 05/01/2014 14:08
 */
public interface DpAgentServices extends ComponentService {

    void create(final TObject tObject);
    void update(final TObject tObject);
    void delete(final TObject tObject);
}
