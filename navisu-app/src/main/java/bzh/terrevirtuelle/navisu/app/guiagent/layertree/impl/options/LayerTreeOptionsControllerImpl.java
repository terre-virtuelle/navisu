package bzh.terrevirtuelle.navisu.app.guiagent.layertree.impl.options;

import bzh.terrevirtuelle.navisu.app.guiagent.options.OptionsController;
import bzh.terrevirtuelle.navisu.app.guiagent.options.OptionsView;

/**
 * NaVisu
 *
 * @author tibus
 * @date 09/11/2013 01:28
 */
public class LayerTreeOptionsControllerImpl implements OptionsController {

    protected final OptionsView view = new LayerTreeOptionsViewImpl();

    @Override
    public String getName() {
        return "Layers";
    }

    @Override
    public void save() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void restoreDefault() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void load() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public OptionsView getOptionsView() {
        return view;
    }
}
