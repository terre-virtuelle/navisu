package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import java.io.Serializable;


public class TideHarmonicPrediction extends Geo
implements Serializable
{

	public TideHarmonicPrediction(Long id)
	{
		this.id=id;
	}

	public TideHarmonicPrediction(){}

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

	private String tideAccuracyOfWaterLevel;

	public String getTideAccuracyOfWaterLevel()
	{
		return tideAccuracyOfWaterLevel;
	}

	public void setTideAccuracyOfWaterLevel(String value)
	{
		this.tideAccuracyOfWaterLevel= value;
	}

	private String tideMethodOftidalPrediction;

	public String getTideMethodOfTidalPrediction()
	{
		return tideMethodOftidalPrediction;
	}

	public void setTideMethodOfTidalPrediction(String value)
	{
		this.tideMethodOftidalPrediction= value;
	}

	private String tideValueOfHarmonicConstituents;

	public String getTideValueOfHarmonicConstituents()
	{
		return tideValueOfHarmonicConstituents;
	}

	public void setTideValueOfHarmonicConstituents(String value)
	{
		this.tideValueOfHarmonicConstituents= value;
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
