/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.common.view.panel;

import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

/**
 *
 * @author Serge
 */
public class TrackPanel
        extends Widget2DController
        implements Initializable {

    @FXML
    public Group trackPanel;
    @FXML
    TextArea names;
    @FXML
    public Text timeStamp;
    @FXML
    public Text shipsInSight;
    @FXML
    public Text countOfAisShipsReceived;
    @FXML
    public Text countOfNamesReceived;
    @FXML
    public ImageView quit;
    @FXML
    public Slider slider;
    @FXML
    TextArea mmsis;
    @FXML
    TextArea status;
    
    private Paint color;
    private boolean debut = true;

    NumberFormat nf = new DecimalFormat("0.###");
    SimpleDateFormat dt = new SimpleDateFormat("hh:mm dd-MM");

    public TrackPanel(KeyCode keyCode, KeyCombination.Modifier keyCombination) {
        super(keyCode, keyCombination);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("aisTracks.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        trackPanel.setOpacity(0.8);
        quit.setOnMouseClicked((MouseEvent event) -> {
            trackPanel.setVisible(false);
        });
        status.textProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<?> observable, Object oldValue,
                    Object newValue) {
                status.setScrollTop(Double.MAX_VALUE); //this will scroll to the bottom
                //use Double.MIN_VALUE to scroll to the top
            }
        });
        names.textProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<?> observable, Object oldValue,
                    Object newValue) {
                names.setScrollTop(Double.MAX_VALUE); //this will scroll to the bottom
                //use Double.MIN_VALUE to scroll to the top
            }
        });
        mmsis.textProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<?> observable, Object oldValue,
                    Object newValue) {
                mmsis.setScrollTop(Double.MAX_VALUE); //this will scroll to the bottom
                //use Double.MIN_VALUE to scroll to the top
            }
        });
        slider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            Platform.runLater(() -> {
                trackPanel.setOpacity(slider.getValue());
            });
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO 

    }

    public void updateAisPanelShips(String time, int ships) {
    	Platform.runLater(() -> {
    	timeStamp.setText(time);
        timeStamp.setFill(color);
        shipsInSight.setText(Integer.toString(ships));
    	});
    }
    
    public void updateAisPanelCount(String time, int ships, int count, int countOfNames) {
    	Platform.runLater(() -> {
    	timeStamp.setText(time);
        if (debut) {
        	color = timeStamp.getFill();
        	debut = false;
        } 
        else {
        	timeStamp.setFill(Color.RED);
        }
        shipsInSight.setText(Integer.toString(ships));
        countOfAisShipsReceived.setText(Integer.toString(count));
        countOfNamesReceived.setText(Integer.toString(countOfNames));
    	});
    }
    
    public void updateAisPanelName(String time, int ships, String name) {
    	Platform.runLater(() -> {
    	timeStamp.setText(time);
        timeStamp.setFill(color);
        shipsInSight.setText(Integer.toString(ships));
        String[] tabNames = null;
        tabNames = names.getText().split("\n");
        int nbNames = tabNames.length;
        String resu = "";
        if (nbNames==100) {
            for (int i=1;i<nbNames;i++) {
                resu = resu + tabNames[i] + "\n";
            }
            names.setText(resu);
        }
        names.appendText(name + "\n");
    	});
    }
   
    public void updateAisPanelMmsi(String time, int ships, int mmsi) {
    	Platform.runLater(() -> {
    	timeStamp.setText(time);
        timeStamp.setFill(color);
        shipsInSight.setText(Integer.toString(ships));
        String[] tabMmsis = null;
        tabMmsis = mmsis.getText().split("\n");
        int nbMmsis = tabMmsis.length;
        String resu = "";
        if (nbMmsis==200) {
            for (int i=1;i<nbMmsis;i++) {
                resu = resu + tabMmsis[i] + "\n";
            }
            mmsis.setText(resu);
        }
        mmsis.appendText(mmsi + "\n");
    	});
    }
   
    public void updateAisPanelStatus(String message) {
    	Platform.runLater(() -> {
    	timeStamp.setFill(color);
        String[] tabLines = null;
        tabLines = status.getText().split("\n");
        int nbLines = tabLines.length;
        String resu = "";
        if (nbLines==500) {
            for (int i=1;i<nbLines;i++) {
                resu = resu + tabLines[i] + "\n";
            }
            status.setText(resu);
        }
        status.appendText(message + "\n");
    	});
    }
    
}
