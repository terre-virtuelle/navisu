/*
The MIT License (MIT)

Copyright (c) 2014 CCHall (aka Cyanobacterium aka cyanobacteruim)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package bzh.terrevirtuelle.navisu.geometry.objects3D;
/**
 * 3-Dimensional Vector implementation inspired by GLSL structure of the same 
 * name. This class and all of its fields are final to optimize performance 
 * under a functional programming model.
 * @author CCHall
 */
public final class Vec3d {
    /**
     * The x coordinate.
     */
    public final double x;

    /**
     * The y coordinate.
     */
    public final double y;

    /**
     * The z coordinate.
     */
    public final double z;

	/**
	 * Constructs a 3D vector
	 * @param x coordinate in the first dimension
	 * @param y coordinate in the second dimension
	 * @param z coordinate in the third dimension
	 */
    public Vec3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    

    /**
     * Multiplies this vector by the specified scalar value.
     * @param scale the scalar value
	 * @return The multiplied vector
     */
    public Vec3d mul(double scale) {
        return new Vec3d(
		x * scale,
        y * scale,
        z * scale);
    }

    /**
     * Gets the difference vector of vectors t1 and t2 (returns t3 = t1 - t2).
     * @param t1 the first vector
     * @param t2 the second vector
	 * @return The vector <code>t1 - t2</code>
     */
    public static Vec3d sub(Vec3d t1, Vec3d t2) {
        return new Vec3d(
		t1.x - t2.x,
        t1.y - t2.y,
        t1.z - t2.z
		);
    }

    /**
     * Returns the difference vector of
     * this vector and vector t1 (return = this - t1) .
     * @param t1 the other vector
	 * @return The vector <code>this - t2</code>
     */
    public Vec3d sub(Vec3d t1) {
        return sub(this,t1);
    }

    /**
     * Returns the sum of vectors t1 and t2 (return = t1 + t2).
     * @param t1 the first vector
     * @param t2 the second vector
	 * @return The vector <code>t1 + t2</code>
     */
    public static Vec3d add(Vec3d t1, Vec3d t2) {
        return new Vec3d(
		t1.x + t2.x,
        t1.y + t2.y,
        t1.z + t2.z
		);
    }

    /**
     * Returns the sum of vectors this vector and t2 (return = this + t1).
     * @param t1 the first vector
	 * @return The vector <code>this + ta</code>
     */
    public Vec3d add(Vec3d t1) {
        return add(this,t1);
    }

    /**
     * Returns the length of this vector.
     * @return the length of this vector
     */
    public double length() {
        return Math.sqrt(this.x*this.x + this.y*this.y + this.z*this.z);
    }

    /**
     * Returns the normalized equivalent to this vector.
	 * @return This vector after normalizing
     */
    public Vec3d normalize() {
        double norm = 1.0 / length();
        return new Vec3d(
		this.x * norm,
        this.y * norm,
        this.z * norm
		);
    }

    /**
     * Returns the vector cross product of vectors v1 and v2.
     * @param v1 the first vector
     * @param v2 the second vector
	 * @return The cross product
     */
    public static Vec3d cross(Vec3d v1, Vec3d v2) {
        double tmpX;
        double tmpY;

        tmpX = v1.y * v2.z - v1.z * v2.y;
        tmpY = v2.x * v1.z - v2.z * v1.x;
		return new Vec3d(
        v1.x * v2.y - v1.y * v2.x,
        tmpX,
        tmpY
		);
    }

    /**
     * Computes the dot product of this vector and vector v1.
     * @param v1 the other vector
     * @return the dot product of this vector and v1
     */
    public double dot(Vec3d v1) {
        return this.x * v1.x + this.y * v1.y + this.z * v1.z;
    }

    /**
     * Returns the hashcode for this <code>Vec3f</code>.
     * @return      a hash code for this <code>Vec3f</code>.
     */
    @Override
    public int hashCode() {
        long bits = 7L;
        bits = 31L * bits + Double.doubleToLongBits(x);
        bits = 31L * bits + Double.doubleToLongBits(y);
        bits = 31L * bits + Double.doubleToLongBits(z);
        return (int) (bits ^ (bits >> 32));
    }

    /**
     * Determines whether or not two 3D points or vectors are equal.
     * Two instances of <code>Vec3d</code> are equal if the values of their
     * <code>x</code>, <code>y</code> and <code>z</code> member fields,
     * representing their position in the coordinate space, are the same.
     * @param obj an object to be compared with this <code>Vec3d</code>
     * @return <code>true</code> if the object to be compared is
     *         an instance of <code>Vec3d</code> and has
     *         the same values; <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Vec3d) {
            Vec3d v = (Vec3d) obj;
            return (x == v.x) && (y == v.y) && (z == v.z);
        }
        return false;
    }

    /**
     * Returns a <code>String</code> that represents the value
     * of this <code>Vec3f</code>.
     * @return a string representation of this <code>Vec3f</code>.
     */
    @Override
    public String toString() {
        return "Vec3d[" + x + ", " + y + ", " + z + "]";
    }
	
	/**
	 * Calculates the angle between two vectors.
	 * @param a A 3D vector
	 * @param b Another 3D vector
	 * @return The angle between the two vectors, in radians.
	 */
	public static double getAngle(Vec3d a, Vec3d b){
		double AdotB = a.dot(b);
		double A = a.length();
		double B = b.length();
		return Math.acos(AdotB / (A * B));
	}
}
