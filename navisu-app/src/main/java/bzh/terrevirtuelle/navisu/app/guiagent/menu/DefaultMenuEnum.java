package bzh.terrevirtuelle.navisu.app.guiagent.menu;

/**
 * NaVisu
 *
 * @author tibus
 * @date 11/11/2013 21:28
 */
public enum DefaultMenuEnum {

    FILE("menu.file"),
    EDIT("menu.edit"),
    WINDOW("menu.window"),
    HELP("menu.help");

    protected final String key;

    DefaultMenuEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public static DefaultMenuEnum[] getAll() {
        return new DefaultMenuEnum[] {
            FILE, EDIT, WINDOW, HELP
        };
    }
}
