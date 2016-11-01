 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.widgets.slider;

import bzh.terrevirtuelle.navisu.widgets.Widget2D;
import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import java.io.IOException;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;

/**
 * @date 16 avril 2015
 * @author Serge Morvan
 */
public class SliderController
        extends Widget2DController {

    @FXML
    public Group sliderPanel;
    @FXML
    public Slider slider;
    @FXML
    public ImageView quit;
    private String css = System.getProperty("user.dir");

    public SliderController() {
        setMouseTransparent(false);
        load();
    }

    public SliderController(KeyCode keyCode, KeyCombination.Modifier keyCombination) {
        super(keyCode, keyCombination);
        setMouseTransparent(false);
        load();
    }

    private void load() {

        
        /* ----------- Répertoire des css dans le launcher -----------------*/
        //File cssfile = new File("css/slider.css");
        //css = ("file:///" + cssfile.getAbsolutePath().replace("\\", "/"));
        //System.out.println(css);
        /*---------------------------------------*/
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SliderPanel.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        
        /*---- si css non défini dans le fxml ------------*/
        //slider.getStylesheets().add(css);
        
        /* ---- css dans les ressources des widgets -----------*/
        css = "css/slider.css";
        slider.getStylesheets().add(Widget2D.class.getResource(css).toExternalForm());
        
        slider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
        // System.out.println("..............");
        });


        quit.setOnMouseClicked((MouseEvent event) -> {
            setVisible(false);
        });
    }

    public ImageView getQuit() {
        return quit;
    }

    public Slider getSlider() {
        return slider;
    }

}
