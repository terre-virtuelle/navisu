package bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.BitVector;

/**
 * 6-bit packed ASCII functions Copyright 2006-2008
 *
 * @author Brian C. Lane <bcl@brianlane.com @author Pierre
 * van de Laar All Rights Reserved
 *
 */
/**
 * This class's methods are used to extract data from the 6-bit packed ASCII
 * string used by AIVDM/AIVDO AIS messages.
 */
public class Sixbit {

    /**
     * Number of BITS obtained from a single Character
     */
    public static final int BITSPERCHAR = 6;

    /**
     * Number of BITS needed to get a single Character
     */
    public static final int BITSNEEDEDFORCHAR = 6;

    private String bits;          //!< raw 6-bit ASCII data string
    private BitVector bv;
    private int nrOfFillBits;	      //!< Number of padding bits at end

    /**
     *
     * @param diff
     */
    public void setNrOfFillBits(int diff) {
        nrOfFillBits = diff;
    }

    /**
     *
     * @param ascii
     * @return
     */
    public static boolean isValidTransportCharacter(char ascii) {
        return ((ascii >= 0x30) && (ascii <= 0x77)) && // 0x30 == 48      0x77 == 119
                ((ascii <= 0x57) || (ascii >= 0x60));		// 0x57 == 87      0x60 ==  96
    }

    /**
     *
     * @param bits
     * @return
     */
    public static boolean isValidString(String bits) {
        //bounded linear search
        int x = 0;
        int y = bits.length();
        while (x != y) {
            if (isValidTransportCharacter(bits.charAt(x))) {
                x++;
            } else {
                y = x;
            }
        }
        return x == bits.length();
    }

    public Sixbit() {
    }

    /**
     * Constructor
     *
     * @param bits
     * @param nrOfFillBits
     * @precondition isValidString (bits)
     * @precondition 0 <= fillBits <= 5
     */
    public Sixbit(String bits, int nrOfFillBits) {
        assert (isValidString(bits));
        this.bits = bits;
        bv = new BitVector(bits.length() * BITSPERCHAR);
        for (int i = 0; i < bits.length(); i++) {
            bv.putLongFromTo(Sixbit.decodeTransportCharacterToBinary(bits.charAt(bits.length() - 1 - i)), i * BITSPERCHAR, (i + 1) * BITSPERCHAR - 1);
        }
        this.nrOfFillBits = nrOfFillBits;
    }

    /*
     * Return the number of bits
     * 
     * Takes into account the number of padding bits.
     */
    /**
     *
     * @return
     */
    public int length() {
        return ((bits.length() * BITSPERCHAR) - nrOfFillBits);
    }

    /*
     * Return the number of available bits
     * 
     * Takes into account the number of padding bits.
     */
    /**
     *
     * @return
     */
    public int available() {
        assert (bv.size() == bits.length() * BITSPERCHAR);

        return bv.size();
    }

    /**
     * Encode a binary value to a transport character.
     *
     * @param value to encode
     * @precondition 0 <= value <= 0x3F
	 * @r
     * eturn encoded value in 6-bit ASCII representation
     *
     * This function returns the ASCII character to be used in the VDM line
     * (adhering to the NMEA standard) of the provided 6-bit binary value.
     *
     * This function is the inverse of @link decodeTransportCharacterToBinary().
     */
    public static char encodeBinaryToTransportCharacter(int value) {
        assert (0 <= value);
        assert (value <= 0x3F);				// 0x3F == 63

        if (value < 0x28) // 0x28 == 40
        {
            return (char) (value + 0x30);	// 0x30 == 48
        } else {
            return (char) (value + 0x38);		// 0x38 == 56
        }
    }

    /**
     * Decode a transport character to a binary value.
     *
     * @param ascii character to decode
     * @precondition isValidChar(char ascii)
     * @postcondition 0 <= retval <= 0x3F
	 * @r
     * eturn decoded value in 6-bit binary representation
     *
     * This function returns the 6-bit binary value of the provided ASCII
     * character used in the VDM line (adhering to the NMEA standard).
     *
     * This function should not be used to decode data from fields such as the
     * name and destination -- Use @link decodeBinaryToContentCharacter()
     * instead.
     *
     * This function is the inverse of @link encodeBinaryToTransportCharacter().
     */
    public static int decodeTransportCharacterToBinary(char ascii) {
        assert (isValidTransportCharacter(ascii));
        int retval;
        if (ascii < 0x60) //0x60 == 96
        {
            retval = (ascii - 0x30);	//0x30 == 48
        } else {
            retval = (ascii - 0x38);	//0x38 == 56
        }
        assert ((retval & 0x3F) == retval);	// 0x3F == 63
        return retval;
    }

    /**
     * Decode a binary value to a content character.
     *
     * @param value to be decoded
     * @return corresponding ASCII value (0x20-0x5F)
     *
     * This function returns the content character as encoded in binary value.
     * See table 44 (page 100) of Rec. ITU-R M.1371-4.
     *
     * This function is used to convert binary data to ASCII. This is different
     * from the 6-bit ASCII to binary conversion for VDM messages; it is used
     * for strings within the datastream itself. eg. Ship Name, Callsign and
     * Destination.
     */
    public static char decodeBinaryToContentCharacter(int value) {
        assert (0 <= value);
        assert (value <= 0x3F);				// 0x3F == 63

        if (value < 0x20) // 0x20 == 32
        {
            return (char) (value + 0x40);	// 0x40 == 64
        } else {
            return (char) value;
        }
    }

    /**
     * encode a content character to a binary value
     *
     * @param value to be encoded
     * @return corresponding binary value
     *
     * This function returns the 6-bit binary value of the content character.
     * See table 44 (page 100) of Rec. ITU-R M.1371-4.
     */
    public static int encodeContentCharacterToBinary(char value) {
        assert (0x20 <= value);				// 0x20 == 32
        assert (value <= 0x5F);				// 0x5F == 95

        if (value >= 0x40) // 0x40 == 64
        {
            return (char) (value - 0x40);	// 0x40 == 64
        } else {
            return (char) value;
        }
    }

    /**
     * get boolean From AIS message
     *
     * @param bitIndex index of bit
     *
     * This method returns the requested bit interpreted as a boolean from the
     * AIS message.
     * @return
     */
    public boolean getBoolean(int bitIndex) {
        return bv.get(bv.size() - bitIndex);
    }

    /**
     * get int From AIS message
     *
     * @param fromIndex begin index (inclusive)
     * @param toIndex end index (inclusive)
     * @return
     * @precondition toIndex - fromIndex + 1 <= 32
     *
     * This method returns the requested bits interpreted as an integer (MSB
     * first) from the AIS message.
     */
    public int getIntFromTo(int fromIndex, int toIndex) {
        assert (toIndex - fromIndex < 32);
        return (int) bv.getLongFromTo(bv.size() - toIndex, bv.size() - fromIndex);
    }

    /**
     * set int in AIS message
     *
     * @param value	value to set
     * @param fromIndex begin index (inclusive)
     * @param toIndex end index (inclusive)
     * @precondition toIndex - fromIndex + 1 <= 32
     *
     * This method sets the requested bits interpreted as an integer (MSB first)
     * in the AIS message.
     */
    public void setIntFromTo(int value, int fromIndex, int toIndex) {
        assert (toIndex - fromIndex < 32);

        bv.putLongFromTo((long) value, bv.size() - toIndex, bv.size() - fromIndex);
    }

    /**
     * get BitVector From AIS message
     *
     * This method returns the BitVector from the AIS message.
     *
     * @return
     */
    public BitVector getBitVector() {
        return bv;
    }

    /**
     * get BitVector From AIS message
     *
     * @param fromIndex begin index (inclusive)
     * @param toIndex end index (inclusive)
     *
     * This method returns the requested bits as a BitVector from the AIS
     * message.
     * @return
     */
    public BitVector getBitVectorFromTo(int fromIndex, int toIndex) {
        return bv.partFromTo(bv.size() - toIndex, bv.size() - fromIndex);
    }

    /**
     * get String From AIS message
     *
     * @param fromIndex begin index (inclusive)
     * @param toIndex end index (inclusive)
     * @return
     * @precondition (toIndex - fromIndex + 1)%6 == 0
     *
     * This method returns the requested bits interpreted as a String from the
     * AIS message.
     */
    public String getStringFromTo(int fromIndex, int toIndex) {
        assert ((toIndex - fromIndex + 1) % BITSNEEDEDFORCHAR == 0);

        StringBuilder sb = new StringBuilder();

        /* Get the 6-bit string, convert to ASCII */
        for (int i = fromIndex; i < toIndex; i += BITSNEEDEDFORCHAR) {
            int value = getIntFromTo(i, i + BITSNEEDEDFORCHAR - 1);
            sb.append(Sixbit.decodeBinaryToContentCharacter(value));
        }
        return sb.toString();
    }
}
