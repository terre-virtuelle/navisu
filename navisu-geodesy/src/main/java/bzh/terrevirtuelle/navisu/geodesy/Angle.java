package bzh.terrevirtuelle.navisu.geodesy;

/**
 * Utility interface for dealing with angles.
 *  
 * @author tibus29
 */
public interface Angle {

   /** Degrees/Radians conversion constant. */
   static final double PiOver180 = Math.PI / 180.0;

   /**
    * @param degrees
    * @return 
    */
   
   static public double toRadians(double degrees) {
      return degrees * PiOver180;
   }
   
   /**
    * Convert radians to degrees.
    * @param radians
    * @return
    */
   static public double toDegrees(double radians) {
      return radians / PiOver180;
   }
}
