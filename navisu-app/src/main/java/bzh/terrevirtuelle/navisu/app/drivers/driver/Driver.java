package bzh.terrevirtuelle.navisu.app.drivers.driver;

import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;

/**
 * NaVisu
 *
 * @author tibus
 * @date 11/11/2013 18:57
 */
public interface Driver {

    default  boolean canOpen(String file){
      return false;  
    }

    default boolean canOpen(String category, String file) {
        return false;
    }

    void open(ProgressHandle progressHandle, String... files);

    String getName();

    String[] getExtensions();
}
