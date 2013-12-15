package bzh.terrevirtuelle.navisu.core.util;

/**
 * NaVisu
 *
 * @author tibus
 * @date 27/11/2013 22:24
 */
public class OS {

    public static final String name = System.getProperty("os.title");
    public static final String arch = System.getProperty("os.arch");
    public static final String version = System.getProperty("os.version");

    public static boolean isWindows() {
        return name.toLowerCase().contains("win");
    }

    public static boolean isLinux() {
        return name.toLowerCase().contains("nix") || name.toLowerCase().contains("nux");
    }

    public static boolean isMac() {
        return name.toLowerCase().contains("mac");
    }

    public static boolean is32() {
        return !arch.contains("64");
    }

    public static boolean is64() {
        return arch.contains("64");
    }

    private OS() {}
}
