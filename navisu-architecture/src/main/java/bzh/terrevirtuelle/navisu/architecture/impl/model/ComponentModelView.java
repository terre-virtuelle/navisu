/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.architecture.impl.model;

import bzh.terrevirtuelle.navisu.domain.architecture.Component;
import org.netbeans.api.visual.vmd.VMDNodeWidget;

/**
 *
 * @author serge
 * @date Nov 11, 2017
 */
public class ComponentModelView {

    private Component component;

    private VMDNodeWidget widget;

    public ComponentModelView(Component component, VMDNodeWidget widget) {
        this.component = component;
        this.widget = widget;
    }

    
    /**
     * Get the value of widget
     *
     * @return the value of widget
     */
    public VMDNodeWidget getWidget() {
        return widget;
    }

    /**
     * Set the value of widget
     *
     * @param widget new value of widget
     */
    public void setWidget(VMDNodeWidget widget) {
        this.widget = widget;
    }

    /**
     * Get the value of component
     *
     * @return the value of component
     */
    public Component getComponent() {
        return component;
    }

    /**
     * Set the value of component
     *
     * @param component new value of component
     */
    public void setComponent(Component component) {
        this.component = component;
    }

}
