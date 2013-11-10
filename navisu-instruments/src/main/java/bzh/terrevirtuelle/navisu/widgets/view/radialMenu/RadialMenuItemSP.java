/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.widgets.view.radialMenu;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import jfxtras.labs.scene.control.radialmenu.RadialMenuItem;

/**
 *
 * @author Serge Morvan
 */
public class RadialMenuItemSP
        extends RadialMenuItem {

    private Text menuItemText = TextBuilder.create().text("").build();
    private Double textScaleX;
    private Double textScaleY;
    private Double textTranslateX;
    private Double textTranslateY;

    public RadialMenuItemSP(final double menuSize, final String text,
            final Node graphic, final Boolean renderGraphic, 
            final EventHandler<ActionEvent> actionHandler) {
        super(menuSize, graphic, actionHandler);
        
        super.text = text;
        menuItemText = TextBuilder.create()
                .fill(Color.RED)
                .managed(false)
                .textOrigin(VPos.CENTER)
                .onMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                menuItemText.setStroke(Color.POWDERBLUE);
                menuItemText.setScaleX(textScaleX + (textScaleX * 0.25));
                menuItemText.setScaleY(textScaleY + (textScaleY * 0.25));
            }
        })
                .onMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                menuItemText.setStroke(Color.BLUE);
                menuItemText.setScaleX(textScaleX);
                menuItemText.setScaleY(textScaleY);
            }
        })
                .text(super.text)
                .build();
        textScaleX = menuItemText.getScaleX();
        textScaleY = menuItemText.getScaleY();
        menuItemText.setVisible(renderGraphic);
        super.getChildren().add(menuItemText);
        menuItemText.textProperty().bind(new SimpleStringProperty(super.text));
        this.graphic.setVisible(renderGraphic);
        this.redraw();
    }

    @Override
    protected void redraw() {
        super.redraw();
        if (null != menuItemText) {
            calculateTextXY();
            menuItemText.setTranslateX(textTranslateX);
            menuItemText.setTranslateY(textTranslateY);
        }
    }

    private void calculateTextXY() {
        final double graphicAngle = super.startAngle.get() + (super.menuSize / 2.0);
        final double radiusValue = this.radius.get();
        final double innerRadiusValue = this.innerRadius.get();
        final double graphicRadius = innerRadiusValue + (radiusValue - innerRadiusValue) / 2.0;//2.0
        final double textRadius = graphicRadius + (radiusValue - graphicRadius) / 2.0;
        textTranslateX = textRadius * Math.cos(Math.toRadians(graphicAngle)) - (menuItemText.getBoundsInParent().getWidth() / 2.0);

        if (!this.clockwise.get()) {
            textTranslateY = -textRadius * Math.sin(Math.toRadians(graphicAngle)) - (menuItemText.getBoundsInParent().getHeight() / 2.0);
        } else {
            textTranslateY = textRadius * Math.sin(Math.toRadians(graphicAngle)) - (menuItemText.getBoundsInParent().getHeight() / 2.0);
        }
    }
}
