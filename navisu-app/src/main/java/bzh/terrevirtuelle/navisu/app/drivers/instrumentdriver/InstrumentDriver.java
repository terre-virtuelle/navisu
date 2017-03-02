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

    default void on(String ... files){
        
    }
    default void openFile(String category, String file){
        
    }
    default void off(){
        
    }
}
