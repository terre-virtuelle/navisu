package nl.esi.metis.aisparser;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import cern.colt.bitvector.BitVector;

/** This class provides functions to analyze the estimated-time-of-arrival (ETA) value that is returned by {@link AISMessage05#getEta()}.
 * @author Pierre America
 */
public class UtilsEta {
	/** The value that indicates that the month is unavailable */
	public static final int UNAVAILABLE_MONTH = 0;

	/** The value that indicates that the day is unavailable */
	public static final int UNAVAILABLE_DAY = 0;

	/** The value that indicates that the hour is unavailable */
	public static final int UNAVAILABLE_HOUR = 24;

	/** The value that indicates that the minute is unavailable */
	public static final int UNAVAILABLE_MINUTE = 60;

	/** The position of the first bit of the minute */
	private static final int MINUTE_FROM = 0;
	
	/** The position of the last bit of the minute */
	private static final int MINUTE_TO = 5;
		
	/** Returns the minute of the estimated-time-of-arrival (ETA).
	 * @param eta a <code>BitVector</code> as returned by {@link AISMessage05#getEta()}
	 * @return the minute of the ETA (0-59) <br>
	 *         60 = {@link #UNAVAILABLE_MINUTE} = unavailable
	 */
	public static int getMinute (BitVector eta) {
		return (int) eta.getLongFromTo(MINUTE_FROM, MINUTE_TO);
	}
	
	/** The position of the first bit of the hours */
	private static final int HOUR_FROM = 6;
	
	/** The position of the last bit of the hours */
	private static final int HOUR_TO = 10;
	
	/** Returns the hour of the estimated-time-of-arrival (ETA).
	 * @param eta a <code>BitVector</code> as returned by {@link AISMessage05#getEta()}
	 * @return the hour of the ETA (0-23) <br>
	 *         24 = {@link #UNAVAILABLE_HOUR} = unavailable
	 */
	public static int getHour (BitVector eta) {
		return (int) eta.getLongFromTo(HOUR_FROM, HOUR_TO);
	}
	
	/** The position of the first bit of the day */
	private static final int DAY_FROM = 11;
	
	/** The position of the last bit of the day */
	private static final int DAY_TO = 15;
	
	/** Returns the day of the estimated-time-of-arrival (ETA).
	 * @param eta a <code>BitVector</code> as returned by {@link AISMessage05#getEta()}
	 * @return the day of the ETA (1-31) <br>
	 *         0 = {@link #UNAVAILABLE_DAY} = unavailable
	 */
	public static int getDay (BitVector eta) {
		return (int) eta.getLongFromTo(DAY_FROM, DAY_TO);
	}
	
	/** The position of the first bit of the month */
	private static final int MONTH_FROM = 16;
	
	/** The position of the last bit of the month */
	private static final int MONTH_TO = 19;
	
	/** Returns the month of the estimated-time-of-arrival (ETA).
	 * @param eta a <code>BitVector</code> as returned by {@link AISMessage05#getEta()}
	 * @return the month of the ETA (1-12) <br>
	 *         0 = {@link #UNAVAILABLE_MONTH} = unavailable
	 */
	public static int getMonth (BitVector eta) {
		return (int) eta.getLongFromTo(MONTH_FROM, MONTH_TO);
	}
	
	/** Checks whether the estimated-time-of-arrival (ETA) {@code BitVector} is semantically correct (according to the standard). 
	 * This means that every field is either a valid value or the standard value indicating unavailability.
	 * Note that this does not check whether the given month actually has enough days to contain the given day.
	 * @param eta a <code>BitVector</code> as returned by {@link AISMessage05#getEta()}
	 * @return true if the {@code BitVector} is semantically correct
	 */
	public static boolean isSemanticallyCorrectValue(BitVector eta)
	{
		if (eta.size() != 20)
			return false;
		
		int month = getMonth(eta);
		if (month > 12)
			return false;
		
		int day = getDay(eta);
		if (day > 31)
			return false;
		
		int hour = getHour(eta);
		if (hour > 24)
			return false;
		
		int minute = getMinute(eta);
		if (minute > 60)
			return false;
		else
			return true;
	}

	/** Checks whether the estimated-time-of-arrival (ETA) is available.
	 * This means that at least one of the components is available.
	 * @param eta a <code>BitVector</code> as returned by {@link AISMessage05#getEta()}
	 * @return true if the ETA is available
	 */
	public static boolean isAvailable (BitVector eta) {
		return (getMonth(eta)!=UNAVAILABLE_MONTH || getDay(eta) !=UNAVAILABLE_DAY || getHour(eta)!=UNAVAILABLE_HOUR || getMinute(eta)!=UNAVAILABLE_MINUTE);
	}
	
	/** Calculates the time indicated by the ETA value.
	 * For this purpose we take the earliest time after a given reference time that corresponds to the ETA value.
	 * @param eta an ETA <code>BitVector</code> as returned by {@link AISMessage05#getEta()}
	 * @param timeNow a reference time, in seconds from January 1, 1970
	 * @return the calculated ETA time, in seconds from January 1, 1970
	 * @precondition isSemanticallyCorrectValue(eta)
	 */
	public static long convertToTime (BitVector eta, double timeNow) {
		GregorianCalendar calendar = new GregorianCalendar(utcTimeZone);
		calendar.setLenient(false); // generate exceptions on illegal input
		calendar.setTimeInMillis((long) (timeNow * 1000));
		
		/** The number of the calendar field that will be incremented (to ensure we get an ETA in the future)
		 * if we find an ETA field that is less than the corresponding reference field.
		 * We will use Calendar.ERA to signal that overflow is no longer necessary (since we have a future time already).
		 */
		int overflowField = Calendar.YEAR;
		
		final int noOverflow = Calendar.ERA;

		int monthEta = getMonth(eta);
		if (monthEta == UNAVAILABLE_MONTH)
			overflowField = Calendar.MONTH;
		else {
			int monthNow = calendar.get(Calendar.MONTH) + 1; // Calendar month numbers start at 0
			if (monthEta != monthNow) {
				if (monthEta < monthNow)
					calendar.add(overflowField, 1);
				calendar.set(Calendar.MONTH, monthEta - 1);
				overflowField = noOverflow;
			}
		}		
		
		int dayEta = getDay(eta);
		if (dayEta == UNAVAILABLE_DAY) {
			if (overflowField == noOverflow)
				calendar.set(Calendar.DAY_OF_MONTH, 1); // since we are already in the future, we choose the minimum day possible
			else
				overflowField = Calendar.DAY_OF_MONTH;
		}
		else {
			int dayNow = calendar.get(Calendar.DAY_OF_MONTH);
			if (dayEta != dayNow) {
				if (dayEta < dayNow  && overflowField != noOverflow)
					calendar.add(overflowField, 1); // do the overflow
				calendar.set(Calendar.DAY_OF_MONTH, dayEta);
				overflowField = noOverflow;
			}
		}
		
		int hourEta = getHour(eta);
		if (hourEta == UNAVAILABLE_HOUR) {
			if (overflowField == noOverflow)
				calendar.set(Calendar.HOUR_OF_DAY, 0); // since we are already in the future, we choose the minimum hour possible
			else
				overflowField = Calendar.HOUR_OF_DAY;
		}
		else {
			int hourNow = calendar.get(Calendar.HOUR_OF_DAY);
			if (hourEta != hourNow) {
				if (hourEta < hourNow && overflowField != noOverflow)
					calendar.add(overflowField, 1); // do the overflow
				calendar.set(Calendar.HOUR_OF_DAY, hourEta);
				overflowField = noOverflow;
			}
		}
		
		int minuteEta = getMinute(eta);
		if (minuteEta == UNAVAILABLE_MINUTE) {
			if (overflowField == noOverflow)
				calendar.set(Calendar.MINUTE, 0); // since we are already in the future, we choose the minimum minute possible
			else
				overflowField = Calendar.MINUTE; // strictly speaking we don't have to change this, as long as it is different from noOverflow.
		}
		else {
			int minuteNow = calendar.get(Calendar.MINUTE);
			if (minuteEta != minuteNow) {
				if (minuteEta < minuteNow && overflowField != noOverflow) 
					calendar.add(overflowField, 1);
				calendar.set(Calendar.MINUTE, minuteEta);
				overflowField = noOverflow;
			}
		}
		
		if (overflowField != noOverflow)
			calendar.add(overflowField, 1); // make really sure that we are in the future
		
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		return calendar.getTimeInMillis() / 1000;
	}
	
	/** Time zone object for UTC */
	private static final TimeZone utcTimeZone = TimeZone.getTimeZone("UTC");

	/** This constructor is made private to indicate that clients should not construct instances of this class. */
	private UtilsEta () {
	}
}
