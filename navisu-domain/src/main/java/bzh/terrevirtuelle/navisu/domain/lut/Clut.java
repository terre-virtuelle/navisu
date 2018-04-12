/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bzh.terrevirtuelle.navisu.domain.lut;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author serge
 * @date Apr 11, 2018
 */
public class Clut {
  String content;  
  String source; 

    public Clut(String source) {
        this.source = source;
      try {
          content = new String(Files.readAllBytes(Paths.get(source)));
      } catch (IOException ex) {
          Logger.getLogger(Clut.class.getName()).log(Level.SEVERE, ex.toString(), ex);
      }
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Clut{" + "content=" + content + '}';
    }
    
}
