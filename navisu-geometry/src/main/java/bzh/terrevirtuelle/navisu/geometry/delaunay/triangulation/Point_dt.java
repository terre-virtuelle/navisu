package bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation;

import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.util.UtDouble;
import java.util.Comparator;

/**
 * This class represents A 3D point, with some simple geometric methods
 * (pointLineTest).
 */
public class Point_dt {

    //todo: set z type to Integer
    public double x, y, z;

    /**
     * Default Constructor. <br />
     * constructs A 3D point at (0,0,0).
     */
    public Point_dt() {
        this(0, 0);
    }

    @Override
    public int hashCode() {
        long bits = java.lang.Double.doubleToLongBits(x);
        bits ^= java.lang.Double.doubleToLongBits(y) * 31;
        return (((int) bits) ^ ((int) (bits >> 32)));
    }

    @Override
    public boolean equals(Object o) {
        return equals2D(o);
    }

    private boolean equals2D(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null) {
            return false;
        }

        if (o instanceof Point_dt) {
            Point_dt other = (Point_dt) o;
            return other.x == x && other.y == y;
        }

        return false;
    }

    public boolean fuzzyEquals(Object o, double tolerance) {
        if (o == this) {
            return true;
        }
        if (o == null) {
            return false;
        }

        if (o instanceof Point_dt) {
            Point_dt other = (Point_dt) o;
          //  System.out.println("x : "+ x +" "+other.x);
            return other.fuzzyEquals(x, tolerance) && other.fuzzyEquals(y, tolerance);
        }
        return false;
    }

    private boolean equals3D(Object o) {
        if (equals2D(o)) {
            return ((Point_dt) o).z == z;
        }
        return false;
    }

    /**
     * constructs A 3D point
     */
    public Point_dt(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * constructs A 3D point with A z value of 0.
     */
    public Point_dt(double x, double y) {
        this(x, y, 0);
    }

    /**
     * simple copy constructor
     */
    public Point_dt(Point_dt p) {
        x = p.x;
        y = p.y;
        z = p.z;
    }

    boolean isLess(Point_dt p) {
        return (x < p.x) || ((x == p.x) && (y < p.y));
    }

    boolean isGreater(Point_dt p) {
        return (x > p.x) || ((x == p.x) && (y > p.y));
    }

    /**
     * return A String in the [x,y,z] format
     */
    public String toString() {
        return (" Pt[" + x + "," + y + "," + z + "]");
    }

    double distance2(Point_dt p) {
        return distance2(p.x, p.y);
    }

    double distance2(double px, double py) {
        return (px - x) * (px - x) + (py - y) * (py - y);
    }

    /**
     * @return the L2 distanse NOTE: 2D only!!!
     */
    public double distance(Point_dt p) {
        double temp = Math.pow(p.x - x, 2) + Math.pow(p.y - y, 2);
        return Math.sqrt(temp);
    }

    /**
     * @return the L2 distanse NOTE: 2D only!!!
     */
    public double distance3D(Point_dt p) {
        double temp = Math.pow(p.x - x, 2) + Math.pow(p.y - y, 2)
                + Math.pow(p.z - z, 2);
        return Math.sqrt(temp);
    }

    /**
     * return A String: x y z (used by the save to file - write_tsin method).
     */
    public String toFile() {
        return ("" + x + " " + y + " " + z);
    }

    String toFileXY() {
        return ("" + x + " " + y);
    }

    // pointLineTest
    // ===============
    // simple geometry to make things easy!
    /**
     * A----+----B
     */
    public final static int ONSEGMENT = 0;

    /**
     * + <br>
     * A---------B
     */
    public final static int LEFT = 1;

    /**
     * A---------B <br> +
     */
    public final static int RIGHT = 2;
    /**
     * +A---------B
     */
    public final static int INFRONTOFA = 3;
    /**
     * A---------B+
     */
    public final static int BEHINDB = 4;
    public final static int ERROR = 5;

    /**
     * tests the relation between this point (as A 2D [x,y] point) and A 2D
     * segment A,B (the Z values are ignored), returns one of the following:
     * LEFT, RIGHT, INFRONTOFA, BEHINDB, ONSEGMENT
     *
     * @param a the first point of the segment.
     * @param b the second point of the segment.
     * @return the value (flag) of the relation between this point and the A,B
     * line-segment.
     */
    public int pointLineTest(Point_dt a, Point_dt b) {

        double dx = b.x - a.x;
        double dy = b.y - a.y;
        double res = dy * (x - a.x) - dx * (y - a.y);

        if (!UtDouble.isEqual(res, 0)) {
            if (res < 0) {
                return LEFT;
            }
            return RIGHT;
        }

        if (dx > 0) {
            if (x < a.x) {
                return INFRONTOFA;
            }
            if (b.x < x) {
                return BEHINDB;
            }
            return ONSEGMENT;
        }
        if (dx < 0) {
            if (x > a.x) {
                return INFRONTOFA;
            }
            if (b.x > x) {
                return BEHINDB;
            }
            return ONSEGMENT;
        }
        if (dy > 0) {
            if (y < a.y) {
                return INFRONTOFA;
            }
            if (b.y < y) {
                return BEHINDB;
            }
            return ONSEGMENT;
        }
        if (dy < 0) {
            if (y > a.y) {
                return INFRONTOFA;
            }
            if (b.y > y) {
                return BEHINDB;
            }
            return ONSEGMENT;
        }
        System.out.println("Error, pointLineTest with A=B");
        return ERROR;
    }

    boolean areCollinear(Point_dt a, Point_dt b) {
        double dx = b.x - a.x;
        double dy = b.y - a.y;
        double res = dy * (x - a.x) - dx * (y - a.y);
        return res == 0;
    }

    /*
     * public ajSegment Bisector( ajPoint B) { double sx = (x+B.x)/2; double sy
	 * = (y+B.y)/2; double dx = B.x-x; double dy = B.y-y; ajPoint p1 = new
	 * ajPoint(sx-dy,sy+dx); ajPoint p2 = new ajPoint(sx+dy,sy-dx); return new
	 * ajSegment( p1,p2 ); }
     */
    @SuppressWarnings("unchecked")
    Point_dt circumcenter(Point_dt a, Point_dt b) {

        double u = ((a.x - b.x) * (a.x + b.x) + (a.y - b.y) * (a.y + b.y)) / 2.0f;
        double v = ((b.x - x) * (b.x + x) + (b.y - y) * (b.y + y)) / 2.0f;
        double den = (a.x - b.x) * (b.y - y) - (b.x - x) * (a.y - b.y);
        if (den == 0) // oops
        {
            System.out.println("circumcenter, degenerate case");
        }
        return new Point_dt((u * (b.y - y) - v * (a.y - b.y)) / den, (v
                * (a.x - b.x) - u * (b.x - x))
                / den);
    }

    @SuppressWarnings("unchecked")
    public static Comparator<Point_dt> getComparator(int flag) {
        return new Compare(flag);
    }

    @SuppressWarnings("unchecked")
    public static Comparator<Point_dt> getComparator() {
        return new Compare(0);
    }
}

class Compare implements Comparator {

    private int _flag;

    public Compare(int i) {
        _flag = i;
    }

    /**
     * compare between two points.
     */
    public int compare(Object o1, Object o2) {
        int ans = 0;
        if (o1 != null && o2 != null && o1 instanceof Point_dt
                && o2 instanceof Point_dt) {
            Point_dt d1 = (Point_dt) o1;
            Point_dt d2 = (Point_dt) o2;
            if (_flag == 0) {
                if (d1.x > d2.x) {
                    return 1;
                }
                if (d1.x < d2.x) {
                    return -1;
                }
                // x1 == x2
                if (d1.y > d2.y) {
                    return 1;
                }
                if (d1.y < d2.y) {
                    return -1;
                }
            } else if (_flag == 1) {
                if (d1.x > d2.x) {
                    return -1;
                }
                if (d1.x < d2.x) {
                    return 1;
                }
                // x1 == x2
                if (d1.y > d2.y) {
                    return -1;
                }
                if (d1.y < d2.y) {
                    return 1;
                }
            } else if (_flag == 2) {
                if (d1.y > d2.y) {
                    return 1;
                }
                if (d1.y < d2.y) {
                    return -1;
                }
                // y1 == y2
                if (d1.x > d2.x) {
                    return 1;
                }
                if (d1.x < d2.x) {
                    return -1;
                }

            } else if (_flag == 3) {
                if (d1.y > d2.y) {
                    return -1;
                }
                if (d1.y < d2.y) {
                    return 1;
                }
                // y1 == y2
                if (d1.x > d2.x) {
                    return -1;
                }
                if (d1.x < d2.x) {
                    return 1;
                }
            }
        } else {
            if (o1 == null && o2 == null) {
                return 0;
            }
            if (o1 == null) {
                return 1;
            }
            if (o2 == null) {
                return -1;
            }
        }
        return ans;
    }

    @Override
    public boolean equals(Object ob) {
        return false;
    }
}
