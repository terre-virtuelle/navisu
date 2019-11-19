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
        /*
        Point3D[] points = new Point3D[]{
            new Point3D(0, 0, 0),
            new Point3D(1.0, 1.0, 0.0),
            new Point3D(3.0, 2.0, 0),
            new Point3D(4.0, 1.0, 0),
            new Point3D(5.0, -1.0, 0)
        };
         */

        int deg = 2;
        List<Point3D> points = new ArrayList<>();
        points.add(new Point3D(0, 0, 0));
        points.add(new Point3D(1.0, 1.0, 0.0));
        points.add(new Point3D(3.0, 2.0, 0));
        points.add(new Point3D(4.0, 1.0, 0));
        points.add(new Point3D(5.0, -1.0, 0));
        
        double[] knots = new double[points.size() + deg + 1];
        int n = points.size(); //5
        knots[0] = 0.0;
        knots[1] = 0.0;
        knots[deg] = 0.0; // deg // 2
        knots[deg + 1] = 1.0;
        knots[deg + 2] = 2.0;
        knots[deg + 3] = 3.0; // n 
        knots[deg + 4] = 3.0;
        knots[deg + 5] = 3.0;  // n + deg 

        double[] w = new double[points.size()];
        for (int i = 0; i < w.length; i++) {
            w[i] = 1.0;
        }

        BSpline bSpline = new BSpline(points, knots, w, deg);

        // Visualization
        double inc = .001;
        Point3D p = new Point3D(0, 0, 0);
        Point3D stop = new Point3D(5.0, -1.0, 0);
        double x = 0.0;
        List<Point3D> pts = new ArrayList<>();
        while (!p.equals(stop)) {
            p = bSpline.getPointAt(x);
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

        Path pathB = new Path();
        pathB.getElements().add(new MoveTo(0.0f, 0.0f));
        pts.stream().map((pt3D) -> {
            return pt3D;
        }).forEachOrdered((pt3D) -> {
            pathB.getElements().add(new LineTo(pt3D.getX() * 100, pt3D.getY() * 100));
        });

        ((Group) scene.getRoot()).getChildren().add(path);
        ((Group) scene.getRoot()).getChildren().add(pathB);

        stage.setScene(scene);
        stage.show();
    }
}
