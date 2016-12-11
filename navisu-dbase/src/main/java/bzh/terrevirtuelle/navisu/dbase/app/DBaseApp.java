/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.dbase.app;

/**
 *
 * @author serge
 */
import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBaseApp {

    public static void main(String args[]) {

        try {
            //
            InputStream inputStream = new FileInputStream("data/out.dbf"); // take dbf file as program argument
//InputStream inputStream = new FileInputStream("DEPARE.dbf");
            DBFReader reader = new DBFReader(inputStream);
            // get the field count if you want for some reasons like the following
            //
            int numberOfFields = reader.getFieldCount();
            // use this count to fetch all field information
            // if required

            for (int i = 0; i < numberOfFields; i++) {
                DBFField field = reader.getField(i);
                System.out.println("s : " + field.getName().trim());
            }

// Now, lets us start reading the rows
            Object[] rowObjects;
            while ((rowObjects = reader.nextRecord()) != null) {
                for (Object rowObject : rowObjects) {
                    System.out.println(rowObject);
                }
            }

            // By now, we have itereated through all of the rows
            // System.out.println("");
        } catch (DBFException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DBaseApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
