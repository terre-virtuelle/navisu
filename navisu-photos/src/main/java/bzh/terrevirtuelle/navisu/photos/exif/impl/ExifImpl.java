/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.photos.exif.impl;

import bzh.terrevirtuelle.navisu.photos.exif.Exif;
import bzh.terrevirtuelle.navisu.photos.exif.ExifServices;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.capcaval.c3.component.ComponentState;

/**
 * NaVisu
 *
 * @date 28 oct. 2015
 * @author Serge Morvan
 */
public class ExifImpl
        implements Exif, ExifServices, ComponentState {

    @Override
    public void componentInitiated() {
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

    @Override
    public Metadata readMetadata(String filename) {
        Metadata metadata = null;
        File file = new File(filename);
        try {
            metadata = ImageMetadataReader.readMetadata(file);
        } catch (ImageProcessingException | IOException ex) {
            Logger.getLogger(ExifImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return metadata;
    }

}
