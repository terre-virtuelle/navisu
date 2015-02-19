package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import java.io.Serializable;


public class SurveyReliability extends Geo
implements Serializable
{

	public SurveyReliability(Long id)
	{
		this.id=id;
	}

	public SurveyReliability(){}

	private String qualityOfSoundingMeasurement;

	public String getQualityOfSoundingMeasurement()
	{
		return qualityOfSoundingMeasurement;
	}

	public void setQualityOfSoundingMeasurement(String value)
	{
		this.qualityOfSoundingMeasurement= value;
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

	private String scaleValueOne;

	public String getScaleValueOne()
	{
		return scaleValueOne;
	}

	public void setScaleValueOne(String value)
	{
		this.scaleValueOne= value;
	}

	private String scaleValueTwo;

	public String getScaleValueTwo()
	{
		return scaleValueTwo;
	}

	public void setScaleValueTwo(String value)
	{
		this.scaleValueTwo= value;
	}

	private String soundingDistanceMaximum;

	public String getSoundingDistanceMaximum()
	{
		return soundingDistanceMaximum;
	}

	public void setSoundingDistanceMaximum(String value)
	{
		this.soundingDistanceMaximum= value;
	}

	private String soundingDistanceMinimum;

	public String getSoundingDistanceMinimum()
	{
		return soundingDistanceMinimum;
	}

	public void setSoundingDistanceMinimum(String value)
	{
		this.soundingDistanceMinimum= value;
	}

	private String surveyAuthority;

	public String getSurveyAuthority()
	{
		return surveyAuthority;
	}

	public void setSurveyAuthority(String value)
	{
		this.surveyAuthority= value;
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

	private String surveyType;

	public String getSurveyType()
	{
		return surveyType;
	}

	public void setSurveyType(String value)
	{
		this.surveyType= value;
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


}
