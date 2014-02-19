package bzh.terrevirtuelle.navisu.core.util;

import java.util.UUID;

/**
 * NaVisu
 *
 * @author tibus
 * @date 19/02/2014 18:52
 */
public class IDGenerator {

    private static int id;

    public static int newIntID() {
        return id++;
    }

    public static String newStringID() {
        return UUID.randomUUID().toString();
    }

    private IDGenerator() {}
}
