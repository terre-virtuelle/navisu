/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bzh.terrevirtuelle.navisu.architecture.impl.view;

import bzh.terrevirtuelle.navisu.domain.architecture.Component;
import org.netbeans.api.visual.vmd.VMDNodeWidget;
import org.netbeans.api.visual.widget.Scene;

/**
 *
 * @author serge
 * @date Nov 6, 2017
 */
public class ComponentView 
        extends VMDNodeWidget{

    public ComponentView(Scene scene) {
        super(scene);
     //   System.out.println("ComponentView");
    }
   public ComponentView(Scene scene, Component component) {
        super(scene);
      //  System.out.println("ComponentView");
    }
}
