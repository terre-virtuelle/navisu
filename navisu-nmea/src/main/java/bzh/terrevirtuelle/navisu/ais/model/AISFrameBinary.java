/******************************************************************************
 * (c) Copyright 2007, IRENav. All rights reserved.
 * Property of ECOLE NAVALE
 *
 * For Unrestricted Internal Use Only
 * Unauthorized reproduction and/or distribution is strictly prohibited.
 * This product is protected under copyright law and trade secret law as an
 * unpublished Work.
 *
 * Modified in 03/2007.
 *
 * Original Designers : RAY, STROH, ALESSIO
 * Modified : Serge Morvan Enib
 *
 ******************************************************************************/

package bzh.terrevirtuelle.navisu.ais.model;

/**
 *
 * @author Morvan
 */
public class AISFrameBinary {
	
    /**
     *
     */
    protected String BinaryFrame;
	
	/**
	 * constructor : initialise an AIS Binary Frame
	 *
	 * @param frame The string that the AIS message in binary format
	 */
	
	protected AISFrameBinary (String frame) {
		
		BinaryFrame = frame;
	}
	
	/**
	 * binaryToInt : convert a binary string into int
	 *
	 * @param begin The starting bit in BinaryFrame
	 * @param end The ending bit in BinaryFrame
	 * @return int The int value of of a subset of the string BinaryFrame
	 */	
	
	protected int binaryToInt (int begin, int end) {
	
		return PosBinStringToInt (BinaryFrame.substring(begin,end));
	}
	
	/**
	 * complementToInt : convert a string in two's complement binary format into int
	 *
	 * @param begin The starting bit in BinaryFrame
	 * @param end The ending bit in BinaryFrame
	 * @return int The int value of of a subset of the string BinaryFrame
	 */	
	
	protected int complementToInt (int begin, int end) {
	
		String word = BinaryFrame.substring(begin,end);
		if (word.charAt(0) == '1') {
			return (-NegBinStringToInt (word));
		}
		else {
			return PosBinStringToInt (word);
		}
	}

	/**
	 * PosBinStringToInt : convert a string in two's complement binary format into int
	 *
	 * @param word The string that represent a positive value in two's complement binary format
	 * @return int The int value of the string
	 */	
	
	private int PosBinStringToInt (String word) {

		int result = 0;
		for (int i=word.length()-1;i>=0;i--){
			int puiss = 1;
			for (int j=1;j<word.length()-i;j++) {
				puiss*=2;
			}
			if ((int)word.charAt(i)==49){
				result+=puiss;
			}
		}
		return result;
	}
	
	/**
	 * NegBinStringToInt : convert a string in two's complement binary format into int
	 *
	 * @param word The string that represent a negative value in two's complement binary format
	 * @return int The int value of the string
	 */	
	
	private int NegBinStringToInt (String word){
	
		int result;

		result = 0;
		for (int i=word.length()-1;i>=1;i--){
			int puiss = 1;
			for(int j=1;j<word.length()-i;j++){
				puiss*=2;
			}
			if ((int)word.charAt(i)==48){
				result+=puiss;
			}
		}
    	result++;
		return result;
	}
	
	/**
	 * binaryToString : convert a binary string in real 'ASCII' string
	 *
	 * @param begin The starting bit in BinaryFrame
	 * @param end The ending bit in BinaryFrame
	 * @return String The string converted
	 */	
	
	protected String binaryToString (int begin, int end){

		int spacecount;
		int ASCII;
		String line; 
		String result;
		
		spacecount = 0;
		line = BinaryFrame.substring(begin,end);
		result = new String();

		for(int i=0; i<line.length();i=i+6){
			String word = line.substring(i,i+6);
			ASCII = PosBinStringToInt(word);
			if (ASCII<31) {
				ASCII=ASCII+64;
			}
			if (ASCII==64) {
				ASCII=32;
				spacecount++;
			}
			else {
				spacecount = 0;
			}
			if (spacecount <=1) {result = result.concat(""+((char)ASCII));}
		}
		return result;
	}
	
} /** AISFrameBinary */
