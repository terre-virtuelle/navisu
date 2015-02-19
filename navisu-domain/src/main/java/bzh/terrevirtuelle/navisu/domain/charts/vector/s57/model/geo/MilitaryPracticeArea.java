package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import java.io.Serializable;


public class MilitaryPracticeArea extends Geo
implements Serializable
{

	public MilitaryPracticeArea(Long id)
	{
		this.id=id;
	}

	public MilitaryPracticeArea(){}

	private String categoryOfMilitaryPracticeArea;

	public String getCategoryOfMilitaryPracticeArea()
	{
		return categoryOfMilitaryPracticeArea;
	}

	public void setCategoryOfMilitaryPracticeArea(String value)
	{
		this.categoryOfMilitaryPracticeArea= value;
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

	private String restriction;

	public String getRestriction()
	{
		return restriction;
	}

	public void setRestriction(String value)
	{
		this.restriction= value;
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
