package bzh.terrevirtuelle.navisu.instruments.ais.aisradar.impl.controller;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * NaVisu
 *
 * @date 4 mai 2015
 * @author Serge Morvan
 */
public class GhostLine
        extends Group {

    double centerX;
    double centerY;
    int radius;
    int radiusLimit;
    double x;
    double y;
    Line line;
    Text text;
    double angle;
    Circle circle;

    public GhostLine(double centerX, double centerY, int radius, int radiusLimit) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radiusLimit = radiusLimit;
        circle = new Circle(centerX, centerY, radius);
        circle.setFill(Color.AZURE);
        getChildren().add(circle);

    }

    public void show(double x, double y) {
        clear();
        computeRadius(centerX, centerY, x, y);
        if (radius < radiusLimit) {
            computeAngle(centerX, centerY, x, y);
            line = new Line(centerX, centerY, x, y);
            line.setStroke(Color.web("0x04fff2"));
            line.setStrokeWidth(1);
            getChildren().add(line);
            text = new Text(Integer.toString((int) angle) + "°");
            text.setX(15 + x);
            text.setY(y);
            text.setFill(Color.web("0x04fff2"));
            text.setFont(Font.font("Roboto Cn", 22));
            getChildren().add(text);
        }
    }

    public void clear() {
        getChildren().remove(line);
        getChildren().remove(text);
    }

    private void computeAngle(double cx, double cy, double x, double y) {
        angle = -Math.toDegrees(Math.atan2(cx - x, cy - y));
        if (angle < 0) {
            angle = 360 + angle;
        }

    }

    private void computeRadius(double cx, double cy, double x, double y) {
        radius = (int) Math.sqrt((cx - x) * (cx - x) + (cy - y) * (cy - y));
    }
}
