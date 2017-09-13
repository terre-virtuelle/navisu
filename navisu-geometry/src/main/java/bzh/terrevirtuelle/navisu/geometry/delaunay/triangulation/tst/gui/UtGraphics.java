 package bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.tst.gui;

import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.Point_dt;
import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.Triangle_dt;

import java.awt.*;
import java.util.ArrayList;

public class UtGraphics {

    public static void drawTriangle(Graphics g, Triangle_dt t, Color cl) {

        if (cl != null)
            g.setColor(cl);
        if (t.isHalfplane()) {
            if (cl == null)
                g.setColor(Color.blue);
            drawLine(g, t.A, t.B);
//            drawLine(g, new Point_dt(t.A.x + 1, t.A.y + 1), new Point_dt(t.B.x + 1, t.B.y + 1));
        } else {
            if (cl == null)
                g.setColor(Color.black);
            drawLine(g, t.A, t.B);
            drawLine(g, t.B, t.C);
            drawLine(g, t.C, t.A);
        }
    }

    /**
     * Draws A polygon represented by Point_dt points
     * <p/>
     * By Udi Schneider
     */
    public static void drawPolygon(Graphics g, Point_dt[] polygon) {
        int[] x = new int[polygon.length];
        int[] y = new int[polygon.length];
        for (int i = 0; i < polygon.length; i++) {
            x[i] = (int) polygon[i].x;
            y[i] = (int) polygon[i].y;
        }
        g.drawPolygon(x, y, polygon.length);
    }

    public static void drawPolygon(Graphics g, ArrayList<Point_dt> polygon) {
        int[] x = new int[polygon.size()];
        int[] y = new int[polygon.size()];
        for (int i = 0; i < polygon.size(); i++) {
            x[i] = (int) polygon.get(i).x;
            y[i] = (int) polygon.get(i).y;
        }
        g.drawPolygon(x, y, polygon.size());
    }

    public static void fillPolygon(Graphics g, ArrayList<Point_dt> polygon) {
        int[] x = new int[polygon.size()];
        int[] y = new int[polygon.size()];
        for (int i = 0; i < polygon.size(); i++) {
            x[i] = (int) polygon.get(i).x;
            y[i] = (int) polygon.get(i).y;
        }
        g.fillPolygon(x, y, polygon.size());
    }

    public static void drawLine(Graphics g, Point_dt p1, Point_dt p2) {
        // g.drawLine((int)p1.x(), (int)p1.y(), (int)p2.x(), (int)p2.y());

        g.drawLine((int) p1.x, (int) p1.y, (int) p2.x, (int) p2.y);
    }

    public static void drawPoint(Graphics g, Point_dt p1, Color cl) {
        drawPoint(g, p1, 4, cl);
    }

    public static void drawPoint(Graphics g, Point_dt p1, int r, Color cl) {
        // g.drawLine((int)p1.x(), (int)p1.y(), (int)p2.x(), (int)p2.y());

        g.setColor(cl);
        g.fillOval((int) p1.x - r / 2, (int) p1.y - r / 2, r, r);
    }

    public static void drawZ(Graphics g, Triangle_dt t, Color c, Object o1) {
        if (t.isHalfplane()) return;
        Color tmp = null;

        if (c != null) {
            tmp = g.getColor();
            g.setColor(c);
        }
        g.drawString((int) t.A.z + "", (int) t.A.x - 2, (int) t.A.y - 1);
        g.drawString((int) t.B.z + "", (int) t.B.x - 2, (int) t.B.y - 1);
        g.drawString((int) t.C.z + "", (int) t.C.x - 2, (int) t.C.y - 1);

        if (tmp != null) {
            g.setColor(tmp);
        }

    }

    public static void drawLine(Graphics g, Point_dt p0, Point_dt p1, int i) {
        p0 = new Point_dt(p0.x + i, p0.y + i, p0.z + i);
        p1 = new Point_dt(p1.x + i, p1.y + i, p1.z + i);
        drawLine(g, p0, p1);
    }
}
