package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import java.io.Serializable;


public class QualityOfData extends Geo
implements Serializable
{

	public QualityOfData(Long id)
	{
		this.id=id;
	}

	public QualityOfData(){}

	private String categoryOfQualityOfData;

	public String getCategoryOfQualityOfData()
	{
		return categoryOfQualityOfData;
	}

	public void setCategoryOfQualityOfData(String value)
	{
		this.categoryOfQualityOfData= value;
	}

	private String categoryOfZoneOfconfidenceInData;

	public String getCategoryOfZoneOfConfidenceInData()
	{
		return categoryOfZoneOfconfidenceInData;
	}

	public void setCategoryOfZoneOfConfidenceInData(String value)
	{
		this.categoryOfZoneOfconfidenceInData= value;
	}

	private String depthRangeValue1;

	public String getDepthRangeValue1()
	{
		return depthRangeValue1;
	}

	public void setDepthRangeValue1(String value)
	{
		this.depthRangeValue1= value;
	}

	private String depthRangeValue2;

	public String getDepthRangeValue2()
	{
		return depthRangeValue2;
	}

	public void setDepthRangeValue2(String value)
	{
		this.depthRangeValue2= value;
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

	private String soundingAccuracy;

	public String getSoundingAccuracy()
	{
		return soundingAccuracy;
	}

	public void setSoundingAccuracy(String value)
	{
		this.soundingAccuracy= value;
	}

	private String surveyDateEnd;

	public String getSurveyDateEnd()
	{
		return surveyDateEnd;
	}

	public void setSurveyDateEnd(String value)
	{
		this.surveyDateEnd= value;
	}

	private String surveyDatestart;

	public String getSurveyDateStart()
	{
		return surveyDatestart;
	}

	public void setSurveyDateStart(String value)
	{
		this.surveyDatestart= value;
	}

	private String techniqueOfSoundingMeasurement;

	public String getTechniqueOfSoundingMeasurement()
	{
		return techniqueOfSoundingMeasurement;
	}

	public void setTechniqueOfSoundingMeasurement(String value)
	{
		this.techniqueOfSoundingMeasurement= value;
	}

	private String verticaldatum;

	public String getVerticaldatum()
	{
		return verticaldatum;
	}

	public void setVerticaldatum(String value)
	{
		this.verticaldatum= value;
	}


}
