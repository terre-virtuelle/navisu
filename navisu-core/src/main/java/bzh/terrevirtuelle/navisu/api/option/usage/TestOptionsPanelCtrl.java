package bzh.terrevirtuelle.navisu.api.option.usage;

import bzh.terrevirtuelle.navisu.api.option.OptionsPanelCtrl;
import javafx.scene.layout.Border;

/**
 * NaVisu
 *
 * @author tibus
 * @date 15/02/2014 18:13
 */
public class TestOptionsPanelCtrl implements OptionsPanelCtrl<TestOptionsPanel, TestModel> {

    @Override
    public void load(TestOptionsPanel view, TestModel model) {
        //System.out.println(TestOptionsPanelCtrl.class + " load");

        view.getDoubleValueTextField().setText(model.getDoubleValue().toString());
        view.getIntValueTextField().setText(model.getIntValue().toString());
        view.getStringValueTextField().setText(model.getStringValue());
    }

    @Override
    public void store(TestOptionsPanel view, TestModel model) {
        //System.out.println(TestOptionsPanelCtrl.class + " store");

        model.setDoubleValue(Double.valueOf(view.getDoubleValueTextField().getText()));
        model.setIntValue(Integer.valueOf(view.getIntValueTextField().getText()));
        model.setStringValue(view.getStringValueTextField().getText());
    }

    @Override
    public boolean valid(TestOptionsPanel view) {
        return this.validDoubleTextField(view) && this.validIntTextField(view);
    }

    protected boolean validDoubleTextField(TestOptionsPanel view) {

        try {
            Double.valueOf(view.getDoubleValueTextField().getText());
        } catch (NumberFormatException ex) {
            view.getDoubleErrorLabel().setVisible(true);
            return false;
        }

        return true;
    }

    protected boolean validIntTextField(TestOptionsPanel view) {

        try {
            Integer.valueOf(view.getIntValueTextField().getText());
        } catch (NumberFormatException ex) {
            view.getIntErrorLabel().setVisible(true);
            return false;
        }

        return true;
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
