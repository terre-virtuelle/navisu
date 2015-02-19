package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import java.io.Serializable;


public class TidalStreamTimeSeries extends Geo
implements Serializable
{

	public TidalStreamTimeSeries(Long id)
	{
		this.id=id;
	}

	public TidalStreamTimeSeries(){}

	private String objectName;

	public String getObjectName()
	{
		return objectName;
	}

	public void setObjectName(String value)
	{
		this.objectName= value;
	}

	private String recordIngdate;

	public String getRecordIngdate()
	{
		return recordIngdate;
	}

	public void setRecordIngdate(String value)
	{
		this.recordIngdate= value;
	}

	private String recordingIndication;

	public String getRecordingIndication()
	{
		return recordingIndication;
	}

	public void setRecordingIndication(String value)
	{
		this.recordingIndication= value;
	}

	private String scaleMaximum;

	public String getScaleMaximum()
	{
		return scaleMaximum;
	}

	public void setScaleMaximum(String value)
	{
		this.scaleMaximum= value;
	}

	private String status;

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String value)
	{
		this.status= value;
	}

	private String tidalStreamtimeSeriesValues;

	public String getTidalStreamTimeSeriesValues()
	{
		return tidalStreamtimeSeriesValues;
	}

	public void setTidalStreamTimeSeriesValues(String value)
	{
		this.tidalStreamtimeSeriesValues= value;
	}

	private String tidetimeIntervalOfValue;

	public String getTideTimeIntervalOfValue()
	{
		return tidetimeIntervalOfValue;
	}

	public void setTideTimeIntervalOfValue(String value)
	{
		this.tidetimeIntervalOfValue= value;
	}

	private String timeEnd;

	public String getTimeEnd()
	{
		return timeEnd;
	}

	public void setTimeEnd(String value)
	{
		this.timeEnd= value;
	}

	private String timeStart;

	public String getTimeStart()
	{
		return timeStart;
	}

	public void setTimeStart(String value)
	{
		this.timeStart= value;
	}

	private String objectNameInNationalLanguage;

	public String getObjectNameInNationalLanguage()
	{
		return objectNameInNationalLanguage;
	}

	public void setObjectNameInNationalLanguage(String value)
	{
		this.objectNameInNationalLanguage= value;
	}


}
