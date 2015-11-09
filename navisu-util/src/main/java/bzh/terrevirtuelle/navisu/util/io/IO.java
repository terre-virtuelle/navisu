/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.util.io;

import java.io.File;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * NaVisu
 *
 * @date 9 nov. 2015
 * @author Serge Morvan
 */
public class IO {

    public static File fileChooser(Stage stage, String rep, String title , String... args) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter
                = new FileChooser.ExtensionFilter(title, args);
        fileChooser.setInitialDirectory(new File(rep));
        fileChooser.getExtensionFilters().add(extFilter);
        return fileChooser.showOpenDialog(stage);
    }
}
