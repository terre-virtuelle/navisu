package bzh.terrevirtuelle.navisu.core.utility;

import java.util.Map;

/**
 *
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 * @author Jordan Mens <jordan.mens at gmail.com>
 */
public class Checker {

    public static void notNull(Object o, String message) {

        if(null == o) {
            throw new NullPointerException(message);
        }
    }

    public static <K, V> void keyNotExistsInMap(Map<K, V> map, K key, String message) {

        if(map.containsKey(key)) {
            throw new RuntimeException(message);
        }
    }

    public static <K, V> void keyExistsInMap(Map<K, V> map, K key, String message) {

        if(!map.containsKey(key)) {
            throw new RuntimeException(message);
        }
    }

    private Checker() {}
}
