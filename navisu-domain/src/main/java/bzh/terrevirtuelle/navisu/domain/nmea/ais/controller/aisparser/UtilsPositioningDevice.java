package nl.esi.metis.aisparser;
/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar, Pierre America (Embedded Systems Institute)
 * All Rights Reserved
 */

/** This class provides a method to analyze the positioning device type as returned by 
 * {@link AISMessage05#getTypeOfElectronicPositionFixingDevice()} or {@link AISMessage19#getTypeOfElectronicPositionFixingDevice()}.
 * 
 * @author Pierre van de Laar
 * @author Pierre America
 */
public class UtilsPositioningDevice {
	
	/** Generates a text string describing the type of electronic position fixing device. 
	 * @param deviceType an integer representing the positioning device type as returned by 
     * {@link AISMessage05#getTypeOfElectronicPositionFixingDevice()} or {@link AISMessage19#getTypeOfElectronicPositionFixingDevice()}.
	 * @return a text string describing the positioning device type
	 */
	static public String toString (int deviceType) {
		switch (deviceType) {
		case 0:
			return "undefined device";
		case 1:
			return "GPS";
		case 2:
			return "GLONASS";
		case 3:
			return "combined GPS/GLONASS";
		case 4:
			return "Loran-C";
		case 5:
			return "Chayka";
		case 6:
			return "integrated navigation system";
		case 7:
			return "surveyed";
		case 8:
			return "Galileo";
		case 15:
			return "internal GNSS";
		default:
			return "not used";
		}
	}

	/** This constructor is made private to indicate that clients should not construct instances of this class. */
	private UtilsPositioningDevice () {
	}
}
