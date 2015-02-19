package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import java.io.Serializable;


public class Wreck extends Location
implements Serializable
{

	public Wreck(Long id)
	{
		this.id=id;
	}

	public Wreck(){}

	private String categoryOfWreck;

	public String getCategoryOfWreck()
	{
		return categoryOfWreck;
	}

	public void setCategoryOfWreck(String value)
	{
		this.categoryOfWreck= value;
	}

	private String conspicuousRadar;

	public String getConspicuousRadar()
	{
		return conspicuousRadar;
	}

	public void setConspicuousRadar(String value)
	{
		this.conspicuousRadar= value;
	}

	private String conspicuousVisually;

	public String getConspicuousVisually()
	{
		return conspicuousVisually;
	}

	public void setConspicuousVisually(String value)
	{
		this.conspicuousVisually= value;
	}

	private String expositionOfSounding;

	public String getExpositionOfSounding()
	{
		return expositionOfSounding;
	}

	public void setExpositionOfSounding(String value)
	{
		this.expositionOfSounding= value;
	}

	private String height;

	public String getHeight()
	{
		return height;
	}

	public void setHeight(String value)
	{
		this.height= value;
	}

	private String objectName;

	public String getObjectName()
	{
		return objectName;
	}

	public void setObjectName(String value)
	{
		this.objectName= value;
	}

	private String pictorialRepresentation;

	public String getPictorialRepresentation()
	{
		return pictorialRepresentation;
	}

	public void setPictorialRepresentation(String value)
	{
		this.pictorialRepresentation= value;
	}

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

	private String scaleMaximum;

	public String getScaleMaximum()
	{
		return scaleMaximum;
	}

	public void setScaleMaximum(String value)
	{
		this.scaleMaximum= value;
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

	private String status;

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String value)
	{
		this.status= value;
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

	private String valueOfSounding;

	public String getValueOfSounding()
	{
		return valueOfSounding;
	}

	public void setValueOfSounding(String value)
	{
		this.valueOfSounding= value;
	}

	private String verticalAccuracy;

	public String getVerticalAccuracy()
	{
		return verticalAccuracy;
	}

	public void setVerticalAccuracy(String value)
	{
		this.verticalAccuracy= value;
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

	private String verticalLength;

	public String getVerticalLength()
	{
		return verticalLength;
	}

	public void setVerticalLength(String value)
	{
		this.verticalLength= value;
	}

	private String waterLevelEffect;

	public String getWaterLevelEffect()
	{
		return waterLevelEffect;
	}

	public void setWaterLevelEffect(String value)
	{
		this.waterLevelEffect= value;
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
