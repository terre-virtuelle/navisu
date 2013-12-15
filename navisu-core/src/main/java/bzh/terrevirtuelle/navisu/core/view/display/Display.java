package bzh.terrevirtuelle.navisu.core.view.display;

/**
 * NaVisu
 *
 * @author tibus
 * @date 07/11/2013 20:31
 * 
 * @param <T> Supported type by the display (Swing, JavaFX...)
 */
public interface Display<T> {

    public static final DisplayFactory factory = DisplayFactory.impl;

    void    setVisible(boolean visible);
    boolean isVisible();

    /*void   setMaxWidth(double maxWidth);
    double getMaxWidth();

    void   setMaxHeight(double maxHeight);
    double getMaxHeight();

    void   setWidth(double width);
    double getWidth();

    void   setHeight(double height);
    double getHeight();*/

    T getDisplayable();
}
