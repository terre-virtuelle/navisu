package bzh.terrevirtuelle.navisu.api.checktree;

/**
 * NaVisu
 *
 * @author tibus
 * @date 11/12/2013 23:20
 */
public interface Action {

    String name();
    Callback callback();
    
    public static Action create(final String name, final Callback cb) {
        return new Action() {

            @Override
            public String name() {
                return name;
            }

            @Override
            public Callback callback() {
                return cb;
            }
        };
    }
}
