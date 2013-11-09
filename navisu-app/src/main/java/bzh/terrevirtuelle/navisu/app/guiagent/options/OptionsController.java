package bzh.terrevirtuelle.navisu.app.guiagent.options;

/**
 * User: Jordan
 * Date: 09/11/2013
 */
public interface OptionsController {

    String getName();

    void save();

    void restoreDefault();

    void load();

    OptionsView getOptionsView();
}
