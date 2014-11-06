package bzh.terrevirtuelle.navisu.app.ddriver;

import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;

/**
 * NaVisu
 *
 * @author tibus
 * @date 11/11/2013 18:57
 */
public interface DDriver {

    boolean canOpen(String file);

    void open(ProgressHandle progressHandle, String... files);

    boolean isEnabled();

    String getName();

    String[] getExtensions();
}
