package bzh.terrevirtuelle.navisu.app.guiagent.dock;

/**
 * NaVisu
 *
 * @date 22 mars 2015
 * @author Serge Morvan
 */
public enum DefaultDockEnum {

    FILE("menu.file"),
    URL("menu.url"),
    EDIT("menu.edit"),
    WINDOW("menu.window"),
    HELP("menu.help");

    protected final String key;

    DefaultDockEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public static DefaultDockEnum[] getAll() {
        return new DefaultDockEnum[] {
            FILE, URL, EDIT, WINDOW, HELP
        };
    }
}
