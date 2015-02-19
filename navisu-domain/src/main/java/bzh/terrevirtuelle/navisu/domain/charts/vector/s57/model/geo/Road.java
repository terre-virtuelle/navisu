package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import java.io.Serializable;


public class Road extends Geo
implements Serializable
{

	public Road(Long id)
	{
		this.id=id;
	}

	public Road(){}

	private String categoryOfRoad;

	public String getCategoryOfRoad()
	{
		return categoryOfRoad;
	}

	public void setCategoryOfRoad(String value)
	{
		this.categoryOfRoad= value;
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

	private String pictorialRepresentation;

	public String getPictorialRepresentation()
	{
		return pictorialRepresentation;
	}

	public void setPictorialRepresentation(String value)
	{
		this.pictorialRepresentation= value;
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
