package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import java.io.Serializable;


public class PipelineOverhead extends Geo
implements Serializable
{

	public PipelineOverhead(Long id)
	{
		this.id=id;
	}

	public PipelineOverhead(){}

	private String categoryOfPipeline;

	public String getCategoryOfPipeline()
	{
		return categoryOfPipeline;
	}

	public void setCategoryOfPipeline(String value)
	{
		this.categoryOfPipeline= value;
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
