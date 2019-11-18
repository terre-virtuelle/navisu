package bzh.terrevirtuelle.navisu.geometry.curves3D.bsplines.app;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author serge
 */
import bzh.terrevirtuelle.navisu.geometry.curves3D.bsplines.impl.BSpline;
import bzh.terrevirtuelle.navisu.geometry.objects3D.Point3D;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

public class Main
        extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("BSpline Sample");
        stage.setWidth(600);
        stage.setHeight(400);

        Point3D[] points = new Point3D[]{
            new Point3D(0, 0, 0),
            new Point3D(1.0, 1.0, 0.0),
            new Point3D(3.0, 2.0, 0),
            new Point3D(4.0, 1.0, 0),
            new Point3D(5.0, -1.0, 0)
        };
        double[] knots = new double[]{0.0, 0.0, 0.0, 1.0, 2, 3.0, 3.0, 3.0};
        double[] w = new double[]{1.0, 1.0, 1.0, 1.0, 1.0};

        BSpline n = new BSpline(points, knots, w, 2);

        double inc = .001;
        Point3D p = new Point3D(0, 0, 0);
        Point3D stop = new Point3D(5.0, -1.0, 0);
        double x = 0.0;
        List<Point3D> pts = new ArrayList<>();
        while (!p.equals(stop)) {
            p = n.getPointAt(x);
            System.out.println(p);
            pts.add(p);
            x += inc;
        }

        Path path = new Path();
        path.getElements().add(new MoveTo(0.0f, 0.0f));
        path.getElements().add(new LineTo(100.0f, 100.0f));
        path.getElements().add(new LineTo(300.0f, 200.0f));
        path.getElements().add(new LineTo(400f, 100.0f));
        path.getElements().add(new LineTo(500.0f, -100.0f));

        Path bSpline = new Path();
        bSpline.getElements().add(new MoveTo(0.0f, 0.0f));
        pts.stream().map((pt3D) -> {
            return pt3D;
        }).forEachOrdered((pt3D) -> {
            bSpline.getElements().add(new LineTo(pt3D.getX() * 100, pt3D.getY() * 100));
        });

        ((Group) scene.getRoot()).getChildren().add(path);
        ((Group) scene.getRoot()).getChildren().add(bSpline);

        stage.setScene(scene);
        stage.show();
    }
}
