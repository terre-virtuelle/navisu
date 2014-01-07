/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.misc;

import java.util.List;

/**
 *
 * @author Serge
 */
public abstract class Handler {

    public abstract void doIt(String data);
    public abstract List<String> getData();
}
