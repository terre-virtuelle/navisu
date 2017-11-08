/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bzh.terrevirtuelle.navisu.architecture.impl.handler;

import bzh.terrevirtuelle.navisu.domain.architecture.Component;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serge
 * @date Nov 7, 2017
 */
public class ComponentHandler 
        extends Handler{

    

    public ComponentHandler() {
        components = new ArrayList<>();
    }
    
    @Override
    public void doIt(Component data) {
        components.add(data);
       // System.out.println("components : " + components.size());
    }

    public List<Component> getComponents() {
        return components;
    }

}
