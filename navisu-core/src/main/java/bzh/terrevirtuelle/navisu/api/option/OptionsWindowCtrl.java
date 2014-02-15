package bzh.terrevirtuelle.navisu.api.option;

/**
 * NaVisu
 *
 * @author tibus
 * @date 15/02/2014 18:03
 */
public class OptionsWindowCtrl {

    protected OptionsWindow optionsWindow;

    public OptionsWindowCtrl() {

        this.optionsWindow = new OptionsWindow();
    }

    public OptionsWindow getView() {
        return this.optionsWindow;
    }
}
