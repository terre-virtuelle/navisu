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
 * @date 29 oct. 2015
 * @author Serge Morvan
 */
@XmlRootElement
@XmlType(name = "imageInfo")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImageInfo {

    private String slices;

    private String dateTime;

    private int imageHeight;

    private int imageWidth;

    private String fileName;

    private long fileSize;

    public ImageInfo() {
    }

    public ImageInfo(String slices, String dateTime, int imageHeight, int imageWidth, String fileName, long fileSize) {
        this.slices = slices;
        this.dateTime = dateTime;
        this.imageHeight = imageHeight;
        this.imageWidth = imageWidth;
        this.fileName = fileName;
        this.fileSize = fileSize;
    }

    /**
     * Get the value of fileSize
     *
     * @return the value of fileSize
     */
    public long getFileSize() {
        return fileSize;
    }

    /**
     * Set the value of fileSize
     *
     * @param fileSize new value of fileSize
     */
    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * Get the value of fileName
     *
     * @return the value of fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Set the value of fileName
     *
     * @param fileName new value of fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Get the value of imageWidth
     *
     * @return the value of imageWidth
     */
    public int getImageWidth() {
        return imageWidth;
    }

    /**
     * Set the value of imageWidth
     *
     * @param imageWidth new value of imageWidth
     */
    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    /**
     * Get the value of imageHeight
     *
     * @return the value of imageHeight
     */
    public int getImageHeight() {
        return imageHeight;
    }

    /**
     * Set the value of imageHeight
     *
     * @param imageHeight new value of imageHeight
     */
    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    /**
     * Get the value of dateTime
     *
     * @return the value of dateTime
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * Set the value of dateTime
     *
     * @param dateTime new value of dateTime
     */
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Get the value of slices
     *
     * @return the value of slices
     */
    public String getSlices() {
        return slices;
    }

    /**
     * Set the value of slices
     *
     * @param slices new value of slices
     */
    public void setSlices(String slices) {
        this.slices = slices;
    }

    @Override
    public String toString() {
        return "ImageInfo{" + "slices=" + slices + ", dateTime=" + dateTime + ", imageHeight=" + imageHeight + ", imageWidth=" + imageWidth + ", fileName=" + fileName + ", fileSize=" + fileSize + '}';
    }

}
