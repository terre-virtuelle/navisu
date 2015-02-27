/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.loggers.view;

/**
 *
 * @author Serge
 */
public class Printer {

    static Printer instance = null;

    private Printer() {
    }

    public static Printer getPrinter() {
        if (instance == null) {
            instance = new Printer();
        }
        return instance;
    }

    public <T> void display(T t) {
        System.out.println(t);
    }
}
