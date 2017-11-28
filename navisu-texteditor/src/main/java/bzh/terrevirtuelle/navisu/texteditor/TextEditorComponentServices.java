/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.texteditor;

import java.awt.Image;
import java.util.List;

import org.capcaval.c3.component.ComponentService;

/**
 * @author serge Date: 9/2/2017
 */
public interface TextEditorComponentServices
        extends ComponentService {

    List<Image> convertPDFFileToImages(String filename, int width, int height, String extension);

    void pageFlipper(List<Image> images);
}
