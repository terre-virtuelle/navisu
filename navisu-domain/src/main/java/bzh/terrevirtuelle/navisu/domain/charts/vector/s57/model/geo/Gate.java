package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import java.io.Serializable;


public class Gate extends Geo
implements Serializable
{

	public Gate(Long id)
	{
		this.id=id;
	}

	public Gate(){}

	private String categoryOfGate;

	public String getCategoryOfGate()
	{
		return categoryOfGate;
	}

	public void setCategoryOfGate(String value)
	{
		this.categoryOfGate= value;
	}

	private String condition;

	public String getCondition()
	{
		return condition;
	}

	public void setCondition(String value)
	{
		this.condition= value;
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

	private String horizontalAccuracy;

	public String getHorizontalAccuracy()
	{
		return horizontalAccuracy;
	}

	public void setHorizontalAccuracy(String value)
	{
		this.horizontalAccuracy= value;
	}

	private String horizontalClearance;

	public String getHorizontalClearance()
	{
		return horizontalClearance;
	}

	public void setHorizontalClearance(String value)
	{
		this.horizontalClearance= value;
	}

	private String natureOfConstruction;

	public String getNatureOfConstruction()
	{
		return natureOfConstruction;
	}

	public void setNatureOfConstruction(String value)
	{
		this.natureOfConstruction= value;
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

	private String verticalAccuracy;

	public String getVerticalAccuracy()
	{
		return verticalAccuracy;
	}

	public void setVerticalAccuracy(String value)
	{
		this.verticalAccuracy= value;
	}

	private String verticalClearance;

	public String getVerticalClearance()
	{
		return verticalClearance;
	}

	public void setVerticalClearance(String value)
	{
		this.verticalClearance= value;
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
