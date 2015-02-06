package nl.esi.metis.aisparser;

/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar, Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 */

/** This class represents the destination area that is returned by {@link AISMessage22#getDestination} or {@link AISMessage23#getDestinationArea}.
 * @author Pierre van de Laar
 * @author Pierre America
 * @author Brian C. Lane
 */
public class DestinationArea extends Destination {
	private double       	   lon_UR;               // 18 bits : Long in degrees	Upper Right corner (north east)
	private double       	   lat_UR;               // 17 bits : Lat in degrees    Upper Right corner (north east)
	private double       	   lon_LL;               // 18 bits : Long in degrees	Lower Left corner (South West)
	private double       	   lat_LL;               // 17 bits : Lat in degrees    Lower Left corner (South West)

	/** Returns the longitude of the upper right corner of the area. 
	 * This value can be analyzed further with utility class {@link UtilsPositionInfo}.
	 * @return a double value representing the longitude in degrees (&plusmn;180&deg;, East = positive, West = negative).
	 */
	public double longitudeUpperRightCornerInDegrees() { return this.lon_UR; }

	/** Returns the latitude of the upper right corner of the area. 
	 * This value can be analyzed further with utility class {@link UtilsPositionInfo}.
	 * @return a double value representing the latitude in 1/10 minute (&plusmn;90&deg;, North = positive, South = negative).
	 */
	public double latitudeUpperRightCornerInDegrees() { return this.lat_UR; }

	/** Returns the longitude of the lower left corner of the area. 
	 * This value can be analyzed further with utility class {@link UtilsPositionInfo}.
	 * @return a double value representing the longitude in degrees (&plusmn;180&deg;, East = positive, West = negative).
	 */
	public double longitudeLowerLeftCornerInDegrees() { return this.lon_LL; }

	/** Returns the latitude of the lower left corner of the area. 
	 * This value can be analyzed further with utility class {@link UtilsPositionInfo}.
	 * @return a double value representing the latitude in degrees (&plusmn;90&deg;, North = positive, South = negative).
	 */
	public double latitudeLowerLeftCornerInDegrees() { return this.lat_LL; }

	/** Constructs a <code>DestinationArea</code> object.
	 * This constructor is used in the implementation classes.
	 * @param offset the offset from the beginning of the AIS message where the destination area starts.
	 * @param content the content of the AIS message
	 */
	public DestinationArea (int offset, Sixbit content)
	{
		lon_UR = UtilsLongitude18.toDegrees( UtilsTwosComplement.convertFrom18Bits( content.getIntFromTo(offset,offset+17) ) );
		lat_UR = UtilsLatitude17.toDegrees( UtilsTwosComplement.convertFrom17Bits( content.getIntFromTo(offset+18,offset+34) ) ); 
		
		lon_LL = UtilsLongitude18.toDegrees( UtilsTwosComplement.convertFrom18Bits( content.getIntFromTo(offset+35, offset+52) ) );
		lat_LL = UtilsLatitude17.toDegrees( UtilsTwosComplement.convertFrom17Bits( content.getIntFromTo(offset+53, offset+69) ) );
	}
}