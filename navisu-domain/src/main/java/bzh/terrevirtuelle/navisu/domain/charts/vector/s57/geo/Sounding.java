package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.Geo;
import java.io.Serializable;


public class Sounding extends Geo
implements Serializable
{

	public Sounding(Long id)
	{
		this.id=id;
	}

	public Sounding(){}

	private String expositionOfSounding;

	public String getExpositionOfSounding()
	{
		return expositionOfSounding;
	}

	public void setExpositionOfSounding(String value)
	{
		this.expositionOfSounding= value;
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

	private String verticaldatum;

	public String getVerticaldatum()
	{
		return verticaldatum;
	}

	public void setVerticaldatum(String value)
	{
		this.verticaldatum= value;
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
