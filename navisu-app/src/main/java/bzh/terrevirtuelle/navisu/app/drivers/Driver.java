package bzh.terrevirtuelle.navisu.app.drivers;

import java.util.List;

/**
 * NaVisu
 *
 * @author tibus
 * @date 11/11/2013 18:57
 */
public interface Driver {

    boolean canOpen(String file);

    void open(String... files);

    String getName();

    String[] getExtensions();
}
