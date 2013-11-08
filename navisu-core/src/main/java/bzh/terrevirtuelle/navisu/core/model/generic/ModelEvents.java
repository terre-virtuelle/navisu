package bzh.terrevirtuelle.navisu.core.model.generic;

/**
 *
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 * @author Jordan Mens <jordan.mens at gmail.com>
 */
public interface ModelEvents<T> {

    void notifyDataCreated(int id, T data);

    void notifyDataUpdated(int id, T data);

    void notifyDataDeleted(int id, T data);

    public interface ModelEventsSubscribe<T> {

        void subscribe  (ModelEvents<T> observer);
        void unsubscribe(ModelEvents<T> observer);
    }
}
