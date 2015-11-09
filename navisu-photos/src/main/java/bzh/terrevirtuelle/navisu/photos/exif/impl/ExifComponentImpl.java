/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.photos.exif.impl;

import bzh.terrevirtuelle.navisu.domain.photos.exif.Exif;
import bzh.terrevirtuelle.navisu.photos.exif.ExifComponent;
import bzh.terrevirtuelle.navisu.photos.exif.ExifComponentServices;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import org.capcaval.c3.component.ComponentState;

/**
 * NaVisu
 *
 * @date 28 oct. 2015
 * @author Serge Morvan
 */
public class ExifComponentImpl
        implements ExifComponent, ExifComponentServices, ComponentState {

    DecimalFormat df = new DecimalFormat("#.#####");

    @Override
    public void componentInitiated() {
        df.setRoundingMode(RoundingMode.HALF_UP);
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
    public Exif create(String filename) throws IOException, ImageProcessingException {
        return populate(readMetadata(filename));
    }

    @Override
    public Exif create(File file) throws IOException, ImageProcessingException {
        return populate(readMetadata(file));
    }

    @Override
    public Exif create(InputStream stream) throws IOException, ImageProcessingException {
        return populate(readMetadata(stream));
    }

    @Override
    public Exif populate(Metadata metadata) {
        Exif exif = new Exif();

        for (Directory directory : metadata.getDirectories()) {
            String tagName;
            String[] tmp;
            String str;
            double deg;
            double min;
            double sec;
            int sign = 1;
            for (Tag tag : directory.getTags()) {
                tagName = tag.getTagName();
                tagName = tagName.replace(" ", "");
                tagName = tagName.replace("/", "");
                tagName = tagName.replace("-", "");
                switch (tagName) {
                    case "Slices":
                        exif.setSlices(tag.getDescription());
                        break;
                    case "DateTime":
                        exif.setDateTime(tag.getDescription());
                        break;
                    case "ImageHeight":
                        tmp = tag.getDescription().split(" ");
                        exif.setImageHeight(Integer.parseInt(tmp[0]));
                        break;
                    case "ImageWidth":
                        tmp = tag.getDescription().split(" ");
                        exif.setImageWidth(Integer.parseInt(tmp[0]));
                        break;
                    case "FileName":
                        exif.setFileName(tag.getDescription());
                        break;
                    case "FileSize":
                        tmp = tag.getDescription().split(" ");
                        exif.setFileSize(Integer.parseInt(tmp[0]));
                        break;
                    case "Model":
                        exif.setModel(tag.getDescription());
                        break;
                    case "Software":
                        exif.setSoftware(tag.getDescription());
                        break;
                    case "ExifVersion":
                        exif.setExifVersion(tag.getDescription());
                        break;
                    case "FNumber":
                        exif.setfNumber(tag.getDescription());
                        break;
                    case "ApertureValue":
                        exif.setApertureValue(tag.getDescription());
                        break;
                    case "FocalLength":
                        tmp = tag.getDescription().split(" ");
                        exif.setFocalLenth(Integer.parseInt(tmp[0]));
                        break;
                    case "LensModel":
                        exif.setLensModel(tag.getDescription());
                        break;
                    case "GPSMapDatum":
                        exif.setGpsMapDatum(tag.getDescription());
                        break;
                    case "GPSLatitude":
                        tmp = tag.getDescription().split(" ");
                        if (tmp[0].contains("-")) {
                            sign = -1;
                            tmp[0] = tmp[0].replace("-", "");
                        }
                        tmp[0] = tmp[0].replace("°", "");
                        deg = Double.parseDouble(tmp[0].trim());
                        tmp[1] = tmp[1].replace("'", "");
                        min = Double.parseDouble(tmp[1].trim());
                        tmp[2] = tmp[2].replace("\"", "");
                        tmp[2] = tmp[2].replace(",", ".");
                        sec = Double.parseDouble(tmp[2].trim());
                        sec = sec / 3600.0;
                        min = min / 60.0;
                        deg = deg + min + sec;
                        deg *= 100000;
                        deg = (int) deg;
                        deg /= 100000;
                        deg *= sign;

                        exif.setGpsLatitude(deg);
                        sign = 1;
                        break;
                    case "GPSLongitude":
                        tmp = tag.getDescription().split(" ");
                        if (tmp[0].contains("-")) {
                            sign = -1;
                            tmp[0] = tmp[0].replace("-", "");
                        }
                        tmp[0] = tmp[0].replace("°", "");
                        deg = Double.parseDouble(tmp[0].trim());
                        tmp[1] = tmp[1].replace("'", "");
                        min = Double.parseDouble(tmp[1].trim());
                        tmp[2] = tmp[2].replace("\"", "");
                        tmp[2] = tmp[2].replace(",", ".");
                        sec = Double.parseDouble(tmp[2].trim());
                        sec = sec / 3600.0;
                        min = min / 60.0;
                        deg = deg + min + sec;
                        deg *= 100000;
                        deg = (int) deg;
                        deg /= 100000;
                        deg *= sign;
                        exif.setGpsLongitude(deg);
                        sign = 1;
                        break;
                    case "GPSAltitudeRef":
                        exif.setGpsAltitudeRef(tag.getDescription());
                        break;
                    case "GPSAltitude":
                        tmp = tag.getDescription().split(" ");
                        exif.setGpsAltitude(Double.parseDouble(tmp[0]));
                        break;
                    case "GPSImgDirectionRef":
                        exif.setGpsImgDirectionRef(tag.getDescription());
                        break;
                    case "GPSImgDirection":
                        tmp = tag.getDescription().trim().split(" ");
                        exif.setGpsImgDirection(Integer.parseInt(tmp[0]));
                        break;
                    case "Artist":
                        exif.setName(tag.getDescription());
                        break;
                    case "Copyright":
                        exif.setCopyright(tag.getDescription());
                        break;
                    default:

                }
            }
            if (directory.hasErrors()) {
                for (String error : directory.getErrors()) {
                    System.err.println("ERROR: " + error);
                }
            }
        }
        return exif;
    }
    
}
