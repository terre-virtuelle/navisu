//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2015.10.27 à 06:10:03 PM CET 
//


package bzh.terrevirtuelle.navisu.domain.photos.exif;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TiffIfd" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="ImageWidth" type="{http://xmlns.oracle.com/ord/meta/exif}positiveIntegerType" minOccurs="0"/>
 *                   &lt;element name="ImageLength" type="{http://xmlns.oracle.com/ord/meta/exif}positiveIntegerType" minOccurs="0"/>
 *                   &lt;element name="BitsPerSample" type="{http://xmlns.oracle.com/ord/meta/exif}bitsPerSampleType" minOccurs="0"/>
 *                   &lt;element name="Compression" type="{http://xmlns.oracle.com/ord/meta/exif}compressionType" minOccurs="0"/>
 *                   &lt;element name="PhotometricInterpretation" type="{http://xmlns.oracle.com/ord/meta/exif}photometricInterpretationType" minOccurs="0"/>
 *                   &lt;element name="Orientation" type="{http://xmlns.oracle.com/ord/meta/exif}orientationType" minOccurs="0"/>
 *                   &lt;element name="SamplesPerPixel" type="{http://xmlns.oracle.com/ord/meta/exif}positiveIntegerType" minOccurs="0"/>
 *                   &lt;element name="PlanarConfiguration" type="{http://xmlns.oracle.com/ord/meta/exif}planarConfigurationType" minOccurs="0"/>
 *                   &lt;element name="YCbCrSubSampling" type="{http://xmlns.oracle.com/ord/meta/exif}yCbCrSubSamplingType" minOccurs="0"/>
 *                   &lt;element name="YCbCrPositioning" type="{http://xmlns.oracle.com/ord/meta/exif}yCbCrPositioningType" minOccurs="0"/>
 *                   &lt;element name="XResolution" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeRealType" minOccurs="0"/>
 *                   &lt;element name="YResolution" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeRealType" minOccurs="0"/>
 *                   &lt;element name="ResolutionUnit" type="{http://xmlns.oracle.com/ord/meta/exif}resolutionType" minOccurs="0"/>
 *                   &lt;element name="StripOffsets" type="{http://xmlns.oracle.com/ord/meta/exif}stripOffsetsType" minOccurs="0"/>
 *                   &lt;element name="RowsPerStrip" type="{http://xmlns.oracle.com/ord/meta/exif}positiveIntegerType" minOccurs="0"/>
 *                   &lt;element name="StripByteCounts" type="{http://xmlns.oracle.com/ord/meta/exif}stripByteCountsType" minOccurs="0"/>
 *                   &lt;element name="JPEGInterChangeFormat" type="{http://xmlns.oracle.com/ord/meta/exif}positiveIntegerType" minOccurs="0"/>
 *                   &lt;element name="JPEGInterChangeFormatLength" type="{http://xmlns.oracle.com/ord/meta/exif}positiveIntegerType" minOccurs="0"/>
 *                   &lt;element name="TransferFunction" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *                   &lt;element name="WhitePoint" type="{http://xmlns.oracle.com/ord/meta/exif}whitePointType" minOccurs="0"/>
 *                   &lt;element name="PrimaryChromaticities" type="{http://xmlns.oracle.com/ord/meta/exif}primaryChromaticitiesType" minOccurs="0"/>
 *                   &lt;element name="YCbCrCoefficients" type="{http://xmlns.oracle.com/ord/meta/exif}yCbCrCoefficientsType" minOccurs="0"/>
 *                   &lt;element name="ReferenceBlackWhite" type="{http://xmlns.oracle.com/ord/meta/exif}primaryChromaticitiesType" minOccurs="0"/>
 *                   &lt;element name="DateTime" type="{http://xmlns.oracle.com/ord/meta/exif}dateTimeType" minOccurs="0"/>
 *                   &lt;element name="ImageDescription" type="{http://xmlns.oracle.com/ord/meta/exif}stringType" minOccurs="0"/>
 *                   &lt;element name="Make" type="{http://xmlns.oracle.com/ord/meta/exif}stringType" minOccurs="0"/>
 *                   &lt;element name="Model" type="{http://xmlns.oracle.com/ord/meta/exif}stringType" minOccurs="0"/>
 *                   &lt;element name="Software" type="{http://xmlns.oracle.com/ord/meta/exif}stringType" minOccurs="0"/>
 *                   &lt;element name="Artist" type="{http://xmlns.oracle.com/ord/meta/exif}stringType" minOccurs="0"/>
 *                   &lt;element name="Copyright" type="{http://xmlns.oracle.com/ord/meta/exif}stringType" minOccurs="0"/>
 *                   &lt;element name="TiffField1" type="{http://xmlns.oracle.com/ord/meta/exif}singleFieldType" minOccurs="0"/>
 *                   &lt;element name="TiffField2" type="{http://xmlns.oracle.com/ord/meta/exif}singleFieldType" minOccurs="0"/>
 *                   &lt;element name="TiffField3" type="{http://xmlns.oracle.com/ord/meta/exif}repeatedFieldType" minOccurs="0"/>
 *                 &lt;/all>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ExifIfd">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="ExifVersion" type="{http://xmlns.oracle.com/ord/meta/exif}stringType" minOccurs="0"/>
 *                   &lt;element name="FlashpixVersion" type="{http://xmlns.oracle.com/ord/meta/exif}stringType" minOccurs="0"/>
 *                   &lt;element name="ColorSpace" type="{http://xmlns.oracle.com/ord/meta/exif}colorSpaceType" minOccurs="0"/>
 *                   &lt;element name="ComponentsConfiguration" type="{http://xmlns.oracle.com/ord/meta/exif}stringType" minOccurs="0"/>
 *                   &lt;element name="CompressedBitsPerPixel" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeRealType" minOccurs="0"/>
 *                   &lt;element name="PixelXDimension" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeIntegerType" minOccurs="0"/>
 *                   &lt;element name="PixelYDimension" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeIntegerType" minOccurs="0"/>
 *                   &lt;element name="MakerNote" type="{http://xmlns.oracle.com/ord/meta/exif}repeatedFieldType" minOccurs="0"/>
 *                   &lt;element name="UserComment" type="{http://xmlns.oracle.com/ord/meta/exif}stringType" minOccurs="0"/>
 *                   &lt;element name="RelatedSoundFile" type="{http://xmlns.oracle.com/ord/meta/exif}stringType" minOccurs="0"/>
 *                   &lt;element name="DateTimeOriginal" type="{http://xmlns.oracle.com/ord/meta/exif}dateTimeType" minOccurs="0"/>
 *                   &lt;element name="DateTimeDigitized" type="{http://xmlns.oracle.com/ord/meta/exif}dateTimeType" minOccurs="0"/>
 *                   &lt;element name="SubSecTime" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeIntegerType" minOccurs="0"/>
 *                   &lt;element name="SubSecTimeOriginal" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeIntegerType" minOccurs="0"/>
 *                   &lt;element name="SubSecTimeDigitized" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeIntegerType" minOccurs="0"/>
 *                   &lt;element name="ExposureTime" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeRealType" minOccurs="0"/>
 *                   &lt;element name="FNumber" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeRealType" minOccurs="0"/>
 *                   &lt;element name="ExposureProgram" type="{http://xmlns.oracle.com/ord/meta/exif}exposureProgramType" minOccurs="0"/>
 *                   &lt;element name="SpectralSensitivity" type="{http://xmlns.oracle.com/ord/meta/exif}stringType" minOccurs="0"/>
 *                   &lt;element name="ISOSpeedRatings" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeIntegerType" minOccurs="0"/>
 *                   &lt;element name="OECF" type="{http://xmlns.oracle.com/ord/meta/exif}repeatedFieldType" minOccurs="0"/>
 *                   &lt;element name="ShutterSpeedValue" type="{http://xmlns.oracle.com/ord/meta/exif}realType" minOccurs="0"/>
 *                   &lt;element name="ApertureValue" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeRealType" minOccurs="0"/>
 *                   &lt;element name="BrightnessValue" type="{http://xmlns.oracle.com/ord/meta/exif}realType" minOccurs="0"/>
 *                   &lt;element name="ExposureBiasValue" type="{http://xmlns.oracle.com/ord/meta/exif}realType" minOccurs="0"/>
 *                   &lt;element name="MaxApertureValue" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeRealType" minOccurs="0"/>
 *                   &lt;element name="SubjectDistance" type="{http://xmlns.oracle.com/ord/meta/exif}stringType" minOccurs="0"/>
 *                   &lt;element name="MeteringMode" type="{http://xmlns.oracle.com/ord/meta/exif}meteringModeType" minOccurs="0"/>
 *                   &lt;element name="LightSource" type="{http://xmlns.oracle.com/ord/meta/exif}lightSourceType" minOccurs="0"/>
 *                   &lt;element name="Flash" type="{http://xmlns.oracle.com/ord/meta/exif}flashType" minOccurs="0"/>
 *                   &lt;element name="FocalLength" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeRealType" minOccurs="0"/>
 *                   &lt;element name="SubjectArea" type="{http://xmlns.oracle.com/ord/meta/exif}subjectAreaType" minOccurs="0"/>
 *                   &lt;element name="FlashEnergy" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeRealType" minOccurs="0"/>
 *                   &lt;element name="SpatialFrequencyResponse" type="{http://xmlns.oracle.com/ord/meta/exif}repeatedFieldType" minOccurs="0"/>
 *                   &lt;element name="FocalPlaneXResolution" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeRealType" minOccurs="0"/>
 *                   &lt;element name="FocalPlaneYResolution" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeRealType" minOccurs="0"/>
 *                   &lt;element name="FocalPlaneResolutionUnit" type="{http://xmlns.oracle.com/ord/meta/exif}resolutionType" minOccurs="0"/>
 *                   &lt;element name="SubjectLocation" type="{http://xmlns.oracle.com/ord/meta/exif}subjectLocationType" minOccurs="0"/>
 *                   &lt;element name="ExposureIndex" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeRealType" minOccurs="0"/>
 *                   &lt;element name="SensingMethod" type="{http://xmlns.oracle.com/ord/meta/exif}sensingMethodType" minOccurs="0"/>
 *                   &lt;element name="FileSource" type="{http://xmlns.oracle.com/ord/meta/exif}fileSourceType" minOccurs="0"/>
 *                   &lt;element name="SceneType" type="{http://xmlns.oracle.com/ord/meta/exif}stringType" minOccurs="0"/>
 *                   &lt;element name="CFAPattern" type="{http://xmlns.oracle.com/ord/meta/exif}repeatedFieldType" minOccurs="0"/>
 *                   &lt;element name="CustomRendered" type="{http://xmlns.oracle.com/ord/meta/exif}customRenderedType" minOccurs="0"/>
 *                   &lt;element name="ExposureMode" type="{http://xmlns.oracle.com/ord/meta/exif}exposureModeType" minOccurs="0"/>
 *                   &lt;element name="WhiteBalance" type="{http://xmlns.oracle.com/ord/meta/exif}whiteBalanceType" minOccurs="0"/>
 *                   &lt;element name="DigitalZoomRatio" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeRealType" minOccurs="0"/>
 *                   &lt;element name="FocalLengthIn35mmFilm" type="{http://xmlns.oracle.com/ord/meta/exif}positiveIntegerType" minOccurs="0"/>
 *                   &lt;element name="SceneCaptureType" type="{http://xmlns.oracle.com/ord/meta/exif}sceneCaptureType" minOccurs="0"/>
 *                   &lt;element name="GainControl" type="{http://xmlns.oracle.com/ord/meta/exif}gainControlType" minOccurs="0"/>
 *                   &lt;element name="Contrast" type="{http://xmlns.oracle.com/ord/meta/exif}contrastType" minOccurs="0"/>
 *                   &lt;element name="Saturation" type="{http://xmlns.oracle.com/ord/meta/exif}saturationType" minOccurs="0"/>
 *                   &lt;element name="Sharpness" type="{http://xmlns.oracle.com/ord/meta/exif}sharpnessType" minOccurs="0"/>
 *                   &lt;element name="DeviceSettingDescription" type="{http://xmlns.oracle.com/ord/meta/exif}repeatedFieldType" minOccurs="0"/>
 *                   &lt;element name="SubjectDistanceRange" type="{http://xmlns.oracle.com/ord/meta/exif}subjectDistanceRangeType" minOccurs="0"/>
 *                   &lt;element name="ImageUniqueID" type="{http://xmlns.oracle.com/ord/meta/exif}uuidType" minOccurs="0"/>
 *                   &lt;element name="Gamma" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeRealType" minOccurs="0"/>
 *                   &lt;element name="ExifField1" type="{http://xmlns.oracle.com/ord/meta/exif}singleFieldType" minOccurs="0"/>
 *                   &lt;element name="ExifField2" type="{http://xmlns.oracle.com/ord/meta/exif}singleFieldType" minOccurs="0"/>
 *                   &lt;element name="ExifField3" type="{http://xmlns.oracle.com/ord/meta/exif}repeatedFieldType" minOccurs="0"/>
 *                 &lt;/all>
 *                 &lt;attGroup ref="{http://xmlns.oracle.com/ord/meta/exif}exifAttrs"/>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="GpsIfd" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="GPSVersionID" type="{http://xmlns.oracle.com/ord/meta/exif}stringType"/>
 *                   &lt;element name="GPSLatitudeRef" type="{http://xmlns.oracle.com/ord/meta/exif}gpsLatitudeRefType" minOccurs="0"/>
 *                   &lt;element name="GPSLatitude" type="{http://xmlns.oracle.com/ord/meta/exif}gpsLatitudeType" minOccurs="0"/>
 *                   &lt;element name="GPSLongitudeRef" type="{http://xmlns.oracle.com/ord/meta/exif}gpsLongitudeRefType" minOccurs="0"/>
 *                   &lt;element name="GPSLongitude" type="{http://xmlns.oracle.com/ord/meta/exif}gpsLongitudeType" minOccurs="0"/>
 *                   &lt;element name="GPSAltitudeRef" type="{http://xmlns.oracle.com/ord/meta/exif}gpsAltitudeRefType" minOccurs="0"/>
 *                   &lt;element name="GPSAltitude" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeRealType" minOccurs="0"/>
 *                   &lt;element name="GPSTimeStamp" type="{http://xmlns.oracle.com/ord/meta/exif}timeType" minOccurs="0"/>
 *                   &lt;element name="GPSSatellites" type="{http://xmlns.oracle.com/ord/meta/exif}stringType" minOccurs="0"/>
 *                   &lt;element name="GPSStatus" type="{http://xmlns.oracle.com/ord/meta/exif}gpsStatusType" minOccurs="0"/>
 *                   &lt;element name="GPSMeasureMode" type="{http://xmlns.oracle.com/ord/meta/exif}gpsMeasureModeType" minOccurs="0"/>
 *                   &lt;element name="GPSDOP" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeRealType" minOccurs="0"/>
 *                   &lt;element name="GPSSpeedRef" type="{http://xmlns.oracle.com/ord/meta/exif}gpsSpeedRefType" minOccurs="0"/>
 *                   &lt;element name="GPSSpeed" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeRealType" minOccurs="0"/>
 *                   &lt;element name="GPSTrackRef" type="{http://xmlns.oracle.com/ord/meta/exif}gpsDirectionType" minOccurs="0"/>
 *                   &lt;element name="GPSTrack" type="{http://xmlns.oracle.com/ord/meta/exif}gpsBearingType" minOccurs="0"/>
 *                   &lt;element name="GPSImgDirectionRef" type="{http://xmlns.oracle.com/ord/meta/exif}gpsDirectionType" minOccurs="0"/>
 *                   &lt;element name="GPSImgDirection" type="{http://xmlns.oracle.com/ord/meta/exif}gpsBearingType" minOccurs="0"/>
 *                   &lt;element name="GPSMapDatum" type="{http://xmlns.oracle.com/ord/meta/exif}stringType" minOccurs="0"/>
 *                   &lt;element name="GPSDestLatitudeRef" type="{http://xmlns.oracle.com/ord/meta/exif}gpsLatitudeRefType" minOccurs="0"/>
 *                   &lt;element name="GPSDestLatitude" type="{http://xmlns.oracle.com/ord/meta/exif}gpsLatitudeType" minOccurs="0"/>
 *                   &lt;element name="GPSDestLongitudeRef" type="{http://xmlns.oracle.com/ord/meta/exif}gpsLongitudeRefType" minOccurs="0"/>
 *                   &lt;element name="GPSDestLongitude" type="{http://xmlns.oracle.com/ord/meta/exif}gpsLongitudeType" minOccurs="0"/>
 *                   &lt;element name="GPSDestBearingRef" type="{http://xmlns.oracle.com/ord/meta/exif}gpsDirectionType" minOccurs="0"/>
 *                   &lt;element name="GPSDestBearing" type="{http://xmlns.oracle.com/ord/meta/exif}gpsBearingType" minOccurs="0"/>
 *                   &lt;element name="GPSDestDistanceRef" type="{http://xmlns.oracle.com/ord/meta/exif}gpsDistanceRefType" minOccurs="0"/>
 *                   &lt;element name="GPSDestDistance" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeRealType" minOccurs="0"/>
 *                   &lt;element name="GPSProcessingMethod" type="{http://xmlns.oracle.com/ord/meta/exif}stringType" minOccurs="0"/>
 *                   &lt;element name="GPSAreaInformation" type="{http://xmlns.oracle.com/ord/meta/exif}stringType" minOccurs="0"/>
 *                   &lt;element name="GPSDateStamp" type="{http://xmlns.oracle.com/ord/meta/exif}dateType" minOccurs="0"/>
 *                   &lt;element name="GPSDifferential" type="{http://xmlns.oracle.com/ord/meta/exif}gpsDifferentialType" minOccurs="0"/>
 *                   &lt;element name="GPSField1" type="{http://xmlns.oracle.com/ord/meta/exif}singleFieldType" minOccurs="0"/>
 *                   &lt;element name="GPSField2" type="{http://xmlns.oracle.com/ord/meta/exif}singleFieldType" minOccurs="0"/>
 *                   &lt;element name="GPSField3" type="{http://xmlns.oracle.com/ord/meta/exif}repeatedFieldType" minOccurs="0"/>
 *                 &lt;/all>
 *                 &lt;attGroup ref="{http://xmlns.oracle.com/ord/meta/exif}exifAttrs"/>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="InteroperabilityIfd" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="InteroperabilityIndex" type="{http://xmlns.oracle.com/ord/meta/exif}interoperabilityType" minOccurs="0"/>
 *                 &lt;/all>
 *                 &lt;attGroup ref="{http://xmlns.oracle.com/ord/meta/exif}exifAttrs"/>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "tiffIfd",
    "exifIfd",
    "gpsIfd",
    "interoperabilityIfd"
})
@XmlRootElement(name = "exifMetadata")
public class ExifMetadata {

    @XmlElement(name = "TiffIfd")
    protected ExifMetadata.TiffIfd tiffIfd;
    @XmlElement(name = "ExifIfd", required = true)
    protected ExifMetadata.ExifIfd exifIfd;
    @XmlElement(name = "GpsIfd")
    protected ExifMetadata.GpsIfd gpsIfd;
    @XmlElement(name = "InteroperabilityIfd")
    protected ExifMetadata.InteroperabilityIfd interoperabilityIfd;

    /**
     * Obtient la valeur de la propriété tiffIfd.
     * 
     * @return
     *     possible object is
     *     {@link ExifMetadata.TiffIfd }
     *     
     */
    public ExifMetadata.TiffIfd getTiffIfd() {
        return tiffIfd;
    }

    /**
     * Définit la valeur de la propriété tiffIfd.
     * 
     * @param value
     *     allowed object is
     *     {@link ExifMetadata.TiffIfd }
     *     
     */
    public void setTiffIfd(ExifMetadata.TiffIfd value) {
        this.tiffIfd = value;
    }

    /**
     * Obtient la valeur de la propriété exifIfd.
     * 
     * @return
     *     possible object is
     *     {@link ExifMetadata.ExifIfd }
     *     
     */
    public ExifMetadata.ExifIfd getExifIfd() {
        return exifIfd;
    }

    /**
     * Définit la valeur de la propriété exifIfd.
     * 
     * @param value
     *     allowed object is
     *     {@link ExifMetadata.ExifIfd }
     *     
     */
    public void setExifIfd(ExifMetadata.ExifIfd value) {
        this.exifIfd = value;
    }

    /**
     * Obtient la valeur de la propriété gpsIfd.
     * 
     * @return
     *     possible object is
     *     {@link ExifMetadata.GpsIfd }
     *     
     */
    public ExifMetadata.GpsIfd getGpsIfd() {
        return gpsIfd;
    }

    /**
     * Définit la valeur de la propriété gpsIfd.
     * 
     * @param value
     *     allowed object is
     *     {@link ExifMetadata.GpsIfd }
     *     
     */
    public void setGpsIfd(ExifMetadata.GpsIfd value) {
        this.gpsIfd = value;
    }

    /**
     * Obtient la valeur de la propriété interoperabilityIfd.
     * 
     * @return
     *     possible object is
     *     {@link ExifMetadata.InteroperabilityIfd }
     *     
     */
    public ExifMetadata.InteroperabilityIfd getInteroperabilityIfd() {
        return interoperabilityIfd;
    }

    /**
     * Définit la valeur de la propriété interoperabilityIfd.
     * 
     * @param value
     *     allowed object is
     *     {@link ExifMetadata.InteroperabilityIfd }
     *     
     */
    public void setInteroperabilityIfd(ExifMetadata.InteroperabilityIfd value) {
        this.interoperabilityIfd = value;
    }


    /**
     * <p>Classe Java pour anonymous complex type.
     * 
     * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;all>
     *         &lt;element name="ExifVersion" type="{http://xmlns.oracle.com/ord/meta/exif}stringType" minOccurs="0"/>
     *         &lt;element name="FlashpixVersion" type="{http://xmlns.oracle.com/ord/meta/exif}stringType" minOccurs="0"/>
     *         &lt;element name="ColorSpace" type="{http://xmlns.oracle.com/ord/meta/exif}colorSpaceType" minOccurs="0"/>
     *         &lt;element name="ComponentsConfiguration" type="{http://xmlns.oracle.com/ord/meta/exif}stringType" minOccurs="0"/>
     *         &lt;element name="CompressedBitsPerPixel" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeRealType" minOccurs="0"/>
     *         &lt;element name="PixelXDimension" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeIntegerType" minOccurs="0"/>
     *         &lt;element name="PixelYDimension" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeIntegerType" minOccurs="0"/>
     *         &lt;element name="MakerNote" type="{http://xmlns.oracle.com/ord/meta/exif}repeatedFieldType" minOccurs="0"/>
     *         &lt;element name="UserComment" type="{http://xmlns.oracle.com/ord/meta/exif}stringType" minOccurs="0"/>
     *         &lt;element name="RelatedSoundFile" type="{http://xmlns.oracle.com/ord/meta/exif}stringType" minOccurs="0"/>
     *         &lt;element name="DateTimeOriginal" type="{http://xmlns.oracle.com/ord/meta/exif}dateTimeType" minOccurs="0"/>
     *         &lt;element name="DateTimeDigitized" type="{http://xmlns.oracle.com/ord/meta/exif}dateTimeType" minOccurs="0"/>
     *         &lt;element name="SubSecTime" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeIntegerType" minOccurs="0"/>
     *         &lt;element name="SubSecTimeOriginal" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeIntegerType" minOccurs="0"/>
     *         &lt;element name="SubSecTimeDigitized" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeIntegerType" minOccurs="0"/>
     *         &lt;element name="ExposureTime" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeRealType" minOccurs="0"/>
     *         &lt;element name="FNumber" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeRealType" minOccurs="0"/>
     *         &lt;element name="ExposureProgram" type="{http://xmlns.oracle.com/ord/meta/exif}exposureProgramType" minOccurs="0"/>
     *         &lt;element name="SpectralSensitivity" type="{http://xmlns.oracle.com/ord/meta/exif}stringType" minOccurs="0"/>
     *         &lt;element name="ISOSpeedRatings" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeIntegerType" minOccurs="0"/>
     *         &lt;element name="OECF" type="{http://xmlns.oracle.com/ord/meta/exif}repeatedFieldType" minOccurs="0"/>
     *         &lt;element name="ShutterSpeedValue" type="{http://xmlns.oracle.com/ord/meta/exif}realType" minOccurs="0"/>
     *         &lt;element name="ApertureValue" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeRealType" minOccurs="0"/>
     *         &lt;element name="BrightnessValue" type="{http://xmlns.oracle.com/ord/meta/exif}realType" minOccurs="0"/>
     *         &lt;element name="ExposureBiasValue" type="{http://xmlns.oracle.com/ord/meta/exif}realType" minOccurs="0"/>
     *         &lt;element name="MaxApertureValue" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeRealType" minOccurs="0"/>
     *         &lt;element name="SubjectDistance" type="{http://xmlns.oracle.com/ord/meta/exif}stringType" minOccurs="0"/>
     *         &lt;element name="MeteringMode" type="{http://xmlns.oracle.com/ord/meta/exif}meteringModeType" minOccurs="0"/>
     *         &lt;element name="LightSource" type="{http://xmlns.oracle.com/ord/meta/exif}lightSourceType" minOccurs="0"/>
     *         &lt;element name="Flash" type="{http://xmlns.oracle.com/ord/meta/exif}flashType" minOccurs="0"/>
     *         &lt;element name="FocalLength" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeRealType" minOccurs="0"/>
     *         &lt;element name="SubjectArea" type="{http://xmlns.oracle.com/ord/meta/exif}subjectAreaType" minOccurs="0"/>
     *         &lt;element name="FlashEnergy" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeRealType" minOccurs="0"/>
     *         &lt;element name="SpatialFrequencyResponse" type="{http://xmlns.oracle.com/ord/meta/exif}repeatedFieldType" minOccurs="0"/>
     *         &lt;element name="FocalPlaneXResolution" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeRealType" minOccurs="0"/>
     *         &lt;element name="FocalPlaneYResolution" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeRealType" minOccurs="0"/>
     *         &lt;element name="FocalPlaneResolutionUnit" type="{http://xmlns.oracle.com/ord/meta/exif}resolutionType" minOccurs="0"/>
     *         &lt;element name="SubjectLocation" type="{http://xmlns.oracle.com/ord/meta/exif}subjectLocationType" minOccurs="0"/>
     *         &lt;element name="ExposureIndex" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeRealType" minOccurs="0"/>
     *         &lt;element name="SensingMethod" type="{http://xmlns.oracle.com/ord/meta/exif}sensingMethodType" minOccurs="0"/>
     *         &lt;element name="FileSource" type="{http://xmlns.oracle.com/ord/meta/exif}fileSourceType" minOccurs="0"/>
     *         &lt;element name="SceneType" type="{http://xmlns.oracle.com/ord/meta/exif}stringType" minOccurs="0"/>
     *         &lt;element name="CFAPattern" type="{http://xmlns.oracle.com/ord/meta/exif}repeatedFieldType" minOccurs="0"/>
     *         &lt;element name="CustomRendered" type="{http://xmlns.oracle.com/ord/meta/exif}customRenderedType" minOccurs="0"/>
     *         &lt;element name="ExposureMode" type="{http://xmlns.oracle.com/ord/meta/exif}exposureModeType" minOccurs="0"/>
     *         &lt;element name="WhiteBalance" type="{http://xmlns.oracle.com/ord/meta/exif}whiteBalanceType" minOccurs="0"/>
     *         &lt;element name="DigitalZoomRatio" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeRealType" minOccurs="0"/>
     *         &lt;element name="FocalLengthIn35mmFilm" type="{http://xmlns.oracle.com/ord/meta/exif}positiveIntegerType" minOccurs="0"/>
     *         &lt;element name="SceneCaptureType" type="{http://xmlns.oracle.com/ord/meta/exif}sceneCaptureType" minOccurs="0"/>
     *         &lt;element name="GainControl" type="{http://xmlns.oracle.com/ord/meta/exif}gainControlType" minOccurs="0"/>
     *         &lt;element name="Contrast" type="{http://xmlns.oracle.com/ord/meta/exif}contrastType" minOccurs="0"/>
     *         &lt;element name="Saturation" type="{http://xmlns.oracle.com/ord/meta/exif}saturationType" minOccurs="0"/>
     *         &lt;element name="Sharpness" type="{http://xmlns.oracle.com/ord/meta/exif}sharpnessType" minOccurs="0"/>
     *         &lt;element name="DeviceSettingDescription" type="{http://xmlns.oracle.com/ord/meta/exif}repeatedFieldType" minOccurs="0"/>
     *         &lt;element name="SubjectDistanceRange" type="{http://xmlns.oracle.com/ord/meta/exif}subjectDistanceRangeType" minOccurs="0"/>
     *         &lt;element name="ImageUniqueID" type="{http://xmlns.oracle.com/ord/meta/exif}uuidType" minOccurs="0"/>
     *         &lt;element name="Gamma" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeRealType" minOccurs="0"/>
     *         &lt;element name="ExifField1" type="{http://xmlns.oracle.com/ord/meta/exif}singleFieldType" minOccurs="0"/>
     *         &lt;element name="ExifField2" type="{http://xmlns.oracle.com/ord/meta/exif}singleFieldType" minOccurs="0"/>
     *         &lt;element name="ExifField3" type="{http://xmlns.oracle.com/ord/meta/exif}repeatedFieldType" minOccurs="0"/>
     *       &lt;/all>
     *       &lt;attGroup ref="{http://xmlns.oracle.com/ord/meta/exif}exifAttrs"/>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {

    })
    public static class ExifIfd {

        @XmlElement(name = "ExifVersion")
        protected StringType exifVersion;
        @XmlElement(name = "FlashpixVersion")
        protected StringType flashpixVersion;
        @XmlElement(name = "ColorSpace")
        protected ColorSpaceType colorSpace;
        @XmlElement(name = "ComponentsConfiguration")
        protected StringType componentsConfiguration;
        @XmlElement(name = "CompressedBitsPerPixel")
        protected NonNegativeRealType compressedBitsPerPixel;
        @XmlElement(name = "PixelXDimension")
        protected NonNegativeIntegerType pixelXDimension;
        @XmlElement(name = "PixelYDimension")
        protected NonNegativeIntegerType pixelYDimension;
        @XmlElement(name = "MakerNote")
        protected RepeatedFieldType makerNote;
        @XmlElement(name = "UserComment")
        protected StringType userComment;
        @XmlElement(name = "RelatedSoundFile")
        protected StringType relatedSoundFile;
        @XmlElement(name = "DateTimeOriginal")
        protected DateTimeType dateTimeOriginal;
        @XmlElement(name = "DateTimeDigitized")
        protected DateTimeType dateTimeDigitized;
        @XmlElement(name = "SubSecTime")
        protected NonNegativeIntegerType subSecTime;
        @XmlElement(name = "SubSecTimeOriginal")
        protected NonNegativeIntegerType subSecTimeOriginal;
        @XmlElement(name = "SubSecTimeDigitized")
        protected NonNegativeIntegerType subSecTimeDigitized;
        @XmlElement(name = "ExposureTime")
        protected NonNegativeRealType exposureTime;
        @XmlElement(name = "FNumber")
        protected NonNegativeRealType fNumber;
        @XmlElement(name = "ExposureProgram")
        protected ExposureProgramType exposureProgram;
        @XmlElement(name = "SpectralSensitivity")
        protected StringType spectralSensitivity;
        @XmlElement(name = "ISOSpeedRatings")
        protected NonNegativeIntegerType isoSpeedRatings;
        @XmlElement(name = "OECF")
        protected RepeatedFieldType oecf;
        @XmlElement(name = "ShutterSpeedValue")
        protected RealType shutterSpeedValue;
        @XmlElement(name = "ApertureValue")
        protected NonNegativeRealType apertureValue;
        @XmlElement(name = "BrightnessValue")
        protected RealType brightnessValue;
        @XmlElement(name = "ExposureBiasValue")
        protected RealType exposureBiasValue;
        @XmlElement(name = "MaxApertureValue")
        protected NonNegativeRealType maxApertureValue;
        @XmlElement(name = "SubjectDistance")
        protected StringType subjectDistance;
        @XmlElement(name = "MeteringMode")
        protected MeteringModeType meteringMode;
        @XmlElement(name = "LightSource")
        protected LightSourceType lightSource;
        @XmlElement(name = "Flash")
        protected FlashType flash;
        @XmlElement(name = "FocalLength")
        protected NonNegativeRealType focalLength;
        @XmlElement(name = "SubjectArea")
        protected SubjectAreaType subjectArea;
        @XmlElement(name = "FlashEnergy")
        protected NonNegativeRealType flashEnergy;
        @XmlElement(name = "SpatialFrequencyResponse")
        protected RepeatedFieldType spatialFrequencyResponse;
        @XmlElement(name = "FocalPlaneXResolution")
        protected NonNegativeRealType focalPlaneXResolution;
        @XmlElement(name = "FocalPlaneYResolution")
        protected NonNegativeRealType focalPlaneYResolution;
        @XmlElement(name = "FocalPlaneResolutionUnit")
        protected ResolutionType focalPlaneResolutionUnit;
        @XmlElement(name = "SubjectLocation")
        protected SubjectLocationType subjectLocation;
        @XmlElement(name = "ExposureIndex")
        protected NonNegativeRealType exposureIndex;
        @XmlElement(name = "SensingMethod")
        protected SensingMethodType sensingMethod;
        @XmlElement(name = "FileSource")
        protected FileSourceType fileSource;
        @XmlElement(name = "SceneType")
        protected StringType sceneType;
        @XmlElement(name = "CFAPattern")
        protected RepeatedFieldType cfaPattern;
        @XmlElement(name = "CustomRendered")
        protected CustomRenderedType customRendered;
        @XmlElement(name = "ExposureMode")
        protected ExposureModeType exposureMode;
        @XmlElement(name = "WhiteBalance")
        protected WhiteBalanceType whiteBalance;
        @XmlElement(name = "DigitalZoomRatio")
        protected NonNegativeRealType digitalZoomRatio;
        @XmlElement(name = "FocalLengthIn35mmFilm")
        protected PositiveIntegerType focalLengthIn35MmFilm;
        @XmlElement(name = "SceneCaptureType")
        protected SceneCaptureType sceneCaptureType;
        @XmlElement(name = "GainControl")
        protected GainControlType gainControl;
        @XmlElement(name = "Contrast")
        protected ContrastType contrast;
        @XmlElement(name = "Saturation")
        protected SaturationType saturation;
        @XmlElement(name = "Sharpness")
        protected SharpnessType sharpness;
        @XmlElement(name = "DeviceSettingDescription")
        protected RepeatedFieldType deviceSettingDescription;
        @XmlElement(name = "SubjectDistanceRange")
        protected SubjectDistanceRangeType subjectDistanceRange;
        @XmlElement(name = "ImageUniqueID")
        protected UuidType imageUniqueID;
        @XmlElement(name = "Gamma")
        protected NonNegativeRealType gamma;
        @XmlElement(name = "ExifField1")
        protected SingleFieldType exifField1;
        @XmlElement(name = "ExifField2")
        protected SingleFieldType exifField2;
        @XmlElement(name = "ExifField3")
        protected RepeatedFieldType exifField3;
        @XmlAttribute(name = "tag", required = true)
        @XmlSchemaType(name = "nonNegativeInteger")
        protected BigInteger tag;

        /**
         * Obtient la valeur de la propriété exifVersion.
         * 
         * @return
         *     possible object is
         *     {@link StringType }
         *     
         */
        public StringType getExifVersion() {
            return exifVersion;
        }

        /**
         * Définit la valeur de la propriété exifVersion.
         * 
         * @param value
         *     allowed object is
         *     {@link StringType }
         *     
         */
        public void setExifVersion(StringType value) {
            this.exifVersion = value;
        }

        /**
         * Obtient la valeur de la propriété flashpixVersion.
         * 
         * @return
         *     possible object is
         *     {@link StringType }
         *     
         */
        public StringType getFlashpixVersion() {
            return flashpixVersion;
        }

        /**
         * Définit la valeur de la propriété flashpixVersion.
         * 
         * @param value
         *     allowed object is
         *     {@link StringType }
         *     
         */
        public void setFlashpixVersion(StringType value) {
            this.flashpixVersion = value;
        }

        /**
         * Obtient la valeur de la propriété colorSpace.
         * 
         * @return
         *     possible object is
         *     {@link ColorSpaceType }
         *     
         */
        public ColorSpaceType getColorSpace() {
            return colorSpace;
        }

        /**
         * Définit la valeur de la propriété colorSpace.
         * 
         * @param value
         *     allowed object is
         *     {@link ColorSpaceType }
         *     
         */
        public void setColorSpace(ColorSpaceType value) {
            this.colorSpace = value;
        }

        /**
         * Obtient la valeur de la propriété componentsConfiguration.
         * 
         * @return
         *     possible object is
         *     {@link StringType }
         *     
         */
        public StringType getComponentsConfiguration() {
            return componentsConfiguration;
        }

        /**
         * Définit la valeur de la propriété componentsConfiguration.
         * 
         * @param value
         *     allowed object is
         *     {@link StringType }
         *     
         */
        public void setComponentsConfiguration(StringType value) {
            this.componentsConfiguration = value;
        }

        /**
         * Obtient la valeur de la propriété compressedBitsPerPixel.
         * 
         * @return
         *     possible object is
         *     {@link NonNegativeRealType }
         *     
         */
        public NonNegativeRealType getCompressedBitsPerPixel() {
            return compressedBitsPerPixel;
        }

        /**
         * Définit la valeur de la propriété compressedBitsPerPixel.
         * 
         * @param value
         *     allowed object is
         *     {@link NonNegativeRealType }
         *     
         */
        public void setCompressedBitsPerPixel(NonNegativeRealType value) {
            this.compressedBitsPerPixel = value;
        }

        /**
         * Obtient la valeur de la propriété pixelXDimension.
         * 
         * @return
         *     possible object is
         *     {@link NonNegativeIntegerType }
         *     
         */
        public NonNegativeIntegerType getPixelXDimension() {
            return pixelXDimension;
        }

        /**
         * Définit la valeur de la propriété pixelXDimension.
         * 
         * @param value
         *     allowed object is
         *     {@link NonNegativeIntegerType }
         *     
         */
        public void setPixelXDimension(NonNegativeIntegerType value) {
            this.pixelXDimension = value;
        }

        /**
         * Obtient la valeur de la propriété pixelYDimension.
         * 
         * @return
         *     possible object is
         *     {@link NonNegativeIntegerType }
         *     
         */
        public NonNegativeIntegerType getPixelYDimension() {
            return pixelYDimension;
        }

        /**
         * Définit la valeur de la propriété pixelYDimension.
         * 
         * @param value
         *     allowed object is
         *     {@link NonNegativeIntegerType }
         *     
         */
        public void setPixelYDimension(NonNegativeIntegerType value) {
            this.pixelYDimension = value;
        }

        /**
         * Obtient la valeur de la propriété makerNote.
         * 
         * @return
         *     possible object is
         *     {@link RepeatedFieldType }
         *     
         */
        public RepeatedFieldType getMakerNote() {
            return makerNote;
        }

        /**
         * Définit la valeur de la propriété makerNote.
         * 
         * @param value
         *     allowed object is
         *     {@link RepeatedFieldType }
         *     
         */
        public void setMakerNote(RepeatedFieldType value) {
            this.makerNote = value;
        }

        /**
         * Obtient la valeur de la propriété userComment.
         * 
         * @return
         *     possible object is
         *     {@link StringType }
         *     
         */
        public StringType getUserComment() {
            return userComment;
        }

        /**
         * Définit la valeur de la propriété userComment.
         * 
         * @param value
         *     allowed object is
         *     {@link StringType }
         *     
         */
        public void setUserComment(StringType value) {
            this.userComment = value;
        }

        /**
         * Obtient la valeur de la propriété relatedSoundFile.
         * 
         * @return
         *     possible object is
         *     {@link StringType }
         *     
         */
        public StringType getRelatedSoundFile() {
            return relatedSoundFile;
        }

        /**
         * Définit la valeur de la propriété relatedSoundFile.
         * 
         * @param value
         *     allowed object is
         *     {@link StringType }
         *     
         */
        public void setRelatedSoundFile(StringType value) {
            this.relatedSoundFile = value;
        }

        /**
         * Obtient la valeur de la propriété dateTimeOriginal.
         * 
         * @return
         *     possible object is
         *     {@link DateTimeType }
         *     
         */
        public DateTimeType getDateTimeOriginal() {
            return dateTimeOriginal;
        }

        /**
         * Définit la valeur de la propriété dateTimeOriginal.
         * 
         * @param value
         *     allowed object is
         *     {@link DateTimeType }
         *     
         */
        public void setDateTimeOriginal(DateTimeType value) {
            this.dateTimeOriginal = value;
        }

        /**
         * Obtient la valeur de la propriété dateTimeDigitized.
         * 
         * @return
         *     possible object is
         *     {@link DateTimeType }
         *     
         */
        public DateTimeType getDateTimeDigitized() {
            return dateTimeDigitized;
        }

        /**
         * Définit la valeur de la propriété dateTimeDigitized.
         * 
         * @param value
         *     allowed object is
         *     {@link DateTimeType }
         *     
         */
        public void setDateTimeDigitized(DateTimeType value) {
            this.dateTimeDigitized = value;
        }

        /**
         * Obtient la valeur de la propriété subSecTime.
         * 
         * @return
         *     possible object is
         *     {@link NonNegativeIntegerType }
         *     
         */
        public NonNegativeIntegerType getSubSecTime() {
            return subSecTime;
        }

        /**
         * Définit la valeur de la propriété subSecTime.
         * 
         * @param value
         *     allowed object is
         *     {@link NonNegativeIntegerType }
         *     
         */
        public void setSubSecTime(NonNegativeIntegerType value) {
            this.subSecTime = value;
        }

        /**
         * Obtient la valeur de la propriété subSecTimeOriginal.
         * 
         * @return
         *     possible object is
         *     {@link NonNegativeIntegerType }
         *     
         */
        public NonNegativeIntegerType getSubSecTimeOriginal() {
            return subSecTimeOriginal;
        }

        /**
         * Définit la valeur de la propriété subSecTimeOriginal.
         * 
         * @param value
         *     allowed object is
         *     {@link NonNegativeIntegerType }
         *     
         */
        public void setSubSecTimeOriginal(NonNegativeIntegerType value) {
            this.subSecTimeOriginal = value;
        }

        /**
         * Obtient la valeur de la propriété subSecTimeDigitized.
         * 
         * @return
         *     possible object is
         *     {@link NonNegativeIntegerType }
         *     
         */
        public NonNegativeIntegerType getSubSecTimeDigitized() {
            return subSecTimeDigitized;
        }

        /**
         * Définit la valeur de la propriété subSecTimeDigitized.
         * 
         * @param value
         *     allowed object is
         *     {@link NonNegativeIntegerType }
         *     
         */
        public void setSubSecTimeDigitized(NonNegativeIntegerType value) {
            this.subSecTimeDigitized = value;
        }

        /**
         * Obtient la valeur de la propriété exposureTime.
         * 
         * @return
         *     possible object is
         *     {@link NonNegativeRealType }
         *     
         */
        public NonNegativeRealType getExposureTime() {
            return exposureTime;
        }

        /**
         * Définit la valeur de la propriété exposureTime.
         * 
         * @param value
         *     allowed object is
         *     {@link NonNegativeRealType }
         *     
         */
        public void setExposureTime(NonNegativeRealType value) {
            this.exposureTime = value;
        }

        /**
         * Obtient la valeur de la propriété fNumber.
         * 
         * @return
         *     possible object is
         *     {@link NonNegativeRealType }
         *     
         */
        public NonNegativeRealType getFNumber() {
            return fNumber;
        }

        /**
         * Définit la valeur de la propriété fNumber.
         * 
         * @param value
         *     allowed object is
         *     {@link NonNegativeRealType }
         *     
         */
        public void setFNumber(NonNegativeRealType value) {
            this.fNumber = value;
        }

        /**
         * Obtient la valeur de la propriété exposureProgram.
         * 
         * @return
         *     possible object is
         *     {@link ExposureProgramType }
         *     
         */
        public ExposureProgramType getExposureProgram() {
            return exposureProgram;
        }

        /**
         * Définit la valeur de la propriété exposureProgram.
         * 
         * @param value
         *     allowed object is
         *     {@link ExposureProgramType }
         *     
         */
        public void setExposureProgram(ExposureProgramType value) {
            this.exposureProgram = value;
        }

        /**
         * Obtient la valeur de la propriété spectralSensitivity.
         * 
         * @return
         *     possible object is
         *     {@link StringType }
         *     
         */
        public StringType getSpectralSensitivity() {
            return spectralSensitivity;
        }

        /**
         * Définit la valeur de la propriété spectralSensitivity.
         * 
         * @param value
         *     allowed object is
         *     {@link StringType }
         *     
         */
        public void setSpectralSensitivity(StringType value) {
            this.spectralSensitivity = value;
        }

        /**
         * Obtient la valeur de la propriété isoSpeedRatings.
         * 
         * @return
         *     possible object is
         *     {@link NonNegativeIntegerType }
         *     
         */
        public NonNegativeIntegerType getISOSpeedRatings() {
            return isoSpeedRatings;
        }

        /**
         * Définit la valeur de la propriété isoSpeedRatings.
         * 
         * @param value
         *     allowed object is
         *     {@link NonNegativeIntegerType }
         *     
         */
        public void setISOSpeedRatings(NonNegativeIntegerType value) {
            this.isoSpeedRatings = value;
        }

        /**
         * Obtient la valeur de la propriété oecf.
         * 
         * @return
         *     possible object is
         *     {@link RepeatedFieldType }
         *     
         */
        public RepeatedFieldType getOECF() {
            return oecf;
        }

        /**
         * Définit la valeur de la propriété oecf.
         * 
         * @param value
         *     allowed object is
         *     {@link RepeatedFieldType }
         *     
         */
        public void setOECF(RepeatedFieldType value) {
            this.oecf = value;
        }

        /**
         * Obtient la valeur de la propriété shutterSpeedValue.
         * 
         * @return
         *     possible object is
         *     {@link RealType }
         *     
         */
        public RealType getShutterSpeedValue() {
            return shutterSpeedValue;
        }

        /**
         * Définit la valeur de la propriété shutterSpeedValue.
         * 
         * @param value
         *     allowed object is
         *     {@link RealType }
         *     
         */
        public void setShutterSpeedValue(RealType value) {
            this.shutterSpeedValue = value;
        }

        /**
         * Obtient la valeur de la propriété apertureValue.
         * 
         * @return
         *     possible object is
         *     {@link NonNegativeRealType }
         *     
         */
        public NonNegativeRealType getApertureValue() {
            return apertureValue;
        }

        /**
         * Définit la valeur de la propriété apertureValue.
         * 
         * @param value
         *     allowed object is
         *     {@link NonNegativeRealType }
         *     
         */
        public void setApertureValue(NonNegativeRealType value) {
            this.apertureValue = value;
        }

        /**
         * Obtient la valeur de la propriété brightnessValue.
         * 
         * @return
         *     possible object is
         *     {@link RealType }
         *     
         */
        public RealType getBrightnessValue() {
            return brightnessValue;
        }

        /**
         * Définit la valeur de la propriété brightnessValue.
         * 
         * @param value
         *     allowed object is
         *     {@link RealType }
         *     
         */
        public void setBrightnessValue(RealType value) {
            this.brightnessValue = value;
        }

        /**
         * Obtient la valeur de la propriété exposureBiasValue.
         * 
         * @return
         *     possible object is
         *     {@link RealType }
         *     
         */
        public RealType getExposureBiasValue() {
            return exposureBiasValue;
        }

        /**
         * Définit la valeur de la propriété exposureBiasValue.
         * 
         * @param value
         *     allowed object is
         *     {@link RealType }
         *     
         */
        public void setExposureBiasValue(RealType value) {
            this.exposureBiasValue = value;
        }

        /**
         * Obtient la valeur de la propriété maxApertureValue.
         * 
         * @return
         *     possible object is
         *     {@link NonNegativeRealType }
         *     
         */
        public NonNegativeRealType getMaxApertureValue() {
            return maxApertureValue;
        }

        /**
         * Définit la valeur de la propriété maxApertureValue.
         * 
         * @param value
         *     allowed object is
         *     {@link NonNegativeRealType }
         *     
         */
        public void setMaxApertureValue(NonNegativeRealType value) {
            this.maxApertureValue = value;
        }

        /**
         * Obtient la valeur de la propriété subjectDistance.
         * 
         * @return
         *     possible object is
         *     {@link StringType }
         *     
         */
        public StringType getSubjectDistance() {
            return subjectDistance;
        }

        /**
         * Définit la valeur de la propriété subjectDistance.
         * 
         * @param value
         *     allowed object is
         *     {@link StringType }
         *     
         */
        public void setSubjectDistance(StringType value) {
            this.subjectDistance = value;
        }

        /**
         * Obtient la valeur de la propriété meteringMode.
         * 
         * @return
         *     possible object is
         *     {@link MeteringModeType }
         *     
         */
        public MeteringModeType getMeteringMode() {
            return meteringMode;
        }

        /**
         * Définit la valeur de la propriété meteringMode.
         * 
         * @param value
         *     allowed object is
         *     {@link MeteringModeType }
         *     
         */
        public void setMeteringMode(MeteringModeType value) {
            this.meteringMode = value;
        }

        /**
         * Obtient la valeur de la propriété lightSource.
         * 
         * @return
         *     possible object is
         *     {@link LightSourceType }
         *     
         */
        public LightSourceType getLightSource() {
            return lightSource;
        }

        /**
         * Définit la valeur de la propriété lightSource.
         * 
         * @param value
         *     allowed object is
         *     {@link LightSourceType }
         *     
         */
        public void setLightSource(LightSourceType value) {
            this.lightSource = value;
        }

        /**
         * Obtient la valeur de la propriété flash.
         * 
         * @return
         *     possible object is
         *     {@link FlashType }
         *     
         */
        public FlashType getFlash() {
            return flash;
        }

        /**
         * Définit la valeur de la propriété flash.
         * 
         * @param value
         *     allowed object is
         *     {@link FlashType }
         *     
         */
        public void setFlash(FlashType value) {
            this.flash = value;
        }

        /**
         * Obtient la valeur de la propriété focalLength.
         * 
         * @return
         *     possible object is
         *     {@link NonNegativeRealType }
         *     
         */
        public NonNegativeRealType getFocalLength() {
            return focalLength;
        }

        /**
         * Définit la valeur de la propriété focalLength.
         * 
         * @param value
         *     allowed object is
         *     {@link NonNegativeRealType }
         *     
         */
        public void setFocalLength(NonNegativeRealType value) {
            this.focalLength = value;
        }

        /**
         * Obtient la valeur de la propriété subjectArea.
         * 
         * @return
         *     possible object is
         *     {@link SubjectAreaType }
         *     
         */
        public SubjectAreaType getSubjectArea() {
            return subjectArea;
        }

        /**
         * Définit la valeur de la propriété subjectArea.
         * 
         * @param value
         *     allowed object is
         *     {@link SubjectAreaType }
         *     
         */
        public void setSubjectArea(SubjectAreaType value) {
            this.subjectArea = value;
        }

        /**
         * Obtient la valeur de la propriété flashEnergy.
         * 
         * @return
         *     possible object is
         *     {@link NonNegativeRealType }
         *     
         */
        public NonNegativeRealType getFlashEnergy() {
            return flashEnergy;
        }

        /**
         * Définit la valeur de la propriété flashEnergy.
         * 
         * @param value
         *     allowed object is
         *     {@link NonNegativeRealType }
         *     
         */
        public void setFlashEnergy(NonNegativeRealType value) {
            this.flashEnergy = value;
        }

        /**
         * Obtient la valeur de la propriété spatialFrequencyResponse.
         * 
         * @return
         *     possible object is
         *     {@link RepeatedFieldType }
         *     
         */
        public RepeatedFieldType getSpatialFrequencyResponse() {
            return spatialFrequencyResponse;
        }

        /**
         * Définit la valeur de la propriété spatialFrequencyResponse.
         * 
         * @param value
         *     allowed object is
         *     {@link RepeatedFieldType }
         *     
         */
        public void setSpatialFrequencyResponse(RepeatedFieldType value) {
            this.spatialFrequencyResponse = value;
        }

        /**
         * Obtient la valeur de la propriété focalPlaneXResolution.
         * 
         * @return
         *     possible object is
         *     {@link NonNegativeRealType }
         *     
         */
        public NonNegativeRealType getFocalPlaneXResolution() {
            return focalPlaneXResolution;
        }

        /**
         * Définit la valeur de la propriété focalPlaneXResolution.
         * 
         * @param value
         *     allowed object is
         *     {@link NonNegativeRealType }
         *     
         */
        public void setFocalPlaneXResolution(NonNegativeRealType value) {
            this.focalPlaneXResolution = value;
        }

        /**
         * Obtient la valeur de la propriété focalPlaneYResolution.
         * 
         * @return
         *     possible object is
         *     {@link NonNegativeRealType }
         *     
         */
        public NonNegativeRealType getFocalPlaneYResolution() {
            return focalPlaneYResolution;
        }

        /**
         * Définit la valeur de la propriété focalPlaneYResolution.
         * 
         * @param value
         *     allowed object is
         *     {@link NonNegativeRealType }
         *     
         */
        public void setFocalPlaneYResolution(NonNegativeRealType value) {
            this.focalPlaneYResolution = value;
        }

        /**
         * Obtient la valeur de la propriété focalPlaneResolutionUnit.
         * 
         * @return
         *     possible object is
         *     {@link ResolutionType }
         *     
         */
        public ResolutionType getFocalPlaneResolutionUnit() {
            return focalPlaneResolutionUnit;
        }

        /**
         * Définit la valeur de la propriété focalPlaneResolutionUnit.
         * 
         * @param value
         *     allowed object is
         *     {@link ResolutionType }
         *     
         */
        public void setFocalPlaneResolutionUnit(ResolutionType value) {
            this.focalPlaneResolutionUnit = value;
        }

        /**
         * Obtient la valeur de la propriété subjectLocation.
         * 
         * @return
         *     possible object is
         *     {@link SubjectLocationType }
         *     
         */
        public SubjectLocationType getSubjectLocation() {
            return subjectLocation;
        }

        /**
         * Définit la valeur de la propriété subjectLocation.
         * 
         * @param value
         *     allowed object is
         *     {@link SubjectLocationType }
         *     
         */
        public void setSubjectLocation(SubjectLocationType value) {
            this.subjectLocation = value;
        }

        /**
         * Obtient la valeur de la propriété exposureIndex.
         * 
         * @return
         *     possible object is
         *     {@link NonNegativeRealType }
         *     
         */
        public NonNegativeRealType getExposureIndex() {
            return exposureIndex;
        }

        /**
         * Définit la valeur de la propriété exposureIndex.
         * 
         * @param value
         *     allowed object is
         *     {@link NonNegativeRealType }
         *     
         */
        public void setExposureIndex(NonNegativeRealType value) {
            this.exposureIndex = value;
        }

        /**
         * Obtient la valeur de la propriété sensingMethod.
         * 
         * @return
         *     possible object is
         *     {@link SensingMethodType }
         *     
         */
        public SensingMethodType getSensingMethod() {
            return sensingMethod;
        }

        /**
         * Définit la valeur de la propriété sensingMethod.
         * 
         * @param value
         *     allowed object is
         *     {@link SensingMethodType }
         *     
         */
        public void setSensingMethod(SensingMethodType value) {
            this.sensingMethod = value;
        }

        /**
         * Obtient la valeur de la propriété fileSource.
         * 
         * @return
         *     possible object is
         *     {@link FileSourceType }
         *     
         */
        public FileSourceType getFileSource() {
            return fileSource;
        }

        /**
         * Définit la valeur de la propriété fileSource.
         * 
         * @param value
         *     allowed object is
         *     {@link FileSourceType }
         *     
         */
        public void setFileSource(FileSourceType value) {
            this.fileSource = value;
        }

        /**
         * Obtient la valeur de la propriété sceneType.
         * 
         * @return
         *     possible object is
         *     {@link StringType }
         *     
         */
        public StringType getSceneType() {
            return sceneType;
        }

        /**
         * Définit la valeur de la propriété sceneType.
         * 
         * @param value
         *     allowed object is
         *     {@link StringType }
         *     
         */
        public void setSceneType(StringType value) {
            this.sceneType = value;
        }

        /**
         * Obtient la valeur de la propriété cfaPattern.
         * 
         * @return
         *     possible object is
         *     {@link RepeatedFieldType }
         *     
         */
        public RepeatedFieldType getCFAPattern() {
            return cfaPattern;
        }

        /**
         * Définit la valeur de la propriété cfaPattern.
         * 
         * @param value
         *     allowed object is
         *     {@link RepeatedFieldType }
         *     
         */
        public void setCFAPattern(RepeatedFieldType value) {
            this.cfaPattern = value;
        }

        /**
         * Obtient la valeur de la propriété customRendered.
         * 
         * @return
         *     possible object is
         *     {@link CustomRenderedType }
         *     
         */
        public CustomRenderedType getCustomRendered() {
            return customRendered;
        }

        /**
         * Définit la valeur de la propriété customRendered.
         * 
         * @param value
         *     allowed object is
         *     {@link CustomRenderedType }
         *     
         */
        public void setCustomRendered(CustomRenderedType value) {
            this.customRendered = value;
        }

        /**
         * Obtient la valeur de la propriété exposureMode.
         * 
         * @return
         *     possible object is
         *     {@link ExposureModeType }
         *     
         */
        public ExposureModeType getExposureMode() {
            return exposureMode;
        }

        /**
         * Définit la valeur de la propriété exposureMode.
         * 
         * @param value
         *     allowed object is
         *     {@link ExposureModeType }
         *     
         */
        public void setExposureMode(ExposureModeType value) {
            this.exposureMode = value;
        }

        /**
         * Obtient la valeur de la propriété whiteBalance.
         * 
         * @return
         *     possible object is
         *     {@link WhiteBalanceType }
         *     
         */
        public WhiteBalanceType getWhiteBalance() {
            return whiteBalance;
        }

        /**
         * Définit la valeur de la propriété whiteBalance.
         * 
         * @param value
         *     allowed object is
         *     {@link WhiteBalanceType }
         *     
         */
        public void setWhiteBalance(WhiteBalanceType value) {
            this.whiteBalance = value;
        }

        /**
         * Obtient la valeur de la propriété digitalZoomRatio.
         * 
         * @return
         *     possible object is
         *     {@link NonNegativeRealType }
         *     
         */
        public NonNegativeRealType getDigitalZoomRatio() {
            return digitalZoomRatio;
        }

        /**
         * Définit la valeur de la propriété digitalZoomRatio.
         * 
         * @param value
         *     allowed object is
         *     {@link NonNegativeRealType }
         *     
         */
        public void setDigitalZoomRatio(NonNegativeRealType value) {
            this.digitalZoomRatio = value;
        }

        /**
         * Obtient la valeur de la propriété focalLengthIn35MmFilm.
         * 
         * @return
         *     possible object is
         *     {@link PositiveIntegerType }
         *     
         */
        public PositiveIntegerType getFocalLengthIn35MmFilm() {
            return focalLengthIn35MmFilm;
        }

        /**
         * Définit la valeur de la propriété focalLengthIn35MmFilm.
         * 
         * @param value
         *     allowed object is
         *     {@link PositiveIntegerType }
         *     
         */
        public void setFocalLengthIn35MmFilm(PositiveIntegerType value) {
            this.focalLengthIn35MmFilm = value;
        }

        /**
         * Obtient la valeur de la propriété sceneCaptureType.
         * 
         * @return
         *     possible object is
         *     {@link SceneCaptureType }
         *     
         */
        public SceneCaptureType getSceneCaptureType() {
            return sceneCaptureType;
        }

        /**
         * Définit la valeur de la propriété sceneCaptureType.
         * 
         * @param value
         *     allowed object is
         *     {@link SceneCaptureType }
         *     
         */
        public void setSceneCaptureType(SceneCaptureType value) {
            this.sceneCaptureType = value;
        }

        /**
         * Obtient la valeur de la propriété gainControl.
         * 
         * @return
         *     possible object is
         *     {@link GainControlType }
         *     
         */
        public GainControlType getGainControl() {
            return gainControl;
        }

        /**
         * Définit la valeur de la propriété gainControl.
         * 
         * @param value
         *     allowed object is
         *     {@link GainControlType }
         *     
         */
        public void setGainControl(GainControlType value) {
            this.gainControl = value;
        }

        /**
         * Obtient la valeur de la propriété contrast.
         * 
         * @return
         *     possible object is
         *     {@link ContrastType }
         *     
         */
        public ContrastType getContrast() {
            return contrast;
        }

        /**
         * Définit la valeur de la propriété contrast.
         * 
         * @param value
         *     allowed object is
         *     {@link ContrastType }
         *     
         */
        public void setContrast(ContrastType value) {
            this.contrast = value;
        }

        /**
         * Obtient la valeur de la propriété saturation.
         * 
         * @return
         *     possible object is
         *     {@link SaturationType }
         *     
         */
        public SaturationType getSaturation() {
            return saturation;
        }

        /**
         * Définit la valeur de la propriété saturation.
         * 
         * @param value
         *     allowed object is
         *     {@link SaturationType }
         *     
         */
        public void setSaturation(SaturationType value) {
            this.saturation = value;
        }

        /**
         * Obtient la valeur de la propriété sharpness.
         * 
         * @return
         *     possible object is
         *     {@link SharpnessType }
         *     
         */
        public SharpnessType getSharpness() {
            return sharpness;
        }

        /**
         * Définit la valeur de la propriété sharpness.
         * 
         * @param value
         *     allowed object is
         *     {@link SharpnessType }
         *     
         */
        public void setSharpness(SharpnessType value) {
            this.sharpness = value;
        }

        /**
         * Obtient la valeur de la propriété deviceSettingDescription.
         * 
         * @return
         *     possible object is
         *     {@link RepeatedFieldType }
         *     
         */
        public RepeatedFieldType getDeviceSettingDescription() {
            return deviceSettingDescription;
        }

        /**
         * Définit la valeur de la propriété deviceSettingDescription.
         * 
         * @param value
         *     allowed object is
         *     {@link RepeatedFieldType }
         *     
         */
        public void setDeviceSettingDescription(RepeatedFieldType value) {
            this.deviceSettingDescription = value;
        }

        /**
         * Obtient la valeur de la propriété subjectDistanceRange.
         * 
         * @return
         *     possible object is
         *     {@link SubjectDistanceRangeType }
         *     
         */
        public SubjectDistanceRangeType getSubjectDistanceRange() {
            return subjectDistanceRange;
        }

        /**
         * Définit la valeur de la propriété subjectDistanceRange.
         * 
         * @param value
         *     allowed object is
         *     {@link SubjectDistanceRangeType }
         *     
         */
        public void setSubjectDistanceRange(SubjectDistanceRangeType value) {
            this.subjectDistanceRange = value;
        }

        /**
         * Obtient la valeur de la propriété imageUniqueID.
         * 
         * @return
         *     possible object is
         *     {@link UuidType }
         *     
         */
        public UuidType getImageUniqueID() {
            return imageUniqueID;
        }

        /**
         * Définit la valeur de la propriété imageUniqueID.
         * 
         * @param value
         *     allowed object is
         *     {@link UuidType }
         *     
         */
        public void setImageUniqueID(UuidType value) {
            this.imageUniqueID = value;
        }

        /**
         * Obtient la valeur de la propriété gamma.
         * 
         * @return
         *     possible object is
         *     {@link NonNegativeRealType }
         *     
         */
        public NonNegativeRealType getGamma() {
            return gamma;
        }

        /**
         * Définit la valeur de la propriété gamma.
         * 
         * @param value
         *     allowed object is
         *     {@link NonNegativeRealType }
         *     
         */
        public void setGamma(NonNegativeRealType value) {
            this.gamma = value;
        }

        /**
         * Obtient la valeur de la propriété exifField1.
         * 
         * @return
         *     possible object is
         *     {@link SingleFieldType }
         *     
         */
        public SingleFieldType getExifField1() {
            return exifField1;
        }

        /**
         * Définit la valeur de la propriété exifField1.
         * 
         * @param value
         *     allowed object is
         *     {@link SingleFieldType }
         *     
         */
        public void setExifField1(SingleFieldType value) {
            this.exifField1 = value;
        }

        /**
         * Obtient la valeur de la propriété exifField2.
         * 
         * @return
         *     possible object is
         *     {@link SingleFieldType }
         *     
         */
        public SingleFieldType getExifField2() {
            return exifField2;
        }

        /**
         * Définit la valeur de la propriété exifField2.
         * 
         * @param value
         *     allowed object is
         *     {@link SingleFieldType }
         *     
         */
        public void setExifField2(SingleFieldType value) {
            this.exifField2 = value;
        }

        /**
         * Obtient la valeur de la propriété exifField3.
         * 
         * @return
         *     possible object is
         *     {@link RepeatedFieldType }
         *     
         */
        public RepeatedFieldType getExifField3() {
            return exifField3;
        }

        /**
         * Définit la valeur de la propriété exifField3.
         * 
         * @param value
         *     allowed object is
         *     {@link RepeatedFieldType }
         *     
         */
        public void setExifField3(RepeatedFieldType value) {
            this.exifField3 = value;
        }

        /**
         * Obtient la valeur de la propriété tag.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getTag() {
            return tag;
        }

        /**
         * Définit la valeur de la propriété tag.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setTag(BigInteger value) {
            this.tag = value;
        }

    }


    /**
     * <p>Classe Java pour anonymous complex type.
     * 
     * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;all>
     *         &lt;element name="GPSVersionID" type="{http://xmlns.oracle.com/ord/meta/exif}stringType"/>
     *         &lt;element name="GPSLatitudeRef" type="{http://xmlns.oracle.com/ord/meta/exif}gpsLatitudeRefType" minOccurs="0"/>
     *         &lt;element name="GPSLatitude" type="{http://xmlns.oracle.com/ord/meta/exif}gpsLatitudeType" minOccurs="0"/>
     *         &lt;element name="GPSLongitudeRef" type="{http://xmlns.oracle.com/ord/meta/exif}gpsLongitudeRefType" minOccurs="0"/>
     *         &lt;element name="GPSLongitude" type="{http://xmlns.oracle.com/ord/meta/exif}gpsLongitudeType" minOccurs="0"/>
     *         &lt;element name="GPSAltitudeRef" type="{http://xmlns.oracle.com/ord/meta/exif}gpsAltitudeRefType" minOccurs="0"/>
     *         &lt;element name="GPSAltitude" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeRealType" minOccurs="0"/>
     *         &lt;element name="GPSTimeStamp" type="{http://xmlns.oracle.com/ord/meta/exif}timeType" minOccurs="0"/>
     *         &lt;element name="GPSSatellites" type="{http://xmlns.oracle.com/ord/meta/exif}stringType" minOccurs="0"/>
     *         &lt;element name="GPSStatus" type="{http://xmlns.oracle.com/ord/meta/exif}gpsStatusType" minOccurs="0"/>
     *         &lt;element name="GPSMeasureMode" type="{http://xmlns.oracle.com/ord/meta/exif}gpsMeasureModeType" minOccurs="0"/>
     *         &lt;element name="GPSDOP" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeRealType" minOccurs="0"/>
     *         &lt;element name="GPSSpeedRef" type="{http://xmlns.oracle.com/ord/meta/exif}gpsSpeedRefType" minOccurs="0"/>
     *         &lt;element name="GPSSpeed" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeRealType" minOccurs="0"/>
     *         &lt;element name="GPSTrackRef" type="{http://xmlns.oracle.com/ord/meta/exif}gpsDirectionType" minOccurs="0"/>
     *         &lt;element name="GPSTrack" type="{http://xmlns.oracle.com/ord/meta/exif}gpsBearingType" minOccurs="0"/>
     *         &lt;element name="GPSImgDirectionRef" type="{http://xmlns.oracle.com/ord/meta/exif}gpsDirectionType" minOccurs="0"/>
     *         &lt;element name="GPSImgDirection" type="{http://xmlns.oracle.com/ord/meta/exif}gpsBearingType" minOccurs="0"/>
     *         &lt;element name="GPSMapDatum" type="{http://xmlns.oracle.com/ord/meta/exif}stringType" minOccurs="0"/>
     *         &lt;element name="GPSDestLatitudeRef" type="{http://xmlns.oracle.com/ord/meta/exif}gpsLatitudeRefType" minOccurs="0"/>
     *         &lt;element name="GPSDestLatitude" type="{http://xmlns.oracle.com/ord/meta/exif}gpsLatitudeType" minOccurs="0"/>
     *         &lt;element name="GPSDestLongitudeRef" type="{http://xmlns.oracle.com/ord/meta/exif}gpsLongitudeRefType" minOccurs="0"/>
     *         &lt;element name="GPSDestLongitude" type="{http://xmlns.oracle.com/ord/meta/exif}gpsLongitudeType" minOccurs="0"/>
     *         &lt;element name="GPSDestBearingRef" type="{http://xmlns.oracle.com/ord/meta/exif}gpsDirectionType" minOccurs="0"/>
     *         &lt;element name="GPSDestBearing" type="{http://xmlns.oracle.com/ord/meta/exif}gpsBearingType" minOccurs="0"/>
     *         &lt;element name="GPSDestDistanceRef" type="{http://xmlns.oracle.com/ord/meta/exif}gpsDistanceRefType" minOccurs="0"/>
     *         &lt;element name="GPSDestDistance" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeRealType" minOccurs="0"/>
     *         &lt;element name="GPSProcessingMethod" type="{http://xmlns.oracle.com/ord/meta/exif}stringType" minOccurs="0"/>
     *         &lt;element name="GPSAreaInformation" type="{http://xmlns.oracle.com/ord/meta/exif}stringType" minOccurs="0"/>
     *         &lt;element name="GPSDateStamp" type="{http://xmlns.oracle.com/ord/meta/exif}dateType" minOccurs="0"/>
     *         &lt;element name="GPSDifferential" type="{http://xmlns.oracle.com/ord/meta/exif}gpsDifferentialType" minOccurs="0"/>
     *         &lt;element name="GPSField1" type="{http://xmlns.oracle.com/ord/meta/exif}singleFieldType" minOccurs="0"/>
     *         &lt;element name="GPSField2" type="{http://xmlns.oracle.com/ord/meta/exif}singleFieldType" minOccurs="0"/>
     *         &lt;element name="GPSField3" type="{http://xmlns.oracle.com/ord/meta/exif}repeatedFieldType" minOccurs="0"/>
     *       &lt;/all>
     *       &lt;attGroup ref="{http://xmlns.oracle.com/ord/meta/exif}exifAttrs"/>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {

    })
    public static class GpsIfd {

        @XmlElement(name = "GPSVersionID", required = true)
        protected StringType gpsVersionID;
        @XmlElement(name = "GPSLatitudeRef")
        protected GpsLatitudeRefType gpsLatitudeRef;
        @XmlElement(name = "GPSLatitude")
        protected GpsLatitudeType gpsLatitude;
        @XmlElement(name = "GPSLongitudeRef")
        protected GpsLongitudeRefType gpsLongitudeRef;
        @XmlElement(name = "GPSLongitude")
        protected GpsLongitudeType gpsLongitude;
        @XmlElement(name = "GPSAltitudeRef")
        protected GpsAltitudeRefType gpsAltitudeRef;
        @XmlElement(name = "GPSAltitude")
        protected NonNegativeRealType gpsAltitude;
        @XmlElement(name = "GPSTimeStamp")
        protected TimeType gpsTimeStamp;
        @XmlElement(name = "GPSSatellites")
        protected StringType gpsSatellites;
        @XmlElement(name = "GPSStatus")
        protected GpsStatusType gpsStatus;
        @XmlElement(name = "GPSMeasureMode")
        protected GpsMeasureModeType gpsMeasureMode;
        @XmlElement(name = "GPSDOP")
        protected NonNegativeRealType gpsdop;
        @XmlElement(name = "GPSSpeedRef")
        protected GpsSpeedRefType gpsSpeedRef;
        @XmlElement(name = "GPSSpeed")
        protected NonNegativeRealType gpsSpeed;
        @XmlElement(name = "GPSTrackRef")
        protected GpsDirectionType gpsTrackRef;
        @XmlElement(name = "GPSTrack")
        protected GpsBearingType gpsTrack;
        @XmlElement(name = "GPSImgDirectionRef")
        protected GpsDirectionType gpsImgDirectionRef;
        @XmlElement(name = "GPSImgDirection")
        protected GpsBearingType gpsImgDirection;
        @XmlElement(name = "GPSMapDatum")
        protected StringType gpsMapDatum;
        @XmlElement(name = "GPSDestLatitudeRef")
        protected GpsLatitudeRefType gpsDestLatitudeRef;
        @XmlElement(name = "GPSDestLatitude")
        protected GpsLatitudeType gpsDestLatitude;
        @XmlElement(name = "GPSDestLongitudeRef")
        protected GpsLongitudeRefType gpsDestLongitudeRef;
        @XmlElement(name = "GPSDestLongitude")
        protected GpsLongitudeType gpsDestLongitude;
        @XmlElement(name = "GPSDestBearingRef")
        protected GpsDirectionType gpsDestBearingRef;
        @XmlElement(name = "GPSDestBearing")
        protected GpsBearingType gpsDestBearing;
        @XmlElement(name = "GPSDestDistanceRef")
        protected GpsDistanceRefType gpsDestDistanceRef;
        @XmlElement(name = "GPSDestDistance")
        protected NonNegativeRealType gpsDestDistance;
        @XmlElement(name = "GPSProcessingMethod")
        protected StringType gpsProcessingMethod;
        @XmlElement(name = "GPSAreaInformation")
        protected StringType gpsAreaInformation;
        @XmlElement(name = "GPSDateStamp")
        protected DateType gpsDateStamp;
        @XmlElement(name = "GPSDifferential")
        protected GpsDifferentialType gpsDifferential;
        @XmlElement(name = "GPSField1")
        protected SingleFieldType gpsField1;
        @XmlElement(name = "GPSField2")
        protected SingleFieldType gpsField2;
        @XmlElement(name = "GPSField3")
        protected RepeatedFieldType gpsField3;
        @XmlAttribute(name = "tag", required = true)
        @XmlSchemaType(name = "nonNegativeInteger")
        protected BigInteger tag;

        /**
         * Obtient la valeur de la propriété gpsVersionID.
         * 
         * @return
         *     possible object is
         *     {@link StringType }
         *     
         */
        public StringType getGPSVersionID() {
            return gpsVersionID;
        }

        /**
         * Définit la valeur de la propriété gpsVersionID.
         * 
         * @param value
         *     allowed object is
         *     {@link StringType }
         *     
         */
        public void setGPSVersionID(StringType value) {
            this.gpsVersionID = value;
        }

        /**
         * Obtient la valeur de la propriété gpsLatitudeRef.
         * 
         * @return
         *     possible object is
         *     {@link GpsLatitudeRefType }
         *     
         */
        public GpsLatitudeRefType getGPSLatitudeRef() {
            return gpsLatitudeRef;
        }

        /**
         * Définit la valeur de la propriété gpsLatitudeRef.
         * 
         * @param value
         *     allowed object is
         *     {@link GpsLatitudeRefType }
         *     
         */
        public void setGPSLatitudeRef(GpsLatitudeRefType value) {
            this.gpsLatitudeRef = value;
        }

        /**
         * Obtient la valeur de la propriété gpsLatitude.
         * 
         * @return
         *     possible object is
         *     {@link GpsLatitudeType }
         *     
         */
        public GpsLatitudeType getGPSLatitude() {
            return gpsLatitude;
        }

        /**
         * Définit la valeur de la propriété gpsLatitude.
         * 
         * @param value
         *     allowed object is
         *     {@link GpsLatitudeType }
         *     
         */
        public void setGPSLatitude(GpsLatitudeType value) {
            this.gpsLatitude = value;
        }

        /**
         * Obtient la valeur de la propriété gpsLongitudeRef.
         * 
         * @return
         *     possible object is
         *     {@link GpsLongitudeRefType }
         *     
         */
        public GpsLongitudeRefType getGPSLongitudeRef() {
            return gpsLongitudeRef;
        }

        /**
         * Définit la valeur de la propriété gpsLongitudeRef.
         * 
         * @param value
         *     allowed object is
         *     {@link GpsLongitudeRefType }
         *     
         */
        public void setGPSLongitudeRef(GpsLongitudeRefType value) {
            this.gpsLongitudeRef = value;
        }

        /**
         * Obtient la valeur de la propriété gpsLongitude.
         * 
         * @return
         *     possible object is
         *     {@link GpsLongitudeType }
         *     
         */
        public GpsLongitudeType getGPSLongitude() {
            return gpsLongitude;
        }

        /**
         * Définit la valeur de la propriété gpsLongitude.
         * 
         * @param value
         *     allowed object is
         *     {@link GpsLongitudeType }
         *     
         */
        public void setGPSLongitude(GpsLongitudeType value) {
            this.gpsLongitude = value;
        }

        /**
         * Obtient la valeur de la propriété gpsAltitudeRef.
         * 
         * @return
         *     possible object is
         *     {@link GpsAltitudeRefType }
         *     
         */
        public GpsAltitudeRefType getGPSAltitudeRef() {
            return gpsAltitudeRef;
        }

        /**
         * Définit la valeur de la propriété gpsAltitudeRef.
         * 
         * @param value
         *     allowed object is
         *     {@link GpsAltitudeRefType }
         *     
         */
        public void setGPSAltitudeRef(GpsAltitudeRefType value) {
            this.gpsAltitudeRef = value;
        }

        /**
         * Obtient la valeur de la propriété gpsAltitude.
         * 
         * @return
         *     possible object is
         *     {@link NonNegativeRealType }
         *     
         */
        public NonNegativeRealType getGPSAltitude() {
            return gpsAltitude;
        }

        /**
         * Définit la valeur de la propriété gpsAltitude.
         * 
         * @param value
         *     allowed object is
         *     {@link NonNegativeRealType }
         *     
         */
        public void setGPSAltitude(NonNegativeRealType value) {
            this.gpsAltitude = value;
        }

        /**
         * Obtient la valeur de la propriété gpsTimeStamp.
         * 
         * @return
         *     possible object is
         *     {@link TimeType }
         *     
         */
        public TimeType getGPSTimeStamp() {
            return gpsTimeStamp;
        }

        /**
         * Définit la valeur de la propriété gpsTimeStamp.
         * 
         * @param value
         *     allowed object is
         *     {@link TimeType }
         *     
         */
        public void setGPSTimeStamp(TimeType value) {
            this.gpsTimeStamp = value;
        }

        /**
         * Obtient la valeur de la propriété gpsSatellites.
         * 
         * @return
         *     possible object is
         *     {@link StringType }
         *     
         */
        public StringType getGPSSatellites() {
            return gpsSatellites;
        }

        /**
         * Définit la valeur de la propriété gpsSatellites.
         * 
         * @param value
         *     allowed object is
         *     {@link StringType }
         *     
         */
        public void setGPSSatellites(StringType value) {
            this.gpsSatellites = value;
        }

        /**
         * Obtient la valeur de la propriété gpsStatus.
         * 
         * @return
         *     possible object is
         *     {@link GpsStatusType }
         *     
         */
        public GpsStatusType getGPSStatus() {
            return gpsStatus;
        }

        /**
         * Définit la valeur de la propriété gpsStatus.
         * 
         * @param value
         *     allowed object is
         *     {@link GpsStatusType }
         *     
         */
        public void setGPSStatus(GpsStatusType value) {
            this.gpsStatus = value;
        }

        /**
         * Obtient la valeur de la propriété gpsMeasureMode.
         * 
         * @return
         *     possible object is
         *     {@link GpsMeasureModeType }
         *     
         */
        public GpsMeasureModeType getGPSMeasureMode() {
            return gpsMeasureMode;
        }

        /**
         * Définit la valeur de la propriété gpsMeasureMode.
         * 
         * @param value
         *     allowed object is
         *     {@link GpsMeasureModeType }
         *     
         */
        public void setGPSMeasureMode(GpsMeasureModeType value) {
            this.gpsMeasureMode = value;
        }

        /**
         * Obtient la valeur de la propriété gpsdop.
         * 
         * @return
         *     possible object is
         *     {@link NonNegativeRealType }
         *     
         */
        public NonNegativeRealType getGPSDOP() {
            return gpsdop;
        }

        /**
         * Définit la valeur de la propriété gpsdop.
         * 
         * @param value
         *     allowed object is
         *     {@link NonNegativeRealType }
         *     
         */
        public void setGPSDOP(NonNegativeRealType value) {
            this.gpsdop = value;
        }

        /**
         * Obtient la valeur de la propriété gpsSpeedRef.
         * 
         * @return
         *     possible object is
         *     {@link GpsSpeedRefType }
         *     
         */
        public GpsSpeedRefType getGPSSpeedRef() {
            return gpsSpeedRef;
        }

        /**
         * Définit la valeur de la propriété gpsSpeedRef.
         * 
         * @param value
         *     allowed object is
         *     {@link GpsSpeedRefType }
         *     
         */
        public void setGPSSpeedRef(GpsSpeedRefType value) {
            this.gpsSpeedRef = value;
        }

        /**
         * Obtient la valeur de la propriété gpsSpeed.
         * 
         * @return
         *     possible object is
         *     {@link NonNegativeRealType }
         *     
         */
        public NonNegativeRealType getGPSSpeed() {
            return gpsSpeed;
        }

        /**
         * Définit la valeur de la propriété gpsSpeed.
         * 
         * @param value
         *     allowed object is
         *     {@link NonNegativeRealType }
         *     
         */
        public void setGPSSpeed(NonNegativeRealType value) {
            this.gpsSpeed = value;
        }

        /**
         * Obtient la valeur de la propriété gpsTrackRef.
         * 
         * @return
         *     possible object is
         *     {@link GpsDirectionType }
         *     
         */
        public GpsDirectionType getGPSTrackRef() {
            return gpsTrackRef;
        }

        /**
         * Définit la valeur de la propriété gpsTrackRef.
         * 
         * @param value
         *     allowed object is
         *     {@link GpsDirectionType }
         *     
         */
        public void setGPSTrackRef(GpsDirectionType value) {
            this.gpsTrackRef = value;
        }

        /**
         * Obtient la valeur de la propriété gpsTrack.
         * 
         * @return
         *     possible object is
         *     {@link GpsBearingType }
         *     
         */
        public GpsBearingType getGPSTrack() {
            return gpsTrack;
        }

        /**
         * Définit la valeur de la propriété gpsTrack.
         * 
         * @param value
         *     allowed object is
         *     {@link GpsBearingType }
         *     
         */
        public void setGPSTrack(GpsBearingType value) {
            this.gpsTrack = value;
        }

        /**
         * Obtient la valeur de la propriété gpsImgDirectionRef.
         * 
         * @return
         *     possible object is
         *     {@link GpsDirectionType }
         *     
         */
        public GpsDirectionType getGPSImgDirectionRef() {
            return gpsImgDirectionRef;
        }

        /**
         * Définit la valeur de la propriété gpsImgDirectionRef.
         * 
         * @param value
         *     allowed object is
         *     {@link GpsDirectionType }
         *     
         */
        public void setGPSImgDirectionRef(GpsDirectionType value) {
            this.gpsImgDirectionRef = value;
        }

        /**
         * Obtient la valeur de la propriété gpsImgDirection.
         * 
         * @return
         *     possible object is
         *     {@link GpsBearingType }
         *     
         */
        public GpsBearingType getGPSImgDirection() {
            return gpsImgDirection;
        }

        /**
         * Définit la valeur de la propriété gpsImgDirection.
         * 
         * @param value
         *     allowed object is
         *     {@link GpsBearingType }
         *     
         */
        public void setGPSImgDirection(GpsBearingType value) {
            this.gpsImgDirection = value;
        }

        /**
         * Obtient la valeur de la propriété gpsMapDatum.
         * 
         * @return
         *     possible object is
         *     {@link StringType }
         *     
         */
        public StringType getGPSMapDatum() {
            return gpsMapDatum;
        }

        /**
         * Définit la valeur de la propriété gpsMapDatum.
         * 
         * @param value
         *     allowed object is
         *     {@link StringType }
         *     
         */
        public void setGPSMapDatum(StringType value) {
            this.gpsMapDatum = value;
        }

        /**
         * Obtient la valeur de la propriété gpsDestLatitudeRef.
         * 
         * @return
         *     possible object is
         *     {@link GpsLatitudeRefType }
         *     
         */
        public GpsLatitudeRefType getGPSDestLatitudeRef() {
            return gpsDestLatitudeRef;
        }

        /**
         * Définit la valeur de la propriété gpsDestLatitudeRef.
         * 
         * @param value
         *     allowed object is
         *     {@link GpsLatitudeRefType }
         *     
         */
        public void setGPSDestLatitudeRef(GpsLatitudeRefType value) {
            this.gpsDestLatitudeRef = value;
        }

        /**
         * Obtient la valeur de la propriété gpsDestLatitude.
         * 
         * @return
         *     possible object is
         *     {@link GpsLatitudeType }
         *     
         */
        public GpsLatitudeType getGPSDestLatitude() {
            return gpsDestLatitude;
        }

        /**
         * Définit la valeur de la propriété gpsDestLatitude.
         * 
         * @param value
         *     allowed object is
         *     {@link GpsLatitudeType }
         *     
         */
        public void setGPSDestLatitude(GpsLatitudeType value) {
            this.gpsDestLatitude = value;
        }

        /**
         * Obtient la valeur de la propriété gpsDestLongitudeRef.
         * 
         * @return
         *     possible object is
         *     {@link GpsLongitudeRefType }
         *     
         */
        public GpsLongitudeRefType getGPSDestLongitudeRef() {
            return gpsDestLongitudeRef;
        }

        /**
         * Définit la valeur de la propriété gpsDestLongitudeRef.
         * 
         * @param value
         *     allowed object is
         *     {@link GpsLongitudeRefType }
         *     
         */
        public void setGPSDestLongitudeRef(GpsLongitudeRefType value) {
            this.gpsDestLongitudeRef = value;
        }

        /**
         * Obtient la valeur de la propriété gpsDestLongitude.
         * 
         * @return
         *     possible object is
         *     {@link GpsLongitudeType }
         *     
         */
        public GpsLongitudeType getGPSDestLongitude() {
            return gpsDestLongitude;
        }

        /**
         * Définit la valeur de la propriété gpsDestLongitude.
         * 
         * @param value
         *     allowed object is
         *     {@link GpsLongitudeType }
         *     
         */
        public void setGPSDestLongitude(GpsLongitudeType value) {
            this.gpsDestLongitude = value;
        }

        /**
         * Obtient la valeur de la propriété gpsDestBearingRef.
         * 
         * @return
         *     possible object is
         *     {@link GpsDirectionType }
         *     
         */
        public GpsDirectionType getGPSDestBearingRef() {
            return gpsDestBearingRef;
        }

        /**
         * Définit la valeur de la propriété gpsDestBearingRef.
         * 
         * @param value
         *     allowed object is
         *     {@link GpsDirectionType }
         *     
         */
        public void setGPSDestBearingRef(GpsDirectionType value) {
            this.gpsDestBearingRef = value;
        }

        /**
         * Obtient la valeur de la propriété gpsDestBearing.
         * 
         * @return
         *     possible object is
         *     {@link GpsBearingType }
         *     
         */
        public GpsBearingType getGPSDestBearing() {
            return gpsDestBearing;
        }

        /**
         * Définit la valeur de la propriété gpsDestBearing.
         * 
         * @param value
         *     allowed object is
         *     {@link GpsBearingType }
         *     
         */
        public void setGPSDestBearing(GpsBearingType value) {
            this.gpsDestBearing = value;
        }

        /**
         * Obtient la valeur de la propriété gpsDestDistanceRef.
         * 
         * @return
         *     possible object is
         *     {@link GpsDistanceRefType }
         *     
         */
        public GpsDistanceRefType getGPSDestDistanceRef() {
            return gpsDestDistanceRef;
        }

        /**
         * Définit la valeur de la propriété gpsDestDistanceRef.
         * 
         * @param value
         *     allowed object is
         *     {@link GpsDistanceRefType }
         *     
         */
        public void setGPSDestDistanceRef(GpsDistanceRefType value) {
            this.gpsDestDistanceRef = value;
        }

        /**
         * Obtient la valeur de la propriété gpsDestDistance.
         * 
         * @return
         *     possible object is
         *     {@link NonNegativeRealType }
         *     
         */
        public NonNegativeRealType getGPSDestDistance() {
            return gpsDestDistance;
        }

        /**
         * Définit la valeur de la propriété gpsDestDistance.
         * 
         * @param value
         *     allowed object is
         *     {@link NonNegativeRealType }
         *     
         */
        public void setGPSDestDistance(NonNegativeRealType value) {
            this.gpsDestDistance = value;
        }

        /**
         * Obtient la valeur de la propriété gpsProcessingMethod.
         * 
         * @return
         *     possible object is
         *     {@link StringType }
         *     
         */
        public StringType getGPSProcessingMethod() {
            return gpsProcessingMethod;
        }

        /**
         * Définit la valeur de la propriété gpsProcessingMethod.
         * 
         * @param value
         *     allowed object is
         *     {@link StringType }
         *     
         */
        public void setGPSProcessingMethod(StringType value) {
            this.gpsProcessingMethod = value;
        }

        /**
         * Obtient la valeur de la propriété gpsAreaInformation.
         * 
         * @return
         *     possible object is
         *     {@link StringType }
         *     
         */
        public StringType getGPSAreaInformation() {
            return gpsAreaInformation;
        }

        /**
         * Définit la valeur de la propriété gpsAreaInformation.
         * 
         * @param value
         *     allowed object is
         *     {@link StringType }
         *     
         */
        public void setGPSAreaInformation(StringType value) {
            this.gpsAreaInformation = value;
        }

        /**
         * Obtient la valeur de la propriété gpsDateStamp.
         * 
         * @return
         *     possible object is
         *     {@link DateType }
         *     
         */
        public DateType getGPSDateStamp() {
            return gpsDateStamp;
        }

        /**
         * Définit la valeur de la propriété gpsDateStamp.
         * 
         * @param value
         *     allowed object is
         *     {@link DateType }
         *     
         */
        public void setGPSDateStamp(DateType value) {
            this.gpsDateStamp = value;
        }

        /**
         * Obtient la valeur de la propriété gpsDifferential.
         * 
         * @return
         *     possible object is
         *     {@link GpsDifferentialType }
         *     
         */
        public GpsDifferentialType getGPSDifferential() {
            return gpsDifferential;
        }

        /**
         * Définit la valeur de la propriété gpsDifferential.
         * 
         * @param value
         *     allowed object is
         *     {@link GpsDifferentialType }
         *     
         */
        public void setGPSDifferential(GpsDifferentialType value) {
            this.gpsDifferential = value;
        }

        /**
         * Obtient la valeur de la propriété gpsField1.
         * 
         * @return
         *     possible object is
         *     {@link SingleFieldType }
         *     
         */
        public SingleFieldType getGPSField1() {
            return gpsField1;
        }

        /**
         * Définit la valeur de la propriété gpsField1.
         * 
         * @param value
         *     allowed object is
         *     {@link SingleFieldType }
         *     
         */
        public void setGPSField1(SingleFieldType value) {
            this.gpsField1 = value;
        }

        /**
         * Obtient la valeur de la propriété gpsField2.
         * 
         * @return
         *     possible object is
         *     {@link SingleFieldType }
         *     
         */
        public SingleFieldType getGPSField2() {
            return gpsField2;
        }

        /**
         * Définit la valeur de la propriété gpsField2.
         * 
         * @param value
         *     allowed object is
         *     {@link SingleFieldType }
         *     
         */
        public void setGPSField2(SingleFieldType value) {
            this.gpsField2 = value;
        }

        /**
         * Obtient la valeur de la propriété gpsField3.
         * 
         * @return
         *     possible object is
         *     {@link RepeatedFieldType }
         *     
         */
        public RepeatedFieldType getGPSField3() {
            return gpsField3;
        }

        /**
         * Définit la valeur de la propriété gpsField3.
         * 
         * @param value
         *     allowed object is
         *     {@link RepeatedFieldType }
         *     
         */
        public void setGPSField3(RepeatedFieldType value) {
            this.gpsField3 = value;
        }

        /**
         * Obtient la valeur de la propriété tag.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getTag() {
            return tag;
        }

        /**
         * Définit la valeur de la propriété tag.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setTag(BigInteger value) {
            this.tag = value;
        }

    }


    /**
     * <p>Classe Java pour anonymous complex type.
     * 
     * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;all>
     *         &lt;element name="InteroperabilityIndex" type="{http://xmlns.oracle.com/ord/meta/exif}interoperabilityType" minOccurs="0"/>
     *       &lt;/all>
     *       &lt;attGroup ref="{http://xmlns.oracle.com/ord/meta/exif}exifAttrs"/>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {

    })
    public static class InteroperabilityIfd {

        @XmlElement(name = "InteroperabilityIndex")
        protected InteroperabilityType interoperabilityIndex;
        @XmlAttribute(name = "tag", required = true)
        @XmlSchemaType(name = "nonNegativeInteger")
        protected BigInteger tag;

        /**
         * Obtient la valeur de la propriété interoperabilityIndex.
         * 
         * @return
         *     possible object is
         *     {@link InteroperabilityType }
         *     
         */
        public InteroperabilityType getInteroperabilityIndex() {
            return interoperabilityIndex;
        }

        /**
         * Définit la valeur de la propriété interoperabilityIndex.
         * 
         * @param value
         *     allowed object is
         *     {@link InteroperabilityType }
         *     
         */
        public void setInteroperabilityIndex(InteroperabilityType value) {
            this.interoperabilityIndex = value;
        }

        /**
         * Obtient la valeur de la propriété tag.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getTag() {
            return tag;
        }

        /**
         * Définit la valeur de la propriété tag.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setTag(BigInteger value) {
            this.tag = value;
        }

    }


    /**
     * <p>Classe Java pour anonymous complex type.
     * 
     * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;all>
     *         &lt;element name="ImageWidth" type="{http://xmlns.oracle.com/ord/meta/exif}positiveIntegerType" minOccurs="0"/>
     *         &lt;element name="ImageLength" type="{http://xmlns.oracle.com/ord/meta/exif}positiveIntegerType" minOccurs="0"/>
     *         &lt;element name="BitsPerSample" type="{http://xmlns.oracle.com/ord/meta/exif}bitsPerSampleType" minOccurs="0"/>
     *         &lt;element name="Compression" type="{http://xmlns.oracle.com/ord/meta/exif}compressionType" minOccurs="0"/>
     *         &lt;element name="PhotometricInterpretation" type="{http://xmlns.oracle.com/ord/meta/exif}photometricInterpretationType" minOccurs="0"/>
     *         &lt;element name="Orientation" type="{http://xmlns.oracle.com/ord/meta/exif}orientationType" minOccurs="0"/>
     *         &lt;element name="SamplesPerPixel" type="{http://xmlns.oracle.com/ord/meta/exif}positiveIntegerType" minOccurs="0"/>
     *         &lt;element name="PlanarConfiguration" type="{http://xmlns.oracle.com/ord/meta/exif}planarConfigurationType" minOccurs="0"/>
     *         &lt;element name="YCbCrSubSampling" type="{http://xmlns.oracle.com/ord/meta/exif}yCbCrSubSamplingType" minOccurs="0"/>
     *         &lt;element name="YCbCrPositioning" type="{http://xmlns.oracle.com/ord/meta/exif}yCbCrPositioningType" minOccurs="0"/>
     *         &lt;element name="XResolution" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeRealType" minOccurs="0"/>
     *         &lt;element name="YResolution" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeRealType" minOccurs="0"/>
     *         &lt;element name="ResolutionUnit" type="{http://xmlns.oracle.com/ord/meta/exif}resolutionType" minOccurs="0"/>
     *         &lt;element name="StripOffsets" type="{http://xmlns.oracle.com/ord/meta/exif}stripOffsetsType" minOccurs="0"/>
     *         &lt;element name="RowsPerStrip" type="{http://xmlns.oracle.com/ord/meta/exif}positiveIntegerType" minOccurs="0"/>
     *         &lt;element name="StripByteCounts" type="{http://xmlns.oracle.com/ord/meta/exif}stripByteCountsType" minOccurs="0"/>
     *         &lt;element name="JPEGInterChangeFormat" type="{http://xmlns.oracle.com/ord/meta/exif}positiveIntegerType" minOccurs="0"/>
     *         &lt;element name="JPEGInterChangeFormatLength" type="{http://xmlns.oracle.com/ord/meta/exif}positiveIntegerType" minOccurs="0"/>
     *         &lt;element name="TransferFunction" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
     *         &lt;element name="WhitePoint" type="{http://xmlns.oracle.com/ord/meta/exif}whitePointType" minOccurs="0"/>
     *         &lt;element name="PrimaryChromaticities" type="{http://xmlns.oracle.com/ord/meta/exif}primaryChromaticitiesType" minOccurs="0"/>
     *         &lt;element name="YCbCrCoefficients" type="{http://xmlns.oracle.com/ord/meta/exif}yCbCrCoefficientsType" minOccurs="0"/>
     *         &lt;element name="ReferenceBlackWhite" type="{http://xmlns.oracle.com/ord/meta/exif}primaryChromaticitiesType" minOccurs="0"/>
     *         &lt;element name="DateTime" type="{http://xmlns.oracle.com/ord/meta/exif}dateTimeType" minOccurs="0"/>
     *         &lt;element name="ImageDescription" type="{http://xmlns.oracle.com/ord/meta/exif}stringType" minOccurs="0"/>
     *         &lt;element name="Make" type="{http://xmlns.oracle.com/ord/meta/exif}stringType" minOccurs="0"/>
     *         &lt;element name="Model" type="{http://xmlns.oracle.com/ord/meta/exif}stringType" minOccurs="0"/>
     *         &lt;element name="Software" type="{http://xmlns.oracle.com/ord/meta/exif}stringType" minOccurs="0"/>
     *         &lt;element name="Artist" type="{http://xmlns.oracle.com/ord/meta/exif}stringType" minOccurs="0"/>
     *         &lt;element name="Copyright" type="{http://xmlns.oracle.com/ord/meta/exif}stringType" minOccurs="0"/>
     *         &lt;element name="TiffField1" type="{http://xmlns.oracle.com/ord/meta/exif}singleFieldType" minOccurs="0"/>
     *         &lt;element name="TiffField2" type="{http://xmlns.oracle.com/ord/meta/exif}singleFieldType" minOccurs="0"/>
     *         &lt;element name="TiffField3" type="{http://xmlns.oracle.com/ord/meta/exif}repeatedFieldType" minOccurs="0"/>
     *       &lt;/all>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {

    })
    public static class TiffIfd {

        @XmlElement(name = "ImageWidth")
        protected PositiveIntegerType imageWidth;
        @XmlElement(name = "ImageLength")
        protected PositiveIntegerType imageLength;
        @XmlElement(name = "BitsPerSample")
        protected BitsPerSampleType bitsPerSample;
        @XmlElement(name = "Compression")
        protected CompressionType compression;
        @XmlElement(name = "PhotometricInterpretation")
        protected PhotometricInterpretationType photometricInterpretation;
        @XmlElement(name = "Orientation")
        protected OrientationType orientation;
        @XmlElement(name = "SamplesPerPixel")
        protected PositiveIntegerType samplesPerPixel;
        @XmlElement(name = "PlanarConfiguration")
        protected PlanarConfigurationType planarConfiguration;
        @XmlElement(name = "YCbCrSubSampling")
        protected YCbCrSubSamplingType yCbCrSubSampling;
        @XmlElement(name = "YCbCrPositioning")
        protected YCbCrPositioningType yCbCrPositioning;
        @XmlElement(name = "XResolution")
        protected NonNegativeRealType xResolution;
        @XmlElement(name = "YResolution")
        protected NonNegativeRealType yResolution;
        @XmlElement(name = "ResolutionUnit")
        protected ResolutionType resolutionUnit;
        @XmlElement(name = "StripOffsets")
        protected StripOffsetsType stripOffsets;
        @XmlElement(name = "RowsPerStrip")
        protected PositiveIntegerType rowsPerStrip;
        @XmlElement(name = "StripByteCounts")
        protected StripByteCountsType stripByteCounts;
        @XmlElement(name = "JPEGInterChangeFormat")
        protected PositiveIntegerType jpegInterChangeFormat;
        @XmlElement(name = "JPEGInterChangeFormatLength")
        protected PositiveIntegerType jpegInterChangeFormatLength;
        @XmlElement(name = "TransferFunction")
        protected Object transferFunction;
        @XmlElement(name = "WhitePoint")
        protected WhitePointType whitePoint;
        @XmlElement(name = "PrimaryChromaticities")
        protected PrimaryChromaticitiesType primaryChromaticities;
        @XmlElement(name = "YCbCrCoefficients")
        protected YCbCrCoefficientsType yCbCrCoefficients;
        @XmlElement(name = "ReferenceBlackWhite")
        protected PrimaryChromaticitiesType referenceBlackWhite;
        @XmlElement(name = "DateTime")
        protected DateTimeType dateTime;
        @XmlElement(name = "ImageDescription")
        protected StringType imageDescription;
        @XmlElement(name = "Make")
        protected StringType make;
        @XmlElement(name = "Model")
        protected StringType model;
        @XmlElement(name = "Software")
        protected StringType software;
        @XmlElement(name = "Artist")
        protected StringType artist;
        @XmlElement(name = "Copyright")
        protected StringType copyright;
        @XmlElement(name = "TiffField1")
        protected SingleFieldType tiffField1;
        @XmlElement(name = "TiffField2")
        protected SingleFieldType tiffField2;
        @XmlElement(name = "TiffField3")
        protected RepeatedFieldType tiffField3;

        /**
         * Obtient la valeur de la propriété imageWidth.
         * 
         * @return
         *     possible object is
         *     {@link PositiveIntegerType }
         *     
         */
        public PositiveIntegerType getImageWidth() {
            return imageWidth;
        }

        /**
         * Définit la valeur de la propriété imageWidth.
         * 
         * @param value
         *     allowed object is
         *     {@link PositiveIntegerType }
         *     
         */
        public void setImageWidth(PositiveIntegerType value) {
            this.imageWidth = value;
        }

        /**
         * Obtient la valeur de la propriété imageLength.
         * 
         * @return
         *     possible object is
         *     {@link PositiveIntegerType }
         *     
         */
        public PositiveIntegerType getImageLength() {
            return imageLength;
        }

        /**
         * Définit la valeur de la propriété imageLength.
         * 
         * @param value
         *     allowed object is
         *     {@link PositiveIntegerType }
         *     
         */
        public void setImageLength(PositiveIntegerType value) {
            this.imageLength = value;
        }

        /**
         * Obtient la valeur de la propriété bitsPerSample.
         * 
         * @return
         *     possible object is
         *     {@link BitsPerSampleType }
         *     
         */
        public BitsPerSampleType getBitsPerSample() {
            return bitsPerSample;
        }

        /**
         * Définit la valeur de la propriété bitsPerSample.
         * 
         * @param value
         *     allowed object is
         *     {@link BitsPerSampleType }
         *     
         */
        public void setBitsPerSample(BitsPerSampleType value) {
            this.bitsPerSample = value;
        }

        /**
         * Obtient la valeur de la propriété compression.
         * 
         * @return
         *     possible object is
         *     {@link CompressionType }
         *     
         */
        public CompressionType getCompression() {
            return compression;
        }

        /**
         * Définit la valeur de la propriété compression.
         * 
         * @param value
         *     allowed object is
         *     {@link CompressionType }
         *     
         */
        public void setCompression(CompressionType value) {
            this.compression = value;
        }

        /**
         * Obtient la valeur de la propriété photometricInterpretation.
         * 
         * @return
         *     possible object is
         *     {@link PhotometricInterpretationType }
         *     
         */
        public PhotometricInterpretationType getPhotometricInterpretation() {
            return photometricInterpretation;
        }

        /**
         * Définit la valeur de la propriété photometricInterpretation.
         * 
         * @param value
         *     allowed object is
         *     {@link PhotometricInterpretationType }
         *     
         */
        public void setPhotometricInterpretation(PhotometricInterpretationType value) {
            this.photometricInterpretation = value;
        }

        /**
         * Obtient la valeur de la propriété orientation.
         * 
         * @return
         *     possible object is
         *     {@link OrientationType }
         *     
         */
        public OrientationType getOrientation() {
            return orientation;
        }

        /**
         * Définit la valeur de la propriété orientation.
         * 
         * @param value
         *     allowed object is
         *     {@link OrientationType }
         *     
         */
        public void setOrientation(OrientationType value) {
            this.orientation = value;
        }

        /**
         * Obtient la valeur de la propriété samplesPerPixel.
         * 
         * @return
         *     possible object is
         *     {@link PositiveIntegerType }
         *     
         */
        public PositiveIntegerType getSamplesPerPixel() {
            return samplesPerPixel;
        }

        /**
         * Définit la valeur de la propriété samplesPerPixel.
         * 
         * @param value
         *     allowed object is
         *     {@link PositiveIntegerType }
         *     
         */
        public void setSamplesPerPixel(PositiveIntegerType value) {
            this.samplesPerPixel = value;
        }

        /**
         * Obtient la valeur de la propriété planarConfiguration.
         * 
         * @return
         *     possible object is
         *     {@link PlanarConfigurationType }
         *     
         */
        public PlanarConfigurationType getPlanarConfiguration() {
            return planarConfiguration;
        }

        /**
         * Définit la valeur de la propriété planarConfiguration.
         * 
         * @param value
         *     allowed object is
         *     {@link PlanarConfigurationType }
         *     
         */
        public void setPlanarConfiguration(PlanarConfigurationType value) {
            this.planarConfiguration = value;
        }

        /**
         * Obtient la valeur de la propriété yCbCrSubSampling.
         * 
         * @return
         *     possible object is
         *     {@link YCbCrSubSamplingType }
         *     
         */
        public YCbCrSubSamplingType getYCbCrSubSampling() {
            return yCbCrSubSampling;
        }

        /**
         * Définit la valeur de la propriété yCbCrSubSampling.
         * 
         * @param value
         *     allowed object is
         *     {@link YCbCrSubSamplingType }
         *     
         */
        public void setYCbCrSubSampling(YCbCrSubSamplingType value) {
            this.yCbCrSubSampling = value;
        }

        /**
         * Obtient la valeur de la propriété yCbCrPositioning.
         * 
         * @return
         *     possible object is
         *     {@link YCbCrPositioningType }
         *     
         */
        public YCbCrPositioningType getYCbCrPositioning() {
            return yCbCrPositioning;
        }

        /**
         * Définit la valeur de la propriété yCbCrPositioning.
         * 
         * @param value
         *     allowed object is
         *     {@link YCbCrPositioningType }
         *     
         */
        public void setYCbCrPositioning(YCbCrPositioningType value) {
            this.yCbCrPositioning = value;
        }

        /**
         * Obtient la valeur de la propriété xResolution.
         * 
         * @return
         *     possible object is
         *     {@link NonNegativeRealType }
         *     
         */
        public NonNegativeRealType getXResolution() {
            return xResolution;
        }

        /**
         * Définit la valeur de la propriété xResolution.
         * 
         * @param value
         *     allowed object is
         *     {@link NonNegativeRealType }
         *     
         */
        public void setXResolution(NonNegativeRealType value) {
            this.xResolution = value;
        }

        /**
         * Obtient la valeur de la propriété yResolution.
         * 
         * @return
         *     possible object is
         *     {@link NonNegativeRealType }
         *     
         */
        public NonNegativeRealType getYResolution() {
            return yResolution;
        }

        /**
         * Définit la valeur de la propriété yResolution.
         * 
         * @param value
         *     allowed object is
         *     {@link NonNegativeRealType }
         *     
         */
        public void setYResolution(NonNegativeRealType value) {
            this.yResolution = value;
        }

        /**
         * Obtient la valeur de la propriété resolutionUnit.
         * 
         * @return
         *     possible object is
         *     {@link ResolutionType }
         *     
         */
        public ResolutionType getResolutionUnit() {
            return resolutionUnit;
        }

        /**
         * Définit la valeur de la propriété resolutionUnit.
         * 
         * @param value
         *     allowed object is
         *     {@link ResolutionType }
         *     
         */
        public void setResolutionUnit(ResolutionType value) {
            this.resolutionUnit = value;
        }

        /**
         * Obtient la valeur de la propriété stripOffsets.
         * 
         * @return
         *     possible object is
         *     {@link StripOffsetsType }
         *     
         */
        public StripOffsetsType getStripOffsets() {
            return stripOffsets;
        }

        /**
         * Définit la valeur de la propriété stripOffsets.
         * 
         * @param value
         *     allowed object is
         *     {@link StripOffsetsType }
         *     
         */
        public void setStripOffsets(StripOffsetsType value) {
            this.stripOffsets = value;
        }

        /**
         * Obtient la valeur de la propriété rowsPerStrip.
         * 
         * @return
         *     possible object is
         *     {@link PositiveIntegerType }
         *     
         */
        public PositiveIntegerType getRowsPerStrip() {
            return rowsPerStrip;
        }

        /**
         * Définit la valeur de la propriété rowsPerStrip.
         * 
         * @param value
         *     allowed object is
         *     {@link PositiveIntegerType }
         *     
         */
        public void setRowsPerStrip(PositiveIntegerType value) {
            this.rowsPerStrip = value;
        }

        /**
         * Obtient la valeur de la propriété stripByteCounts.
         * 
         * @return
         *     possible object is
         *     {@link StripByteCountsType }
         *     
         */
        public StripByteCountsType getStripByteCounts() {
            return stripByteCounts;
        }

        /**
         * Définit la valeur de la propriété stripByteCounts.
         * 
         * @param value
         *     allowed object is
         *     {@link StripByteCountsType }
         *     
         */
        public void setStripByteCounts(StripByteCountsType value) {
            this.stripByteCounts = value;
        }

        /**
         * Obtient la valeur de la propriété jpegInterChangeFormat.
         * 
         * @return
         *     possible object is
         *     {@link PositiveIntegerType }
         *     
         */
        public PositiveIntegerType getJPEGInterChangeFormat() {
            return jpegInterChangeFormat;
        }

        /**
         * Définit la valeur de la propriété jpegInterChangeFormat.
         * 
         * @param value
         *     allowed object is
         *     {@link PositiveIntegerType }
         *     
         */
        public void setJPEGInterChangeFormat(PositiveIntegerType value) {
            this.jpegInterChangeFormat = value;
        }

        /**
         * Obtient la valeur de la propriété jpegInterChangeFormatLength.
         * 
         * @return
         *     possible object is
         *     {@link PositiveIntegerType }
         *     
         */
        public PositiveIntegerType getJPEGInterChangeFormatLength() {
            return jpegInterChangeFormatLength;
        }

        /**
         * Définit la valeur de la propriété jpegInterChangeFormatLength.
         * 
         * @param value
         *     allowed object is
         *     {@link PositiveIntegerType }
         *     
         */
        public void setJPEGInterChangeFormatLength(PositiveIntegerType value) {
            this.jpegInterChangeFormatLength = value;
        }

        /**
         * Obtient la valeur de la propriété transferFunction.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getTransferFunction() {
            return transferFunction;
        }

        /**
         * Définit la valeur de la propriété transferFunction.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setTransferFunction(Object value) {
            this.transferFunction = value;
        }

        /**
         * Obtient la valeur de la propriété whitePoint.
         * 
         * @return
         *     possible object is
         *     {@link WhitePointType }
         *     
         */
        public WhitePointType getWhitePoint() {
            return whitePoint;
        }

        /**
         * Définit la valeur de la propriété whitePoint.
         * 
         * @param value
         *     allowed object is
         *     {@link WhitePointType }
         *     
         */
        public void setWhitePoint(WhitePointType value) {
            this.whitePoint = value;
        }

        /**
         * Obtient la valeur de la propriété primaryChromaticities.
         * 
         * @return
         *     possible object is
         *     {@link PrimaryChromaticitiesType }
         *     
         */
        public PrimaryChromaticitiesType getPrimaryChromaticities() {
            return primaryChromaticities;
        }

        /**
         * Définit la valeur de la propriété primaryChromaticities.
         * 
         * @param value
         *     allowed object is
         *     {@link PrimaryChromaticitiesType }
         *     
         */
        public void setPrimaryChromaticities(PrimaryChromaticitiesType value) {
            this.primaryChromaticities = value;
        }

        /**
         * Obtient la valeur de la propriété yCbCrCoefficients.
         * 
         * @return
         *     possible object is
         *     {@link YCbCrCoefficientsType }
         *     
         */
        public YCbCrCoefficientsType getYCbCrCoefficients() {
            return yCbCrCoefficients;
        }

        /**
         * Définit la valeur de la propriété yCbCrCoefficients.
         * 
         * @param value
         *     allowed object is
         *     {@link YCbCrCoefficientsType }
         *     
         */
        public void setYCbCrCoefficients(YCbCrCoefficientsType value) {
            this.yCbCrCoefficients = value;
        }

        /**
         * Obtient la valeur de la propriété referenceBlackWhite.
         * 
         * @return
         *     possible object is
         *     {@link PrimaryChromaticitiesType }
         *     
         */
        public PrimaryChromaticitiesType getReferenceBlackWhite() {
            return referenceBlackWhite;
        }

        /**
         * Définit la valeur de la propriété referenceBlackWhite.
         * 
         * @param value
         *     allowed object is
         *     {@link PrimaryChromaticitiesType }
         *     
         */
        public void setReferenceBlackWhite(PrimaryChromaticitiesType value) {
            this.referenceBlackWhite = value;
        }

        /**
         * Obtient la valeur de la propriété dateTime.
         * 
         * @return
         *     possible object is
         *     {@link DateTimeType }
         *     
         */
        public DateTimeType getDateTime() {
            return dateTime;
        }

        /**
         * Définit la valeur de la propriété dateTime.
         * 
         * @param value
         *     allowed object is
         *     {@link DateTimeType }
         *     
         */
        public void setDateTime(DateTimeType value) {
            this.dateTime = value;
        }

        /**
         * Obtient la valeur de la propriété imageDescription.
         * 
         * @return
         *     possible object is
         *     {@link StringType }
         *     
         */
        public StringType getImageDescription() {
            return imageDescription;
        }

        /**
         * Définit la valeur de la propriété imageDescription.
         * 
         * @param value
         *     allowed object is
         *     {@link StringType }
         *     
         */
        public void setImageDescription(StringType value) {
            this.imageDescription = value;
        }

        /**
         * Obtient la valeur de la propriété make.
         * 
         * @return
         *     possible object is
         *     {@link StringType }
         *     
         */
        public StringType getMake() {
            return make;
        }

        /**
         * Définit la valeur de la propriété make.
         * 
         * @param value
         *     allowed object is
         *     {@link StringType }
         *     
         */
        public void setMake(StringType value) {
            this.make = value;
        }

        /**
         * Obtient la valeur de la propriété model.
         * 
         * @return
         *     possible object is
         *     {@link StringType }
         *     
         */
        public StringType getModel() {
            return model;
        }

        /**
         * Définit la valeur de la propriété model.
         * 
         * @param value
         *     allowed object is
         *     {@link StringType }
         *     
         */
        public void setModel(StringType value) {
            this.model = value;
        }

        /**
         * Obtient la valeur de la propriété software.
         * 
         * @return
         *     possible object is
         *     {@link StringType }
         *     
         */
        public StringType getSoftware() {
            return software;
        }

        /**
         * Définit la valeur de la propriété software.
         * 
         * @param value
         *     allowed object is
         *     {@link StringType }
         *     
         */
        public void setSoftware(StringType value) {
            this.software = value;
        }

        /**
         * Obtient la valeur de la propriété artist.
         * 
         * @return
         *     possible object is
         *     {@link StringType }
         *     
         */
        public StringType getArtist() {
            return artist;
        }

        /**
         * Définit la valeur de la propriété artist.
         * 
         * @param value
         *     allowed object is
         *     {@link StringType }
         *     
         */
        public void setArtist(StringType value) {
            this.artist = value;
        }

        /**
         * Obtient la valeur de la propriété copyright.
         * 
         * @return
         *     possible object is
         *     {@link StringType }
         *     
         */
        public StringType getCopyright() {
            return copyright;
        }

        /**
         * Définit la valeur de la propriété copyright.
         * 
         * @param value
         *     allowed object is
         *     {@link StringType }
         *     
         */
        public void setCopyright(StringType value) {
            this.copyright = value;
        }

        /**
         * Obtient la valeur de la propriété tiffField1.
         * 
         * @return
         *     possible object is
         *     {@link SingleFieldType }
         *     
         */
        public SingleFieldType getTiffField1() {
            return tiffField1;
        }

        /**
         * Définit la valeur de la propriété tiffField1.
         * 
         * @param value
         *     allowed object is
         *     {@link SingleFieldType }
         *     
         */
        public void setTiffField1(SingleFieldType value) {
            this.tiffField1 = value;
        }

        /**
         * Obtient la valeur de la propriété tiffField2.
         * 
         * @return
         *     possible object is
         *     {@link SingleFieldType }
         *     
         */
        public SingleFieldType getTiffField2() {
            return tiffField2;
        }

        /**
         * Définit la valeur de la propriété tiffField2.
         * 
         * @param value
         *     allowed object is
         *     {@link SingleFieldType }
         *     
         */
        public void setTiffField2(SingleFieldType value) {
            this.tiffField2 = value;
        }

        /**
         * Obtient la valeur de la propriété tiffField3.
         * 
         * @return
         *     possible object is
         *     {@link RepeatedFieldType }
         *     
         */
        public RepeatedFieldType getTiffField3() {
            return tiffField3;
        }

        /**
         * Définit la valeur de la propriété tiffField3.
         * 
         * @param value
         *     allowed object is
         *     {@link RepeatedFieldType }
         *     
         */
        public void setTiffField3(RepeatedFieldType value) {
            this.tiffField3 = value;
        }

    }

}
