package bzh.terrevirtuelle.navisu.app.drivers;

import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;

import java.util.List;

/**
 * NaVisu
 *
 * @author tibus
 * @date 11/11/2013 18:57
 */
public interface Driver {

    boolean canOpen(String file);

    void open(ProgressHandle progressHandle, String... files);

    String getName();

    String[] getExtensions();
}
