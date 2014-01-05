/**
 * This file is part of NaVisu.
 *
 * NaVisu is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * NaVisu is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * NaVisu. If not, see <http://www.gnu.org/licenses/>.
 */
package bzh.terrevirtuelle.navisu.app.drivers.charts.impl.imageryinstaller.impl;

import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.core.util.Checker;
import gov.nasa.worldwind.*;
import gov.nasa.worldwind.avlist.*;
import gov.nasa.worldwind.data.TiledImageProducer;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.util.*;
import org.w3c.dom.Document;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.*;
import java.util.logging.Level;
import bzh.terrevirtuelle.navisu.app.drivers.charts.impl.imageryinstaller.ImageryInstaller;

/**
 * @author Thibault
 */
public class ImageryInstallerImpl implements ImageryInstaller
{
    private static final Logger LOGGER = Logger.getLogger(ImageryInstallerImpl.class.getName());
    static {
        LOGGER.setLevel(Level.INFO);
    }

    /** Define a subdirectory in the installed-data area */
    protected static final String BASE_CACHE_PATH = "Earth/Charts";

    /** The tiles image format (PNG or DDS) */
    protected ImageFormatEnum imageFormat = ImageFormatEnum.DDS;

    /** A {@code TiledImageProducer} to install the imagery. */
    protected TiledImageProducer producer;

    /**
     * TODO handle progression
     *
     * {@inheritDoc}
     */
    @Override
    public Layer installSurfaceImage(Object imageSource, ProgressHandle progressHandle) {

        Checker.notNull(imageSource, "Image source must not be null.");

        final String imageSourceName = getImageSourceName(imageSource);
        Layer layer;
        
        if((layer = getInstalledSurfaceImage(imageSource)) != null) {
            LOGGER.log(Level.INFO, "Image [{0}] is already installed !", imageSourceName);
            return layer;
        }

        AVList params = this.computeParams(imageSource);

        try  {
            producer = new TiledImageProducer();
            // Configure the TiledImageProducer with the parameter list and the image source.
            producer.setStoreParameters(params);
            producer.offerDataSource(imageSourceToFile(imageSource), null);

            progressHandle.switchToDeterminate(100);

            producer.addPropertyChangeListener(evt -> {
                    
                if(evt.getPropertyName().endsWith(AVKey.PROGRESS)) {

                    int progress = (int)((Double)evt.getNewValue() * 100);
                    progressHandle.progress("Installing " + progress + "%", progress);
                }
            });
            
            // Install the imagery.
            progressHandle.progress("Start production");
            producer.startProduction();
        }
        catch (Exception e)
        {
            producer.removeProductionState(); // Clean up on failure.
            LOGGER.log(Level.SEVERE, "An unknown error occurred", e);

            return null;
        }

        // Extract the data configuration document from the installed results. If the installation successfully
        // completed, the TiledImageProducer should always contain a document in the production results, but test
        // the results anyway.
        Iterable<?> results = producer.getProductionResults();
        if (results == null || results.iterator() == null || !results.iterator().hasNext())
            return null;

        Object o = results.iterator().next();
        if (o == null || !(o instanceof Document))
            return null;

        return instanceLayerFromDataConfigDoc((Document) o);
    }

    /**
     * Create a parameter list specifying the install location information.
     *
     * @param imageSourceObj The image source. It can be a {@code String} or a {@code File} in absolute path.
     * @return A parameter list specifying the install location information.
     */
    protected AVList computeParams(Object imageSourceObj) {

        AVList params = new AVListImpl();
        // Compute the titleText of the image
        String imageSourceName = getImageSourceName(imageSourceObj);
        // Compute the cache titleText of the tiles location
        String cacheName = BASE_CACHE_PATH + File.separator + imageSourceName;

        params.setValue(AVKey.FILE_STORE_LOCATION, WorldWind.getDataFileStore().getWriteLocation().getAbsolutePath());
        params.setValue(AVKey.DATA_CACHE_NAME, cacheName);
        params.setValue(AVKey.DATASET_NAME, imageSourceName);
        params.setValue(AVKey.IMAGE_FORMAT, this.imageFormatToString(this.imageFormat));

        return params;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Layer getInstalledSurfaceImage(Object imageSource) {

        String imageSourceName = getImageSourceName(imageSource);
        String imageSourceConfigurationFileName = imageSourceName + ".xml";

        String fileStoreWriteLocation = WorldWind.getDataFileStore().getWriteLocation().getAbsolutePath();
        File installedLocation = Paths.get(fileStoreWriteLocation, BASE_CACHE_PATH).toFile();
        String[] cacheNames = WWIO.listDescendantFilenames(installedLocation, new DataConfigurationFilter(), false);

        if(cacheNames == null) {
            return null;
        }
        
        String dataConfigFilePath = null;
        
        for(String cacheName : cacheNames) {

            if(cacheName.equals(imageSourceName + File.separator + imageSourceConfigurationFileName)) {
                dataConfigFilePath = Paths.get(installedLocation.getAbsolutePath(), cacheName).toString();
                break;
            }
        }

        if(null == dataConfigFilePath) {
            return null;
        }

        Document dataConfigDoc = WWXML.openDocument(dataConfigFilePath);
        dataConfigDoc = DataConfigurationUtils.convertToStandardDataConfigDocument(dataConfigDoc);

        return instanceLayerFromDataConfigDoc(dataConfigDoc);
    }

    protected Layer instanceLayerFromDataConfigDoc(Document dataConfigDoc) {

        // Construct a Layer by passing the data configuration document to a LayerFactory.
        Layer layer = (Layer) BasicFactory.create(AVKey.LAYER_FACTORY, dataConfigDoc.getDocumentElement());
        // The layer impl creates layers that are initially disabled, so enable the layer.
        layer.setEnabled(true);
        // Return the layer
        return layer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setImageFormat(ImageFormatEnum imageFormat) {
        this.imageFormat = imageFormat;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImageFormatEnum getImageFormat() {
        return this.imageFormat;
    }

    /**
     *
     * @param imageFormat The {@code ImageFormatEnum} image format
     * @return The MIME type
     */
    protected String imageFormatToString(ImageFormatEnum imageFormat) {
        return imageFormat == ImageFormatEnum.DDS ? "image/dds" : "image/png";
    }

    protected static String getImageSourceName(Object imageSourceObj) {
        
        // Cast the image source object to File
        File imageSourceFile = imageSourceToFile(imageSourceObj);
        // Compute the image titleText, without its extension
        String imageSourceName = imageSourceFile.getName().substring(0, imageSourceFile.getName().lastIndexOf("."));
        // Return the titleText
        return imageSourceName;
    }
    
    public static boolean checkImageSourceType(Object imageSourceObj) {
        
        boolean result = true;
        Class<?> clz = imageSourceObj.getClass();
        
        if(!Path.class.isAssignableFrom(clz) && !File.class.isAssignableFrom(clz) && !String.class.isAssignableFrom(clz)) {
            result = false;
        }
        
        return result;
    }
    
    public static File imageSourceToFile(Object imageSource) {
       
        if(!checkImageSourceType(imageSource)) 
        {
            throw new IllegalArgumentException("Image source must be a java.nio.Path or a java.io.File or a java.lang.String. Founnd : " + imageSource.getClass());
        }
        
        if(imageSource instanceof Path) 
        {
            return ((Path) imageSource).toFile();
        }
        else if(imageSource instanceof String)
        {
            return new File((String) imageSource);
        }
        
        return (File) imageSource;
    }
}
