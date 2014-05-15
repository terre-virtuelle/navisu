package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.Geo;
import java.io.Serializable;


public class Daymark extends Geo
implements Serializable
{

	public Daymark(Long id)
	{
		this.id=id;
	}

	public Daymark(){}

	private String categoryOfSpecialPurposeMark;

	public String getCategoryOfSpecialPurposeMark()
	{
		return categoryOfSpecialPurposeMark;
	}

	public void setCategoryOfSpecialPurposeMark(String value)
	{
		this.categoryOfSpecialPurposeMark= value;
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

	private String colourPattern;

	public String getColourPattern()
	{
		return colourPattern;
	}

	public void setColourPattern(String value)
	{
		this.colourPattern= value;
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

	private String elevation;

	public String getElevation()
	{
		return elevation;
	}

	public void setElevation(String value)
	{
		this.elevation= value;
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

	private String status;

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String value)
	{
		this.status= value;
	}

	private String topMark_daymarkshape;

	public String getTopMark_daymarkshape()
	{
		return topMark_daymarkshape;
	}

	public void setTopMark_daymarkshape(String value)
	{
		this.topMark_daymarkshape= value;
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
