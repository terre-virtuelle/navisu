/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bzh.terrevirtuelle.navisu.architecture.impl.handler;

/**
 *
 * @author serge
 * @date Nov 3, 2017
 */
public class PrintHandler implements Handler{

    @Override
    public void doIt(String data) {
        System.out.println(data);
    }

}
