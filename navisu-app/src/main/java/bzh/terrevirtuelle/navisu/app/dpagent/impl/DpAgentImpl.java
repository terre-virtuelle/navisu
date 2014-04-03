package bzh.terrevirtuelle.navisu.app.dpagent.impl;

import bzh.terrevirtuelle.navisu.app.dpagent.DpAgent;
import bzh.terrevirtuelle.navisu.app.dpagent.DpAgentEvents;
import bzh.terrevirtuelle.navisu.app.dpagent.DpAgentServices;
import bzh.terrevirtuelle.navisu.core.model.generic.Model;
import bzh.terrevirtuelle.navisu.core.model.generic.ModelEvents;
import bzh.terrevirtuelle.navisu.core.model.tobject.TObject;
import org.capcaval.c3.component.ComponentStateAdaptor;
import org.capcaval.c3.component.annotation.ProducedEvent;

import java.util.logging.Logger;

/**
 * NaVisu
 *
 * @author tibus
 * @date 05/01/2014 14:09
 */
public class DpAgentImpl extends ComponentStateAdaptor implements DpAgent, DpAgentServices {

    private static final Logger LOGGER = Logger.getLogger(DpAgentImpl.class.getName());

    @ProducedEvent
    protected DpAgentEvents produceDpAgentEvents;

    protected Model<TObject> model;

    @Override
    public void componentInitiated() {

        this.model = Model.factory.newModel(TObject.class);
        this.model.getEventsSubscribe().subscribe(this.createModelEvents());
    }

    protected ModelEvents<TObject> createModelEvents() {
        return new ModelEvents<TObject>() {

            @Override
            public void notifyDataCreated(int id, TObject data) {
                produceDpAgentEvents.notifyCreated(data);
            }

            @Override
            public void notifyDataUpdated(int id, TObject data) {
                produceDpAgentEvents.notifyUpdated(data);
            }

            @Override
            public void notifyDataDeleted(int id, TObject data) {
                produceDpAgentEvents.notifyDeleted(data);
            }
        };
    }

    @Override
    public void create(final TObject tObject) {
        try {
            this.model.getWriteDataServices().create(tObject.getID(), tObject);
        } catch (Exception e) {
            System.out.println("e " + e);
        }
    }

    @Override
    public void update(final TObject tObject) {
        this.model.getWriteDataServices().update(tObject.getID(), tObject);
    }

    @Override
    public void delete(final TObject tObject) {
        this.model.getWriteDataServices().delete(tObject.getID());
    }
}
