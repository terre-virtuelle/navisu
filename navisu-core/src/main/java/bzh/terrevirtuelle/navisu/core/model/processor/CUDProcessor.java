package bzh.terrevirtuelle.navisu.core.model.processor;

/**
 * NaVisu
 *
 * @author Serge
 * @param <I>
 * @param <O>
 * @date 24/02/2014 18:48
 */
public interface CUDProcessor<I, O> {

    O processCreated(int id, I input);
    O processUpdated(int id, I input, O output);
    O processDeleted(int id, I input, O output);

    Class<? extends I> getType();
}
