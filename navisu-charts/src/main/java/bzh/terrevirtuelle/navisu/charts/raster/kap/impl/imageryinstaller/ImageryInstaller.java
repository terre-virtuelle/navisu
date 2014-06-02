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
package bzh.terrevirtuelle.navisu.charts.raster.kap.impl.imageryinstaller;

import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import gov.nasa.worldwind.layers.Layer;

/**
 * @author Thibault
 */
public interface ImageryInstaller
{
    public static final ImageryInstallerFactory factory = ImageryInstallerFactory.impl;

    /**
     * TODO add the same method without the ProgressBar
     * 
     * @param imageSource
     * @return
     */
    Layer installSurfaceImage(Object imageSource, ProgressHandle progressHandle);

    /**
     *
     * @param imageSource
     * @return
     */
    Layer getInstalledSurfaceImage(Object imageSource);

    public enum ImageFormatEnum { PNG, DDS };

    /**
     * The the tiles image format.
     *
     * @param imageFormat The {@code ImageFormatEnum} tiles image format.
     */
    void setImageFormat(ImageFormatEnum imageFormat);

    /**
     *  Get the tiles image format.
     *
     * @return The {@code ImageFormatEnum} tiles image format.
     */
    ImageFormatEnum getImageFormat();
}
