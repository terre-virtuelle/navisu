package nl.esi.metis.aisparser;

/** This class provides a method to analyze the ship type as returned by {@link AISMessage05#getTypeOfShipAndCargoType()} or {@link AISMessage24PartB#getTypeOfShipAndCargoType()}.
 * @author Pierre van de Laar
 * @author Pierre America
 */
public class UtilsShipType8 {
	/* Table 50 of ITU-R M.1371-4 is captured in the following tables
	 * Description is shortened for display purposes
	 * Singular description is consistently chosen (standard uses both 'vessel' and 'vessels')
	 */
	final static private String[] specialCraft_5x = {
			"Pilot vessel",							//0
			"Search and rescue vessel",				//1
			"Tug",									//2
			"Port tender",							//3
			"Vessel with anti-pollution capability",//4
			"Law enforcement vessel",				//5
			"Spare - for local vessels",			//6
			"Spare - for local vessels",			//7
			"Medical transport",					//8
			"Ship not party to an armed conflict"	//9
	};

	final static private String[] firstDigit = {
			"Not specified",			//0
			"Reserved for future use",	//1
			"WIG",						//2
			"Vessel",					//3
			"HSC",						//4
			"See above",				//5
			"Passenger ship",			//6
			"Cargo ship",				//7
			"Tanker",					//8
			"Other types of ship"		//9
	};
	
	final static private String[] secondDigit = {
			"General",					//0
			"Category X",				//1
			"Category Y",				//2
			"Category Z",				//3
			"Category OS",				//4
			"Reserved for future use",	//5
			"Reserved for future use",	//6
			"Reserved for future use",	//7
			"Reserved for future use",	//8
			"No additional information" //9
	};

	final static private String[] vessel_3x = {
			"Fishing",										//0
			"Towing",										//1
			"Towing and exceeding",							//2
			"Engaged in dredging or underwater operations",	//3
			"Engaged in diving operations",					//4
			"Engaged in military operations",				//5
			"Sailing",										//6
			"Pleasure craft",								//7
			"Reserved for future use",						//8
			"Reserved for future use",						//9
	};
	
	/** Generates a text string describing the ship and cargo type.
	 *  See Table 49 & 50 of ITU-R M.1371-4 (pages 105-107).
	 * @param type an integer value representing the ship/cargo type as returned by 
	 * {@link AISMessage05#getTypeOfShipAndCargoType()} or {@link AISMessage24PartB#getTypeOfShipAndCargoType()}.
	 * @return a text string describing the ship and cargo type.
	 * @precondition 0 <= id < 256
	 */
	static public String shipTypeToString (int type)
	{
		assert(firstDigit.length == 10);
		assert(secondDigit.length == 10);
		assert(vessel_3x.length == 10);
		assert(specialCraft_5x.length == 10);
		
		assert(0<= type && type <= 255);
		
		//See table 49 (page 105 of ITU-R M.1371-4)
		if (type >= 200)
		{
			return "Reserved for future use";
		}
		if (type >= 100)
		{
			return "Reserved for regional use";
		}
		if (type == 0)
		{
			return "Not available / no ship";
		}
		
		assert(1 <= type);
		assert(type <= 99);
		
		int ten = type/10;
		assert(0<= ten && ten <= 9);
		int unit = type%10;
		assert(0<= unit && unit <= 9);
		switch(ten)
		{
			case 0:
				return firstDigit[0] + " " + Integer.toString(type);
			case 3:
				return firstDigit[3] + ", " + vessel_3x[unit];
			case 5: 
				return specialCraft_5x[unit];
			default:
				return firstDigit[ten] + ", " + secondDigit[unit]; 
		}
	}

	/** This constructor is made private to indicate that clients should not construct instances of this class. */
	private UtilsShipType8 () {
	}
}