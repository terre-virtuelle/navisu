/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.photos.exif;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * NaVisu
 *
 * @date 28 oct. 2015
 * @author Serge Morvan
 */
@XmlRootElement
@XmlType(name = "exif")
@XmlAccessorType(XmlAccessType.FIELD)
public class Exif {

    ImageInfo imageInfo;
    HardSoft hardSoft;
    Optic optic;
    Position position;
    Author author;

    public Exif() {
        imageInfo = new ImageInfo();
        hardSoft = new HardSoft();
        optic = new Optic();
        position = new Position();
        author = new Author();
    }

    /*
     * Get the value of fileSize
     *
     * @return the value of fileSize
     */
    public long getFileSize() {
        return imageInfo.getFileSize();
    }

    /**
     * Set the value of fileSize
     *
     * @param fileSize new value of fileSize
     */
    public void setFileSize(long fileSize) {
        imageInfo.setFileSize(fileSize);
    }

    /**
     * Get the value of fileName
     *
     * @return the value of fileName
     */
    public String getFileName() {
        return imageInfo.getFileName();
    }

    /**
     * Set the value of fileName
     *
     * @param fileName new value of fileName
     */
    public void setFileName(String fileName) {
        imageInfo.setFileName(fileName);
    }

    /**
     * Get the value of imageWidth
     *
     * @return the value of imageWidth
     */
    public int getImageWidth() {
        return imageInfo.getImageWidth();
    }

    /**
     * Set the value of imageWidth
     *
     * @param imageWidth new value of imageWidth
     */
    public void setImageWidth(int imageWidth) {
        imageInfo.setImageWidth(imageWidth);
    }

    /**
     * Get the value of imageHeight
     *
     * @return the value of imageHeight
     */
    public int getImageHeight() {
        return imageInfo.getImageHeight();
    }

    /**
     * Set the value of imageHeight
     *
     * @param imageHeight new value of imageHeight
     */
    public void setImageHeight(int imageHeight) {
        imageInfo.setImageHeight(imageHeight);
    }

    /**
     * Get the value of dateTime
     *
     * @return the value of dateTime
     */
    public String getDateTime() {
        return imageInfo.getDateTime();
    }

    /**
     * Set the value of dateTime
     *
     * @param dateTime new value of dateTime
     */
    public void setDateTime(String dateTime) {
        imageInfo.setDateTime(dateTime);
    }

    /**
     * Get the value of slices
     *
     * @return the value of slices
     */
    public String getSlices() {
        return imageInfo.getSlices();
    }

    /**
     * Set the value of slices
     *
     * @param slices new value of slices
     */
    public void setSlices(String slices) {
        imageInfo.setSlices(slices);
    }

    public ImageInfo getImageInfo() {
        return imageInfo;
    }

    public void setImageInfo(ImageInfo imageInfo) {
        this.imageInfo = imageInfo;
    }

    public HardSoft getHardSoft() {
        return hardSoft;
    }

    public void setHardSoft(HardSoft hardSoft) {
        this.hardSoft = hardSoft;
    }

    public Optic getOptic() {
        return optic;
    }

    public void setOptic(Optic optic) {
        this.optic = optic;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Get the value of exifVersion
     *
     * @return the value of exifVersion
     */
    public String getExifVersion() {
        return hardSoft.getExifVersion();
    }

    /**
     * Set the value of exifVersion
     *
     * @param exifVersion new value of exifVersion
     */
    public void setExifVersion(String exifVersion) {
        hardSoft.setExifVersion(exifVersion);
    }

    /**
     * Get the value of software
     *
     * @return the value of software
     */
    public String getSoftware() {
        return hardSoft.getSoftware();
    }

    /**
     * Set the value of software
     *
     * @param software new value of software
     */
    public void setSoftware(String software) {
        hardSoft.setSoftware(software);
    }

    /**
     * Get the value of model
     *
     * @return the value of model
     */
    public String getModel() {
        return hardSoft.getModel();
    }

    /**
     * Set the value of model
     *
     * @param model new value of model
     */
    public void setModel(String model) {
        hardSoft.setModel(model);
    }
    /**
     * Get the value of gpsImgDirection
     *
     * @return the value of gpsImgDirection
     */
    public int getGpsImgDirection() {
        return position.getGpsImgDirection();
    }

    /**
     * Set the value of gpsImgDirection
     *
     * @param gpsImgDirection new value of gpsImgDirection
     */
    public void setGpsImgDirection(int gpsImgDirection) {
        position.setGpsImgDirection(gpsImgDirection);
    }

    /**
     * Get the value of gpsImgDirectionRef
     *
     * @return the value of gpsImgDirectionRef
     */
    public String getGpsImgDirectionRef() {
        return position.getGpsImgDirectionRef();
    }

    /**
     * Set the value of gpsImgDirectionRef
     *
     * @param gpsImgDirectionRef new value of gpsImgDirectionRef
     */
    public void setGpsImgDirectionRef(String gpsImgDirectionRef) {
        position.setGpsImgDirectionRef(gpsImgDirectionRef);
    }

    /**
     * Get the value of gpsAltitude
     *
     * @return the value of gpsAltitude
     */
    public double getGpsAltitude() {
        return position.getGpsAltitude();
    }

    /**
     * Set the value of gpsAltitude
     *
     * @param gpsAltitude new value of gpsAltitude
     */
    public void setGpsAltitude(double gpsAltitude) {
        position.setGpsAltitude(gpsAltitude);
    }

    /**
     * Get the value of gpsAltitudeRef
     *
     * @return the value of gpsAltitudeRef
     */
    public String getGpsAltitudeRef() {
        return position.getGpsAltitudeRef();
    }

    /**
     * Set the value of gpsAltitudeRef
     *
     * @param gpsAltitudeRef new value of gpsAltitudeRef
     */
    public void setGpsAltitudeRef(String gpsAltitudeRef) {
        position.setGpsAltitudeRef(gpsAltitudeRef);
    }

    /**
     * Get the value of gpsLongitude
     *
     * @return the value of gpsLongitude
     */
    public double getGpsLongitude() {
        return position.getGpsLongitude();
    }

    /**
     * Set the value of gpsLongitude
     *
     * @param gpsLongitude new value of gpsLongitude
     */
    public void setGpsLongitude(double gpsLongitude) {
        position.setGpsLongitude(gpsLongitude);
    }

    /**
     * Get the value of gpsLatitude
     *
     * @return the value of gpsLatitude
     */
    public double getGpsLatitude() {
        return position.getGpsLatitude();
    }

    /**
     * Set the value of gpsLatitude
     *
     * @param gpsLatitude new value of gpsLatitude
     */
    public void setGpsLatitude(double gpsLatitude) {
        position.setGpsLatitude(gpsLatitude);
    }

    /**
     * Get the value of gpsMapDatum
     *
     * @return the value of gpsMapDatum
     */
    public String getGpsMapDatum() {
        return position.getGpsMapDatum();
    }

    /**
     * Set the value of gpsMapDatum
     *
     * @param gpsMapDatum new value of gpsMapDatum
     */
    public void setGpsMapDatum(String gpsMapDatum) {
        position.setGpsMapDatum(gpsMapDatum);
    }
    /**
     * Get the value of copyright
     *
     * @return the value of copyright
     */
    public String getCopyright() {
        return author.getCopyright();
    }

    /**
     * Set the value of copyright
     *
     * @param copyright new value of copyright
     */
    public void setCopyright(String copyright) {
        author.setCopyright(copyright);
    }

    /**
     * Get the value of artist
     *
     * @return the value of artist
     */
    public String getName() {
        return author.getName();
    }

    /**
     * Set the value of artist
     *
     * @param artist new value of artist
     */
    public void setName(String name) {
        author.setName(name);
    }
/**
     * Get the value of lensModel
     *
     * @return the value of lensModel
     */
    public String getLensModel() {
        return optic.getLensModel();
    }

    /**
     * Set the value of lensModel
     *
     * @param lensModel new value of lensModel
     */
    public void setLensModel(String lensModel) {
        optic.setLensModel(lensModel);
    }

    /**
     * Get the value of focalLenth
     *
     * @return the value of focalLenth
     */
    public int getFocalLenth() {
        return optic.getFocalLenth();
    }

    /**
     * Set the value of focalLenth
     *
     * @param focalLenth new value of focalLenth
     */
    public void setFocalLenth(int focalLenth) {
        optic.setFocalLenth(focalLenth);
    }

    /**
     * Get the value of apertureValue
     *
     * @return the value of apertureValue
     */
    public String getApertureValue() {
        return optic.getApertureValue();
    }

    /**
     * Set the value of apertureValue
     *
     * @param apertureValue new value of apertureValue
     */
    public void setApertureValue(String apertureValue) {
        optic.setApertureValue(apertureValue);
    }

    /**
     * Get the value of fNumber
     *
     * @return the value of fNumber
     */
    public String getfNumber() {
        return optic.getfNumber();
    }

    /**
     * Set the value of fNumber
     *
     * @param fNumber new value of fNumber
     */
    public void setfNumber(String fNumber) {
        optic.setfNumber(fNumber);
    }
    @Override
    public String toString() {
        return "Exif{"  + imageInfo + ", " + hardSoft + ", " + optic + ", " + position + ", " + author + '}';
    }
    
}
