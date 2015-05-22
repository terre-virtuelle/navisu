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
    
    default void newSector() {};
    
    default void drawerOn() {};
    
    default void savePolygon() {};
    
    default void saveAllPolygons() {};
    
    default void loadPolygons() {};
    
    default void polyShapeOn() {};
    
    default void ellipseShapeOn() {};
    
    default void circleShapeOn() {};
    
    default void quadShapeOn() {};
    
    default void freeHandOn() {};
    
    default void createCpaZone500() {};
    
    default void createCpaZone1000() {};
    
    default void createCpaZone() {};
    
    default void activateCpaZone() {};
    
    default void createPath() {};
    
    default void activatePath() {};
    
    default void savePath() {};
    
    default void loadPath() {};
}
