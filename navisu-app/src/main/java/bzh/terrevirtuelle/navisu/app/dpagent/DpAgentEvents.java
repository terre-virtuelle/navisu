package bzh.terrevirtuelle.navisu.app.dpagent;

import bzh.terrevirtuelle.navisu.core.model.tmodel.TObject;
import org.capcaval.c3.component.ComponentEvent;
import org.capcaval.c3.component.ComponentEventSubscribe;

/**
 * NaVisu
 *
 * @author tibus
 * @date 05/01/2014 14:12
 */
public interface DpAgentEvents extends ComponentEvent {

    void notifyCreated(final TObject tObject);
    void notifyUpdated(final TObject tObject);
    void notifyDeleted(final TObject tObject);

    public interface DpAgentEventsSubscribe extends ComponentEventSubscribe<DpAgentEvents> {

    }
}
