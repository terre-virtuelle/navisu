package bzh.terrevirtuelle.navisu.api.common;

/**
 * NaVisu
 *
 * @author tibus
 * @date 12/12/2013 22:24
 */
public interface Selectable {

    boolean selected();
    void    setSelected(boolean selected);

    public static boolean isSelectable(Object obj) {
        return Selectable.class.isAssignableFrom(obj.getClass());
    }
}
