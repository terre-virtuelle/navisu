package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.geo;

import java.io.Serializable;


public class BeaconCardinal extends Location
implements Serializable
{
private String beaconShape;
private String categoryOfcardinalMark;
private String colour;
private String colourPattern;
private String condition;
private String conspicuousRadar;
private String conspicuousVisually;
private String dateEnd;
private String dateStart;
private String elevation;
private String height;
private String marksNavigationalSystemof;
private String natureOfConstruction;
private String objectName;
private String periodicDateStart;
private String periodicDateEnd;
private String pictorialRepresentation;
private String recordIngdate;
private String scaleMaximum;
private String verticalAccuracy;
private String recordingIndication;
private String verticaldatum;
private String verticalLength;
private String objectNameInNationalLanguage;
private String status;
	public BeaconCardinal(Long id)
	{
		this.id=id;
	}

	public BeaconCardinal(){}

	

	public String getBeaconShape()
	{
		return beaconShape;
	}

	public void setBeaconShape(String value)
	{
		this.beaconShape= value;
	}

	

	public String getCategoryOfCardinalMark()
	{
		return categoryOfcardinalMark;
	}

	public void setCategoryOfCardinalMark(String value)
	{
		this.categoryOfcardinalMark= value;
	}

	

	public String getColour()
	{
		return colour;
	}

	public void setColour(String value)
	{
		this.colour= value;
	}

	

	public String getColourPattern()
	{
		return colourPattern;
	}

	public void setColourPattern(String value)
	{
		this.colourPattern= value;
	}

	

	public String getCondition()
	{
		return condition;
	}

	public void setCondition(String value)
	{
		this.condition= value;
	}

	
	public String getConspicuousRadar()
	{
		return conspicuousRadar;
	}

	public void setConspicuousRadar(String value)
	{
		this.conspicuousRadar= value;
	}

	
	public String getConspicuousVisually()
	{
		return conspicuousVisually;
	}

	public void setConspicuousVisually(String value)
	{
		this.conspicuousVisually= value;
	}

	

	public String getDateEnd()
	{
		return dateEnd;
	}

	public void setDateEnd(String value)
	{
		this.dateEnd= value;
	}

	

	public String getDateStart()
	{
		return dateStart;
	}

	public void setDateStart(String value)
	{
		this.dateStart= value;
	}

	

	public String getElevation()
	{
		return elevation;
	}

	public void setElevation(String value)
	{
		this.elevation= value;
	}

	

	public String getHeight()
	{
		return height;
	}

	public void setHeight(String value)
	{
		this.height= value;
	}

	

	public String getMarksNavigationalSystemof()
	{
		return marksNavigationalSystemof;
	}

	public void setMarksNavigationalSystemof(String value)
	{
		this.marksNavigationalSystemof= value;
	}

	

	public String getNatureOfConstruction()
	{
		return natureOfConstruction;
	}

	public void setNatureOfConstruction(String value)
	{
		this.natureOfConstruction= value;
	}

	

	public String getObjectName()
	{
		return objectName;
	}

	public void setObjectName(String value)
	{
		this.objectName= value;
	}

	

	public String getPeriodicDateEnd()
	{
		return periodicDateEnd;
	}

	public void setPeriodicDateEnd(String value)
	{
		this.periodicDateEnd= value;
	}

	
	public String getPeriodicDateStart()
	{
		return periodicDateStart;
	}

	public void setPeriodicDateStart(String value)
	{
		this.periodicDateStart= value;
	}

	

	public String getPictorialRepresentation()
	{
		return pictorialRepresentation;
	}

	public void setPictorialRepresentation(String value)
	{
		this.pictorialRepresentation= value;
	}

	

	public String getRecordIngdate()
	{
		return recordIngdate;
	}

	public void setRecordIngdate(String value)
	{
		this.recordIngdate= value;
	}

	
	public String getRecordingIndication()
	{
		return recordingIndication;
	}

	public void setRecordingIndication(String value)
	{
		this.recordingIndication= value;
	}

	
	public String getScaleMaximum()
	{
		return scaleMaximum;
	}

	public void setScaleMaximum(String value)
	{
		this.scaleMaximum= value;
	}

	

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String value)
	{
		this.status= value;
	}

	

	public String getVerticalAccuracy()
	{
		return verticalAccuracy;
	}

	public void setVerticalAccuracy(String value)
	{
		this.verticalAccuracy= value;
	}

	

	public String getVerticaldatum()
	{
		return verticaldatum;
	}

	public void setVerticaldatum(String value)
	{
		this.verticaldatum= value;
	}

	

	public String getVerticalLength()
	{
		return verticalLength;
	}

	public void setVerticalLength(String value)
	{
		this.verticalLength= value;
	}

	

	public String getObjectNameInNationalLanguage()
	{
		return objectNameInNationalLanguage;
	}

	public void setObjectNameInNationalLanguage(String value)
	{
		this.objectNameInNationalLanguage= value;
	}

    @Override
    public String toString() {
        return super.toString() + "\nBeaconCardinal{" + "beaconShape=" + beaconShape + ", categoryOfcardinalMark=" + categoryOfcardinalMark + ", colour=" + colour + ", colourPattern=" + colourPattern + ", condition=" + condition + ", conspicuousRadar=" + conspicuousRadar + ", conspicuousVisually=" + conspicuousVisually + ", dateEnd=" + dateEnd + ", dateStart=" + dateStart + ", elevation=" + elevation + ", height=" + height + ", marksNavigationalSystemof=" + marksNavigationalSystemof + ", natureOfConstruction=" + natureOfConstruction + ", objectName=" + objectName + ", periodicDateStart=" + periodicDateStart + ", periodicDateEnd=" + periodicDateEnd + ", pictorialRepresentation=" + pictorialRepresentation + ", recordIngdate=" + recordIngdate + ", scaleMaximum=" + scaleMaximum + ", verticalAccuracy=" + verticalAccuracy + ", recordingIndication=" + recordingIndication + ", verticaldatum=" + verticaldatum + ", verticalLength=" + verticalLength + ", objectNameInNationalLanguage=" + objectNameInNationalLanguage + ", status=" + status + '}';
    }


}
