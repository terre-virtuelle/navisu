/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.view.gps;

//import com.sun.prism.paint.Color;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBuilder;
import bzh.terrevirtuelle.navisu.instruments.misc.Utils;
import bzh.terrevirtuelle.navisu.instruments.model.Display;

/**
 *
 * @author Serge Morvan
 */
public class DataPane
        extends GPSPane {

    private String latStr = "  Lat  :  ";
    private String lonStr = "  Lon :  ";
    private String cogStr = "      COG        ";
    private String sogStr = "           SOG ";
    private String cogUnit = " Deg        ";
    private String sogUnit = " Knt";
    private String utcStr = "            UTC ";
    private String dateStr = "      Date      ";
    private String typo = "wwDigital";
    private String lat_temp = "";
    private String lon_temp = "";
    private String ymd_temp = "";
    private String utc_temp = "";
    private String cog_temp = "";
    private String sog_temp = "";
    private Text Label_Lat = null;
    private Text Label_Lon = null;
    private Text Label_ymd = null;
    private Text Label_utc = null;
    private Text Label_cog = null;
    private Text Label_sog = null;
    private Text latitude = null;
    private Text longitude = null;
    private Text ymd = null;
    private Text utc = null;
    private Text cog = null;
    private Text sog = null;
    private Font valueFont;
    private Font labelFont;
    private final Color GPS_GREEN = Color.rgb(152, 255, 75);
    private final String VALUE_FONT_NAME = "wwDigital.ttf";
    private final int VALUE_FONT_CORPS = 16;
    private final String LABEL_FONT_NAME = "Roboto-Bold.ttf";
    private final int LABEL_FONT_CORPS = 16;
    private Font CURRENT_FONT = Font.font("Courrier New", FontWeight.BOLD, 30);
    /* ------------------------------
     * couleurs des typos
     * valeurs RGB pour web #98ff4b
     ------------------------------ */
    int r = 152;
    int g = 255;
    int b = 75;

    public DataPane(Display display) {
        super(display);
        backgroundFileName = "gpsValuesBackground.png";
        foregroundFileName = "verreValues.png";
        backgroundNightFileName = "night_gpsValuesBackground.png";
        
        valueFont = Font.loadFont(getClass().getResource(FONTS + VALUE_FONT_NAME).toExternalForm(), VALUE_FONT_CORPS);
        labelFont = Font.loadFont(getClass().getResource(FONTS + LABEL_FONT_NAME).toExternalForm(), LABEL_FONT_CORPS);

        createScene();
    }

    private void createScene() {
        setId("dataPane");
        root = new Group();
        root.setLayoutX(28);
        root.setLayoutY(28);
        getChildren().add(root);
        backgroundImage = new Image(rootDir + IMAGES + backgroundFileName);
        background = ImageViewBuilder.create()
                .id("posSat")
                .layoutX(-1)
                .layoutY(-1)
                .pickOnBounds(true)
                .image(backgroundImage)
                .build();
        root.getChildren().add(background);
        foregroundImage = new Image(rootDir + IMAGES + foregroundFileName);
        foreground = ImageViewBuilder.create()
                .id("verreSat")
                .pickOnBounds(true)
                .image(foregroundImage)
                .build();
        root.getChildren().add(foreground);
        /*---- Afficheurs-----*/
        VBox vBox = new VBox(1);
        vBox.setLayoutX(14);
        vBox.setLayoutY(15);//24
        root.getChildren().add(vBox);

        /*-- latitude Longitude ----*/
        HBox hBox1 = new HBox();
        vBox.getChildren().add(hBox1);

        Label_Lat =
                TextBuilder.create()
                .text(latStr)
                .fill(GPS_GREEN)
                .font(labelFont)
                .textAlignment(TextAlignment.CENTER)
                .build();
        hBox1.getChildren().add(Label_Lat);

        latitude =
                TextBuilder.create()
                .text(lat_temp)
                .fill(GPS_GREEN)
                .font(valueFont)
                .textAlignment(TextAlignment.CENTER)
                .build();
        hBox1.getChildren().add(latitude);

        HBox hBox2 = new HBox();
        vBox.getChildren().add(hBox2);
        Label_Lon =
                TextBuilder.create()
                .text(lonStr)
                /*.layoutX(43)
                 .layoutY(150)*/
                .fill(GPS_GREEN)
                .font(labelFont)
                .textAlignment(TextAlignment.CENTER)
                .build();
        hBox2.getChildren().add(Label_Lon);

        longitude =
                TextBuilder.create()
                .text(lon_temp)
                .fill(GPS_GREEN)
                .font(valueFont)
                .textAlignment(TextAlignment.CENTER)
                .build();
        hBox2.getChildren().add(longitude);

//--------------------------------------------
        HBox hBox0 = new HBox();
        vBox.getChildren().add(hBox0);
        final Text empty0 = new Text(" \n    ");
        hBox0.getChildren().add(empty0);

//---------------------------------------------------------

        // ------------ COG et SOG --------------
        HBox hBox3 = new HBox();
        vBox.getChildren().add(hBox3);
        Label_cog =
                TextBuilder.create()
                .text(cogStr)
                .fill(GPS_GREEN)
                .font(labelFont)
                .textAlignment(TextAlignment.CENTER)
                .build();
        hBox3.getChildren().add(Label_cog);
        HBox hBox4 = new HBox();
        vBox.getChildren().add(hBox4);
        cog =
                TextBuilder.create()
                .text(lon_temp)
                .fill(GPS_GREEN)
                .font(valueFont)
                .textAlignment(TextAlignment.CENTER)
                .build();
        hBox4.getChildren().add(cog);


        Label_sog =
                TextBuilder.create()
                .text(sogStr)
                .fill(GPS_GREEN)
                .font(labelFont)
                .textAlignment(TextAlignment.CENTER)
                .build();
        hBox3.getChildren().add(Label_sog);
        sog =
                TextBuilder.create()
                .text(sog_temp)
                .fill(GPS_GREEN)
                .font(valueFont)
                .textAlignment(TextAlignment.CENTER)
                .build();
        hBox4.getChildren().add(sog);
//--------------------------------------------------------------
        HBox hBox00 = new HBox();
        vBox.getChildren().add(hBox00);
        final Text empty1 = new Text(" \n    ");
        hBox00.getChildren().add(empty1);
//--------------------------------------------------------------
        //------------- Date heure --------------
        HBox hBox5 = new HBox();
        vBox.getChildren().add(hBox5);
        Label_ymd =
                TextBuilder.create()
                .text(dateStr)
                .fill(GPS_GREEN)
                .font(labelFont)
                .textAlignment(TextAlignment.CENTER)
                .build();
        hBox5.getChildren().add(Label_ymd);
        ymd =
                TextBuilder.create()
                .text(ymd_temp)
                .fill(GPS_GREEN)
                .font(labelFont)
                .textAlignment(TextAlignment.CENTER)
                .build();
        HBox hBox6 = new HBox();
        vBox.getChildren().add(hBox6);
        hBox6.getChildren().add(ymd);

        Label_utc =
                TextBuilder.create()
                .text(utcStr)
                .fill(GPS_GREEN)
                .font(labelFont)
                .textAlignment(TextAlignment.CENTER)
                .build();
        hBox5.getChildren().add(Label_utc);


        utc =
                TextBuilder.create()
                .text(utc_temp)
                .fill(GPS_GREEN)
                .font(valueFont)
                .textAlignment(TextAlignment.CENTER)
                .build();
        hBox6.getChildren().add(utc);

        /*ymd = new Text(dateStr+ "     -  -    ");
         ymd.setFont(Font.font(typo, 16));
         ymd.setFill(Color.rgb(r, g, b));
         hBox6.getChildren().add(ymd);

         utc = new Text(utcStr+ "     :  :  ");
         utc.setFont(Font.font(typo, 16));
         utc.setFill(Color.rgb(r, g, b));
         hBox6.getChildren().add(utc);*/

        // vBox.getChildren().addAll(hBox1,hBox2,hBox0,hBox3,hBox4,hBox00,hBox6);  

        // Test affichage statique

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                latitude.setText(/*latStr + */"06° 12' 20\" N");
                longitude.setText(/*lonStr + */"50° 45' 28\" W");
                cog.setText(/*cogStr + */" 125" + cogUnit);
                sog.setText(/*sogStr + */"8.2" + sogUnit);
                utc.setText(/*utcStr + */"    17:36:30");
                ymd.setText(/*dateStr + */"06/27/2013");
            }
        });

    }

    public void initMenu() {
    }

    public void setDate(final Date dt) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //System.out.println("dat : " + dt.getTime());
                String ymd_temp;
                ymd_temp = new SimpleDateFormat("  dd-MM-yy").format(dt);
                ymd.setText(ymd_temp);
                String utc_temp;
                utc_temp = new SimpleDateFormat("    HH:mm:ss").format(dt);
                utc.setText(utc_temp);
            }
        });
    }

    public void setCog(final float val) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                String cog_temp;
                cog_temp = /*cogStr + */ new Float(val).toString() + cogUnit;
                cog.setText(cog_temp);
            }
        });
    }

    public void setSog(final float val) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                String sog_temp;
                sog_temp = /*sogStr + */ new Float(val).toString() + sogUnit;
                sog.setText(sog_temp);
            }
        });
    }

    public void setLocation(final float lat, final float lon) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                String NS;
                float tmp = lat;
                if (tmp >= 0) {
                    NS = "N";
                } else {
                    NS = "S";
                    tmp = -tmp;
                }
                float[] l = Utils.degToHms(tmp);
                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(2);
                String lat_temp;
                lat_temp = /*latStr + */ String.format("%02d", (int) l[0]) + "° "
                        + String.format("%02d", (int) l[1]) + "' "
                        + df.format(l[2]) + "\" "
                        + NS;


                latitude.setText(lat_temp);

                tmp = lon;
                String EW;
                if (tmp >= 0) {
                    EW = "E";
                } else {
                    EW = "W";
                    tmp = -tmp;
                }
                l = Utils.degToHms(tmp);
                String lon_temp;
                lon_temp = /*lonStr +*/ String.format("%02d", (int) l[0]) + "° "
                        + String.format("%02d", (int) l[1]) + "' "
                        + df.format(l[2]) + "\" "
                        + EW;
                longitude.setText(lon_temp);
            }
        });

    }
}
