package bzh.terrevirtuelle.navisu.app.guiagent.factory;

import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;

/**
 * User: jordan
 * Date: 05/04/2014
 */
public interface WidgetFactory {

    /**
     * Create button with navisu look and feel
     * @param text
     * @return
     */
    static public Button makeButton(String text) {

        Button button = new Button("text");
        button.setId("navisu-button");
        return button;
    }

    /**
     * Create toggle button with navisu look and feel
     * @param text
     * @return
     */
    static public ToggleButton makeToggleButton(String text) {
        ToggleButton toggleButton = new ToggleButton(text);
        toggleButton.setId("navisu-toggle-button");
        return toggleButton;
    }
}
