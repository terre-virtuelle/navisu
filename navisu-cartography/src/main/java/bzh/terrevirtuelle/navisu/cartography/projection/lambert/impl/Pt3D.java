package bzh.terrevirtuelle.navisu.cartography.projection.lambert.impl;

public class Pt3D {

    private double x;
    private double y;
    private double z;

    Pt3D(double x, double y , double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

   public double getY() {
        return y;
    }

   public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void translate(double x , double y, double z){

        this.x+= x;
        this.y+= y;
        this.z+= z;
    }

    public Pt3D toDegree(){
        this.x = this.x * 180/Math.PI;
        this.y = this.y * 180/Math.PI;
        this.z = this.z * 180/Math.PI;

        return this;
    }

    @Override
    public String toString() {
        return "Point3D{" + "x=" + x + ", y=" + y + ", z=" + z + '}';
    }
    
}
