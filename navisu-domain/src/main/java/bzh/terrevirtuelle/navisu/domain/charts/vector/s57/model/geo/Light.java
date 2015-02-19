package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import java.io.Serializable;


public class Light extends Location
implements Serializable
{

	public Light(Long id)
	{
		this.id=id;
	}

	public Light(){}

	private String categoryOfLight;

	public String getCategoryOfLight()
	{
		return categoryOfLight;
	}

	public void setCategoryOfLight(String value)
	{
		this.categoryOfLight= value;
	}

	private String colour;

	public String getColour()
	{
		return colour;
	}

	public void setColour(String value)
	{
		this.colour= value;
	}

	private String dateEnd;

	public String getDateEnd()
	{
		return dateEnd;
	}

	public void setDateEnd(String value)
	{
		this.dateEnd= value;
	}

	private String dateStart;

	public String getDateStart()
	{
		return dateStart;
	}

	public void setDateStart(String value)
	{
		this.dateStart= value;
	}

	private String exhibitionConditionOfLight;

	public String getExhibitionConditionOfLight()
	{
		return exhibitionConditionOfLight;
	}

	public void setExhibitionConditionOfLight(String value)
	{
		this.exhibitionConditionOfLight= value;
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

	private String lightCharacteristic;

	public String getLightCharacteristic()
	{
		return lightCharacteristic;
	}

	public void setLightCharacteristic(String value)
	{
		this.lightCharacteristic= value;
	}

	private String lightVisibility;

	public String getLightVisibility()
	{
		return lightVisibility;
	}

	public void setLightVisibility(String value)
	{
		this.lightVisibility= value;
	}

	private String marksNavigationalSystemof;

	public String getMarksNavigationalSystemof()
	{
		return marksNavigationalSystemof;
	}

	public void setMarksNavigationalSystemof(String value)
	{
		this.marksNavigationalSystemof= value;
	}

	private String multiplicityOfLights;

	public String getMultiplicityOfLights()
	{
		return multiplicityOfLights;
	}

	public void setMultiplicityOfLights(String value)
	{
		this.multiplicityOfLights= value;
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

	private String orientation;

	public String getOrientation()
	{
		return orientation;
	}

	public void setOrientation(String value)
	{
		this.orientation= value;
	}

	private String periodicDateEnd;

	public String getPeriodicDateEnd()
	{
		return periodicDateEnd;
	}

	public void setPeriodicDateEnd(String value)
	{
		this.periodicDateEnd= value;
	}

	private String periodicDateStart;

	public String getPeriodicDateStart()
	{
		return periodicDateStart;
	}

	public void setPeriodicDateStart(String value)
	{
		this.periodicDateStart= value;
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

	private String sectorLimitOne;

	public String getSectorLimitOne()
	{
		return sectorLimitOne;
	}

	public void setSectorLimitOne(String value)
	{
		this.sectorLimitOne= value;
	}

	private String sectorLimitTwo;

	public String getSectorLimitTwo()
	{
		return sectorLimitTwo;
	}

	public void setSectorLimitTwo(String value)
	{
		this.sectorLimitTwo= value;
	}

	private String signalGroup;

	public String getSignalGroup()
	{
		return signalGroup;
	}

	public void setSignalGroup(String value)
	{
		this.signalGroup= value;
	}

	private String signalPeriod;

	public String getSignalPeriod()
	{
		return signalPeriod;
	}

	public void setSignalPeriod(String value)
	{
		this.signalPeriod= value;
	}

	private String signalsequence;

	public String getSignalSequence()
	{
		return signalsequence;
	}

	public void setSignalSequence(String value)
	{
		this.signalsequence= value;
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

	private String valueOfNominalRange;

	public String getValueOfNominalRange()
	{
		return valueOfNominalRange;
	}

	public void setValueOfNominalRange(String value)
	{
		this.valueOfNominalRange= value;
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
