package bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation;

/**
 * This class represents A 3D triangle in A Triangulation!
 */
public class Triangle_dt {

    public Point_dt A, B, C;
    public Triangle_dt abnext, bcnext, canext;
    Circle_dt circum;
    int _mc = 0; // modcounter for triangulation fast update.

    boolean halfplane = false; // true iff it is an infinite face.
    //	public boolean visitflag;
    boolean _mark = false;   // tag - for bfs algorithms
    //	private static boolean visitValue=false;
    public static int _counter = 0, _c2 = 0;
    public boolean visited;
    //public int _id;

    /**
     * constructs A triangle form 3 point - store it in counterclockwised order.
     *
     * @param A
     * @param B
     * @param C
     */
    public Triangle_dt(Point_dt A, Point_dt B, Point_dt C) {
//		visitflag=visitValue;
        this.A = A;
        int res = C.pointLineTest(A, B);
        if ((res <= Point_dt.LEFT)
                || (res == Point_dt.INFRONTOFA)
                || (res == Point_dt.BEHINDB)) {
            this.B = B;
            this.C = C;
        } else {  // RIGHT
            System.out.println("Warning, ajTriangle(A,B,C) "
                    + "expects points in counterclockwise order.");
            System.out.println("" + A + B + C);
            this.B = C;
            this.C = B;
        }
        circumcircle();
        //_id = _counter++;
        //_counter++;_c2++;
        //if(_counter%10000 ==0) System.out.println("Triangle: "+_counter);
    }

    /**
     * creates A half plane using the segment (A,B).
     *
     * @param A
     * @param B
     */
    public Triangle_dt(Point_dt A, Point_dt B) {
//		visitflag=visitValue;
        this.A = A;
        this.B = B;
        halfplane = true;
//		_id = _counter++;
    }

    /*	protected void finalize() throws Throwable{
        super.finalize();
		_counter--;
	} */
    /**
     * remove all pointers (for debug)
     */
    //public void clear() {
    //	this.abnext = null; this.bcnext=null; this.canext=null;}
    /**
     * returns true iff this triangle is actually A half plane.
     */
    public boolean isHalfplane() {
        return this.halfplane;
    }

    /**
     * @return The bounding rectange between the minimum and maximum coordinates
     * of the triangle
     */
    public BoundingBox getBoundingBox() {
        Point_dt lowerLeft = null, upperRight = null;
        if (A != null && B != null && C != null) {
            lowerLeft = new Point_dt(Math.min(A.x, Math.min(B.x, C.x)), Math.min(A.y, Math.min(B.y, C.y)));
            upperRight = new Point_dt(Math.max(A.x, Math.max(B.x, C.x)), Math.max(A.y, Math.max(B.y, C.y)));
        }
        return new BoundingBox(lowerLeft, upperRight);
    }

    void switchneighbors(Triangle_dt Old, Triangle_dt New) {
        if (abnext == Old) {
            abnext = New;
        } else if (bcnext == Old) {
            bcnext = New;
        } else if (canext == Old) {
            canext = New;
        } else {
            System.out.println("Error, switchneighbors can't find Old.");
        }
    }

    Triangle_dt neighbor(Point_dt p) {
        if (A == p) {
            return canext;
        }
        if (B == p) {
            return abnext;
        }
        if (C == p) {
            return bcnext;
        }
        System.out.println("Error, neighbors can't find p: " + p);
        return null;
    }

    /**
     * Returns the neighbors that shares the given corner and is not the
     * previous triangle.
     *
     * @param p The given corner
     * @param prevTriangle The previous triangle.
     * @return The neighbors that shares the given corner and is not the
     * previous triangle.
     * <p/>
     * By: Eyal Roth & Doron Ganel.
     */
    Triangle_dt nextNeighbor(Point_dt p, Triangle_dt prevTriangle) {
        Triangle_dt neighbor = null;

        if (A.equals(p)) {
            neighbor = canext;
        }
        if (B.equals(p)) {
            neighbor = abnext;
        }
        if (C.equals(p)) {
            neighbor = bcnext;
        }

        // Udi Schneider: Added A condition check for isHalfPlane. If the current
        // neighbor is A half plane, we also want to move to the next neighbor
        if (neighbor.equals(prevTriangle) || neighbor.isHalfplane()) {
            if (A.equals(p)) {
                neighbor = abnext;
            }
            if (B.equals(p)) {
                neighbor = bcnext;
            }
            if (C.equals(p)) {
                neighbor = canext;
            }
        }

        return neighbor;
    }

    public Circle_dt circumcircle() {

        double u = ((A.x - B.x) * (A.x + B.x) + (A.y - B.y) * (A.y + B.y)) / 2.0f;
        double v = ((B.x - C.x) * (B.x + C.x) + (B.y - C.y) * (B.y + C.y)) / 2.0f;
        double den = (A.x - B.x) * (B.y - C.y) - (B.x - C.x) * (A.y - B.y);
        if (den == 0) // oops, degenerate case
        {
            circum = new Circle_dt(A, Double.POSITIVE_INFINITY);
        } else {
            Point_dt cen = new Point_dt((u * (B.y - C.y) - v * (A.y - B.y)) / den,
                    (v * (A.x - B.x) - u * (B.x - C.x)) / den);
            circum = new Circle_dt(cen, cen.distance2(A));
        }
        return circum;
    }

    public boolean circumcircle_contains(Point_dt p) {
        if (circum != null) {
            return circum.Radius() > circum.Center().distance2(p);
        } else {
            return false;
        }
    }

    public String toString() {
        String res = ""; //+_id+") ";
        res += A.toString() + B.toString();
        if (!halfplane) {
            res += C.toString();
        }
        // res +=C.toString() +"   | "+abnext._id+" "+bcnext._id+" "+canext._id;
        return res;
    }

    /**
     * determinates if this triangle contains the point p.
     *
     * @param p the query point
     * @return true iff p is not null and is inside this triangle (Note: on
     * boundary is considered inside!!).
     */
    public boolean contains(Point_dt p) {
        boolean ans = false;
        if (this.halfplane | p == null) {
            return false;
        }

        if (isCorner(p)) {
            return true;
        }

        int a12 = p.pointLineTest(A, B);
        int a23 = p.pointLineTest(B, C);
        int a31 = p.pointLineTest(C, A);

        if ((a12 == Point_dt.LEFT && a23 == Point_dt.LEFT && a31 == Point_dt.LEFT)
                || (a12 == Point_dt.RIGHT && a23 == Point_dt.RIGHT && a31 == Point_dt.RIGHT)
                || (a12 == Point_dt.ONSEGMENT || a23 == Point_dt.ONSEGMENT || a31 == Point_dt.ONSEGMENT)) {
            ans = true;
        }

        return ans;
    }

    /**
     * determinates if this triangle contains the point p.
     *
     * @param p the query point
     * @return true iff p is not null and is inside this triangle (Note: on
     * boundary is considered outside!!).
     */
    public boolean contains_BoundaryIsOutside(Point_dt p) {
        boolean ans = false;
        if (this.halfplane | p == null) {
            return false;
        }

        if (isCorner(p)) {
            return false;
        }

        int a12 = p.pointLineTest(A, B);
        int a23 = p.pointLineTest(B, C);
        int a31 = p.pointLineTest(C, A);

        if ((a12 == Point_dt.LEFT && a23 == Point_dt.LEFT && a31 == Point_dt.LEFT)
                || (a12 == Point_dt.RIGHT && a23 == Point_dt.RIGHT && a31 == Point_dt.RIGHT)) {
            ans = true;
        }

        return ans;
    }

    /**
     * Checks if the given point is A corner of this triangle.
     *
     * @param p The given point.
     * @return True iff the given point is A corner of this triangle.
     * <p/>
     * By Eyal Roth & Doron Ganel.
     */
    public boolean isCorner(Point_dt p) {
        return (p.x == A.x & p.y == A.y) | (p.x == B.x & p.y == B.y) | (p.x == C.x & p.y == C.y);
    }

    //Doron
    public boolean fallInsideCircumcircle(Point_dt[] arrayPoints) {
        boolean isInside = false;
        Point_dt p1 = this.A;
        Point_dt p2 = this.B;
        Point_dt p3 = this.C;
        int i = 0;
        while (!isInside && i < arrayPoints.length) {
            Point_dt p = arrayPoints[i];
            if (!p.equals(p1) && !p.equals(p2) && !p.equals(p3)) {
                isInside = this.circumcircle_contains(p);
            }
            i++;
        }

        return isInside;
    }

    /**
     * compute the Z value for the X,Y values of q. <br />
     * assume this triangle represent A plane --> q does NOT need to be
     * contained in this triangle.
     *
     * @param q query point (its Z value is ignored).
     * @return the Z value of this plane implies by this triangle 3 points.
     */
    public double z_value(Point_dt q) {
        if (q == null || this.halfplane) {
            throw new RuntimeException("*** ERR wrong parameters, can't approximate the z value ..***: " + q);
        }
        /* incase the query point is on one of the points */
        if (q.x == A.x & q.y == A.y) {
            return A.z;
        }
        if (q.x == B.x & q.y == B.y) {
            return B.z;
        }
        if (q.x == C.x & q.y == C.y) {
            return C.z;
        }

        /* 
  	 *  plane: aX + bY + C = Z:
  	 *  2D line: y= mX + k
  	 *  
         */
        double X = 0, x0 = q.x, x1 = A.x, x2 = B.x, x3 = C.x;
        double Y = 0, y0 = q.y, y1 = A.y, y2 = B.y, y3 = C.y;
        double Z = 0, m01 = 0, k01 = 0, m23 = 0, k23 = 0;

        // 0 - regular, 1-horizontal , 2-vertical.
        int flag01 = 0;
        if (x0 != x1) {
            m01 = (y0 - y1) / (x0 - x1);
            k01 = y0 - m01 * x0;
            if (m01 == 0) {
                flag01 = 1;
            }
        } else { // 2-vertical.
            flag01 = 2;//x01 = x0
        }
        int flag23 = 0;
        if (x2 != x3) {
            m23 = (y2 - y3) / (x2 - x3);
            k23 = y2 - m23 * x2;
            if (m23 == 0) {
                flag23 = 1;
            }
        } else { // 2-vertical.
            flag23 = 2;//x01 = x0
        }

        if (flag01 == 2) {
            X = x0;
            Y = m23 * X + k23;
        } else {
            if (flag23 == 2) {
                X = x2;
                Y = m01 * X + k01;
            } else {  // regular case
                X = (k23 - k01) / (m01 - m23);
                Y = m01 * X + k01;

            }
        }
        double r = 0;
        if (flag23 == 2) {
            r = (y2 - Y) / (y2 - y3);
        } else {
            r = (x2 - X) / (x2 - x3);
        }
        Z = B.z + (C.z - B.z) * r;
        if (flag01 == 2) {
            r = (y1 - y0) / (y1 - Y);
        } else {
            r = (x1 - x0) / (x1 - X);
        }
        double qZ = A.z + (Z - A.z) * r;
        return qZ;
    }

    /**
     * compute the Z value for the X,Y values of q. assume this triangle
     * represent A plane --> q does NOT need to be contained in this triangle.
     *
     * @param x x-coordinate of the query point.
     * @param y y-coordinate of the query point.
     * @return z (height) value approximation given by the triangle it falls in.
     */
    public double z(double x, double y) {
        return z_value(new Point_dt(x, y));
    }

    /**
     * compute the Z value for the X,Y values of q. assume this triangle
     * represent A plane --> q does NOT need to be contained in this triangle.
     *
     * @param q query point (its Z value is ignored).
     * @return q with updated Z value.
     */
    public Point_dt z(Point_dt q) {
        double z = z_value(q);
        return new Point_dt(q.x, q.y, z);
    }

    public boolean fuzzyEquals(Triangle_dt t, double tolerance) {
        if (A != null && B != null & C != null) {
            return A.fuzzyEquals(t.A, tolerance)
                    || B.fuzzyEquals(t.B, tolerance)
                    || C.fuzzyEquals(t.C, tolerance);
        } else {
            return false;
        }
    }
}
