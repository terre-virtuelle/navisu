package bzh.terrevirtuelle.navisu.weather.app;

import bzh.terrevirtuelle.navisu.domain.country.Abbreviations;
import bzh.terrevirtuelle.navisu.weather.impl.darksky.controller.FIOAlerts;
import bzh.terrevirtuelle.navisu.weather.impl.darksky.controller.FIOCurrently;
import bzh.terrevirtuelle.navisu.weather.impl.darksky.controller.FIODaily;
import bzh.terrevirtuelle.navisu.weather.impl.darksky.controller.FIOFlags;
import bzh.terrevirtuelle.navisu.weather.impl.darksky.controller.FIOHourly;
import bzh.terrevirtuelle.navisu.weather.impl.darksky.controller.FIOMinutely;
import bzh.terrevirtuelle.navisu.weather.impl.darksky.controller.ForecastIO;


public class FIOLibTest {

	private static final String API_KEY = "";

	public static void main(String[] args) {

		
		ForecastIO fio = new ForecastIO(API_KEY,
                Abbreviations.UNIT.get("SI"), Abbreviations.LANG.get("ENGLISH"),
                "38.7252993" , "-9.1500364");
		//fio.setUnits(Abbreviations.UNIT.get("SI"));
		//fio.setLang(Abbreviations.LANG.get("ENGLISH"));
		//fio.getForecast("38.7252993" , "-9.1500364");

		//Response Headers info
		System.out.println("Response Headers:");
		System.out.println("Cache-Control: "+fio.getHeaderCache_Control());
		System.out.println("Expires: "+fio.getHeaderExpires());
		System.out.println("X-Forecast-API-Calls: "+fio.getHeaderX_Forecast_API_Calls());
		System.out.println("X-Response-Time: "+fio.getHeaderX_Response_Time());
		System.out.println("\n");

		//ForecastIO info
		System.out.println("Latitude: "+fio.getLatitude());
		System.out.println("Longitude: "+fio.getLongitude());
		System.out.println("Timezone: "+fio.getTimezone());
		System.out.println("Offset: "+fio.offsetValue());
		System.out.println("\n");	

		//Currently data
		FIOCurrently currently = new FIOCurrently(fio);

		System.out.println("\nCurrently\n");
		String [] f  = currently.get().getFieldsArray();
		for(int i = 0; i<f.length;i++)
			System.out.println(f[i]+": "+currently.get().getByKey(f[i]));
		System.out.println("\n");

		//Minutely data
		FIOMinutely minutely = new FIOMinutely(fio);
		if(minutely.minutes()<0)
			System.out.println("No minutely data.");
		else
			System.out.println("\nMinutely\n");
		for(int i = 0; i<minutely.minutes(); i++){
			String [] m = minutely.getMinute(i).getFieldsArray();
			System.out.println("Minute #"+(i+1));
			for(int j=0; j<m.length; j++)
				System.out.println(m[j]+": "+minutely.getMinute(i).getByKey(m[j]));
			System.out.println("\n");
		}
		//Hourly data
		FIOHourly hourly = new FIOHourly(fio);
		if(hourly.hours()<0)
			System.out.println("No hourly data.");
		else
			System.out.println("\nHourly:\n");
		for(int i = 0; i<hourly.hours(); i++){
			String [] h = hourly.getHour(i).getFieldsArray();
			System.out.println("Hour #"+(i+1));
			for(int j=0; j<h.length; j++)
				System.out.println(h[j]+": "+hourly.getHour(i).getByKey(h[j]));
			System.out.println("\n");
		}
		//Daily data
		FIODaily daily = new FIODaily(fio);
		if(daily.days()<0)
			System.out.println("No daily data.");
		else
			System.out.println("\nDaily:\n");
		for(int i = 0; i<daily.days(); i++){
			String [] h = daily.getDay(i).getFieldsArray();
			System.out.println("Day #"+(i+1));
			for(int j=0; j<h.length; j++)
				System.out.println(h[j]+": "+daily.getDay(i).getByKey(h[j]));
			System.out.println("\n");
		}
		//Flags data
		FIOFlags flags = new FIOFlags(fio);
		System.out.println("Available Flags: ");
		for(int i=0; i<flags.availableFlags().length; i++)
			System.out.println(flags.availableFlags()[i]);
		System.out.println("\n");
		for(int i=0; i<flags.metarStations().length; i++)
			System.out.println("Metar Station: "+flags.metarStations()[i]);
		System.out.println("\n");
		for(int i=0; i<flags.isdStations().length; i++)
			System.out.println("ISD Station: "+flags.isdStations()[i]);
		System.out.println("\n");
		for(int i=0; i<flags.sources().length; i++)
			System.out.println("Source: "+flags.sources()[i]);
		System.out.println("\n");
		System.out.println("Units: " + flags.units());
		System.out.println("\n");

		//Alerts data
		FIOAlerts alerts = new FIOAlerts(fio);
		if(alerts.NumberOfAlerts() <= 0){
			System.out.println("No alerts for this location.");
		} else {
			System.out.println("Alerts");
			for(int i=0; i<alerts.NumberOfAlerts(); i++)
				System.out.println(alerts.getAlert(i));
		}

	}//main
}
