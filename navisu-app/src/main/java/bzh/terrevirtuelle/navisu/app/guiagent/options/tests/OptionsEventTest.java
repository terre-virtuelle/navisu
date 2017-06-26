/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.app.guiagent.options.tests;

import bzh.terrevirtuelle.navisu.app.guiagent.options.domain.Option;
import bzh.terrevirtuelle.navisu.app.guiagent.options.domain.OwnerShipOption;
import bzh.terrevirtuelle.navisu.app.guiagent.options.domain.UserOption;
import bzh.terrevirtuelle.navisu.app.guiagent.options.events.OwnerShipConfEvent;
import bzh.terrevirtuelle.navisu.app.guiagent.options.events.UserConfEvent;
import org.capcaval.c3.component.ComponentEventSubscribe;
import org.capcaval.c3.componentmanager.ComponentManager;

/**
 *
 * @author serge
 * @date Jun 25, 2017
 */
public class OptionsEventTest {

    ComponentManager cm;
    ComponentEventSubscribe<OwnerShipConfEvent> oscES;
    ComponentEventSubscribe<UserConfEvent> ucES;

    public OptionsEventTest() {
        cm = ComponentManager.componentManager;
        oscES = cm.getComponentEventSubscribe(OwnerShipConfEvent.class);
        ucES = cm.getComponentEventSubscribe(UserConfEvent.class);
    }

    public final void subscribe() {
        oscES.subscribe(new OwnerShipConfEvent() {
            @Override
            public <T extends Option> void notifyConfMessageChanged(T d) {
                OwnerShipOption data = (OwnerShipOption) d;
                System.out.println("data " + data);
            }
        });
        ucES.subscribe(new UserConfEvent() {
            @Override
            public <T extends Option> void notifyConfMessageChanged(T d) {
                UserOption data = (UserOption) d;
                System.out.println("data " + data);
            }
        });
    }
}
