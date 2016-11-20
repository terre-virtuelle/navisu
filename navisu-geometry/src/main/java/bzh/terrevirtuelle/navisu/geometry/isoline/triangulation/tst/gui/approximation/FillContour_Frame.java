package bzh.terrevirtuelle.navisu.geometry.isoline.triangulation.tst.gui.approximation;

import bzh.terrevirtuelle.navisu.geometry.isoline.triangulation.Contour;
import bzh.terrevirtuelle.navisu.geometry.isoline.triangulation.Delaunay_Triangulation;
import bzh.terrevirtuelle.navisu.geometry.isoline.triangulation.Point_dt;
import bzh.terrevirtuelle.navisu.geometry.isoline.triangulation.Triangle_dt;
import bzh.terrevirtuelle.navisu.geometry.isoline.triangulation.util.DT;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.util.*;

public class FillContour_Frame {

    public static void main(String[] args) {
        int count = 50;
        final Random rnd = new Random(948731674);

        Delaunay_Triangulation dt = new Delaunay_Triangulation();

        int xMax = 700;
        final int yMax = 700;
        final int zMax = 40;
        for (int i = 0; i < count; i++) {
            double x = rnd.nextInt(xMax);
            double y = rnd.nextInt(yMax);
            double z = rnd.nextInt(zMax);

            Point_dt point_dt = new Point_dt(x, y, z);
            dt.insertPoint(point_dt);
        }

        double avgEdgeLength = dt.avgEdgeLength();
        dt.divideTillEpsilon( avgEdgeLength);
        final ArrayList<Triangle_dt> convexHull = dt.convexHull();
        final ArrayList<Contour> contours = DT.contours(dt, 5);

        class MyPanel extends JPanel {
            int contourNum = 0;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                g.setColor(Color.BLACK);
                int k = 1;
                for (Contour c : contours) {
                    g.drawString("A " + k + "-" + c.level, (int) c.getA().x, (int) c.getA().y);
                    g.drawString("B " + k + "-" + c.level, (int) c.getB().x, (int) c.getB().y);
                    k++;
                }
                k = 1;
                g.setColor(Color.RED);
                for (Triangle_dt t : convexHull) {
                    g.drawString(" " + k, (int) (t.A.x + t.B.x) / 2 + 5, (int) (t.A.y + t.B.y) / 2 + 5);
                    k++;
                }


                if ((contourNum >= 0) && (contourNum <= (contours.size() - 1))) {
                    Contour c = contours.get(contourNum);

                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.setColor(Color.GREEN);
                    ListIterator<Point_dt> itr = c.points.listIterator();
                    Point_dt a = itr.next();//throws Exception if points size = 0
                    Path2D poly = new Path2D.Double();
                    boolean isFirst = true;
                    while (a != null && itr.hasNext()) {
                        Point_dt b = itr.next();
                        double x = a.x;
                        double y = a.y;

                        if (isFirst) {
                            poly.moveTo(x, y);
                            isFirst = false;
                        } else {
                            poly.lineTo(x, y);
                        }
                        a = b;
                    }
                    poly.closePath();
                    Shape shape = new Area(poly);
                    int gg = rnd.nextInt(250);
                    int r = rnd.nextInt(250);
                    int B = rnd.nextInt(250);
                    g.setColor(new Color(r, gg, B));
                    g2d.fill(shape);
                    g2d.dispose();

                    g.setColor(Color.BLUE);
                    g.drawString("" + c.level, (int) c.getA().x, (int) c.getA().y);
                }
            }

            public void upd(int num) {
                contourNum = num;
                revalidate();
                repaint();
            }
        }


        JFrame frame = new JFrame();

        final MyPanel viewer = new MyPanel();

        frame.getContentPane().
                add(viewer, BorderLayout.CENTER);
        final JSpinner spinner = new JSpinner();
        spinner.setBounds(50, 60, 80, 30);

        spinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent changeEvent) {
                viewer.upd((Integer) spinner.getValue());
            }
        });

        frame.getContentPane().
                add(spinner, BorderLayout.EAST);


//        viewer.add(spinner);

        frame.setDefaultCloseOperation(3);
        frame.setSize(new

                        Dimension(900, 780)

        );
        frame.setTitle("Interpolate_Frame");
        frame.setVisible(true);
    }

}
