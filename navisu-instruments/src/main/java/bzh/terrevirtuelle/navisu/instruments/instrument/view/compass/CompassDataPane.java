/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.instrument.view.compass;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.animation.TranslateTransitionBuilder;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.GroupBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineBuilder;
import javafx.scene.shape.RectangleBuilder;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBuilder;
import javafx.util.Duration;
import bzh.terrevirtuelle.navisu.instruments.instrument.model.Display;

/**
 *
 * @author Serge Morvan
 */
public class CompassDataPane
        extends CompassPane {

    private Group alidade;
    private String numbers;
    private String numString;
    private List<Line> lines;
    private List<Line> tmpLines;
    private double dist0;
    private double dist;
    private double dist1;
    private double dist2;
    private int x;
    private int x0;
    private int x1;
    private int x2;
    private final String NUM_FORMAT = "%03d" + "%s";
    private Text tmp;
    private Text textRef;
    private Text headingLabel;
    private Text headingValue;
    private Text variationLabel;
    private Text variationValue;
    private Text deviationLabel;
    private Text deviationValue;
    private final Color COMPASS_GREEN = Color.rgb(152, 255, 75);
    private final String VALUE_FONT_NAME = "digital-mono.ttf";
    private final int VALUE_FONT_CORPS = 24;
    private final String LABEL_FONT_NAME = "Roboto-Condensed.ttf";
    private final int LABEL_FONT_CORPS = 13;
    private Font CURRENT_FONT = Font.font("Courrier New", FontWeight.BOLD, 30);
    private Font valueFont;
    private Font labelFont;
    private TranslateTransition transTransition;
    private double previousAlidadeHeading = 0.0;
    private double alidadeHeading;
    private int count = 0;
    private final Duration DURATION = new Duration(1000);

    public CompassDataPane(Display display) {
        super(display);
        setId("dataPane");
        backgroundFileName = "compassValuesBackground.png";
        backgroundNightFileName = "night_compassValuesBackground.png";
        lines = new ArrayList<>();
        tmpLines = new ArrayList<>();
        numbers = new String();
        valueFont = Font.loadFont(getClass().getResource(FONTS + VALUE_FONT_NAME).toExternalForm(), VALUE_FONT_CORPS);
        labelFont = Font.loadFont(getClass().getResource(FONTS + LABEL_FONT_NAME).toExternalForm(), LABEL_FONT_CORPS);
        createScene();
        setHeading(200);
        setVariation(0);
        setDeviation(0);
    }

    private void createScene() {
        createBasicScene();

        tmp = TextBuilder.create()
                .font(CURRENT_FONT)
                .build();
        dist0 = new Text(String.format(NUM_FORMAT, 0, "")).getLayoutBounds().getWidth() / 2;
        tmp.setText("000  ");
        dist2 = tmp.getLayoutBounds().getWidth() / 5;//intervalle 2 deg
        dist1 = dist2 / 2;//intervalle 1 deg
        alidade = new Group();
        textRef = TextBuilder.create()
                .layoutX(-20)
                .layoutY(20)
                .textOrigin(VPos.TOP)
                .textAlignment(TextAlignment.JUSTIFY)
                .text(numbers)
                .fill(Color.BLACK)
                .font(CURRENT_FONT)
                .build();// cache des valeurs 

        alidade.getChildren().addAll(textRef);
        alidade.getChildren().addAll(lines);

        root.getChildren().add(
                GroupBuilder.create()
                .layoutX(40)
                .layoutY(47)
                .children(
                RectangleBuilder.create()
                .layoutX(9)
                .layoutY(11)
                .height(50)
                .width(182)
                .fill(Color.WHITE)
                .build(),//fond rectangle blanc
                GroupBuilder.create()
                .children(alidade)
                .clip(
                RectangleBuilder.create()
                .layoutX(9)
                .height(100)
                .width(182)
                .build())//clip
                .build(),
                LineBuilder.create()
                .startX(100)
                .startY(10)
                .endX(100)
                .endY(60)
                .strokeWidth(3)
                .stroke(Color.RED)
                .build(),//bande verticale rouge
                LineBuilder.create()
                .startX(10)
                .startY(10)
                .endX(190)
                .endY(10)
                .strokeWidth(3)
                .stroke(Color.RED)
                .build(),//bande horizontale rouge
                LineBuilder.create()
                .startX(10)
                .startY(60)
                .endX(190)
                .endY(60)
                .strokeWidth(3)
                .stroke(Color.GREEN)
                .build(),//bande horizontale verte
                ImageViewBuilder.create()
                .image(new Image(rootDir + IMAGES + "display_1.png"))
                .build()//entourage
                )//children
                .build());//root
        headingLabel = TextBuilder.create()
                .text("Heading")
                .layoutX(43)
                .layoutY(180)
                .fill(COMPASS_GREEN)
                .font(labelFont)
                .textAlignment(TextAlignment.CENTER)
                .build();
        root.getChildren().add(headingLabel);
        headingValue = TextBuilder.create()
                .layoutX(44)
                .layoutY(215)
                .fill(COMPASS_GREEN)
                .font(valueFont)
                .textAlignment(TextAlignment.JUSTIFY)
                .build();
        root.getChildren().add(headingValue);
        variationLabel = TextBuilder.create()
                .text("Variation")
                .layoutX(113)
                .layoutY(180)
                .fill(COMPASS_GREEN)
                .font(labelFont)
                .textAlignment(TextAlignment.CENTER)
                .build();
        root.getChildren().add(variationLabel);
        variationValue = TextBuilder.create()
                .layoutX(116)
                .layoutY(215)
                .fill(COMPASS_GREEN)
                .font(valueFont)
                .textAlignment(TextAlignment.CENTER)
                .build();
        root.getChildren().add(variationValue);
        deviationLabel = TextBuilder.create()
                .text("Deviation")
                .layoutX(182)
                .layoutY(180)
                .fill(COMPASS_GREEN)
                .font(labelFont)
                .textAlignment(TextAlignment.CENTER)
                .build();
        root.getChildren().add(deviationLabel);
        deviationValue = TextBuilder.create()
                .layoutX(187)
                .layoutY(215)
                .fill(COMPASS_GREEN)
                .font(valueFont)
                .textAlignment(TextAlignment.CENTER)
                .build();
        root.getChildren().add(deviationValue);
    }

    public void initMenu() {
    }

    public final void setHeading(final float data) {
        numString = "";
        numbers = "";
        lines.clear();
        tmpLines.clear();
        tmp.setText("");

        if (count == 2) {
            alidade.getChildren().remove(31, 91);//on retire, n'en rajouter que la moitie
        } else {
            count++;
        }
        x = (int) data;
        x1 = x;
        if (x1 < 10) {
            x1 = 10;
        } else {
            x1 = x1 + 10 - x1 % 10;
        }
        x0 = x1 - 20;
        x2 = x1 + 30;//30
        for (int i = x0; i < x2; i += 10) {
            if (i < 0) {
                numString = String.format(NUM_FORMAT, 350, "  ");
            } else {
                numString = String.format(NUM_FORMAT, i % 360, "  ");
            }
            numbers = numbers.concat(numString);
            tmp.setText(numbers);

            tmpLines.add(LineBuilder.create()
                    .startX(dist)
                    .startY(13)
                    .endX(dist)
                    .endY(25)
                    .strokeWidth(2)
                    .stroke(Color.BLACK)
                    .build());//lignes verticales 10 degres
            for (int j = 1; j < 6; j++) {
                tmpLines.add(LineBuilder.create()
                        .startX(dist + j * dist2)
                        .startY(13)
                        .endX(dist + j * dist2)
                        .endY(17)
                        .strokeWidth(2)
                        .stroke(Color.BLACK)
                        .build());//lignes 2 degres
                tmpLines.add(LineBuilder.create()
                        .startX(dist + j * dist2 - dist1)
                        .startY(13)
                        .endX(dist + j * dist2 - dist1)
                        .endY(14)
                        .strokeWidth(2)
                        .stroke(Color.BLACK)
                        .build());//lignes 1 degres
            }
            tmpLines.add(LineBuilder.create()
                    .startX(dist + 5 * dist2 - dist1)
                    .startY(13)
                    .endX(dist + 5 * dist2 - dist1)
                    .endY(14)
                    .strokeWidth(2)
                    .stroke(Color.BLACK)
                    .build());//lignes 1 degres, dernier intervalle

            //calcul précis de la longueur de texte
            dist = dist0 + tmp.getLayoutBounds().getWidth();
        }
        textRef.setText(numbers);

        lines.addAll(tmpLines);
        alidade.getChildren().addAll(lines);
        alidadeHeading = 98 + (x0 - x) * 7.4;
        transTransition = TranslateTransitionBuilder.create()
                .duration(DURATION)
                .node(alidade)
                .fromX(previousAlidadeHeading)
                .toX(alidadeHeading)
                .interpolator(Interpolator.LINEAR)
                .build();

        transTransition.play();
        previousAlidadeHeading = alidadeHeading;
        if (previousAlidadeHeading <= -36) {
            previousAlidadeHeading = 24;
        }
        headingValue.setText(String.format(NUM_FORMAT, new Integer((int) data), ""));
        headingValue.setFont(valueFont);
    }

    public final void setVariation(final float data) {
        variationValue.setText(String.format(NUM_FORMAT, new Integer((int) data), ""));
        variationValue.setFont(valueFont);
    }

    public final void setDeviation(final float data) {
        deviationValue.setText(String.format(NUM_FORMAT, new Integer((int) data), ""));
        deviationValue.setFont(valueFont);
    }
}
