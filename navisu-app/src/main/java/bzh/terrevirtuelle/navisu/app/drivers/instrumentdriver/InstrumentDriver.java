package bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver;

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

    void on(String ... files);

    void off();
    
    default void newSector() {return;};
    
    default void drawerOn() {return;};
    
    default void savePolygon() {return;};
    
    default void polyShapeOn() {return;};
    
    default void ellipseShapeOn() {return;};
    
    default void circleShapeOn() {return;};
    
    default void freeHandOn() {return;};
    
    default void createCpaZone() {return;};
    
    default void activateCpaZone() {return;};
}
