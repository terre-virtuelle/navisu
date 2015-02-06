package nl.esi.metis.aisparser;

/** This class provides utilities to deal with Strings.
 * This is used internally by classes in this package.
 * 
 * According to the standard (paragraph 3.3.7 'Message structure' on page 38):
 * Character strings are presented left to right most significant bit first. 
 * All unused characters should be represented by the @ symbol, and they should be placed at the end of the string.
 * 
 * Note that according to TABLE 44 on page 100, @ symbol is a valid character.
 * 
 * We have found no info how to handle the special case that @ is the last character of the string.
 * 
 * @author Pierre America
 * @author Pierre van de Laar
 */
public class UtilsString {
	/** Removes the trailing at signs (@) at the end of a string.
	 * @param orig a string with possibly trailing at signs
	 * @return the string with the at signs at the end removed
	 */
	static public String stripAtSigns (String orig) {
		int x = orig.length()-1;
		int y = -1;
		while (x != y)
		{
			if (orig.charAt(x) == '@')
			{
				x = x -1;
			}
			else
			{
				y = x;
			}
		}
		return orig.substring(0, x+1);
	}

}
