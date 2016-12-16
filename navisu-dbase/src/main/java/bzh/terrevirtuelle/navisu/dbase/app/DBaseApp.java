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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBaseApp {

    private List<List<String>> dbList;
    private DBFReader reader = null;
    private Map<String, Integer> keys = null;

    public DBaseApp() {
        dbList = new ArrayList<>();
        keys = new HashMap<>();
        try {
            //
            InputStream inputStream = new FileInputStream("data/DEPARE.dbf");
            reader = new DBFReader(inputStream);

            int numberOfFields = reader.getFieldCount();
            // use this count to fetch all field information
            // if required
            for (int i = 0; i < numberOfFields; i++) {
                DBFField field = reader.getField(i);
                keys.put(field.getName().trim(), i);
            }
            // Now, lets us start reading the rows
            Object[] rowObjects;
            try {
                while ((rowObjects = reader.nextRecord()) != null) {
                    List<String> tmp = new ArrayList<>();
                    for (Object rowObject : rowObjects) {
                        tmp.add(rowObject.toString());
                    }
                    dbList.add(tmp);
                }
            } catch (Exception e) {

            }
            int index = keys.get("auteurs");
            dbList.forEach((l) -> {
                System.out.println(l.get(index));
            });
        } catch (DBFException | FileNotFoundException ex) {
            Logger.getLogger(DBaseApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String args[]) {
        new DBaseApp();
    }

    public class Record {

        private String key;

        private List<String> values;

        public Record() {
        }

        public Record(String key, List<String> values) {
            this.key = key;
            this.values = values;
        }

        /**
         * Get the value of values
         *
         * @return the value of values
         */
        public List<String> getValues() {
            return values;
        }

        /**
         * Set the value of values
         *
         * @param values new value of values
         */
        public void setValues(List<String> values) {
            this.values = values;
        }

        public void add(String value) {
            values.add(value);
        }

        public String getValue(int index) {
            return values.get(index);
        }

        /**
         * Get the value of key
         *
         * @return the value of key
         */
        public String getKey() {
            return key;
        }

        /**
         * Set the value of key
         *
         * @param key new value of key
         */
        public void setKey(String key) {
            this.key = key;
        }

    }
}
