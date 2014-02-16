package bzh.terrevirtuelle.navisu.api.option;

import bzh.terrevirtuelle.navisu.core.view.display.jfx.impl.JFXAbstractDisplay;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * NaVisu
 *
 * @author tibus
 * @date 15/02/2014 16:40
 */
public class OptionsWindow extends JFXAbstractDisplay {

    protected BorderPane content;
    protected TabPane tabPane;

    protected HBox southContainer;
    protected Button cancelBtn;
    protected Button okBtn;

    protected Runnable onOkListener;
    protected Runnable onCancelListener;


    public OptionsWindow() {

        this.content = new BorderPane();
        this.tabPane = new TabPane();

        this.southContainer = new HBox(20);
        this.southContainer.setAlignment(Pos.CENTER_RIGHT);
        this.southContainer.setPadding(new Insets(20));

        this.cancelBtn = new Button(this.getCancelButtonText());
        this.cancelBtn.setOnAction(e -> {
            if(this.onCancelListener != null) this.onCancelListener.run();
        });

        this.okBtn = new Button(this.getOkButtonText());
        this.okBtn.setOnAction(e -> {
            if(this.onOkListener != null) this.onOkListener.run();
        });

        this.southContainer.getChildren().addAll(
                this.cancelBtn,
                this.okBtn);

        this.content.setCenter(this.tabPane);
        this.content.setBottom(this.southContainer);
    }

    protected String getCancelButtonText() {
        return "Cancel";
    }

    protected String getOkButtonText() {
        return "Ok";
    }

    public void addTab(String title, Node content) {
        Tab tab = new Tab(title);
        tab.setContent(content);
        tab.setClosable(false);
        this.tabPane.getTabs().add(tab);
    }

    public void onOk(Runnable runnable) {
        this.onOkListener = runnable;
    }

    public void onCancel(Runnable runnable) {
        this.onCancelListener = runnable;
    }

    public Button getCancelBtn() {
        return cancelBtn;
    }

    public Button getOkBtn() {
        return okBtn;
    }

    @Override
    public Node getDisplayable() {
        return content;
    }
}
