/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.core.view.geoview.layer;

/**
 *
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 * @author Jordan Mens <jordan.mens at gmail.com>
 */
//TODO: Est-ce qu'on doit être capable de voir ce que la GeoLayer contient ?
public interface GeoLayer<T> {

    public static final GeoLayerFactory factory = GeoLayerFactory.instance;

    String getName();
    void   setName(String name);
    
    void    setVisible(boolean visible);
    boolean isVisible();

    void   setOpacity(double opacity);
    double getOpacity();

    T getDisplayLayer();
}
