/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.architecture.impl.handler;

import bzh.terrevirtuelle.navisu.domain.architecture.Component;

/**
 *
 * @author serge
 * @date Nov 6, 2017
 */
public class SimpleComponentHandler implements Handler {

    @Override
    public void doIt(Component data) {
        System.out.println(data);
    }
}
