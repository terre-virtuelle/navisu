package bzh.terrevirtuelle.navisu.api.progress;

/**
 * NaVisu
 *
 * @author tibus
 * @date 02/12/2013 21:18
 */
public interface ProgressRunnable<T> {

    T run(ProgressHandle progressHandle);
}
