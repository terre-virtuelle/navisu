package nl.esi.metis.aisparser;

/** This class contains some utility methods to convert unsigned integers to signed integers using two's complement.
 * They are used internally in various implementation classes in this package.
 * 
 * @author Pierre America
 * @author Pierre van de Laar
 */
public class UtilsTwosComplement {

	/** Converts an 8-bit unsigned integer value to a signed value using 2's complement.
	 * @param bits an (8-bit) unsigned integer value
	 * @return a signed value
	 */
	public static int convertFrom8Bits (int bits)
	{
		assert( (bits & 0xFF) == bits);
		
		int retval = bits;
		if( bits >= 0x80 )
	    {
	        retval -= 0x100;
	    } 
		return retval;
	}

	/** Converts a 17-bit unsigned integer value to a signed value using 2's complement.
	 * @param bits a (17-bit) unsigned integer value
	 * @return a signed value
	 */
	public static int convertFrom17Bits (int bits)
	{
		assert( (bits & 0x1FFFF) == bits);
		
		int retval = bits;
		if( bits >= 0x10000 )
	    {
	        retval -= 0x20000;
	    } 
		return retval;
	}

	/** Converts an 18-bit unsigned integer value to a signed value using 2's complement.
	 * @param bits an (18-bit) unsigned integer value
	 * @return a signed value
	 */
	public static int convertFrom18Bits (int bits)
	{
		assert( (bits & 0x3FFFF) == bits);
		
		int retval = bits;
		if( bits >= 0x20000 )
	    {
	        retval -= 0x40000;
	    } 
		return retval;
	}

	/** Converts a 27-bit unsigned integer value to a signed value using 2's complement.
	 * @param bits a (27-bit) unsigned integer value
	 * @return a signed value
	 */
	public static int convertFrom27Bits (int bits)
	{
		assert( (bits & 0x7FFFFFF) == bits);
		
		int retval = bits;
		if( bits >= 0x4000000 )
	    {
	        retval -= 0x8000000;
	    } 
		return retval;
	}

	/** Converts a 28-bit unsigned integer value to a signed  value using 2's complement.
	 * @param bits a (28-bit) unsigned integer value
	 * @return a signed value
	 */
	public static int convertFrom28Bits (int bits)
	{
		assert( (bits & 0xFFFFFFF) == bits);
		
		int retval = bits;
		if( bits >= 0x8000000 )
	    {
	        retval -= 0x10000000;
	    } 
		return retval;
	}
}
