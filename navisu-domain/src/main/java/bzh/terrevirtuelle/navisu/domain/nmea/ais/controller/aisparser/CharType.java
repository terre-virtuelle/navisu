package nl.esi.metis.aisparser;

/** Type of character.
 * Enumeration of
 * ALPHABETIC, NUMERIC, and OTHER
 * 
 * @author Pierre van de Laar
 */
public enum CharType {
	ALPHABETIC,
	NUMERIC,
	OTHER;
	
	/** What is the type of character?.
	 *  APLHABETIC, NUMERIC, or OTHER?
	 * @param character
	 * @return type of character
	 */
	public static CharType getType(char character)
	{
			if ('A' <= character && character <= 'Z')
			{
				return ALPHABETIC;
			}
			else if ('0' <= character && character <= '9')
			{
				return NUMERIC;
			}
			else
			{
				return OTHER;
			}
	}
}
