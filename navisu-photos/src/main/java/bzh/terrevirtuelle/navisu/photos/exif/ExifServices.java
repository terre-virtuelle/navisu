/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.photos.exif;

import bzh.terrevirtuelle.navisu.domain.photos.exif.ExifType;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @date 26 août 2015
 * @author Serge Morvan
 */
public interface ExifServices
        extends ComponentService {

    Metadata readMetadata(String filename) throws IOException, ImageProcessingException;

    Metadata readMetadata(File file) throws IOException, ImageProcessingException;

    Metadata readMetadata(InputStream stream) throws IOException, ImageProcessingException;

    ExifType create(String filename) throws IOException, ImageProcessingException;

    ExifType create(File file) throws IOException, ImageProcessingException;

    ExifType create(InputStream stream) throws IOException, ImageProcessingException;

    public ExifType populate(Metadata metadata);
}
