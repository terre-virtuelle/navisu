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
        System.out.println(TestOptionsPanelCtrl.class + " load");

        view.getDoubleValueTextField().setText(model.getDoubleValue().toString());
        view.getIntValueTextField().setText(model.getIntValue().toString());
        view.getStringValueTextField().setText(model.getStringValue());
    }

    @Override
    public void store(TestOptionsPanel view, TestModel oldModel) {
        System.out.println(TestOptionsPanelCtrl.class + " store");
    }

    @Override
    public boolean valid(TestOptionsPanel view) {
        System.out.println(TestOptionsPanelCtrl.class + " valid");
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
