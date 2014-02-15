package bzh.terrevirtuelle.navisu.api.option.usage;

import bzh.terrevirtuelle.navisu.api.option.OptionsPanelCtrl;

/**
 * NaVisu
 *
 * @author tibus
 * @date 15/02/2014 18:13
 */
public class TestOptionsPanelCtrl implements OptionsPanelCtrl<TestOptionsPanel, TestModel> {

    @Override
    public void load(TestOptionsPanel view, TestModel model) {

    }

    @Override
    public TestModel store(TestOptionsPanel view, TestModel oldModel) {
        return null;
    }

    @Override
    public boolean valid(TestOptionsPanel view) {
        return false;
    }

    @Override
    public String getTitle() {
        return "Test";
    }

    @Override
    public Class<TestOptionsPanel> getViewType() {
        return TestOptionsPanel.class;
    }

    @Override
    public Class<TestModel> getModelType() {
        return TestModel.class;
    }
}
