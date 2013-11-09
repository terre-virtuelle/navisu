/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.instrument.view.gps;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import java.util.Enumeration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuButtonBuilder;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuItemBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.ColumnConstraintsBuilder;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.GridPaneBuilder;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.RowConstraintsBuilder;
import bzh.terrevirtuelle.navisu.instruments.instrument.model.Display;

/**
 *
 * @author Serge Morvan
 */
public class CommPane
        extends GPSPane {

    private GPSHandler handler;
    private MenuItem[] ports;
    private Button close;
    private Button open;

    public CommPane(GPS gps, final GPSHandler handler) {
        super((Display) gps);
        this.handler = handler;
        setId("commPane");
        backgroundFileName = "gpsCommBackground.png";
        foregroundFileName = "verre.png";
        backgroundNightFileName = "night_gpsBackground.png";
        LAYOUT_X = 32;
        LAYOUT_Y = 30;
        createScene();
        if (gps.getInitComm() == true) {
            init();
        }
    }

    private void createScene() {
        root = new Group();
        root.setLayoutX(LAYOUT_X - 6);
        root.setLayoutY(LAYOUT_Y - 2);
        getChildren().add(root);
        
        backgroundImage = new Image(rootDir + IMAGES + backgroundFileName);
        background = ImageViewBuilder.create()
                .id("commPaneBackground")
                .pickOnBounds(true)
                .image(backgroundImage)
                .build();
        root.getChildren().add(background);

        ColumnConstraints columnConstraints0 = ColumnConstraintsBuilder.create()
                .hgrow(Priority.NEVER)
                .prefWidth(104.0)
                .build();
        ColumnConstraints columnConstraints1 = ColumnConstraintsBuilder.create()
                .hgrow(Priority.NEVER)
                .prefWidth(107.0)
                .build();
        RowConstraints rowConstraints0 = RowConstraintsBuilder.create()
                .prefHeight(30.0)
                .vgrow(Priority.NEVER)
                .build();
        RowConstraints rowConstraints1 = RowConstraintsBuilder.create()
                .prefHeight(30.0)
                .vgrow(Priority.NEVER)
                .build();
        RowConstraints rowConstraints2 = RowConstraintsBuilder.create()
                .prefHeight(30.0)
                .vgrow(Priority.NEVER)
                .build();
        RowConstraints rowConstraints3 = RowConstraintsBuilder.create()
                .prefHeight(30.0)
                .vgrow(Priority.NEVER)
                .build();
        RowConstraints rowConstraints4 = RowConstraintsBuilder.create()
                .prefHeight(30.0)
                .vgrow(Priority.NEVER)
                .build();
        RowConstraints rowConstraints5 = RowConstraintsBuilder.create()
                .prefHeight(30.0)
                .vgrow(Priority.NEVER)
                .build();
        RowConstraints rowConstraints6 = RowConstraintsBuilder.create()
                .prefHeight(30.0)
                .vgrow(Priority.NEVER)
                .build();
        GridPane gridPane = GridPaneBuilder.create()
                .prefHeight(210)
                .prefWidth(210)
                .columnConstraints(columnConstraints0, columnConstraints1)
                .rowConstraints(rowConstraints0, rowConstraints1, rowConstraints2, rowConstraints3, rowConstraints4, rowConstraints5, rowConstraints6)
                .build();
        root.getChildren().add(gridPane);

        ports = new MenuItem[6];
        ports[0] = MenuItemBuilder.create().text("").build();
        ports[1] = MenuItemBuilder.create().text("").build();
        ports[2] = MenuItemBuilder.create().text("").build();
        ports[3] = MenuItemBuilder.create().text("").build();
        ports[4] = MenuItemBuilder.create().text("").build();
        ports[5] = MenuItemBuilder.create().text("").build();
        MenuButton port = MenuButtonBuilder.create()
                .mnemonicParsing(false)
                .prefWidth(110)
                .text("Ports")
                .items(ports)
                .build();

        for (int i = 0; i < 6; i++) {
            ports[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent evt) {
                    String tmp = ((MenuItem) evt.getSource()).getText();
                    if (tmp != null && !tmp.equals("")) {
                        tmp = tmp.substring(3);
                        handler.setPortNumber(new Integer(tmp));
                    }
                }
            });
        }

        gridPane.add(port, 0, 0);

        MenuItem[] baudRates = new MenuItem[9];
        baudRates[0] = MenuItemBuilder.create().text("4800").build();
        baudRates[1] = MenuItemBuilder.create().text("9600").build();
        baudRates[2] = MenuItemBuilder.create().text("14400").build();
        baudRates[3] = MenuItemBuilder.create().text("19200").build();
        baudRates[4] = MenuItemBuilder.create().text("28800").build();
        baudRates[5] = MenuItemBuilder.create().text("38400").build();
        baudRates[6] = MenuItemBuilder.create().text("57600").build();
        baudRates[7] = MenuItemBuilder.create().text("115200").build();
        baudRates[8] = MenuItemBuilder.create().text("230400").build();
        MenuButton baudRate = MenuButtonBuilder.create()
                .mnemonicParsing(false)
                .prefWidth(110)
                .text("Baud rate")
                .items(baudRates)
                .build();
        for (int i = 0; i < 9; i++) {
            baudRates[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent evt) {
                    String tmp = ((MenuItem) evt.getSource()).getText();
                    if (tmp != null && !tmp.equals("")) {
                        handler.setBaudRate(new Integer(tmp));
                    }
                }
            });
        }
        gridPane.add(baudRate, 1, 0);

        MenuItem[] dataBits = new MenuItem[4];
        dataBits[0] = MenuItemBuilder.create().text("5").build();
        dataBits[1] = MenuItemBuilder.create().text("6").build();
        dataBits[2] = MenuItemBuilder.create().text("7").build();
        dataBits[3] = MenuItemBuilder.create().text("8").build();
        MenuButton dataBit = MenuButtonBuilder.create()
                .mnemonicParsing(false)
                .prefWidth(110)
                .text("Data bits")
                .items(dataBits)
                .build();
        for (int i = 0; i < 4; i++) {
            dataBits[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent evt) {
                    String text = ((MenuItem) evt.getSource()).getText();
                    switch (text) {
                        case "5":
                            handler.setDataBit(SerialPort.DATABITS_5);
                            break;
                        case "6":
                            handler.setDataBit(SerialPort.DATABITS_6);
                            break;
                        case "7":
                            handler.setDataBit(SerialPort.DATABITS_7);
                            break;
                        case "8":
                            handler.setDataBit(SerialPort.DATABITS_8);
                            break;
                        default:
                    }
                }
            });
        }
        gridPane.add(dataBit, 0, 1);

        MenuItem[] parities = new MenuItem[3];
        parities[0] = MenuItemBuilder.create().text("None").build();
        parities[1] = MenuItemBuilder.create().text("Even").build();
        parities[2] = MenuItemBuilder.create().text("Odd").build();
        MenuButton parity = MenuButtonBuilder.create()
                .mnemonicParsing(false)
                .prefWidth(110)
                .text("Parity")
                .items(parities)
                .build();
        for (int i = 0; i < 3; i++) {
            parities[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent evt) {
                    String text = ((MenuItem) evt.getSource()).getText();
                    switch (text) {
                        case "None":
                            handler.setParity(SerialPort.PARITY_NONE);
                            break;
                        case "Even":
                            handler.setParity(SerialPort.PARITY_EVEN);
                            break;
                        case "Odd":
                            handler.setParity(SerialPort.PARITY_ODD);
                            break;
                    }
                }
            });
        }
        gridPane.add(parity, 1, 1);

        MenuItem[] stopBits = new MenuItem[3];
        stopBits[0] = MenuItemBuilder.create().text("1").build();
        stopBits[1] = MenuItemBuilder.create().text("1.5").build();
        stopBits[2] = MenuItemBuilder.create().text("2").build();
        MenuButton stopBit = MenuButtonBuilder.create()
                .mnemonicParsing(false)
                .prefWidth(110)
                .text("Stop bits")
                .items(stopBits)
                .build();
        for (int i = 0; i < 3; i++) {
            stopBits[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent evt) {
                    String text = ((MenuItem) evt.getSource()).getText();
                    switch (text) {
                        case "1":
                            handler.setStopBit(SerialPort.STOPBITS_1);
                            break;
                        case "1.5":
                            handler.setStopBit(SerialPort.STOPBITS_1_5);
                            break;
                        case "2":
                            handler.setStopBit(SerialPort.STOPBITS_2);
                            break;
                    }
                }
            });
        }
        gridPane.add(stopBit, 0, 2);

        MenuItem[] flowControlIns = new MenuItem[3];
        flowControlIns[0] = MenuItemBuilder.create().text("None").build();
        flowControlIns[1] = MenuItemBuilder.create().text("Xon/Xoff").build();
        flowControlIns[2] = MenuItemBuilder.create().text("RTS/CTS").build();
        MenuButton flowControlIn = MenuButtonBuilder.create()
                .mnemonicParsing(false)
                .prefWidth(110)
                .text("Contr. in")
                .items(flowControlIns)
                .build();
        for (int i = 0; i < 3; i++) {
            flowControlIns[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent evt) {
                    //nothing
                }
            });
        }
        gridPane.add(flowControlIn, 0, 4);

        MenuItem[] flowControlOuts = new MenuItem[3];
        flowControlOuts[0] = MenuItemBuilder.create().text("None").build();
        flowControlOuts[1] = MenuItemBuilder.create().text("Xon/Xoff").build();
        flowControlOuts[2] = MenuItemBuilder.create().text("RTS/CTS").build();
        MenuButton flowControlOut = MenuButtonBuilder.create()
                .mnemonicParsing(false)
                .prefWidth(110)
                .text("Contr. out")
                .items(flowControlOuts)
                .build();
        for (int i = 0; i < 3; i++) {
            flowControlOuts[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent evt) {
                    //nothing
                }
            });
        }
        gridPane.add(flowControlOut, 1, 4);

        open = ButtonBuilder.create()
                .text("Open connect.")
                /*.styleClass(".button-open")*/
                .prefWidth(110)
                .build();

        gridPane.add(open, 0, 6);
        close = ButtonBuilder.create()
                .text("Close connect.")
                .prefWidth(110)
                .build();

        gridPane.add(close, 1, 6);
        //- verre sur les boutons -
        /*foregroundImage = new Image(rootDir + IMAGES + foregroundFileName);
        foreground = ImageViewBuilder.create()
                .id("verreSat")
                .layoutX(-3)
                .layoutY(-2)
                .mouseTransparent(true)
                .pickOnBounds(true)
                .image(foregroundImage)
                .build();
        root.getChildren().add(foreground);*/
    }

    public void initMenu() {
       

    }

    public final void init() {
        initPorts();
        initOpen();
        initClose();
    }

    private void initPorts() {
        Enumeration<CommPortIdentifier> portEnum = handler.getSerialComm().listPorts();
        int j = 0;
        if (portEnum != null) {
            while (portEnum.hasMoreElements()) {
                CommPortIdentifier portIdentifier = portEnum.nextElement();
                ports[j].setText(portIdentifier.getName());
            }
        }
    }

    private void initOpen() {
        open.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (!handler.isConnected() && handler.getPortNumber() != 0) {
                    handler.connect();
                }
            }
        });
    }

    private void initClose() {
        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                handler.close();
            }
        });
    }
}
