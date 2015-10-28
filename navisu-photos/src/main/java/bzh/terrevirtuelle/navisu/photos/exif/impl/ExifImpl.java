/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.photos.exif.impl;

import bzh.terrevirtuelle.navisu.domain.photos.exif.ExifType;
import bzh.terrevirtuelle.navisu.photos.exif.Exif;
import bzh.terrevirtuelle.navisu.photos.exif.ExifServices;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
    public Metadata readMetadata(String filename) throws IOException, ImageProcessingException {
        return ImageMetadataReader.readMetadata(new File(filename));
    }

    @Override
    public Metadata readMetadata(File file) throws IOException, ImageProcessingException {
        return ImageMetadataReader.readMetadata(file);
    }

    @Override
    public Metadata readMetadata(InputStream stream) throws IOException, ImageProcessingException {
        return ImageMetadataReader.readMetadata(stream);
    }

    @Override
    public ExifType create(String filename) throws IOException, ImageProcessingException {
        return populate(readMetadata(filename));
    }

    @Override
    public ExifType create(File file) throws IOException, ImageProcessingException {
        return populate(readMetadata(file));
    }

    @Override
    public ExifType create(InputStream stream) throws IOException, ImageProcessingException {
        return populate(readMetadata(stream));
    }

    @Override
    public ExifType populate(Metadata metadata) {
        //TODO
        for (Directory directory : metadata.getDirectories()) {
            String tagName;
            for (Tag tag : directory.getTags()) {
                tagName = tag.getTagName();
                tagName = tagName.replace(" ", "");
                tagName = tagName.replace("/", "");
                System.out.println(tagName + " " + tag.getDescription());
            }
            if (directory.hasErrors()) {
                for (String error : directory.getErrors()) {
                    System.err.println("ERROR: " + error);
                }
            }
        }
        ExifType exif = new ExifType();
        // populate
        return exif;
    }
}
