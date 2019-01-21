package bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver;

import gov.nasa.worldwind.ogc.kml.impl.KMLSurfacePolygonImpl;

/**
 * NaVisu
 *
 * @date 28 mars 2015
 * @author Serge Morvan
 */
public interface InstrumentDriver {

    
    default boolean canOpen(String category) {
        return false;
    }

    default void on(String ... files){
        
    }
    default InstrumentDriver openFile(String category, String file){
        return null;
    }
    default void off(){
        
    }
    default void showGUI(KMLSurfacePolygonImpl polygon){
        
    }
}
