package bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver;

public interface ZoneDriver extends InstrumentDriver {
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
    
    default void createCpaZone() {};
    
    default void createCpaZone(double yards) {};
    
    default void activateCpaZone() {};
    
    default void createPath() {};
    
    default void activatePath() {};
    
    default void savePath() {};
    
    default void loadPath() {};
    
    default void createRule() {};
    
    default void activateRule() {};
}
