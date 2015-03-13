/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.bathymetry.db;

import java.sql.Statement;
import org.capcaval.c3.component.ComponentService;

/**
 * @date 13 mars 2015
 * @author Serge Morvan
 */
public interface BathymetryDBServices
        extends ComponentService {

    Statement connect(String dbName, String hostName, String protocol, String port, 
            String driverName, 
            String userName, String passwd, String dataFileName);

    void open();

    void close();

    void create();
}
