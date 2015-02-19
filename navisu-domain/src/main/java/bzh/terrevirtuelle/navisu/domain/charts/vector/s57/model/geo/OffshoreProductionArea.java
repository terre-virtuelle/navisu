package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import java.io.Serializable;


public class OffshoreProductionArea extends Geo
implements Serializable
{

	public OffshoreProductionArea(Long id)
	{
		this.id=id;
	}

	public OffshoreProductionArea(){}

	private String categoryOfProductionArea;

	public String getCategoryOfProductionArea()
	{
		return categoryOfProductionArea;
	}

	public void setCategoryOfProductionArea(String value)
	{
		this.categoryOfProductionArea= value;
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

	private String product;

	public String getProduct()
	{
		return product;
	}

	public void setProduct(String value)
	{
		this.product= value;
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

	private String verticalAccuracy;

	public String getVerticalAccuracy()
	{
		return verticalAccuracy;
	}

	public void setVerticalAccuracy(String value)
	{
		this.verticalAccuracy= value;
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
