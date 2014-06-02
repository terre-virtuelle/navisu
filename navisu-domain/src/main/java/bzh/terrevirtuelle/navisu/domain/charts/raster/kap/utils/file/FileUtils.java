package bzh.terrevirtuelle.navisu.domain.charts.raster.kap.utils.file;

import java.nio.file.Path;

/**
 *
 * @author jordan
 */
public class FileUtils {

    private FileUtils() {}
    
    /**
     * Return file extension
     * @param filepath
     * @return 
     */
    public static String getExtension(Path filepath) {
        
        String filename = filepath.toFile().getName();
        int dot = filename.lastIndexOf(".");
        return filename.substring(dot + 1);
    }
}
