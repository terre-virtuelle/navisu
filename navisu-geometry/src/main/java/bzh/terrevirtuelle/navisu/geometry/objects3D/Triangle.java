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

import java.util.Arrays;


/**
 * This object represents a triangle in 3D space.
 * @author CCHall
 */
public class Triangle {
	private final Vec3d[] vertices;
	private final Vec3d normal;
	/**
	 * Creates a triangle with the given vertices at its corners. The normal is 
	 * calculated by assuming that the vertices were provided in right-handed 
	 * coordinate space (counter-clockwise)
	 * @param v1 A corner vertex
	 * @param v2 A corner vertex
	 * @param v3 A corner vertex
	 */
	public Triangle(Vec3d v1, Vec3d v2, Vec3d v3){
		vertices = new Vec3d[3];
		vertices[0] = v1;
		vertices[1] = v2;
		vertices[2] = v3;
		Vec3d edge1 = v2.sub(v1);
		Vec3d edge2 = v3.sub(v1);
		normal = Vec3d.cross(edge1, edge2).normalize();
	}
	/**
	 * Moves the triangle in the X,Y,Z direction
	 * @param translation A vector of the delta for each coordinate.
	 */
	public void translate(Vec3d translation){
		for(int i = 0; i < vertices.length; i++){
			vertices[i] = vertices[i].add(translation);
		}
	}
	/**
	 * @see java.lang.Object#toString() 
	 * @return A string that provides some information about this triangle
	 */
	@Override public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Triangle[");
		for(Vec3d v : vertices){
			sb.append(v.toString());
		}
		sb.append("]");
		return sb.toString();
	}
	/**
	 * Gets the vertices at the corners of this triangle
	 * @return An array of vertices
	 */
	public Vec3d[] getVertices(){
		return vertices;
	}
	/**
	 * Gets the normal vector
	 * @return A vector pointing in a direction perpendicular to the surface of 
	 * the triangle.
	 */
	public Vec3d getNormal(){
		return normal;
	}
	
	/**
	 * @see java.lang.Object#equals(java.lang.Object) 
	 * @param obj Object to test equality
	 * @return True if the other object is a triangle whose verticese are the 
	 * same as this one.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Triangle other = (Triangle) obj;
		if (!Arrays.deepEquals(this.vertices, other.vertices)) {
			return false;
		}
		return true;
	}
	/**
	 * @see java.lang.Object#hashCode() 
	 * @return A hashCode for this triangle
	 */
	@Override
	public int hashCode() {
		int hash = 7;
		hash = 67 * hash + Arrays.deepHashCode(this.vertices);
		return hash;
	}
}
