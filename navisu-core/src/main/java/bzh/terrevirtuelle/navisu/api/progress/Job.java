package bzh.terrevirtuelle.navisu.api.progress;

/**
 * NaVisu
 *
 * @author tibus
 * @date 02/12/2013 21:18
 */
@FunctionalInterface
public interface Job {

    void run(ProgressHandle progressHandle);
}
