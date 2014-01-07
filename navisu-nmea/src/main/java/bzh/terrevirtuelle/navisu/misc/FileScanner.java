/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.misc;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Serge Morvan
 */
public class FileScanner {

    private Scanner sc;
    private Handler handler;

    public FileScanner(String fileName, Handler handler) {
        this.handler = handler;
        try {
            sc = new Scanner(new File(fileName));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileScanner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void scan() {
        while (sc.hasNext()) {
            handler.doIt(sc.nextLine().toString());
        }
    }

}
