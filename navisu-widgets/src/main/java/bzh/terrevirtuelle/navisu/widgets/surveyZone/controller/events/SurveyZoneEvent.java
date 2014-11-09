/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.widgets.surveyZone.controller.events;

import bzh.terrevirtuelle.navisu.widgets.surveyZone.model.SurveyZone;
import org.capcaval.c3.component.ComponentEvent;

/**
 *
 * @author Serge
 */
public interface SurveyZoneEvent
        extends ComponentEvent {

    /**
     *
     * @param <T>
     * @param data
     */
    public <T extends SurveyZone> void notifySurveyZoneMessageChanged(T data);
}
