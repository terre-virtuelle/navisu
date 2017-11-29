/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.architecture.impl.controller.handler;

import bzh.terrevirtuelle.navisu.domain.architecture.Component;
import java.util.List;

/**
 *
 * @author serge
 * @date Nov 3, 2017
 */
public abstract class Handler {

    protected List<Component> components;

    public abstract void doIt(Component data);

    public List<Component> getComponents() {
        return components;
    }
    
}
